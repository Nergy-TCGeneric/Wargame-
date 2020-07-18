package com.github.tcgeneric.wargame.core

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.entity.Entity
import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.events.EntityMoveEvent
import com.github.tcgeneric.wargame.map.MapData
import com.github.tcgeneric.wargame.map.Tile
import com.github.tcgeneric.wargame.teams.Team
import com.github.tcgeneric.wargame.util.RangedVector
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player
import kotlin.math.abs

class MapHandler(private val instance:Wargame, private var mapData:MapData) {

    fun createEntityOn(entity:Entity, coord:Pair<Int, Int>):Boolean {
        val tile = mapData.tiles[coordToIdx(coord)]!!
        if(tile.entity != null)
            return false
        tile.entity = entity
        entity.parentTile = tile
        mapData.entities[entity] = coord
        return true
    }

    fun removeEntity(entity:Entity):Boolean {
        val coord = getEntityCoordinate(entity) ?: throw IllegalStateException("Entity without id is present")
        mapData.tiles[coordToIdx(coord)]!!.entity = null
        mapData.entities.remove(entity)
        return true
    }

    fun moveUnitTo(unit:Unit, coord:Pair<Int, Int>):Boolean {
        val tile = mapData.tiles[coordToIdx(coord)]!!
        tile.entity = unit
        mapData.entities[unit] = coord
        unit.parentTile = tile
        Bukkit.getPluginManager().callEvent(EntityMoveEvent(unit, coord))
        return true
    }

    fun canUnitMoveTo(coord:Pair<Int, Int>):Boolean {
        return isPassableTile(coord) && !isUnit(coord)
    }

    fun isPassableTile(coord:Pair<Int, Int>):Boolean {
        return mapData.tiles[coordToIdx(coord)]!!.passable
    }

    fun isUnit(coord:Pair<Int, Int>):Boolean {
        return mapData.tiles[coordToIdx(coord)]!!.entity is Unit
    }

    fun isEntityOnMap(entity:Entity):Boolean {
        return mapData.entities.containsKey(entity) && mapData.entities[entity] != null
    }

    fun isNearUnitCombatRange(unit:Unit, coord:Pair<Int, Int>):Boolean {
        return manhattanDist(unit, coord) <= unit.combatRange
    }

    fun isNearUnitCombatRange(first:Unit, second:Unit):Boolean {
        return isNearUnitCombatRange(first, mapData.entities[second]!!)
    }

    fun isNearUnitMoveRange(unit:Unit, coord:Pair<Int, Int>):Boolean {
        return manhattanDist(unit, coord) <= unit.moveRange
    }

    fun isNearUnitMoveRange(first:Unit, second:Unit):Boolean {
        return isNearUnitMoveRange(first, mapData.entities[second]!!)
    }

    fun isOnPlayerSight(tile:Tile, player:Player):Boolean {
        val team = instance.teamManager.getPlayerTeam(player) ?: return false
        val t = team.sight.findTile(tile) ?: return false
        return t.isSynced
    }

    fun getEntityCoordinate(entity:Entity):Pair<Int, Int>? {
        return mapData.entities[entity]
    }

    fun getTile(coord:Pair<Int, Int>): Tile? {
        return mapData.tiles[coordToIdx(coord)]
    }

    fun findTileCoord(tile:Tile): Pair<Int, Int>? {
        val idx = mapData.tiles.indexOf(tile)
        return if(idx >= 0) idxToCoord(idx)
        else null
    }

    fun getTileVector(coord:Pair<Int, Int>): RangedVector? {
        if(mapData.tiles[coordToIdx(coord)] != null)
            return mapData.startPoint.add(coord.first, 0, coord.second)
        return null
    }

    fun locToCoord(loc:Location):Pair<Int, Int> {
        // TODO: Given range is stub; replace with actual value
        val vec = RangedVector(loc.blockX, loc.blockY, loc.blockZ, 4)
        val diff = mapData.startPoint.subtract(vec)
        if(diff.x < 0 || diff.z < 0 || diff.x > mapData.width || diff.z > mapData.height)
            throw IllegalStateException("Given coordinate is out of range")
        return Pair(diff.x, diff.z)
    }

    private fun coordToIdx(coord:Pair<Int, Int>):Int { return coord.first * mapData.width + coord.second }

    private fun idxToCoord(idx:Int):Pair<Int, Int> { return Pair(idx / mapData.width, idx % mapData.width)}

    private fun manhattanDist(entity:Entity, coord:Pair<Int, Int>):Int {
        for(k in mapData.entities.keys) {
            if(k.id == entity.id) {
                val unitLoc = getEntityCoordinate(k)!!
                return abs(coord.first - unitLoc.first) + abs(coord.second - unitLoc.second)
            }
        }
        throw IllegalStateException("Entity without id is present")
    }
}