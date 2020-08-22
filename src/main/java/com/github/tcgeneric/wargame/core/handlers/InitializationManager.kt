package com.github.tcgeneric.wargame.core.handlers

import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.teams.Team

class InitializationManager {
    companion object {
        fun loadUnits() {
            TODO("Load unit configurations from units.yml and then store them to UnitCache class")
        }

        fun loadTeams():List<Team> {
            TODO("Load default team names from team_name.txt and then create instances")
        }
    }
}