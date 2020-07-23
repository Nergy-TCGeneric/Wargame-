package com.github.tcgeneric.wargame

import com.github.tcgeneric.wargame.core.*
import com.github.tcgeneric.wargame.map.MapGenerator

class Wargame {
    val behaviorHandler = BehaviorHandler(this)
    val dataLoader = DataLoader(this)
    val pDataHandler = PlayerDataHandler()
    val turnHandler = TurnHandler(this)
    val mapGenerator = MapGenerator(this)
    val mapHandler = MapHandler(this, dataLoader.loadMapFrame(), dataLoader.loadMapData())
    val unitHandler = UnitHandler(this)
    val teamManager = TeamManager(this)
    val displayHandler = MinecraftGraphicManager(this)

    var isGameStarted:Boolean = false
}