package com.github.tcgeneric.wargame.util

data class Coordinate(var x:Int, var z:Int) {
    fun subtract(vec:Coordinate):Coordinate {
        this.x -= vec.x
        this.z -= vec.z
        return this
    }

    fun add(vec:Coordinate):Coordinate {
        this.x += vec.x
        this.z += vec.z
        return this
    }

    fun add(x:Int, z:Int):Coordinate {
        return add(x, z)
    }
}