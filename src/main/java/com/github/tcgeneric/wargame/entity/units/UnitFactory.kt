package com.github.tcgeneric.wargame.entity.units

import java.lang.IllegalArgumentException

class UnitFactory {
    fun createUnitFrom(id:Int, unit:Unit, amount:Int):Unit {
        when(unit) {
            is WorkerUnit -> return WorkerUnit(id, unit.combatRange, unit.combatStrength, unit.moral, amount, unit.sight, unit.moveRange)
            is CombatUnit -> return CombatUnit(id, unit.combatRange, unit.combatStrength, unit.moral, amount, unit.sight, unit.moveRange)
        }
        throw IllegalArgumentException("Wrong unit is given.")
    }

    fun createUnit(id:Int, type:UnitType, amount:Int):Unit {
        return when(type) {
            UnitType.COMBAT -> CombatUnit(id, 1, 3, 2, amount, 1, 1) // TODO: Used stub value; replace it later
            UnitType.WORKER -> WorkerUnit(id, 1,  3, 2, amount, 1, 1) // TODO: Used stub value; replace it later
        }
    }
}