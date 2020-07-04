package com.github.tcgeneric.wargame.entity.units

import com.github.tcgeneric.wargame.map.Tile
import com.github.tcgeneric.wargame.teams.Team

class CombatUnit(
        id:Int,
        override var combatRange:Int,
        override var combatStrength:Int,
        override var moral:Int,
        override var amount:Int):Unit(id) {
}