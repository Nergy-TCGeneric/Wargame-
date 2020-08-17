package com.github.tcgeneric.wargame.map

import com.github.tcgeneric.wargame.core.data.TileChangeRequest
import com.github.tcgeneric.wargame.entity.Entity
import com.github.tcgeneric.wargame.util.Coordinate
import java.util.*
import kotlin.collections.HashMap

// Premise : variable 'tiles' must be initialized in ascending order by map generator before using it.
data class MapData(val width:Int, val height:Int, private val tiles:HashMap<Coordinate, Tile>) {
    private val requestQueue: LinkedList<TileChangeRequest> = LinkedList()
    private val entities: HashMap<Coordinate, Entity> = HashMap(width * height) // For fast search

    fun queue(req:TileChangeRequest):Boolean {
        return requestQueue.add(req)
    }
    
    fun cancel(req:TileChangeRequest):Boolean {
        return requestQueue.remove(req)
    }

    fun apply():Boolean {
        for(t in tiles.values)
            t.isSynced = false
        if(requestQueue.isEmpty()) return false
        while(requestQueue.peek() != null) {
            val req = requestQueue.pop()
            val prevTile = getTile(req.coordinate)
            if(entities.containsValue(prevTile?.entityAbove))
                entities.remove(prevTile?.coord, prevTile?.entityAbove)
            tiles[req.coordinate] = req.newTile
            tiles[req.coordinate]!!.isSynced = true
            if(req.newTile.entityAbove != null)
                entities[req.coordinate] = req.newTile.entityAbove!!
        }
        return true
    }

    fun hasTile(tile:Tile):Boolean {
        return tiles.containsValue(tile)
    }

    fun getTiles():HashMap<Coordinate, Tile> {
        return tiles
    }

    fun getTile(coordinate:Coordinate):Tile? {
        return tiles[coordinate]
    }

    fun getEntity(coordinate:Coordinate):Entity? {
        return entities[coordinate]
    }

    fun getEntityCoordinate(entity:Entity):Coordinate? {
        entities.forEach { (t, u) ->
            if(u.id == entity.id)
                return t
        }
        return null
    }

    fun getEntities():HashMap<Coordinate, Entity> {
        return entities
    }
}