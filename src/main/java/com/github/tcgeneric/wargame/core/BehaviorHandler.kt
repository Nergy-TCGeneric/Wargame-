package com.github.tcgeneric.wargame.core

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.behaviors.*
import com.github.tcgeneric.wargame.effects.UnitAttackEffect
import com.github.tcgeneric.wargame.effects.UnitMergeEffect
import com.github.tcgeneric.wargame.effects.UnitMoveEffect
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
        behaviorQueue.forEach {
            handle(it)
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
                instance.unitHandler
                instance.displayHandler.addReservedEffect(UnitAttackEffect(behavior.actor, behavior.target))
               true
            }
            is UnitDivideBehavior -> {
                // TODO: By changing UnitDivideBehavior's parameter, make this handle UnitGroup too.
//                instance.displayHandler.addReservedEffect(UnitDivideEffect())
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
            else -> throw IllegalArgumentException("Unknown unit behavior type")
        }
    }
}