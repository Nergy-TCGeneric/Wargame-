package com.github.tcgeneric.wargame.map

import com.github.tcgeneric.wargame.entity.Entity
import com.github.tcgeneric.wargame.util.Coordinate

data class Tile(
        var entityAbove: Entity? = null,
        var isSynced: Boolean = false,
        var protectionRate: Float,
        val coord: Coordinate,
        val type: TileType
)