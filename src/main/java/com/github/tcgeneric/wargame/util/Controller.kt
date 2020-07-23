package com.github.tcgeneric.wargame.util

import java.util.*

data class Controller<T>(val uuid:UUID, val actualObject: T)