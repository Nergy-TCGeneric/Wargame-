package com.github.tcgeneric.wargame.entity.structures

import com.github.tcgeneric.wargame.entity.Entity
import com.github.tcgeneric.wargame.map.Tile

abstract class Structure(id:Int, tile: Tile):Entity(id, tile) {
    var completed:Boolean = false
    var toughness:Double = 1.0
}