package com.github.tcgeneric.wargame.map

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.util.Coordinate
import kotlin.random.Random.Default.nextDouble

class MapGenerator(private val instance:Wargame) {
    fun generate(width:Int, height:Int):MapData {
        val noises = Array(width) { Array(height) {0.0} }
        for(i in 0 until width) {
            for(j in 0 until height) {
                noises[i][j] = nextDouble()
            }
        }
        val filtered = redNoiseFilter(width, height, noises)
        val result = HashMap<Coordinate, Tile>()
        for(i in 0 until width) {
            for(j in 0 until height) {
                val coord = Coordinate(i, j)
                result[coord] = Tile(
                        entityAbove = null,
                        isSynced = false,
                        protectionRate = 1f,
                        coord = coord,
                        type = classify(filtered[i][j])
                )
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
        for(i in 0 until height) {
            for(j in 0 until width) {
                result[i][j] = (noise[i+1][j] + noise[i][j])/2
            }
        }
        return result
    }

    private fun classify(v:Double):TileType {
        return when {
            v < -0.4 -> TileType.WATER
            v < 0.35 -> TileType.PLAINS
            v < 0.5 -> TileType.HILLS
            else -> TileType.MOUNTAIN
        }
    }
}