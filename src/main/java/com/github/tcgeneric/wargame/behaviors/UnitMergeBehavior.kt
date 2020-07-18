package com.github.tcgeneric.wargame.behaviors

import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.entity.units.UnitGroup
import org.bukkit.entity.Player

class UnitMergeBehavior(actor:Unit, player:Player, time:Long, val target:UnitGroup):UnitBehavior(actor, player, time)