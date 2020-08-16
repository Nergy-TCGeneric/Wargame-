package com.github.tcgeneric.wargame

import com.github.tcgeneric.wargame.core.*
import com.github.tcgeneric.wargame.listener.RightClickEventListener
import com.github.tcgeneric.wargame.listener.WargameEventListener
import com.github.tcgeneric.wargame.map.MapGenerator
import org.bukkit.plugin.java.JavaPlugin

class Wargame:JavaPlugin() {
    val behaviorHandler = BehaviorHandler(this)
    val dataLoader = DataLoader(this)
    val pDataHandler = PlayerDataHandler()
    val turnHandler = TurnHandler(this)
    val mapGenerator = MapGenerator(this)
    val unitHandler = UnitHandler(this)
    val teamManager = TeamManager(this)
    val displayHandler = GraphicManager(this)
    val schematicLoader = SchematicLoader(this)
    lateinit var mapHandler:MapHandler

    var isGameStarted:Boolean = false
    var loaded:Boolean = false

    override fun onEnable() {
        server.pluginManager.registerEvents(RightClickEventListener(this), this)
        server.pluginManager.registerEvents(WargameEventListener(this), this)
        server.scheduler.runTaskAsynchronously(this) { ->
            val frame = dataLoader.loadMapFrame()
            mapHandler = MapHandler(this, frame, dataLoader.loadMapData(frame))
            loaded = true
        }
    }

    override fun onDisable() {
        super.onDisable()
    }
}