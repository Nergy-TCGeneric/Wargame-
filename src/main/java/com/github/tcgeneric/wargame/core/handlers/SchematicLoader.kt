package com.github.tcgeneric.wargame.core.handlers

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.core.data.Schematic
import java.io.BufferedReader
import java.io.File
import java.io.FileReader

class SchematicLoader
{
    companion object {
        private val cache = HashMap<String, Schematic>()

        fun load(name: String): Schematic? {
            if (cache.containsKey(name))
                return cache[name]
            val file = File(Wargame.dataFolder, "schematics/${name}.schem")
            if(!file.exists()) return null
            val reader = BufferedReader(FileReader(file))
            TODO("Read file here")
        }
    }
}