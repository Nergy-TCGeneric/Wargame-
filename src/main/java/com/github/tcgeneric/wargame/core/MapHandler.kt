package com.github.tcgeneric.wargame.core

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.entity.Entity
import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.events.UnitMoveEvent
import com.github.tcgeneric.wargame.map.MapData
import com.github.tcgeneric.wargame.map.Tile
import com.github.tcgeneric.wargame.util.Coordinate
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player
import kotlin.math.abs

class MapHandler(private val instance:Wargame, private var mapData:MapData) {

    fun createEntityOn(entity:Entity, coord:Coordinate):Boolean {
        val tile = mapData.tiles[coordToIdx(coord)]
        if(tile.entity != null)
            return false
        tile.entity = entity
        entity.parentTile = tile
        mapData.entities[entity] = coord
        return true
    }

    fun removeEntity(entity:Entity):Boolean {
        val coord = getEntityCoordinate(entity) ?: throw IllegalStateException("Entity without id is present")
        mapData.tiles[coordToIdx(coord)].entity = null
        mapData.entities.remove(entity)
        return true
    }

    fun moveUnitTo(unit:Unit, coord:Coordinate):Boolean {
        val tile = mapData.tiles[coordToIdx(coord)]
        tile.entity = unit
        mapData.entities[unit] = coord
        unit.parentTile = tile
        return true
    }

    fun canUnitMoveTo(unit:Unit, coord:Coordinate):Boolean {
        return getTile(coord)!!.passable && isNearUnitMoveRange(unit, coord)
    }

    fun isUnit(coord:Coordinate):Boolean {
        return mapData.tiles[coordToIdx(coord)].entity is Unit
    }

    fun isEntityOnMap(entity:Entity):Boolean {
        return mapData.entities.containsKey(entity) && mapData.entities[entity] != null
    }

    fun isNearUnitCombatRange(unit:Unit, coord:Coordinate):Boolean {
        return manhattanDist(unit, coord) <= unit.combatRange
    }

    fun isNearUnitCombatRange(first:Unit, second:Unit):Boolean {
        return isNearUnitCombatRange(first, mapData.entities[second]!!)
    }

    fun isNearUnitMoveRange(unit:Unit, coord:Coordinate):Boolean {
        return manhattanDist(unit, coord) <= unit.moveRange
    }

    fun isNearUnitMoveRange(first:Unit, second:Unit):Boolean {
        return isNearUnitMoveRange(first, mapData.entities[second]!!)
    }

    fun isOnPlayerSight(tile:Tile, player:Player):Boolean {
        val team = instance.teamManager.getPlayerTeam(player) ?: return false
        if(team.sight.tiles.contains(tile))
            return tile.isSynced
        return false
    }

    fun getEntityCoordinate(entity:Entity):Coordinate? {
        return mapData.entities[entity]
    }

    fun getTile(coord:Coordinate): Tile? {
        return mapData.tiles[coordToIdx(coord)]
    }

    fun locToCoord(loc:Location):Coordinate {
        val diff = Coordinate(loc.blockX, loc.blockZ).subtract(mapData.startPoint)
        if(diff.x < 0) diff.x = 0
        if(diff.x > mapData.width) diff.x = mapData.width
        if(diff.z < 0) diff.z = 0
        if(diff.z > mapData.height) diff.z = mapData.height
        return diff
    }

    fun isInsideMap(loc:Location):Boolean {
        val diff = Coordinate(loc.blockX, loc.blockZ).subtract(mapData.startPoint)
        return diff.x >= 0 && diff.x <= mapData.width && diff.z >= 0 && diff.z <= mapData.height
    }

    fun synchronize(sight:MapData) {

    }

    private fun coordToIdx(coord:Coordinate):Int { return coord.x * mapData.width + coord.z }

    private fun manhattanDist(entity:Entity, coord:Coordinate):Int {
        for(k in mapData.entities.keys) {
            if(k.id == entity.id) {
                val unitLoc = getEntityCoordinate(k)!!
                return abs(coord.x - unitLoc.x) + abs(coord.z - unitLoc.z)
            }
        }
        throw IllegalStateException("Entity without id is present")
    }
}