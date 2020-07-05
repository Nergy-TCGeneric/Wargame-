package com.github.tcgeneric.wargame.behaviors

import com.github.tcgeneric.wargame.entity.units.Unit
import org.bukkit.entity.Player

abstract class UnitBehavior(
    val id:Int,
    val actor:Unit,
    val player:Player
)