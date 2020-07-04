package com.github.tcgeneric.wargame.events

import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class TurnCalculationEndEvent(val turn:Int): Event() {

    private val handlerList:HandlerList = HandlerList()

    override fun getHandlers(): HandlerList {
        return handlerList
    }

    override fun getEventName(): String {
        return super.getEventName()
    }
}