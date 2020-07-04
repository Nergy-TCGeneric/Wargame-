package com.github.tcgeneric.wargame.entity.units

import com.github.tcgeneric.wargame.core.Constants
import java.util.*

class UnitGroup {

    private val units:LinkedList<Unit> = LinkedList()

    constructor(first:Unit) {
        units.add(first)
    }

    constructor(first:Unit, second:Unit) {
        units.add(first)
        units.add(second)
    }

    constructor(first:UnitGroup, second:Unit) {
        first.units.add(second)
        first.units.forEach {
            if(!addUnit(it)) return
        }
    }

    fun addUnit(unit: Unit):Boolean {
        if(units.size >= Constants.MAXIMUM_UNIT_GROUP_SIZE)
            return false
        return units.add(unit)
    }

    fun removeUnit(unit: Unit):Boolean {
        return units.remove(unit)
    }

    fun getUnits():Array<Unit> {
        return units.toTypedArray()
    }
}