package com.github.tcgeneric.wargame.core.data

import com.github.tcgeneric.wargame.map.Tile
import com.github.tcgeneric.wargame.util.Coordinate
import org.bukkit.entity.Player

data class TileChangeRequest(val coordinate:Coordinate, val newTile:Tile, val reqTime:Long)