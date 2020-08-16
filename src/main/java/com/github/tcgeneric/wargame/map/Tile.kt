package com.github.tcgeneric.wargame.map

import com.github.tcgeneric.wargame.entity.Entity
import com.github.tcgeneric.wargame.Resource
import com.github.tcgeneric.wargame.util.Coordinate

data class Tile(
        var entity:Entity?,
        val destructible:Boolean = true,
        val passable:Boolean = false,
        var durability:Int,
        val resource:Resource? = null,
        var isSynced:Boolean = false,
        var protectionRate:Float,
        val coord:Coordinate,
        val type:TileType
)