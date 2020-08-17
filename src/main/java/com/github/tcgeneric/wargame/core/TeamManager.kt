package com.github.tcgeneric.wargame.core

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.teams.Team
import org.bukkit.entity.Player

class TeamManager(private val instance:Wargame) {

    private val teamList:ArrayList<Team> = ArrayList()

    fun getPlayerTeam(player: Player):Team? {
        for(t in teamList) {
            if(t.players.contains(player))
                return t
        }
        return null
    }

    fun addPlayer(player: Player):Boolean {
        val playerCount:HashMap<Team, Int> = hashMapOf()
        for(team in teamList)
            playerCount[team] = team.players.size
        TODO("Get team that has minimum player among the teams and make the player join that team")
    }

    fun removePlayerFrom(player:Player, team:Team):Boolean {
        return team.players.remove(player)
    }
}