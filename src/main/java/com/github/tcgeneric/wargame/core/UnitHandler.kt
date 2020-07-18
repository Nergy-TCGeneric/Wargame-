package com.github.tcgeneric.wargame.core

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.entity.units.*
import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.util.Coordinate
import java.lang.IllegalArgumentException

class UnitHandler(private val instance:Wargame) {

    private var id = 0
    private val unitFactory:UnitFactory = UnitFactory()

    // Assuming that every unit is on its owner(player)'s sight

    fun divideUnitTo(unit: Unit, amount:Int, coord:Coordinate):Boolean {
        if(instance.mapHandler.isEntityOnMap(unit) &&
                amount > 0 &&
                unit.amount > amount &&
                instance.mapHandler.canUnitMoveTo(unit, coord)) {
            val newUnit = createUnitFrom(unit, amount)
            unit.amount -= amount
            return instance.mapHandler.createEntityOn(newUnit, coord)
        }
        return false
    }

    fun divideUnitTo(unitGroup:UnitGroup, target: Unit, coord:Coordinate):Boolean {
        if(instance.mapHandler.isEntityOnMap(unitGroup) &&
                unitGroup.contains(target) &&
                instance.mapHandler.canUnitMoveTo(target, coord)) {
            unitGroup.removeUnit(target)
            return instance.mapHandler.createEntityOn(target, coord)
        }
        return false
    }

    fun mergeUnit(first:Unit, second:Unit):Boolean {
        return mergeUnit(first, createUnitGroup(second))
    }

    // At first, first unit moves to second unit's position. and then merge happens.
    fun mergeUnit(first:Unit, second:UnitGroup):Boolean {
        if(instance.mapHandler.isEntityOnMap(first) &&
                instance.mapHandler.isEntityOnMap(second) &&
                instance.mapHandler.isNearUnitMoveRange(first, second.first())) {
            val group = createUnitGroup(first, second)
            val pos = instance.mapHandler.getEntityCoordinate(second)
            if(pos != null) {
                instance.mapHandler.removeEntity(first)
                instance.mapHandler.removeEntity(second)
                return instance.mapHandler.createEntityOn(group, pos)
            }
        }
        return false
    }

    fun createUnit(type:UnitType, amount:Int):Unit {
        return unitFactory.createUnit(id++, type, amount)
    }

    fun createUnitFrom(unit:Unit, amount:Int):Unit {
        return unitFactory.createUnitFrom(id++, unit, amount)
    }

    fun createUnitGroup(first:Unit):UnitGroup {
        return UnitGroup(id++, first)
    }

    fun createUnitGroup(first:Unit, second:Unit):UnitGroup {
        return UnitGroup(id++, first, second)
    }

    fun createUnitGroup(first:Unit, second:UnitGroup):UnitGroup {
        return UnitGroup(id++, first, second)
    }

    /*
    fun calculateCausalities(first:Unit, second:Unit, firstTile:Tile, secondTile:Tile):Int {

    }
     */
}