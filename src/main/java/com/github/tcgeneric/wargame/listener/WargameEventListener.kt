package com.github.tcgeneric.wargame.listener

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.events.EntityMoveEvent
import com.github.tcgeneric.wargame.events.EntitySelectEvent
import com.github.tcgeneric.wargame.events.TileSelectEvent
import com.github.tcgeneric.wargame.util.Selection
import org.bukkit.event.EventHandler

class WargameEventListener(private val instance:Wargame) {
    @EventHandler
    fun onUnitMove(e:EntityMoveEvent) {

    }

    @EventHandler
    fun onEntitySelection(e:EntitySelectEvent) {

    }

    @EventHandler
    fun onTileSelection(e:TileSelectEvent) {
        val pData = instance.pDataHandler.dataMap[e.player.uniqueId]
        if(pData?.selected == null)
            pData?.selected = Selection(e.tile.coord, e.tile.entity)
    }
}