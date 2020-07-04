package com.github.tcgeneric.wargame.teams

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
    var woodAmount:Int = 0
    var maxWoodAmount:Int = 0
    var foodAmount:Int = 0
    var maxFoodAmount:Int = 0
    var ironAmount:Int = 0
    var maxIronAmount:Int = 0
    var maximumUnitAmount:Int = 0
}