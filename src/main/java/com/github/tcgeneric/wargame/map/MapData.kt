package com.github.tcgeneric.wargame.map

import com.github.tcgeneric.wargame.entity.Entity
import com.github.tcgeneric.wargame.entity.structures.ControlPoint
import com.github.tcgeneric.wargame.util.Direction
import com.github.tcgeneric.wargame.util.Coordinate

data class MapData(val startPoint:Coordinate, val direction:Pair<Direction, Direction>, val width:Int, val height:Int, val seed:Int) {
    val tiles = arrayOfNulls<Tile>(width * height)
    val entities: HashMap<Entity, Coordinate> = HashMap(width * height) // For fast search
    val controlPoints = HashMap<ControlPoint, Coordinate>()

    fun findTile(tile:Tile):Tile? {
        val k = tiles.indexOf(tile)
        if(k >= 0) return tiles[k]
        return null
    }
}