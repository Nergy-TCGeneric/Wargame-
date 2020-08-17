package com.github.tcgeneric.wargame.core

import com.github.tcgeneric.wargame.Wargame
import com.github.tcgeneric.wargame.map.MapData
import com.github.tcgeneric.wargame.map.MapFrame
import com.github.tcgeneric.wargame.map.Tile
import com.github.tcgeneric.wargame.util.Direction
import com.github.tcgeneric.wargame.util.Coordinate
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class DataLoader(private val instance:Wargame) {
    fun loadMapData(frame:MapFrame):MapData? {
        val file = File(instance.dataFolder, "mapdata.yml")
        val yaml = YamlConfiguration.loadConfiguration(file)
        val tiles:HashMap<Coordinate, Tile> = HashMap()
        val list = yaml.getList("tiles")!!
        TODO("Get real value from yaml file")
        return MapData(frame.width, frame.height, tiles)
    }

    fun loadMapFrame():MapFrame {
        val file = File(instance.dataFolder, "mapframe.yml")
        val yaml = YamlConfiguration.loadConfiguration(file)
        val sPoint = yaml.getIntegerList("startingPoint")
        val direction = yaml.getStringList("direction")
        return MapFrame(Coordinate(sPoint.first(), sPoint.last()),
                Pair(Direction.valueOf(direction.first()), Direction.valueOf(direction.last())),
                yaml.getInt("width"),
                yaml.getInt("height"))
    }
}