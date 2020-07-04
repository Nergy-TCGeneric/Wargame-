package com.github.tcgeneric.wargame.entity.units

import java.lang.IllegalArgumentException

class UnitFactory {
    fun unitFromExistingUnit(unit:Unit, amount:Int):Unit {
        when(unit) {
            is WorkerUnit -> return WorkerUnit(unit.combatRange, unit.combatStrength, amount, unit.moral)
            is CombatUnit -> return CombatUnit(unit.combatRange, unit.combatStrength, amount, unit.moral)
        }
        throw IllegalArgumentException("Wrong unit is given.")
    }

    fun createUnit(type:UnitType, amount:Int):Unit {
        return when(type) {
            UnitType.COMBAT -> CombatUnit(5, 1, amount, 3) // TODO: Used stub value; replace it later
            UnitType.WORKER -> WorkerUnit(5, 1, amount, 3) // TODO: Used stub value; replace it later
        }
    }
}