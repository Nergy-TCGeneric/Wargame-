package com.github.tcgeneric.wargame.core

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.behaviors.*
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
        // TODO: Sort behavior by given priority(Read the note)
        behaviorQueue.forEach {
            handle(it)
        }
        Bukkit.getServer().pluginManager.callEvent(TurnCalculationEndEvent(e.turn))
    }

    private fun handle(behavior:UnitBehavior):Boolean {
        return when(behavior) {
            is UnitAttackBehavior -> {
                // TODO: Check if given target is on map. if does, calculate casualities and apply
               true
            }
            is UnitDivideBehavior -> {
                // TODO: Check if given unit is valid. if does, attempt dividing them.
                true
            }
            is UnitMergeBehavior -> {
                // TODO: Check if given units are valid. if does, attempt merging them.
                true
            }
            is UnitMoveBehavior -> {
                if(instance.mapHandler.canUnitMoveTo(behavior.coord)
                        && instance.mapHandler.isNearUnitCombatRange(behavior.actor, behavior.coord))
                    instance.mapHandler.moveUnitTo(behavior.actor, behavior.coord)
                false
            }
            else -> throw IllegalArgumentException("Unknown unit behavior type")
        }
    }
}