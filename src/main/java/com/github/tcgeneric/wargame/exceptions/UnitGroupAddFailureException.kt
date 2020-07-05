package com.github.tcgeneric.wargame.exceptions

class UnitGroupAddFailureException(private val msg:String):Exception() {
    override val message: String?
        get() = msg
}