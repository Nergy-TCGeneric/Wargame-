package com.github.tcgeneric.wargame.core

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.core.data.DamageResult
import com.github.tcgeneric.wargame.entity.Entity
import com.github.tcgeneric.wargame.entity.structures.Structure
import com.github.tcgeneric.wargame.entity.units.*
import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.map.Tile
import com.github.tcgeneric.wargame.util.Coordinate
import kotlin.math.ceil

class UnitHandler(private val instance:Wargame) {

    private var id = 0
    private val unitFactory:UnitFactory = UnitFactory()

    // Assuming that every unit is on its owner(player)'s sight

    fun damage(unit:Unit, damage:Int):DamageResult {
        if(unit.amount - damage < 0)
            return DamageResult(unit, unit.amount, 0)
        return DamageResult(unit, unit.amount, unit.amount - damage)
    }

    /*
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
        if(!instance.mapHandler.isEntityOnMap(first) || !instance.mapHandler.isEntityOnMap(second)) return false
        val fCoord = instance.mapHandler.getEntityCoordinate(first) ?: return false
        val sCoord = instance.mapHandler.getEntityCoordinate(second) ?: return false
        if(fCoord.manhattanDist(sCoord) < first.moveRange) return false
        instance.mapHandler.removeEntity(first)
        instance.mapHandler.removeEntity(second)
        return instance.mapHandler.createEntityOn(createUnitGroup(first, second), sCoord)
    }
     */

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

    // TODO: This formula is stub. change it later(it doesn't consider tile's protection rate).
    fun getInflictingDamage(attacker:Unit, defender:Entity?):DamageResult {
        if(defender == null) return DamageResult(defender, 0, 0)
        return when(defender) {
            is Unit -> DamageResult(defender, defender.amount, attacker.amount * attacker.combatStrength)
            is Structure -> DamageResult(defender, defender.durability, attacker.amount * attacker.combatStrength)
            else -> TODO("Not implemented yet")
        }
    }
}