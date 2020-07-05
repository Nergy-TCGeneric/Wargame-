package com.github.tcgeneric.wargame.behaviors

import com.github.tcgeneric.wargame.entity.units.Unit
import org.bukkit.entity.Player

class UnitMoveBehavior(id:Int, actor:Unit, player:Player, val coord:Pair<Int, Int>):UnitBehavior(id, actor, player)