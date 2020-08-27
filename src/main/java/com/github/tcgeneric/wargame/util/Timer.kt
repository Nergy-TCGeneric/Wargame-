package com.github.tcgeneric.wargame.util

class Timer(private var second:Int) {

    fun start(callback: (second:Int) -> Unit, endCallback: () -> Unit) {
        while(second > 0) {
            Thread.sleep(1000)
            onTimerElapse(callback)
            second -= 1
        }
        onTimerEnd(endCallback)
    }

    private fun onTimerElapse(callback:(second:Int) -> Unit) {
        callback(second)
    }

    private fun onTimerEnd(endCallback:() -> Unit) {
        endCallback()
    }
}