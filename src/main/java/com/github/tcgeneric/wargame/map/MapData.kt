package com.github.tcgeneric.wargame.map

import com.github.tcgeneric.wargame.entity.Entity
import com.github.tcgeneric.wargame.entity.structures.ControlPoint

data class MapData(val width:Int, val height:Int, val seed:Int) {
    val tiles = arrayOfNulls<Tile>(width * height)
    val entities = HashMap<Entity, Pair<Int, Int>>(width * height) // For fast search
    val controlPoints = HashMap<ControlPoint, Pair<Int, Int>>()
}