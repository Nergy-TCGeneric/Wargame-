package com.github.tcgeneric.wargame.core

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.effects.Effect
import com.github.tcgeneric.wargame.events.TurnCalculationEndEvent
import com.github.tcgeneric.wargame.events.TurnCompletionEvent
import com.github.tcgeneric.wargame.teams.Team
import com.github.tcgeneric.wargame.util.Controller
import org.bukkit.Bukkit
import org.bukkit.event.EventHandler
import java.util.*

abstract class GraphicManager(private val instance:Wargame) {

    private val effectQueue: LinkedList<Effect> = LinkedList()

    @EventHandler
    fun onTurnCalculationEndEvent(e:TurnCalculationEndEvent) {
        // TODO: Use an abstracted wargame's event class instead
        Bukkit.getServer().pluginManager.callEvent(TurnCompletionEvent(e.turn + 1))
    }

    fun addReservedEffect(e:Effect):GraphicManager {
        effectQueue.add(e)
        return this
    }

    fun removeReservedEffect(e:Effect):GraphicManager {
        effectQueue.remove(e)
        return this
    }

    abstract fun drawMap(team:Team)
    abstract fun drawGameFrame(controller: Controller<*>)
}