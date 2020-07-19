package com.github.tcgeneric.wargame.entity.units

import com.github.tcgeneric.wargame.entity.Entity
import com.github.tcgeneric.wargame.exceptions.UnitGroupAddFailureException
import java.util.*

class UnitGroup(id:Int):Entity(id) {

    private val units:LinkedList<Unit> = LinkedList()

    constructor(id:Int, first:Unit) {
        units.add(first)
    }

    constructor(id:Int, first:Unit, second:Unit) {
        units.add(first)
        units.add(second)
    }

    constructor(id:Int, first:Unit, second:UnitGroup) {
        second.units.add(first)
        second.units.forEach {
            if(!addUnit(it)) return
        }
    }

    fun addUnit(unit: Unit):Boolean {
        if(units.size >= 3) // TODO: Stub value, change it later.
            throw UnitGroupAddFailureException("Unit group is full!")
        return units.add(unit)
    }

    fun removeUnit(unit: Unit):Boolean {
        return units.remove(unit)
    }

    fun getUnits():Array<Unit> {
        return units.toTypedArray()
    }

    fun getUnit(idx:Int):Unit {
        return units[idx]
    }

    fun contains(unit:Unit):Boolean {
        return units.contains(unit)
    }

    fun first():Unit {
        return units.first
    }

    fun getSlowestUnit():Unit {
        var min:Pair<Unit, Int> = Pair(units.first, units.first.moveRange)
        for(u in units) {
            if(u.moveRange <= min.second)
                min = Pair(u, u.moveRange)
        }
        return min.first
    }

    fun size():Int {
        return units.size
    }
}