package com.github.tcgeneric.wargame.core

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.entity.units.UnitGroup
import java.util.*
import kotlin.collections.HashMap

class UnitHandler(val instance:Wargame) {

    val unitList:HashMap<UUID, Unit> = HashMap()

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

    fun moveUnit(unit:Unit, xPos:Int, yPos:Int) {

    }
}