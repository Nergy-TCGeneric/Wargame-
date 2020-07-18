package com.github.tcgeneric.wargame.behaviors

import com.github.tcgeneric.wargame.entity.Entity
import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.entity.units.UnitGroup
import org.bukkit.entity.Player

class UnitAttackBehavior(actor:Unit, player:Player, time:Long, val target:Entity):UnitBehavior(actor, player, time)