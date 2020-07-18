package com.github.tcgeneric.wargame.teams

import com.github.tcgeneric.wargame.Resource
import com.github.tcgeneric.wargame.ResourceType
import com.github.tcgeneric.wargame.entity.structures.ControlPoint
import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.map.MapData
import org.bukkit.entity.Player
import java.util.*
import kotlin.collections.ArrayList

class Team(val name:String) {
    val controlPoints:ArrayList<ControlPoint> = ArrayList()
    val players:ArrayList<Player> = ArrayList()
    val units: LinkedList<Unit> = LinkedList()
    lateinit var sight:MapData
    // TODO: Below maxAmount is stub
    val woods:Resource = Resource(ResourceType.WOOD, 0, 100)
    val foods:Resource = Resource(ResourceType.FOOD, 0, 100)
    val irons:Resource = Resource(ResourceType.IRON, 0, 100)
    var maximumUnitAmount:Int = 0
}