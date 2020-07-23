package com.github.tcgeneric.wargame.core

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.map.MapData
import com.github.tcgeneric.wargame.map.MapFrame
import com.github.tcgeneric.wargame.util.Direction
import com.github.tcgeneric.wargame.util.Coordinate

class DataLoader(private val instance:Wargame) {
    // TODO: This value is stub. replace with actual value.
    fun loadMapData():MapData {
        return MapData(0, 0, HashMap())
    }

    // TODO: This value is stub. replace with actual value.
    fun loadMapFrame():MapFrame {
        return MapFrame(Coordinate(0, 0), Pair(Direction.W, Direction.E), 0, 0)
    }
}