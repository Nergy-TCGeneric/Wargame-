package com.github.tcgeneric.wargame.events

import com.github.tcgeneric.wargame.entity.Entity
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class EntitySelectEvent(val player: Player, val entity: Entity):Event() {

    private val handlerList:HandlerList = HandlerList()
    override fun getHandlers(): HandlerList {
        return handlerList
    }

    override fun getEventName(): String {
        return super.getEventName()
    }
}