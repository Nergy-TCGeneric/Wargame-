package com.github.tcgeneric.wargame.behaviors

import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.entity.units.UnitGroup

@Deprecated("Not used")
class UnitMergeBehavior(actor: Unit, time: Long, val target: UnitGroup):UnitBehavior(actor, time)