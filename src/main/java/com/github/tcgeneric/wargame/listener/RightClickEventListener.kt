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
        val targetTile = Wargame.mapHandler.getTile(Wargame.mapHandler.locToCoord(e.block.location))

        if(targetTile == null && pData.queuedBehavior != null) {
            Bukkit.getPluginManager().callEvent(BehaviorCancellationEvent(pData.queuedBehavior!!, pData.player))
            return
        }
        
        if(!Wargame.mapHandler.isOnPlayerSight(targetTile!!, player)) return
        if(pData.selectedTile == null) {
            Bukkit.getPluginManager().callEvent(TileSelectEvent(player, targetTile))
        } else {
            val selectedEntity = pData.selectedTile?.entityAbove ?: return
            if(selectedEntity == targetTile.entityAbove || selectedEntity.controller != player) return
            if(selectedEntity is Unit) {
                if(targetTile.entityAbove == null)
                    Bukkit.getPluginManager().callEvent(UnitMoveReservingEvent(selectedEntity, targetTile))
                else
                    Bukkit.getPluginManager().callEvent(UnitInteractReservingEvent(selectedEntity, targetTile))
            }
        }
    }
}