package com.github.tcgeneric.wargame.core.handlers

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.entity.Entity
import com.github.tcgeneric.wargame.entity.structures.ControlPoint
import com.github.tcgeneric.wargame.entity.structures.Structure
import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.events.BaseCaptureEvent
import com.github.tcgeneric.wargame.events.EntityDamagedEvent
import org.bukkit.Bukkit
import kotlin.random.Random

class UnitInteractionHandler {
    companion object {
        fun getInflictingDamageRatio(attacker: Unit, defender: Entity?): Double {
            if (defender == null) return 0.0
            val dT = Wargame.mapHandler.getTileByEntity(defender) ?: throw IllegalStateException("Illegal tile state change")
            val R = Random(System.currentTimeMillis()).nextDouble(0.8, 1.2)
            return when (defender) {
                is Unit -> R * attacker.healthPoint / defender.healthPoint * attacker.combatStrength / dT.protectionRate
                is Structure -> R * attacker.healthPoint * attacker.combatStrength / dT.protectionRate / defender.toughness
                else -> throw IllegalArgumentException("Not a valid entity type")
            }
        }

        fun applyDamageTo(victim:Entity, damageRatio:Double) {
            victim.healthPoint -= victim.healthPoint * damageRatio.toInt()
            Bukkit.getServer().pluginManager.callEvent(EntityDamagedEvent(victim, damageRatio))
            if(victim.healthPoint < 0) {
                victim.healthPoint = 0
                if(victim is ControlPoint)
                    Bukkit.getServer().pluginManager.callEvent(BaseCaptureEvent(victim))
                else
                    victim.willRemoved = true
            }
        }
    }
}