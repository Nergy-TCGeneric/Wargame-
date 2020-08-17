package com.github.tcgeneric.wargame.events

import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.map.Tile
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

@Deprecated("Not used")
class UnitDivideEvent(val unit:Unit, val tile:Tile, val amount:Int): Event() {

    private val handlerList:HandlerList = HandlerList()
    override fun getHandlers(): HandlerList {
        return handlerList
    }
}