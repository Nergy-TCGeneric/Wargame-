package com.github.tcgeneric.wargame.core.handlers

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.core.data.Schematic

class SchematicLoader
{
    companion object {
        private val cache = HashMap<String, Schematic>()

        fun load(name: String): Schematic? {
            if (cache.containsKey(name))
                return cache[name]
            TODO("Read file here")
        }
    }
}