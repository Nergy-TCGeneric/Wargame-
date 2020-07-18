package com.github.tcgeneric.wargame.events

import com.github.tcgeneric.wargame.map.Tile
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class TileSelectEvent(val player: Player, val tile:Tile): Event() {

    private val handlerList:HandlerList = HandlerList()
    override fun getHandlers(): HandlerList {
        return handlerList
    }

    override fun getEventName(): String {
        return super.getEventName()
    }
}