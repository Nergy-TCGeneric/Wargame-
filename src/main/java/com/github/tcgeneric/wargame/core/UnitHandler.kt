package com.github.tcgeneric.wargame.core

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.entity.units.*
import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.map.Tile
import java.util.*
import kotlin.collections.HashMap

class UnitHandler(val instance:Wargame) {

    private val unitList:HashMap<UUID, Unit> = HashMap()
    private var id = 0

    fun divideUnit(unit: Unit, amount:Int):Pair<Unit, Unit> {
        // val newUnit = Unit(unit.combatRange, unit.combatStrength, unit.moral, amount, unit.parentTile)
        // TODO: This code is stub; replace it later
        return Pair(unit, unit)
    }

    fun divideUnit(unitGroup:UnitGroup, target: Unit):Pair<Unit, UnitGroup> {
        unitGroup.removeUnit(target)
        return Pair(target, unitGroup)
    }

    fun mergeUnit(first:Unit, second:Unit):UnitGroup {
        return UnitGroup(first, second)
    }

    fun moveUnit(unit:Unit, coord:Pair<Int, Int>) {
        instance.mapHandler.moveUnitTo(unit, coord)
    }

    fun createUnit(type:UnitType, amount:Int):Unit {
        return when(type) {
            UnitType.COMBAT -> CombatUnit(id++, 5, 3, 1, amount) // TODO: Stub code
            UnitType.WORKER -> WorkerUnit(id++, 5, 3, 1, amount) // TODO: Stub code
        }
    }

    /*
    fun calculateCausalities(first:Unit, second:Unit, firstTile:Tile, secondTile:Tile):Int {

    }
     */
}