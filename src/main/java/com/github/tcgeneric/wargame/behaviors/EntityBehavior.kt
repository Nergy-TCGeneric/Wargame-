package com.github.tcgeneric.wargame.behaviors

import com.github.tcgeneric.wargame.entity.Entity

abstract class EntityBehavior {
    abstract val target:Entity
    abstract val type:BehaviorType
}