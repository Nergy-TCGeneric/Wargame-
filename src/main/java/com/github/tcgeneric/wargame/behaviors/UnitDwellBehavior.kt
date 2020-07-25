package com.github.tcgeneric.wargame.behaviors

import com.github.tcgeneric.wargame.entity.structures.Structure
import com.github.tcgeneric.wargame.entity.units.Unit

class UnitDwellBehavior(actor: Unit, time: Long, val target: Structure): UnitBehavior(actor, time)