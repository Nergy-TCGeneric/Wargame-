package com.github.tcgeneric.wargame.behaviors

import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.map.Tile

class UnitMoveBehavior(actor: Unit, time: Long, val tile: Tile):UnitBehavior(actor, time)