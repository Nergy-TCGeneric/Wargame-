package com.github.tcgeneric.wargame.listener

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.behaviors.*
import com.github.tcgeneric.wargame.entity.structures.Structure
import com.github.tcgeneric.wargame.events.UnitMoveEvent
import com.github.tcgeneric.wargame.events.TileSelectEvent
import com.github.tcgeneric.wargame.events.UnitInteractionEvent
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import java.lang.IllegalStateException

class WargameEventListener(private val instance:Wargame):Listener {

    @EventHandler
    fun onUnitMove(e:UnitMoveEvent) {
        if(!instance.mapHandler.canUnitMoveTo(e.unit, e.tile.coord)) return
        val b = UnitMoveBehavior(e.unit, System.currentTimeMillis(), e.tile)
        instance.behaviorHandler.queue(b)
        instance.pDataHandler.dataMap[e.unit.controller.uniqueId]!!.queuedBehavior = b
    }

    @EventHandler
    fun onTileSelection(e:TileSelectEvent) {
        val pData = instance.pDataHandler.dataMap[e.player.uniqueId]
        pData!!.selectedTile = e.tile
        // TODO: Do something here
    }

    @EventHandler
    fun onUnitInteraction(e:UnitInteractionEvent) {
        lateinit var b: UnitBehavior
        val currentTime = System.currentTimeMillis()
        val pData = instance.pDataHandler.dataMap[e.unit.controller.uniqueId] ?: throw IllegalStateException("Invalid PlayerData found.")
        if(pData.isBuildingMode && e.target.entity == null) {
            b = UnitBuildBehavior(e.unit, currentTime, pData.reservedStructure, e.target)
        } else {
            if(e.unit.parentTeam == e.target.entity?.parentTeam) {
                b = when(val target = e.target.entity) {
                    is Structure ->
                        UnitDwellBehavior(e.unit, currentTime, target)
                    else -> TODO("Not implemented yet")
                }
            } else {
                if(e.target.entity != null)
                    b = UnitAttackBehavior(e.unit, currentTime, e.target.entity!!)
            }
        }
        instance.behaviorHandler.queue(b)
        instance.pDataHandler.dataMap[e.unit.controller.uniqueId]?.queuedBehavior = b
    }
}