package com.github.tcgeneric.wargame

import com.github.tcgeneric.wargame.core.BehaviorHandler
import com.github.tcgeneric.wargame.core.TurnHandler
import com.github.tcgeneric.wargame.map.MapGenerator
import com.github.tcgeneric.wargame.core.MapHandler
import com.github.tcgeneric.wargame.core.UnitHandler

class Wargame {
    val behaviorHandler = BehaviorHandler(this)
    val turnHandler = TurnHandler(this)
    val mapGenerator = MapGenerator(this)
    val mapHandler = MapHandler(this)
    val unitHandler = UnitHandler(this)
}