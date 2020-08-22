package com.github.tcgeneric.wargame.core.handlers

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.events.TurnCompletionEvent
import com.github.tcgeneric.wargame.events.TurnStartEvent
import com.github.tcgeneric.wargame.events.TurnTimeEndEvent
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import org.bukkit.scheduler.BukkitRunnable

class TurnHandler(private val instance:Wargame) {

    private var turn:Int = 0

    @EventHandler
    fun onTurnCompletion(e:TurnCompletionEvent) {
        startTimer(20)
        turn = e.turn
        // TODO: If there's no problem, execute this
        Bukkit.getServer().pluginManager.callEvent(TurnStartEvent(turn))
    }

    fun getTurn():Int {
        return turn
    }

    fun startTimer(second:Int) {
        Bukkit.getServer().scheduler.runTaskTimer(instance, TurnTimer(second, {}) {
            Bukkit.getServer().pluginManager.callEvent(TurnTimeEndEvent(turn))
        }, 0, 20L)
    }

    private class TurnTimer(var second:Int, val callback: (time:Int) -> Unit, val endCallback:() -> Unit):BukkitRunnable() {
        override fun run() {
            if(--second <= 0) {
                this.cancel()
                endCallback
            }
            callback(second)
        }
    }
}