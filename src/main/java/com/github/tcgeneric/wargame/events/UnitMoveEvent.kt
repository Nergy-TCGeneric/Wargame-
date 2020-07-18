package com.github.tcgeneric.wargame.events

import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.map.Tile
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class UnitMoveEvent(val unit:Unit, val tile:Tile):Event() {

    private val handlerList:HandlerList = HandlerList()

    override fun getHandlers(): HandlerList {
        return handlerList
    }

    override fun getEventName(): String {
        return super.getEventName()
    }
}