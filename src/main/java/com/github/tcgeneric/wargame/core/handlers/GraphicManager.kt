package com.github.tcgeneric.wargame.core.handlers

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.exceptions.InvalidEntityException
import com.github.tcgeneric.wargame.map.Tile
import com.github.tcgeneric.wargame.map.TileType
import com.github.tcgeneric.wargame.teams.Team
import com.github.tcgeneric.wargame.transitions.*
import org.bukkit.*
import java.util.*

class GraphicManager {

    companion object {
        val transitionQueue: LinkedList<Transition> = LinkedList()
            get() = field

        fun apply(delaymilis:Long) {
            if(delaymilis < 0) throw IllegalArgumentException("Delay cannot be negative")
            while(transitionQueue.peek() != null) {
                Thread.sleep(delaymilis) // TODO: This code is only for experiment and must not be used in production state
                handle(transitionQueue.pop())
            }
        }

        fun drawTeamSight(team: Team) {
            for (t in team.sight.getTiles().values) {
                TODO("Use network packet")
            }
        }

        fun drawTileGlobally(world: World) {
            val tiles = Wargame.mapHandler.getMapTiles()
            val start = Wargame.mapHandler.getMapFrame().startingPoint
            for (t in tiles.values) {
                val adjusted = start.clone().add(t.coord)
                val loc = Location(world, adjusted.x.toDouble(), 64.0, adjusted.z.toDouble())
                loc.clone().subtract(0.0, 1.0, 0.0).block.type = Material.BEDROCK
                when (t.type) {
                    TileType.PLAINS -> loc.block.type = Material.GRASS
                    TileType.WATER -> loc.block.type = Material.WATER
                    TileType.HILLS -> loc.block.type = Material.COARSE_DIRT
                    TileType.MOUNTAIN -> loc.block.type = Material.COBBLESTONE
                }
            }
        }

        fun drawTileTo(tile: Tile, team: Team) {
            if(tile.entityAbove == null) return
            if(tile.type == TileType.MOUNTAIN) {
                for(p in team.players) {
                    TODO("Use network packet here")
                }
            }
            TODO("Further implementation required")
        }

        private fun handle(t: Transition) {
            when (t) {
                is UnitAttackTransition -> {
                    val t1 = Wargame.mapHandler.getTileByEntity(t.unit) ?: throw InvalidEntityException()
                    val t2 = Wargame.mapHandler.getTileByEntity(t.target) ?: throw InvalidEntityException()
                    val owner = TeamManager.getPlayerTeam(t.unit.controller) ?: throw InvalidEntityException()
                    FeedbackHandler.showTileParticleToTeam(owner, Particle.EXPLOSION_NORMAL, t1)
                    FeedbackHandler.showTileParticleToTeam(owner, Particle.EXPLOSION_NORMAL, t2)
                    FeedbackHandler.playSoundToTeam(owner, Sound.ENTITY_GENERIC_EXPLODE, t1)
                }
                is UnitDamageTransition -> {
                    if (t.damaged == null) return
                    val tile = Wargame.mapHandler.getTileByEntity(t.damaged) ?: throw InvalidEntityException()
                    val owner = TeamManager.getPlayerTeam(t.damaged.controller)
                            ?: throw InvalidEntityException()
                    drawTileTo(tile, owner)
                }
                is UnitMoveTransition -> {
                    val t1 = Wargame.mapHandler.getTileByEntity(t.unit)
                    // TODO: Find one of available paths, then move it along
                    TODO("Add unit move animations here by using packet")
                }
            }
        }
    }
}