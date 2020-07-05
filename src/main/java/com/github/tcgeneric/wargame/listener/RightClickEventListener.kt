package com.github.tcgeneric.wargame.listener

import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityInteractEvent

class RightClickEventListener
{
    @EventHandler
    fun onRightClickAtEntity(e:EntityInteractEvent) {
        // TODO: By using entity's location, find which tile user had right-clicked and call TileSelectEvent.
    }
}