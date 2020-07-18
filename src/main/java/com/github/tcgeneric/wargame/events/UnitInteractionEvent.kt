package com.github.tcgeneric.wargame.events

import com.github.tcgeneric.wargame.entity.Entity
import com.github.tcgeneric.wargame.entity.units.Unit
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class UnitInteractionEvent(val unit:Unit, val target:Entity):Event() {

    private val handlerList:HandlerList = HandlerList()
    override fun getHandlers(): HandlerList {
        return handlerList
    }

    override fun getEventName(): String {
        return super.getEventName()
    }
}