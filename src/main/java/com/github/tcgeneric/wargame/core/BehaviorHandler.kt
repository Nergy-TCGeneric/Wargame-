package com.github.tcgeneric.wargame.core

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.behaviors.*
import com.github.tcgeneric.wargame.effects.*
import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.entity.units.UnitGroup
import com.github.tcgeneric.wargame.events.TurnCalculationEndEvent
import com.github.tcgeneric.wargame.events.TurnTimeEndEvent
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import java.lang.IllegalArgumentException
import java.util.*

class BehaviorHandler(private val instance:Wargame) {
    private val behaviorQueue: LinkedList<UnitBehavior> = LinkedList()

    @EventHandler
    fun onTurnTimeEndEvent(e:TurnTimeEndEvent) {
        sort()
        while(behaviorQueue.peek() != null) {
            handle(behaviorQueue.pop())
        }
        Bukkit.getServer().pluginManager.callEvent(TurnCalculationEndEvent(e.turn))
    }

    fun queue(behavior:UnitBehavior) {
        behaviorQueue.add(behavior)
    }

    fun cancel(behavior:UnitBehavior) {
        behaviorQueue.remove(behavior)
    }

    private fun sort() {
        // TODO: Sort behavior by given priority(Read the note)
    }

    private fun handle(behavior:UnitBehavior):Boolean {
        return when(behavior) {
            is UnitAttackBehavior -> {
                // TODO: Needed to change Unitbehavior's parameter.
                val dmgToTarget = instance.unitHandler.getInflictingDamage(behavior.actor, behavior.target)
                // TODO: Apply damage to unit's actual health value somewhere
                instance.displayHandler.addReservedEffect(UnitAttackEffect(behavior.actor, behavior.target))
                        .addReservedEffect(UnitDamageEffect(dmgToTarget))
                if(behavior.target is Unit && dmgToTarget.after > 0) {
                    // TODO: This implementation is wrong because this is based on entity's previous undamaged state
                    val dmgToActor = instance.unitHandler.getInflictingDamage(behavior.actor, behavior.target)
                    instance.displayHandler.addReservedEffect(UnitAttackEffect(behavior.target, behavior.actor))
                            .addReservedEffect(UnitDamageEffect(dmgToActor))
                }
               true
            }
            is UnitDivideBehavior -> {
                val ug = behavior.tile.entity as UnitGroup
                instance.displayHandler.addReservedEffect(UnitDivideEffect(behavior.actor, ug))
                true
            }
            is UnitMergeBehavior -> {
                instance.unitHandler.mergeUnit(behavior.actor, behavior.target)
                instance.displayHandler.addReservedEffect(UnitMergeEffect(behavior.actor, behavior.target))
                true
            }
            is UnitMoveBehavior -> {
                if(behavior.tile.passable
                        && instance.mapHandler.isNearUnitMoveRange(behavior.actor, behavior.tile.coord)) {
                    instance.mapHandler.moveUnitTo(behavior.actor, behavior.tile.coord)
                    instance.displayHandler.addReservedEffect(UnitMoveEffect(behavior.actor, behavior.tile.coord))
                }
                false
            }
            is UnitDwellBehavior -> {
                // TODO: Code goes here
                false
            }
            is UnitBuildBehavior -> {
                // TODO: Code goes here
                false
            }
            else -> throw IllegalArgumentException("Unknown unit behavior type")
        }
    }
}