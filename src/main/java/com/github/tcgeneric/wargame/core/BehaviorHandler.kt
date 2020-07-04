package com.github.tcgeneric.wargame.core

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.behaviors.BehaviorType
import com.github.tcgeneric.wargame.behaviors.EntityBehavior

class BehaviorHandler(private val instance:Wargame) {
    fun handle(entityBehavior: EntityBehavior) {
        when(entityBehavior.type) {
            BehaviorType.ATTACK -> {
                entityBehavior.target
            }
            BehaviorType.DIVIDE -> { // TODO: Need an explicit class type
                // instance.unitHandler.divideUnit(entityBehavior.target)
            }
            BehaviorType.MERGE -> { // TODO: Need an explicit class type
                // instance.unitHandler.mergeUnit(entityBehavior.target,)
            }
            BehaviorType.MOVE -> {

            }
            BehaviorType.PRODUCE -> {

            }
        }
    }
}