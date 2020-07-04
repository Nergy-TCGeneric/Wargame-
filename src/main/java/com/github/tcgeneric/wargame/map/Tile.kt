package com.github.tcgeneric.wargame.map

import com.github.tcgeneric.wargame.entity.Entity
import com.github.tcgeneric.wargame.Resources

data class Tile(
    var entity:Entity?,
    val indestructible:Boolean,
    val impassable:Boolean,
    var durability:Int,
    val resource:Resources?
)