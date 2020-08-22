package com.github.tcgeneric.wargame.events

import com.github.tcgeneric.wargame.behaviors.UnitBehavior
import org.bukkit.entity.Player
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

data class BehaviorCancellationEvent(val b:UnitBehavior, val p:Player):Event() {

    private val handler = HandlerList()
    override fun getHandlers(): HandlerList {
        return handler
    }
}