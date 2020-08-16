package com.github.tcgeneric.wargame.core

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.core.data.Schematic

class SchematicLoader(private val instance:Wargame)
{
    private val cache = HashMap<String, Schematic>()

    fun load(name:String):Schematic? {
        if(cache.containsKey(name))
            return cache[name]
        TODO("Read file here")
    }
}