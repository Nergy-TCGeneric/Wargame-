package com.github.tcgeneric.wargame.entity.structures

import com.github.tcgeneric.wargame.entity.Entity
import com.github.tcgeneric.wargame.map.Tile

abstract class Structure(id:Int, tile: Tile, var indestructible:Boolean):Entity(id, tile) {
    var durability:Int = 0
    var completed:Boolean = false
}