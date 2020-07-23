package com.github.tcgeneric.wargame.map

import com.github.tcgeneric.wargame.util.Coordinate
import com.github.tcgeneric.wargame.util.Direction

data class MapFrame(val startingPoint:Coordinate, val direction:Pair<Direction, Direction>, val width:Int, val height:Int)