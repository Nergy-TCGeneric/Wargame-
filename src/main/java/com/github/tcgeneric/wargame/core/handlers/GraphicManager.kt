package com.github.tcgeneric.wargame.core.handlers

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.events.TurnCalculationEndEvent
import com.github.tcgeneric.wargame.events.TurnCompletionEvent
import com.github.tcgeneric.wargame.exceptions.InvalidEntityException
import com.github.tcgeneric.wargame.map.Tile
import com.github.tcgeneric.wargame.map.TileType
import com.github.tcgeneric.wargame.teams.Team
import com.github.tcgeneric.wargame.transitions.*
import org.bukkit.*
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import java.util.*

class GraphicManager {

    companion object {
        val transitionQueue: LinkedList<Transition> = LinkedList()
            get() = field

        @EventHandler
        fun onTurnCalculationEndEvent(e: TurnCalculationEndEvent) {
            for (t in transitionQueue) {
                handle(t)
                // TODO: Delay it for a moment if needed
            }
            Bukkit.getServer().pluginManager.callEvent(TurnCompletionEvent(e.turn + 1))
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
            if (tile.entityAbove == null) return
            TODO("Further implementation required")
        }

        fun showTileParticleToTeam(team: Team, particle: Particle, tile: Tile) {
            for (p in team.players)
                showTileParticleToPlayer(p, particle, tile)
        }

        fun showTileParticleToPlayer(player: Player, particle: Particle, tile: Tile) {
            val loc = Wargame.mapHandler.coordToLoc(player.world, tile.coord)
            player.spawnParticle(particle, loc, 4) // TODO: Stub value was used. replace it with actual value.
        }

        fun showDesignatingLine(player: Player, particle: Particle, start: Tile, dest: Tile) {
            TODO("Implementation required")
        }

        fun playSoundToTeam(team: Team, sound: Sound, tile: Tile) {
            for (p in team.players)
                playSoundToPlayer(p, sound, tile)
        }

        fun playSoundToPlayer(player: Player, sound: Sound, tile: Tile) {
            val loc = Wargame.mapHandler.coordToLoc(player.world, tile.coord)
            player.playSound(loc, sound, 2f, 1f)
        }

        private fun handle(t: Transition) {
            when (t) {
                is UnitAttackTransition -> {
                    val t1 = Wargame.mapHandler.getTileByEntity(t.unit) ?: throw InvalidEntityException()
                    val t2 = Wargame.mapHandler.getTileByEntity(t.target) ?: throw InvalidEntityException()
                    val owner = TeamManager.getPlayerTeam(t.unit.controller) ?: throw InvalidEntityException()
                    showTileParticleToTeam(owner, Particle.EXPLOSION_NORMAL, t1)
                    showTileParticleToTeam(owner, Particle.EXPLOSION_NORMAL, t2)
                    playSoundToTeam(owner, Sound.ENTITY_GENERIC_EXPLODE, t1)
                }
                is UnitDamageTransition -> {
                    if (t.damage.damaged == null) return
                    val tile = Wargame.mapHandler.getTileByEntity(t.damage.damaged) ?: throw InvalidEntityException()
                    val owner = TeamManager.getPlayerTeam(t.damage.damaged.controller)
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