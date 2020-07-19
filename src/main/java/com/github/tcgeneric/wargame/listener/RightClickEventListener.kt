package com.github.tcgeneric.wargame.listener

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.events.*
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.entity.EntityInteractEvent

class RightClickEventListener(private val instance:Wargame)
{
    // Note: This listener doesn't validate ownership of entity
    @EventHandler
    fun onRightClickAtEntity(e:EntityInteractEvent) {
        val player = e.entity
        if(player !is Player) return
        val pData = instance.pDataHandler.dataMap[player.uniqueId]
        val tile = instance.mapHandler.getTile(instance.mapHandler.locToCoord(e.block.location)) ?: return
        if(!instance.mapHandler.isOnPlayerSight(tile, player)) return

        // TODO: Explicit rule is required
        if(pData?.selectedTile == null) {
            when(tile.entity) {
                null -> Bukkit.getPluginManager().callEvent(TileSelectEvent(player, tile))
                else -> Bukkit.getPluginManager().callEvent(EntitySelectEvent(player, tile.entity!!))
            }
        } else {
            val pEntity = pData.selectedTile!!.entity
            if(pEntity == tile.entity && pEntity != null) return
            when(tile.entity) {
                null -> {
                    if(pEntity is Unit)
                        if(pData.isDivideMode)
                            Bukkit.getPluginManager().callEvent(UnitDivideEvent(pEntity, tile, pData.divideAmount))
                        else
                            Bukkit.getPluginManager().callEvent(UnitMoveEvent(pEntity, tile))
                }
                else -> {
                    if(pEntity is Unit)
                        Bukkit.getPluginManager().callEvent(UnitInteractionEvent(pEntity, tile.entity!!.parentTile!!))
                }
            }
        }
    }
}