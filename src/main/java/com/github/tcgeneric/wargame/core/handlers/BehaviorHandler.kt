package com.github.tcgeneric.wargame.core.handlers

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.behaviors.*
import com.github.tcgeneric.wargame.transitions.*
import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.events.TurnCalculationEndEvent
import com.github.tcgeneric.wargame.events.TurnTimeEndEvent
import com.github.tcgeneric.wargame.util.Coordinate
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import java.lang.IllegalArgumentException
import java.util.*

class BehaviorHandler {
    companion object {
        private val behaviorQueue: LinkedList<UnitBehavior> = LinkedList()

        @EventHandler
        fun onTurnTimeEndEvent(e: TurnTimeEndEvent) {
            sort()
            while (behaviorQueue.peek() != null) {
                handle(behaviorQueue.pop())
            }
            Bukkit.getServer().pluginManager.callEvent(TurnCalculationEndEvent(e.turn))
        }

        fun queue(behavior: UnitBehavior) {
            behaviorQueue.add(behavior)
        }

        fun cancel(behavior: UnitBehavior) {
            behaviorQueue.remove(behavior)
        }

        private fun sort() {
            // TODO: Sort behavior by given priority(Read the note)
        }

        private fun handle(behavior: UnitBehavior): Boolean {
            return when (behavior) {
                is UnitAttackBehavior -> {
                    val dmgToTarget = UnitInteractionHandler.getInflictingDamage(behavior.actor, behavior.target)
                    // TODO: Apply damage to unit's actual health value somewhere
                    GraphicManager.transitionQueue.add(UnitAttackTransition(behavior.actor, behavior.target))
                    GraphicManager.transitionQueue.add(UnitDamageTransition(dmgToTarget))
                    // Retaliation
                    if (behavior.target is Unit && dmgToTarget.after > 0) {
                        // TODO: This implementation is wrong because this is based on entity's previous undamaged state
                        val dmgToActor = UnitInteractionHandler.getInflictingDamage(behavior.actor, behavior.target)
                        GraphicManager.transitionQueue.add(UnitAttackTransition(behavior.target, behavior.actor))
                        GraphicManager.transitionQueue.add(UnitDamageTransition(dmgToActor))
                    }
                    true
                }
                is UnitMoveBehavior -> {
                    val coord: Coordinate = Wargame.mapHandler.getEntityCoordinate(behavior.actor)
                    if (behavior.tile.entityAbove == null
                            && coord.manhattanDist(behavior.tile.coord) >= behavior.actor.moveRange) {
                        Wargame.mapHandler.moveUnitTo(behavior.actor, behavior.tile.coord)
                        GraphicManager.transitionQueue.add(UnitMoveTransition(behavior.actor, behavior.tile.coord))
                    }
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
}