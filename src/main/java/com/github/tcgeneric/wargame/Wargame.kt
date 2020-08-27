package com.github.tcgeneric.wargame

import com.github.tcgeneric.wargame.core.handlers.*
import com.github.tcgeneric.wargame.events.TurnStartEvent
import com.github.tcgeneric.wargame.listener.InventoryClickListener
import com.github.tcgeneric.wargame.listener.MinecraftEventListener
import com.github.tcgeneric.wargame.listener.WargameEventListener
import com.github.tcgeneric.wargame.map.MapGenerator
import com.github.tcgeneric.wargame.teams.Team
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

object Wargame:JavaPlugin() {

    lateinit var mapHandler: MapHandler
    lateinit var teams:List<Team>
    var turn:Int = 0 // Assuming there's only one wargame session

    var isGameStarted:Boolean = false
    var loaded:Boolean = false

    override fun onEnable() {
        server.pluginManager.registerEvents(MinecraftEventListener(), this)
        server.pluginManager.registerEvents(InventoryClickListener(), this)
        server.pluginManager.registerEvents(WargameEventListener(this), this)
        server.scheduler.runTaskAsynchronously(this) { ->
            InitializationManager.loadUnits()
            teams = InitializationManager.loadTeams()
            val frame = DataLoader.loadMapFrame()
            val mapData = DataLoader.loadMapData(frame) ?: MapGenerator.generate(frame.width, frame.height)
            mapHandler = MapHandler(frame, mapData)
            loaded = true
        }
    }

    override fun onDisable() {
        super.onDisable()
    }

    fun startSession(delaySec:Int) {
        if(!loaded) throw IllegalStateException("Essential files are not loaded yet")
        if(isGameStarted) throw IllegalStateException("Attempted to run more than one Wargame session!")
        Bukkit.getServer().pluginManager.callEvent(TurnStartEvent(delaySec))
    }
}