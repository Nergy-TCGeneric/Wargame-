package com.github.tcgeneric.wargame.listener

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.behaviors.*
import com.github.tcgeneric.wargame.core.handlers.*
import com.github.tcgeneric.wargame.events.*
import com.github.tcgeneric.wargame.exceptions.InvalidEntityException
import com.github.tcgeneric.wargame.util.Timer
import org.bukkit.Bukkit
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener

class WargameEventListener(private val instance:Wargame):Listener {

    @EventHandler
    fun onUnitMoveReservation(e:UnitMoveReservingEvent) {
        if(!instance.mapHandler.canUnitMoveTo(e.unit, e.tile.coord)) return
        val b = UnitMoveBehavior(e.unit, System.currentTimeMillis(), e.tile)
        BehaviorHandler.queue(b)
        PlayerDataHandler.dataMap[e.unit.controller.uniqueId]!!.queuedBehavior = b
        val unitTile = instance.mapHandler.getTileByEntity(e.unit) ?: throw InvalidEntityException()
        FeedbackHandler.showDesignatingLine(e.unit.controller, Particle.REDSTONE, unitTile, e.tile)
        FeedbackHandler.playSoundToPlayer(e.unit.controller, Sound.BLOCK_ANVIL_PLACE, unitTile)
    }

    @EventHandler
    fun onTileSelection(e:TileSelectEvent) {
        val pData = PlayerDataHandler.dataMap[e.player.uniqueId]
        pData!!.selectedTile = e.tile
        FeedbackHandler.showTileParticleToPlayer(e.player, Particle.COMPOSTER, e.tile)
        FeedbackHandler.playSoundToPlayer(e.player, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, e.tile)
        // TODO: If possible, make the block glow only for a player
    }

    @EventHandler
    fun onUnitInteractReservation(e:UnitInteractReservingEvent) {
        lateinit var b: UnitBehavior
        val currentTime = System.currentTimeMillis()
        val pData = PlayerDataHandler.dataMap[e.unit.controller.uniqueId] ?: throw IllegalArgumentException("Invalid player is given!")
        if(e.target.entityAbove == null) {
            if(pData.isBuildingMode && pData.reservedStructure != null)
                b = UnitBuildBehavior(e.unit, currentTime, pData.reservedStructure!!, e.target)
        } else {
            if(e.unit.parentTeam != e.target.entityAbove?.parentTeam)
                b = UnitAttackBehavior(e.unit, currentTime, e.target.entityAbove!!)
        }
        BehaviorHandler.queue(b)
        PlayerDataHandler.dataMap[e.unit.controller.uniqueId]?.queuedBehavior = b
    }

    @EventHandler
    fun onEntityDamagedEvent(e:EntityDamagedEvent) {

    }

    @EventHandler
    fun onTurnStartEvent(e: TurnStartEvent) {
        val timer = Timer(e.delaySec)
        Wargame.server.scheduler.runTaskAsynchronously(Wargame) { ->
            timer.start({second ->
                if(second > 10) return@start
                for(t in TeamManager.getTeams())
                    FeedbackHandler.showMessageToTeam(t, "New turn", "starts in $second") },
                    {
                // TODO: Add timer end callback if needed
            })
        }
    }

    @EventHandler
    fun onTurnTimeEndEvent(e: TurnTimeEndEvent) {
        BehaviorHandler.apply()
        Bukkit.getServer().pluginManager.callEvent(TurnCalculationEndEvent())
    }

    @EventHandler
    fun onTurnCalculationEndEvent(e: TurnCalculationEndEvent) {
        GraphicManager.apply(1000)
        Wargame.turn += 1
        Bukkit.getServer().pluginManager.callEvent(TurnCompletionEvent())
    }

    @EventHandler
    fun onBaseCapture(e:BaseCaptureEvent) {
        e.captured.healthPoint = (e.captured.maxHealthPoint * 0.33).toInt()
    }

    @EventHandler
    fun onBehaviorCancellation(e:BehaviorCancellationEvent) {
        BehaviorHandler.cancel(e.behavior)
    }
}