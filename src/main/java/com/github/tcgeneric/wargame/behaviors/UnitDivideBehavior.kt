package com.github.tcgeneric.wargame.behaviors

import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.util.Coordinate
import org.bukkit.entity.Player

class UnitDivideBehavior(actor: Unit, player: Player, val coord:Coordinate, val amount:Int):UnitBehavior(actor, player)