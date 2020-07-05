package com.github.tcgeneric.wargame.behaviors

import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.entity.units.UnitGroup
import org.bukkit.entity.Player

class UnitAttackBehavior(id:Int, actor:Unit, player:Player, val target:Unit):UnitBehavior(id, actor, player)