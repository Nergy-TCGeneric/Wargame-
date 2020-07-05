package com.github.tcgeneric.wargame.entity.units

class CombatUnit(
        id:Int,
        override var combatRange:Int,
        override var combatStrength:Int,
        override var moral:Int,
        override var amount:Int,
        override var sight:Int,
        override var moveRange: Int):Unit(id) {
}