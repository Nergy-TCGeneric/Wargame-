package com.github.tcgeneric.wargame

import com.github.tcgeneric.wargame.core.handlers.*
import com.github.tcgeneric.wargame.listener.RightClickEventListener
import com.github.tcgeneric.wargame.listener.WargameEventListener
import com.github.tcgeneric.wargame.map.MapGenerator
import com.github.tcgeneric.wargame.teams.Team
import org.bukkit.plugin.java.JavaPlugin

object Wargame:JavaPlugin() {
    lateinit var mapHandler: MapHandler
    lateinit var teams:List<Team>

    var isGameStarted:Boolean = false
    var loaded:Boolean = false

    override fun onEnable() {
        server.pluginManager.registerEvents(RightClickEventListener(), this)
        server.pluginManager.registerEvents(WargameEventListener(this), this)
        server.scheduler.runTaskAsynchronously(this) { ->
            InitializationManager.loadUnits()
            teams = InitializationManager.loadTeams()
            val frame = DataLoader.loadMapFrame()
            val mapData = DataLoader.loadMapData(frame) ?: MapGenerator.generate(frame.width, frame.height)
            mapHandler = MapHandler(this, frame, mapData)
            loaded = true
        }
    }

    override fun onDisable() {
        super.onDisable()
    }
}