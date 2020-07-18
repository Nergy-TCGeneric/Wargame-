package com.github.tcgeneric.wargame.map

import com.github.tcgeneric.wargame.entity.Entity
import com.github.tcgeneric.wargame.Resource
import com.github.tcgeneric.wargame.util.Coordinate

// TODO: Remove coord value from MapData. Just store tile data into list.
data class Tile(
        var entity:Entity?,
        val destructible:Boolean,
        val passable:Boolean,
        var durability:Int,
        val resource:Resource?,
        var isSynced:Boolean,
        val coord:Coordinate
)