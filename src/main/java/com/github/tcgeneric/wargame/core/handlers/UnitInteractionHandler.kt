package com.github.tcgeneric.wargame.core.handlers

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.core.data.DamageResult
import com.github.tcgeneric.wargame.entity.Entity
import com.github.tcgeneric.wargame.entity.structures.Structure
import com.github.tcgeneric.wargame.entity.units.Unit

class UnitInteractionHandler {
    companion object {
        // TODO: Damage calculation should be flexible. how about using float?
        fun getInflictingDamage(attacker: Unit, defender: Entity?): DamageResult {
            if (defender == null) return DamageResult(defender, 0, 0)
            val dT = Wargame.mapHandler.getTileByEntity(defender) ?: throw IllegalStateException("Illegal tile state change")
            return when (defender) {
                is Unit -> DamageResult(
                        defender,
                        defender.amount,
                        (attacker.amount / defender.amount * attacker.combatStrength / dT.protectionRate).toInt())
                is Structure -> DamageResult(
                        defender,
                        defender.durability,
                        (attacker.amount * attacker.combatStrength / dT.protectionRate).toInt())
                else -> TODO("Not implemented yet")
            }
        }
    }
}