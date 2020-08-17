package com.github.tcgeneric.wargame.core

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.core.data.TileChangeRequest
import com.github.tcgeneric.wargame.entity.Entity
import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.map.MapData
import com.github.tcgeneric.wargame.map.MapFrame
import com.github.tcgeneric.wargame.map.Tile
import com.github.tcgeneric.wargame.util.Coordinate
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Player

class MapHandler(private val instance:Wargame, private var frame:MapFrame, private var mapData:MapData) {

    fun setMapData(mapData: MapData) {
        if(frame.height != mapData.height || frame.width != mapData.width)
            throw IllegalArgumentException("Given MapData doesn't fit into given MapFrame.")
        if(instance.isGameStarted)
            throw IllegalStateException("Cannot change mapdata while game is already started.")
        this.mapData = mapData
    }

    fun setFrame(frame: MapFrame) {
        if(frame.height < 0 || frame.width < 0)
            throw IllegalArgumentException("Frame's height or width cannot be less than 0")
        if(frame.direction.first == frame.direction.second || frame.direction.first.i + frame.direction.second.i == 0)
            throw IllegalArgumentException("Invalid direction is given.")
        if(instance.isGameStarted)
            throw IllegalStateException("Cannot change mapframe while game is already started.")
        this.frame = frame
    }

    fun createEntityOn(entity:Entity, coord:Coordinate):Boolean {
        val tile = mapData.getTile(coord) ?: return false
        tile.entityAbove = entity
        entity.parentTile = tile
        return mapData.queue(TileChangeRequest(coord, tile, System.currentTimeMillis()))
    }

    fun removeEntity(entity:Entity):Boolean {
        val coord = mapData.getEntityCoordinate(entity) ?: return false
        val tile = getTile(coord) ?: return false
        if(tile.entityAbove == null) return false
        tile.entityAbove = null
        return mapData.queue(TileChangeRequest(coord, tile, System.currentTimeMillis()))
    }

    // Doesn't consider unit merging; this just overwrites
    fun moveUnitTo(unit:Unit, coord:Coordinate):Boolean {
        removeEntity(unit)
        return createEntityOn(unit, coord)
    }

    fun canUnitMoveTo(unit:Unit, coord:Coordinate):Boolean {
        val uCoord = getEntityCoordinate(unit) ?: return false
        return getTile(coord)!!.entityAbove == null && uCoord.manhattanDist(coord) <= unit.moveRange
    }

    fun isEntityOnMap(entity:Entity):Boolean {
        return mapData.getEntityCoordinate(entity) != null
    }
    
    fun isOnPlayerSight(tile:Tile, player:Player):Boolean {
        val team = instance.teamManager.getPlayerTeam(player) ?: return false
        if(team.sight.hasTile(tile))
            return tile.isSynced
        return false
    }

    fun getEntityCoordinate(entity:Entity):Coordinate? {
        return mapData.getEntityCoordinate(entity)
    }

    fun getTile(coord:Coordinate): Tile? {
        return mapData.getTile(coord)
    }

    fun getTileByEntity(entity:Entity): Tile? {
        val coord = getEntityCoordinate(entity) ?: return null
        return getTile(coord)
    }

    fun locToCoord(loc:Location):Coordinate {
        val diff = Coordinate(loc.blockX, loc.blockZ).subtract(frame.startingPoint)
        diff.x = diff.x.coerceIn(0, frame.width)
        diff.z = diff.z.coerceIn(0, frame.height)
        return diff
    }

    fun coordToLoc(world: World, coord:Coordinate):Location {
        // TODO: Stub value was used. replace it with actual value
        return Location(world, coord.x.toDouble(), 64.0, coord.z.toDouble())
    }

    fun isInsideMap(loc:Location):Boolean {
        val diff = Coordinate(loc.blockX, loc.blockZ).subtract(frame.startingPoint)
        return diff.x >= 0 && diff.x <= frame.width && diff.z >= 0 && diff.z <= frame.height
    }
}