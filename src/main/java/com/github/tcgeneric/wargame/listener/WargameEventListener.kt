package com.github.tcgeneric.wargame.listener

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.behaviors.*
import com.github.tcgeneric.wargame.entity.structures.Structure
import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.entity.units.UnitGroup
import com.github.tcgeneric.wargame.events.UnitMoveEvent
import com.github.tcgeneric.wargame.events.TileSelectEvent
import com.github.tcgeneric.wargame.events.UnitInteractionEvent
import org.bukkit.event.EventHandler

class WargameEventListener(private val instance:Wargame) {

    @EventHandler
    fun onUnitMove(e:UnitMoveEvent) {
        if(!instance.mapHandler.canUnitMoveTo(e.unit, e.tile.coord)) return
        val b = UnitMoveBehavior(e.unit, e.unit.controller, System.currentTimeMillis(), e.tile)
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
        if(e.unit.parentTeam == e.target.entity?.parentTeam) {
            b = when(val target = e.target.entity) {
                is Unit -> {
                    val uGroup = instance.unitHandler.createUnitGroup(target)
                    UnitMergeBehavior(e.unit, e.unit.controller, currentTime, uGroup)
                }
                is UnitGroup ->
                    UnitMergeBehavior(e.unit, e.unit.controller, currentTime, target)
                is Structure ->
                    UnitDwellBehavior(e.unit, e.unit.controller, currentTime, target)
                else -> TODO("Not implemented")
            }
        } else {
            val pData = instance.pDataHandler.dataMap[e.unit.controller.uniqueId]!!
            b = when(val target = e.target.entity) {
                null -> UnitDivideBehavior(e.unit, e.unit.controller, currentTime, e.target, pData.divideAmount)
                else -> UnitAttackBehavior(e.unit, e.unit.controller, currentTime, target)
            }
        }
        instance.behaviorHandler.queue(b)
        instance.pDataHandler.dataMap[e.unit.controller.uniqueId]?.queuedBehavior = b
    }
}