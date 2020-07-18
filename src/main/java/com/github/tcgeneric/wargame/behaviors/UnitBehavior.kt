package com.github.tcgeneric.wargame.behaviors

import com.github.tcgeneric.wargame.entity.units.Unit
import org.bukkit.entity.Player

abstract class UnitBehavior(
    val actor:Unit,
    val player:Player,
    val time:Long
)