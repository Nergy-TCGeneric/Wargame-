package com.github.tcgeneric.wargame.core

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.entity.Entity
import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.map.MapData

class MapHandler(val instance:Wargame) {
    private lateinit var mapData:MapData

    fun moveUnitTo(unit:Unit, coord:Pair<Int, Int>) {
//        val dist = unit.parentTile
        // TODO: Calculate the distance later
        val old = mapData.tiles[coord.first * mapData.width + coord.second]!!
    }
}