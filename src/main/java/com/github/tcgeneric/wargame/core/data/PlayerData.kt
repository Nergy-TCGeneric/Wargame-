package com.github.tcgeneric.wargame.core.data

import com.github.tcgeneric.wargame.util.Selection
import org.bukkit.entity.Player

data class PlayerData(val player:Player) {
    var selected:Selection? = null
}