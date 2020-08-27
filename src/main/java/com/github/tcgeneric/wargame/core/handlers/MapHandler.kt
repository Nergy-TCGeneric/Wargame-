package com.github.tcgeneric.wargame.core.handlers

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
import kotlin.math.abs

class MapHandler(private var frame:MapFrame, private var mapData:MapData) {

    fun setMapData(mapData: MapData) {
        if(frame.height != mapData.height || frame.width != mapData.width)
            throw IllegalArgumentException("Given MapData doesn't fit into given MapFrame.")
        if(Wargame.isGameStarted)
            throw IllegalStateException("Cannot change mapdata while game is already started.")
        this.mapData = mapData
    }

    fun setFrame(frame: MapFrame) {
        if(frame.height < 0 || frame.width < 0)
            throw IllegalArgumentException("Frame's height or width cannot be less than 0")
        if(frame.direction.first == frame.direction.second || frame.direction.first.i + frame.direction.second.i == 0)
            throw IllegalArgumentException("Invalid direction is given.")
        if(Wargame.isGameStarted)
            throw IllegalStateException("Cannot change mapframe while game is already started.")
        this.frame = frame
    }

    fun getMapTiles():HashMap<Coordinate, Tile> {
        return mapData.getTiles()
    }

    fun getMapFrame():MapFrame {
        return frame
    }

    fun syncSight(sight:MapData) {
        for(entry in sight.getEntities()) {
            var cord = entry.key.clone()
            if(entry.value is Unit) {
                val sr = (entry.value as Unit).sightRange
                for(x in 0 until sr) {
                    for(z in 0 until sr) {
                        if(abs(x) + abs(z) <= sr) {
                            cord.add(x, z)
                            sight.queue(TileChangeRequest(cord, getTile(cord)!!, System.currentTimeMillis()))
                            cord = entry.key.clone()
                        }
                    }
                }
            }
        }
        sight.apply()
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
    
    fun isOnPlayerSight(tile:Tile, player:Player):Boolean {
        val team = TeamManager.getPlayerTeam(player) ?: return false
        if(team.sight.hasTile(tile))
            return tile.isSynced
        return false
    }

    fun getEntityCoordinate(entity:Entity):Coordinate {
        return mapData.getEntityCoordinate(entity)
    }

    fun getTile(coord:Coordinate): Tile? {
        return mapData.getTile(coord)
    }

    fun getTileByEntity(entity:Entity): Tile? {
        val coord = getEntityCoordinate(entity)
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

    fun findPassage(start:Tile, dest:Tile):List<Coordinate> {
        TODO("Further implementation required")
        return listOf()
    }
}