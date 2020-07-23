package com.github.tcgeneric.wargame.behaviors

import com.github.tcgeneric.wargame.entity.structures.Structure
import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.util.Controller

class UnitDwellBehavior(actor:Unit, player: Controller<*>, time:Long, val target:Structure): UnitBehavior(actor, player, time)