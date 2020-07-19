package com.github.tcgeneric.wargame.behaviors

import com.github.tcgeneric.wargame.entity.structures.Structure
import com.github.tcgeneric.wargame.entity.units.Unit
import org.bukkit.entity.Player

class UnitDwellBehavior(actor:Unit, player: Player, time:Long, val target:Structure): UnitBehavior(actor, player, time)