package com.github.tcgeneric.wargame.core

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.entity.Entity
import com.github.tcgeneric.wargame.events.TurnCalculationEndEvent
import com.github.tcgeneric.wargame.events.TurnCompletionEvent
import com.github.tcgeneric.wargame.events.UnitMoveEvent
import com.github.tcgeneric.wargame.map.Tile
import com.github.tcgeneric.wargame.teams.Team
import com.github.tcgeneric.wargame.transitions.*
import org.bukkit.Bukkit
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import java.util.*

class GraphicManager(private val instance:Wargame) {

    private val transitionQueue: LinkedList<Transition> = LinkedList()
    private val idGroupMgr = IdGroupManager()

    @EventHandler
    fun onTurnCalculationEndEvent(e:TurnCalculationEndEvent) {
        for(t in transitionQueue)
            handle(t)
        Bukkit.getServer().pluginManager.callEvent(TurnCompletionEvent(e.turn + 1))
    }

    fun addReservedEffect(e:Transition):GraphicManager {
        transitionQueue.add(e)
        return this
    }

    fun removeReservedEffect(e:Transition):GraphicManager {
        transitionQueue.remove(e)
        return this
    }

    fun drawMap(team:Team) {

    }

    fun drawGameFrame(controller: Player) {

    }

    fun drawTileTo(tile:Tile, team:Team) {
        if(tile.entity == null) return

    }

    fun showParticleTo(team:Team, particle:Particle, tile:Tile) {
        for(p in team.players) {
            val loc = instance.mapHandler.coordToLoc(p.world, tile.coord)
            p.spawnParticle(particle, loc, 4) // TODO: Stub value was used. replace it with actual value.
        }
    }

    fun playSoundTo(team:Team, sound:Sound, tile:Tile) {
        for(p in team.players) {
            val loc = instance.mapHandler.coordToLoc(p.world, tile.coord)
            p.playSound(loc, sound, 2f, 1f)
        }
    }

    private fun handle(t:Transition) {
        when(t) {
            is UnitAttackTransition -> {
                // TODO: Add some delay here
                val t1 = instance.mapHandler.getTileByEntity(t.unit) ?: throw IllegalStateException()
                val t2 = instance.mapHandler.getTileByEntity(t.target) ?: throw IllegalStateException()
                val owner = instance.teamManager.getPlayerTeam(t.unit.controller) ?: throw IllegalStateException()
                showParticleTo(owner, Particle.EXPLOSION_NORMAL, t1)
                showParticleTo(owner, Particle.EXPLOSION_NORMAL, t2)
                playSoundTo(owner, Sound.ENTITY_GENERIC_EXPLODE, t1)
            }
            is UnitDamageTransition -> {
                if(t.damage.damaged == null) return
                val tile = instance.mapHandler.getTileByEntity(t.damage.damaged) ?: throw IllegalStateException()
                val owner = instance.teamManager.getPlayerTeam(t.damage.damaged.controller) ?: throw IllegalStateException()
                drawTileTo(tile, owner)
            }
            is UnitDivideTransition -> {
                TODO("Not implemented")
            }
            is UnitMergeTransition -> {
                TODO("Not implemented")
            }
            is UnitMoveTransition -> {
                TODO("Add unit move animations here")
            }
        }
    }
}