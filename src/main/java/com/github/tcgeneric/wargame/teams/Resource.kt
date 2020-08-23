package com.github.tcgeneric.wargame.teams

import kotlin.math.max

data class Resource(val type: ResourceType, private var amount:Int, private var maxAmount:Int) {
    fun isAffordable(cost:Int):Boolean {
        return amount >= cost
    }
    fun subtract(cost:Int) {
        amount -= cost
        if(amount < 0)
            throw IllegalStateException("Resource amount reached below 0. Did you do something wrong?")
    }
    fun add(cost:Int) {
        amount = max(cost + amount, maxAmount)
    }
}