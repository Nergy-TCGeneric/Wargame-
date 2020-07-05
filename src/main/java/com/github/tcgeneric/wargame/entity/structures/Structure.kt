package com.github.tcgeneric.wargame.entity.structures

import com.github.tcgeneric.wargame.entity.Entity
import com.github.tcgeneric.wargame.map.Tile
import com.github.tcgeneric.wargame.teams.Team

abstract class Structure(id:Int, tile: Tile, val owningTeam:Team):Entity(id, tile) {
    var durability:Int = 0
    var completed:Boolean = false
}