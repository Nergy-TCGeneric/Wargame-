package com.github.tcgeneric.wargame.core

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.effects.Effect
import com.github.tcgeneric.wargame.events.TurnCalculationEndEvent
import com.github.tcgeneric.wargame.events.TurnCompletionEvent
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import java.util.*

class DisplayHandler(private val instance:Wargame) {

    private val effectQueue: LinkedList<Effect> = LinkedList()

    @EventHandler
    fun onTurnCalculationEndEvent(e:TurnCalculationEndEvent) {
        // TODO: Do some drawings
        Bukkit.getServer().pluginManager.callEvent(TurnCompletionEvent(e.turn + 1))
    }

    fun addReservedEffect(e:Effect) {
        effectQueue.add(e)
    }

    fun removeReservedEffect(e:Effect) {
        effectQueue.remove(e)
    }
}