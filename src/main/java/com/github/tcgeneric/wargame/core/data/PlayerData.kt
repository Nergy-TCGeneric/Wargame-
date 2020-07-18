package com.github.tcgeneric.wargame.core.data

import com.github.tcgeneric.wargame.behaviors.UnitBehavior
import com.github.tcgeneric.wargame.map.Tile
import org.bukkit.entity.Player

data class PlayerData(val player:Player) {
    var selectedTile: Tile? = null
    var queuedBehavior: UnitBehavior? = null
}