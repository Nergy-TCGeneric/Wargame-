package com.github.tcgeneric.wargame.util

// TODO: This vector should not have range value. rather it should be handled by other manager classes.
data class RangedVector(val x:Int, val y:Int, val z:Int, val range:Int) {
    fun subtract(vec:RangedVector):RangedVector {
        return RangedVector(x - vec.x, y - vec.y, z - vec.z, range)
    }

    fun add(vec:RangedVector):RangedVector {
        return RangedVector(x + vec.x, y + vec.y, z + vec.z, range)
    }

    fun add(x:Int, y:Int, z:Int):RangedVector {
        return add(RangedVector(x, y, z, this.range))
    }
}