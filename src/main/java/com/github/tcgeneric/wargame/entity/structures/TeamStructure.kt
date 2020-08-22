package com.github.tcgeneric.wargame.entity.structures

import com.github.tcgeneric.wargame.map.Tile
import com.github.tcgeneric.wargame.teams.Team

abstract class TeamStructure(id:Int, tile: Tile, val owningTeam: Team):Structure(id, tile, false)