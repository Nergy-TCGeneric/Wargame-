package com.github.tcgeneric.wargame.listener

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.events.EntityMoveEvent
import com.github.tcgeneric.wargame.events.EntitySelectEvent
import com.github.tcgeneric.wargame.events.TileSelectEvent
import com.github.tcgeneric.wargame.events.UnitInteractionEvent
import com.github.tcgeneric.wargame.util.Selection
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityInteractEvent

class RightClickEventListener(private val instance:Wargame)
{
    // Note: This listener doesn't validate ownership of entity
    @EventHandler
    fun onRightClickAtEntity(e:EntityInteractEvent) {
        // TODO: By using entity's location, find which tile user had right-clicked and call TileSelectEvent.
        val player = e.entity
        if(player !is Player) return
        val pData = instance.pDataHandler.dataMap[player.uniqueId]
        val coord = instance.mapHandler.locToCoord(e.block.location)
        val tile = instance.mapHandler.getTile(coord) ?: return
        if(!instance.mapHandler.isOnPlayerSight(tile, player)) return

        if(pData?.selected == null) {
            when(tile.entity) {
                null -> Bukkit.getPluginManager().callEvent(TileSelectEvent(player, tile))
                else -> {
                    pData?.selected = Selection(coord, tile.entity!!)
                    Bukkit.getPluginManager().callEvent(EntitySelectEvent(player, tile.entity!!))
                }
            }
        } else if(pData.selected!!.entity != null) {
            if(pData.selected!!.entity == tile.entity) return
            when(tile.entity) {
                null -> {
                    val tCoord = instance.mapHandler.findTileCoord(tile)!!
                    Bukkit.getPluginManager().callEvent(EntityMoveEvent(pData.selected!!.entity!!, tCoord))
                }
                // TODO: This code is stub; replace with actual value
                else -> Bukkit.getPluginManager().callEvent(UnitInteractionEvent())
            }
        }
    }
}