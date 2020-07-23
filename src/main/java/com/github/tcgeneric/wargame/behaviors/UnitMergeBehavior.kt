package com.github.tcgeneric.wargame.behaviors

import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.entity.units.UnitGroup
import com.github.tcgeneric.wargame.util.Controller
import org.bukkit.entity.Player

@Deprecated("Not used")
class UnitMergeBehavior(actor:Unit, player:Controller<*>, time:Long, val target:UnitGroup):UnitBehavior(actor, player, time)