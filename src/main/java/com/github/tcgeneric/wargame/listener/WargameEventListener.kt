package com.github.tcgeneric.wargame.listener

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.behaviors.*
import com.github.tcgeneric.wargame.entity.structures.Structure
import com.github.tcgeneric.wargame.events.UnitMoveReservingEvent
import com.github.tcgeneric.wargame.events.TileSelectEvent
import com.github.tcgeneric.wargame.events.UnitInteractReservingEvent
import com.github.tcgeneric.wargame.exceptions.InvalidEntityException
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import kotlin.IllegalStateException

class WargameEventListener(private val instance:Wargame):Listener {

    @EventHandler
    fun onUnitMoveReservation(e:UnitMoveReservingEvent) {
        if(!instance.mapHandler.canUnitMoveTo(e.unit, e.tile.coord)) return
        val b = UnitMoveBehavior(e.unit, System.currentTimeMillis(), e.tile)
        instance.behaviorHandler.queue(b)
        instance.pDataHandler.dataMap[e.unit.controller.uniqueId]!!.queuedBehavior = b
        val unitTile = instance.mapHandler.getTileByEntity(e.unit) ?: throw InvalidEntityException()
        instance.displayHandler.showDesignatingLine(e.unit.controller, Particle.REDSTONE, unitTile, e.tile)
        instance.displayHandler.playSoundToPlayer(e.unit.controller, Sound.BLOCK_ANVIL_PLACE, unitTile)
    }

    @EventHandler
    fun onTileSelection(e:TileSelectEvent) {
        val pData = instance.pDataHandler.dataMap[e.player.uniqueId]
        pData!!.selectedTile = e.tile
        instance.displayHandler.showTileParticleToPlayer(e.player, Particle.COMPOSTER, e.tile)
        instance.displayHandler.playSoundToPlayer(e.player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, e.tile)
        // TODO: If possible, make the block glow only for a player
    }

    @EventHandler
    fun onUnitInteractReservation(e:UnitInteractReservingEvent) {
        lateinit var b: UnitBehavior
        val currentTime = System.currentTimeMillis()
        val pData = instance.pDataHandler.dataMap[e.unit.controller.uniqueId]!!
        if(pData.isBuildingMode && e.target.entityAbove == null) {
            b = UnitBuildBehavior(e.unit, currentTime, pData.reservedStructure, e.target)
        } else {
            if(e.unit.parentTeam == e.target.entityAbove?.parentTeam) {
                b = when(val target = e.target.entityAbove) {
                    is Structure ->
                        UnitDwellBehavior(e.unit, currentTime, target)
                    else -> TODO("Not implemented yet")
                }
            } else {
                if(e.target.entityAbove != null)
                    b = UnitAttackBehavior(e.unit, currentTime, e.target.entityAbove!!)
            }
        }
        instance.behaviorHandler.queue(b)
        instance.pDataHandler.dataMap[e.unit.controller.uniqueId]?.queuedBehavior = b
    }
}