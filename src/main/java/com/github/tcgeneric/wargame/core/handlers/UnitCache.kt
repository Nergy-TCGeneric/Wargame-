package com.github.tcgeneric.wargame.core.handlers

import com.github.tcgeneric.wargame.entity.units.Unit
import com.github.tcgeneric.wargame.exceptions.InvalidEntityException

class UnitCache {
    companion object {
        private val cache: HashMap<String, Unit> = hashMapOf()

        fun getUnit(name: String): Unit? {
            return cache[name] ?: throw InvalidEntityException()
        }

        fun addUnit(name: String, unit: Unit) {
            cache[name] = unit
        }
    }
}