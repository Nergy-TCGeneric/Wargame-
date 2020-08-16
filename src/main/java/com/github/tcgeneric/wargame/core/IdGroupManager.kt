package com.github.tcgeneric.wargame.core

import com.github.tcgeneric.wargame.core.data.IDGroup

class IdGroupManager {
    private val groupList:ArrayList<IDGroup> = arrayListOf()

    fun allocate(amount:Int):IDGroup {
        if(amount <= 0)
            throw IllegalArgumentException("Amount cannot be negative or 0")
        if(groupList.isEmpty())
            groupList.add(IDGroup(0, amount))
        for(x in 0 until groupList.size - 1) {
            if(groupList[x+1].first - groupList[x].second >= amount) {
                val idg = IDGroup(groupList[x].second, groupList[x].second + amount)
                groupList.add(idg)
                return idg
            }
        }
        val last = groupList.last().second
        val idg = IDGroup(last, last + amount)
        groupList.add(idg)
        return idg
    }

    fun free(id:Int):Boolean {
        for(x in groupList) {
            if(x.first >= id && x.second <= id)
                return groupList.remove(x)
        }
        return false
    }

    fun get(entityId:Int):IDGroup? {
        for(x in groupList) {
            if(x.first >= entityId && x.second <= entityId)
                return x
        }
        return null
    }
}