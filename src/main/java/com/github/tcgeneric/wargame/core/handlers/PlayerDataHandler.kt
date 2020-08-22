package com.github.tcgeneric.wargame.core.handlers

import com.github.tcgeneric.wargame.core.data.PlayerData
import java.util.*
import kotlin.collections.HashMap

class PlayerDataHandler {
    companion object {
        val dataMap = HashMap<UUID, PlayerData>()
    }
}