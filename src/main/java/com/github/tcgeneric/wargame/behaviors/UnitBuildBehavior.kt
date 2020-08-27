package com.github.tcgeneric.wargame.behaviors

import com.github.tcgeneric.wargame.entity.structures.Structure
import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.map.Tile
import org.bukkit.entity.Player

class UnitBuildBehavior(actor:Unit, time:Long, val structure: Structure, val target:Tile):UnitBehavior(actor, time) {
    override fun toString(): String {
        return "건설 명령"
    }
}