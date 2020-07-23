package com.github.tcgeneric.wargame.entity

import com.github.tcgeneric.wargame.map.Tile
import com.github.tcgeneric.wargame.teams.Team
import com.github.tcgeneric.wargame.util.Controller
import org.bukkit.entity.Player

abstract class Entity(val id:Int, var parentTile: Tile? = null) {
    lateinit var parentTeam: Team
    lateinit var controller: Controller<*>
    var willRemoved:Boolean = false
}