package com.github.tcgeneric.wargame.listener

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.core.handlers.BehaviorHandler
import com.github.tcgeneric.wargame.core.handlers.PlayerDataHandler
import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.events.*
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityInteractEvent

class RightClickEventListener:Listener
{
    @EventHandler
    fun onRightClickAtEntity(e:EntityInteractEvent) {
        val player = e.entity
        if(player !is Player) return
        val pData = PlayerDataHandler.dataMap[player.uniqueId] ?: return
        val tile = Wargame.mapHandler.getTile(Wargame.mapHandler.locToCoord(e.block.location)) ?: return

        if(!Wargame.mapHandler.isOnPlayerSight(tile, player)) return
        // TODO: Needed to clarify below code
        if(pData.selectedTile == null) {
            if(tile.entityAbove == null)
                Bukkit.getPluginManager().callEvent(TileSelectEvent(player, tile))
        } else {
            val pEntity = pData.selectedTile!!.entityAbove
            if(pEntity == tile.entityAbove && pEntity != null) return
            if(pData.queuedBehavior != null) {
                BehaviorHandler.cancel(pData.queuedBehavior!!)
                Bukkit.getPluginManager().callEvent(BehaviorCancellationEvent(pData.queuedBehavior!!, pData.player))
                return
            }
            when(tile.entityAbove) {
                null -> {
                    if(pEntity is Unit)
                        Bukkit.getPluginManager().callEvent(UnitMoveReservingEvent(pEntity, tile))
                }
                else -> {
                    if(pEntity is Unit)
                        Bukkit.getPluginManager().callEvent(UnitInteractReservingEvent(pEntity, tile.entityAbove!!.parentTile!!))
                }
            }
        }
    }
}