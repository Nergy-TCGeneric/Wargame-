package com.github.tcgeneric.wargame.events

import com.github.tcgeneric.wargame.entity.Entity
import com.github.tcgeneric.wargame.entity.units.Unit
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class EntityDamagedEvent(val unit:Entity, val damage:Double):Event() {
    private val handlerList:HandlerList = HandlerList()
    override fun getHandlers(): HandlerList {
        return handlerList
    }
}