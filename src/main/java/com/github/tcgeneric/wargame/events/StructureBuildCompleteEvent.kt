package com.github.tcgeneric.wargame.events

import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class StructureBuildCompleteEvent:Event() {
    private val handler = HandlerList()
    override fun getHandlers(): HandlerList {
        return handler
    }

    override fun getEventName(): String {
        return super.getEventName()
    }
}