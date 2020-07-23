package com.github.tcgeneric.wargame.behaviors

import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.map.Tile
import com.github.tcgeneric.wargame.util.Controller
import org.bukkit.entity.Player

class UnitMoveBehavior(actor:Unit, player:Controller<*>, time: Long, val tile: Tile):UnitBehavior(actor, player, time)