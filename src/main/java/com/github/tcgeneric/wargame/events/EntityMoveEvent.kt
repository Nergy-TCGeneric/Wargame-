package com.github.tcgeneric.wargame.events

import com.github.tcgeneric.wargame.entity.Entity
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

// TODO: Only units can move. How about refactoring this code?
class EntityMoveEvent(val unit: Entity, val coord:Pair<Int, Int>):Event() {

    private val handlerList:HandlerList = HandlerList()

    override fun getHandlers(): HandlerList {
        return handlerList
    }

    override fun getEventName(): String {
        return super.getEventName()
    }
}