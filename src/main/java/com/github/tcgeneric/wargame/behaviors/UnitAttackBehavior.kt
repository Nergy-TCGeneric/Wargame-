package com.github.tcgeneric.wargame.behaviors

import com.github.tcgeneric.wargame.entity.Entity
import com.github.tcgeneric.wargame.entity.units.Unit

class UnitAttackBehavior(actor: Unit, time: Long, val target: Entity):UnitBehavior(actor, time) {
    override fun toString(): String {
        return "공격 명령"
    }
}