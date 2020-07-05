package com.github.tcgeneric.wargame.entity

import com.github.tcgeneric.wargame.map.Tile
import com.github.tcgeneric.wargame.teams.Team

abstract class Entity(val id:Int, var parentTile: Tile? = null) {
    lateinit var parentTeam: Team
}