package com.github.tcgeneric.wargame.behaviors

import com.github.tcgeneric.wargame.entity.units.Unit

abstract class UnitBehavior(
        val actor:Unit,
        val time:Long
)