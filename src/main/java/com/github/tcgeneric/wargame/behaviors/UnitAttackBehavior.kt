package com.github.tcgeneric.wargame.behaviors

import com.github.tcgeneric.wargame.entity.Entity
import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.entity.units.UnitGroup
import com.github.tcgeneric.wargame.util.Controller
import org.bukkit.entity.Player

class UnitAttackBehavior(actor:Unit, player: Controller<*>, time:Long, val target:Entity):UnitBehavior(actor, player, time)