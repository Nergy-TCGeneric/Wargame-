package com.github.tcgeneric.wargame.map

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.util.Coordinate
import kotlin.random.Random.Default.nextDouble

class MapGenerator(private val instance:Wargame) {
    fun generate(width:Int, height:Int, seed:Int):MapData {
        val noises = Array(width + 1) { Array(height + 1) {0.0} }
        for(i in 0 until width + 1) {
            for(j in 0 until height + 1) {
                noises[i][j] = nextDouble()
            }
        }
        val filtered = redNoiseFilter(width, height, noises)
        val result = HashMap<Coordinate, Tile>()
        for(i in 0 until width) {
            for(j in 0 until height) {
                result[Coordinate(i, j)] = Tile(null, true, false, 0, null, false, 1f, Coordinate(i, j), classify(filtered[i][j], -0.4, 0.35))
            }
        }
        return MapData(width, height, result)
    }

    private fun redNoiseFilter(width:Int, height:Int, noise:Array<Array<Double>>):Array<Array<Double>> {
        val result = Array(width) { Array(height) { 0.0 } }
        for(i in 0 until width) {
            for(j in 0 until height) {
                result[i][j] = (noise[i][j] + noise[i][j+1])/2
            }
        }
        return result
    }

    private fun classify(v:Double, seaLevel:Double, mntLevel:Double):TileType {
        return if(v < seaLevel) TileType.WATER
        else if(v >= seaLevel && v < mntLevel) TileType.PLAINS
        else TileType.MOUNTAIN
    }
}