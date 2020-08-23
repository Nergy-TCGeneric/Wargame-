package com.github.tcgeneric.wargame.core.handlers

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.behaviors.*
import com.github.tcgeneric.wargame.transitions.*
import com.github.tcgeneric.wargame.events.TurnCalculationEndEvent
import com.github.tcgeneric.wargame.events.TurnTimeEndEvent
import com.github.tcgeneric.wargame.util.Coordinate
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import java.lang.IllegalArgumentException
import java.util.*

class BehaviorHandler {
    companion object {
        private var behaviorQueue: LinkedList<UnitBehavior> = LinkedList()

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
            val sorted:LinkedList<UnitBehavior> = LinkedList()
            val t1:LinkedList<UnitBehavior> = LinkedList()
            val t2:LinkedList<UnitBehavior> = LinkedList()
            for(b in behaviorQueue) {
                when (b) {
                    is UnitAttackBehavior -> sorted.add(b)
                    is UnitBuildBehavior -> t1.add(b)
                    is UnitMoveBehavior -> t2.add(b)
                }
            }
            sorted.addAll(t2)
            sorted.addAll(t1)
            behaviorQueue = sorted
        }

        private fun handle(behavior: UnitBehavior): Boolean {
            return when (behavior) {
                is UnitAttackBehavior -> {
                    val dmgToTarget = UnitInteractionHandler.getInflictingDamageRatio(behavior.actor, behavior.target)
                    UnitInteractionHandler.applyDamageTo(behavior.target, dmgToTarget)
                    GraphicManager.transitionQueue.add(UnitAttackTransition(behavior.actor, behavior.target))
                    GraphicManager.transitionQueue.add(UnitDamageTransition(behavior.target))
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