package com.github.tcgeneric.wargame.core

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.teams.Team
import org.bukkit.entity.Player

class TeamManager(val instance:Wargame) {

    private val teamList:ArrayList<Team> = ArrayList()

    fun getPlayerTeam(player: Player):Team? {
        for(t in teamList) {
            if(t.players.contains(player))
                return t
        }
        return null
    }

    /*
    fun addPlayerTo(player: Player, team:Team):Boolean {

    }

    fun removePlayerFrom(player:Player, team:Team):Boolean {

    }
     */
}