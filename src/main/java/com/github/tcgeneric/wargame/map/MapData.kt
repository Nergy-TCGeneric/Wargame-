package com.github.tcgeneric.wargame.map

data class MapData(val width:Int, val height:Int, val seed:Int) {
    var tiles = arrayOfNulls<Tile>(width * height)
}