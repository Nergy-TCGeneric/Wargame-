package com.github.tcgeneric.wargame.core

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.events.TurnCalculationEndEvent
import com.github.tcgeneric.wargame.events.TurnCompletionEvent
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler

class DisplayHandler(val instance:Wargame) {
    @EventHandler
    fun onTurnCalculationEndEvent(e:TurnCalculationEndEvent) {
        // TODO: Do some drawings
        Bukkit.getServer().pluginManager.callEvent(TurnCompletionEvent(e.turn + 1))
    }
}