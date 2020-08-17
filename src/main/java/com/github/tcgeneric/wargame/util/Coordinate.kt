package com.github.tcgeneric.wargame.util

import kotlin.math.abs

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

    fun manhattanDist(coord:Coordinate):Int {
        return abs(x - coord.x ) + abs(z - coord.z )
    }

    fun clone():Coordinate {
        return Coordinate(x, z)
    }

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

    override fun hashCode(): Int {
        return 31 * x * x + 7 * z
    }

    override fun toString(): String {
        return "({$x}, {$z})"
    }
}