package com.github.tcgeneric.wargame.entity.units

import com.github.tcgeneric.wargame.entity.Entity
import com.github.tcgeneric.wargame.map.Tile
import com.github.tcgeneric.wargame.teams.Team
import org.bukkit.entity.Player

abstract class Unit(id:Int, tile:Tile):Entity(id, tile) {
    var combatRange:Int = 0
    var combatStrength:Int = 0
    var moral:Int = 0
    var moveRange:Int = 0
    var sightRange:Int = 0
    var amount:Int = 0
}