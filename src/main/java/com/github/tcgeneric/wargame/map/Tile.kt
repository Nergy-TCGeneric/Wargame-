package com.github.tcgeneric.wargame.map

import com.github.tcgeneric.wargame.entity.Entity
import com.github.tcgeneric.wargame.Resource
import com.github.tcgeneric.wargame.util.Coordinate

data class Tile(
        var entity:Entity?,
        val destructible:Boolean,
        val passable:Boolean,
        var durability:Int,
        val resource:Resource?,
        var isSynced:Boolean,
        var protectionRate:Float,
        val coord:Coordinate
)