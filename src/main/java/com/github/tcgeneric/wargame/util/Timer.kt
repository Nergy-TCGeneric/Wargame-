package com.github.tcgeneric.wargame.util

class Timer(private var second:Int) {

    // Must be executed on asynchronous thread
    fun start(callback: (second:Int) -> Unit) {
        while(second > 0) {
            Thread.sleep(1000)
            onTimerElapse(callback)
            second -= 1
        }
    }

    private fun onTimerElapse(callback:(second:Int) -> Unit) {
        callback(second)
    }
}