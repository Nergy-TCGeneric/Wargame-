package com.github.tcgeneric.wargame.events

import com.github.tcgeneric.wargame.entity.Entity
import com.github.tcgeneric.wargame.entity.structures.ControlPoint
import com.github.tcgeneric.wargame.entity.units.Unit
import org.bukkit.event.Event
import org.bukkit.event.HandlerList

class BaseCaptureEvent(val captured:ControlPoint, val attacker:Unit):Event() {
    private val handler = HandlerList()
    override fun getHandlers(): HandlerList {
        return handler
    }
}