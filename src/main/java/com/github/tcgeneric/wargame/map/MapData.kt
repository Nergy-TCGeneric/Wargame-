package com.github.tcgeneric.wargame.map

import com.github.tcgeneric.wargame.entity.Entity
import com.github.tcgeneric.wargame.entity.structures.ControlPoint
import com.github.tcgeneric.wargame.util.Direction
import com.github.tcgeneric.wargame.util.Coordinate

// Premise : variable 'tiles' must be initialized in ascending order by map generator before using it.
data class MapData(val startPoint:Coordinate, val direction:Pair<Direction, Direction>, val width:Int, val height:Int) {
    val tiles = ArrayList<Tile>(width * height)
    val entities: HashMap<Entity, Coordinate> = HashMap(width * height) // For fast search
    val controlPoints = HashMap<ControlPoint, Coordinate>()
}