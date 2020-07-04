package com.github.tcgeneric.wargame.core

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.behaviors.EntityBehavior
import org.bukkit.Bukkit
import java.util.*

class TurnHandler(private val instance:Wargame) {

    private val behaviorQueue = LinkedList<EntityBehavior>()
    var turn = 0

    fun nextTurn() {
        while(behaviorQueue.peek() != null) {
            instance.behaviorHandler.handle(behaviorQueue.pop())
        }
        // TODO: Propagate TurnElapseEvent
        turn++
    }

    fun addReservedBehavior(entityBehavior: EntityBehavior) {
        behaviorQueue.add(entityBehavior)
    }

    fun removeReservedBehavior(entityBehavior: EntityBehavior) {
        behaviorQueue.remove(entityBehavior)
    }
}