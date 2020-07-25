package com.github.tcgeneric.wargame.core

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.transitions.Transition
import com.github.tcgeneric.wargame.entity.Entity
import com.github.tcgeneric.wargame.events.TurnCalculationEndEvent
import com.github.tcgeneric.wargame.events.TurnCompletionEvent
import com.github.tcgeneric.wargame.teams.Team
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import java.util.*

class GraphicManager(private val instance:Wargame) {

    private val transitionQueue: LinkedList<Transition> = LinkedList()

    @EventHandler
    fun onTurnCalculationEndEvent(e:TurnCalculationEndEvent) {
        // TODO: Use an abstracted wargame's event class instead
        Bukkit.getServer().pluginManager.callEvent(TurnCompletionEvent(e.turn + 1))
    }

    fun addReservedEffect(e:Transition):GraphicManager {
        transitionQueue.add(e)
        return this
    }

    fun removeReservedEffect(e:Transition):GraphicManager {
        transitionQueue.remove(e)
        return this
    }

    fun drawMap(team:Team) {

    }

    fun drawGameFrame(controller: Player) {

    }

    fun drawEntityTo(entity: Entity, team:Team) {

    }
}