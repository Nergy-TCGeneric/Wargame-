package com.github.tcgeneric.wargame.behaviors

import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.map.Tile

@Deprecated("Not used")
class UnitDivideBehavior(actor: Unit, time: Long, val tile: Tile, val amount: Int):UnitBehavior(actor, time)