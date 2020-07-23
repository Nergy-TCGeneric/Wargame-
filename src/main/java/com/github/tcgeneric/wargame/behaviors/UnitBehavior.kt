package com.github.tcgeneric.wargame.behaviors

import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.util.Controller

abstract class UnitBehavior(
        val actor:Unit,
        val player: Controller<*>,
        val time:Long
)