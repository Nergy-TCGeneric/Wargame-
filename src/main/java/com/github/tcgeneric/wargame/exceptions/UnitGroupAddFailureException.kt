package com.github.tcgeneric.wargame.exceptions

@Deprecated("Not used")
class UnitGroupAddFailureException(private val msg:String):Exception() {
    override val message: String?
        get() = msg
}