package com.github.tcgeneric.wargame.core.data

import org.bukkit.block.Block
import org.bukkit.util.Vector

data class Schematic(val startPoint:Vector, val blocks:List<Pair<Vector, Block>>)