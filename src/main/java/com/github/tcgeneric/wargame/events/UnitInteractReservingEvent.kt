package com.github.tcgeneric.wargame.events

import com.github.tcgeneric.wargame.entity.Entity
import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.map.Tile
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class UnitInteractReservingEvent(val unit:Unit, val target:Tile):Event() {

    private val handlerList:HandlerList = HandlerList()
    override fun getHandlers(): HandlerList {
        return handlerList
    }

    override fun getEventName(): String {
        return super.getEventName()
    }
}