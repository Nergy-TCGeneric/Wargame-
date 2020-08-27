package com.github.tcgeneric.wargame.core.handlers

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.map.Tile
import com.github.tcgeneric.wargame.teams.Team
import org.bukkit.Particle
import org.bukkit.Sound
import org.bukkit.entity.Player

class FeedbackHandler {
    companion object {

        fun showMessageToTeam(team:Team, title:String, message:String) {
            for (p in team.players)
                p.sendTitle(title, message, 10, 20, 10)
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
            val L1 = Wargame.mapHandler.coordToLoc(player.world, start.coord)
            val L2 = Wargame.mapHandler.coordToLoc(player.world, dest.coord)
            val diff: Pair<Double, Double> = Pair((L2.x - L1.x) / 10, (L2.z - L1.z) / 10) // TODO: used stub sample amount
            for (i in 0..10)
                player.spawnParticle(particle, L1.add(diff.first, 0.0, diff.second), 4)
        }

        fun playSoundToTeam(team: Team, sound: Sound, tile: Tile) {
            for (p in team.players)
                playSoundToPlayer(p, sound, tile)
        }

        fun playSoundToPlayer(player: Player, sound: Sound, tile: Tile) {
            val loc = Wargame.mapHandler.coordToLoc(player.world, tile.coord)
            player.playSound(loc, sound, 2f, 1f)
        }
    }
}