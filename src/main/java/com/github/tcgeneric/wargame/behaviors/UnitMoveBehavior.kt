package com.github.tcgeneric.wargame.behaviors

import com.github.tcgeneric.wargame.entity.units.Unit

class UnitMoveBehavior(id:Int, actor:Unit, val coord:Pair<Int, Int>):UnitBehavior(id, actor)