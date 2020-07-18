package com.github.tcgeneric.wargame.behaviors

import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.map.Tile
import org.bukkit.entity.Player

class UnitMoveBehavior(actor:Unit, player:Player, time: Long, val tile: Tile):UnitBehavior(actor, player, time)