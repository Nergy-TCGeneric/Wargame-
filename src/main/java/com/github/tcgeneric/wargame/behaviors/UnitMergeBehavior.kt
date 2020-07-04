package com.github.tcgeneric.wargame.behaviors

import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.entity.units.UnitGroup

class UnitMergeBehavior(id:Int, actor:Unit, val target:UnitGroup):UnitBehavior(id, actor)