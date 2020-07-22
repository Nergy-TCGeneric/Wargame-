package com.github.tcgeneric.wargame.core.data

import com.github.tcgeneric.wargame.entity.Entity
import com.github.tcgeneric.wargame.entity.units.Unit

data class DamageResult(val damaged:Entity?, val before:Int, val after:Int)