package com.github.tcgeneric.wargame.core

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.entity.Entity
import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.map.MapData
import kotlin.math.abs

class MapHandler(val instance:Wargame) {
    private lateinit var mapData:MapData

    fun coordToIdx(coord:Pair<Int, Int>):Int { return coord.first * mapData.width + coord.second }

    fun moveUnitTo(unit:Unit, coord:Pair<Int, Int>):Boolean {
        mapData.tiles[coordToIdx(coord)]!!.entity = unit
        return true
    }

    fun canUnitMoveTo(coord:Pair<Int, Int>):Boolean {
        return mapData.tiles[coordToIdx(coord)]!!.impassable
    }

    fun manhattanDist(unit:Unit, coord:Pair<Int, Int>):Int {
        for(x in mapData.tiles.indices) { // TODO: For fast calculation, you can store unit's coordinations at somewhere.
            if(mapData.tiles[x]!!.entity?.id == unit.id)
                return abs(coord.first - x/mapData.width) + abs(coord.second - x%mapData.width)
        }
        throw IllegalStateException("Entity without id is present")
    }

    fun isUnit(coord:Pair<Int, Int>):Boolean {
        val target = mapData.tiles[coordToIdx(coord)]!!.entity
        return target is Unit
    }

    fun isNearUnitCombatRange(unit:Unit, coord:Pair<Int, Int>):Boolean {
        if(manhattanDist(unit, coord) > unit.combatRange)
            return false
        return true
    }
}