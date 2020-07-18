package com.github.tcgeneric.wargame

import com.github.tcgeneric.wargame.core.*
import com.github.tcgeneric.wargame.map.MapGenerator
import org.bukkit.plugin.java.JavaPlugin

class Wargame:JavaPlugin() {
    val behaviorHandler = BehaviorHandler(this)
    val dataLoader = DataLoader(this)
    val pDataHandler = PlayerDataHandler()
    val turnHandler = TurnHandler(this)
    val mapGenerator = MapGenerator(this)
    val mapHandler = MapHandler(this, dataLoader.loadMapData())
    val unitHandler = UnitHandler(this)
    val teamManager = TeamManager(this)
    val displayHandler = DisplayHandler(this)
}