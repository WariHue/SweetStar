package com.warihue.sweetstar.saves

import com.warihue.sweetstar.core.Team
import io.github.monun.tap.config.Config
import io.github.monun.tap.config.ConfigSupport
import io.github.monun.tap.config.Name
import java.io.File

@Name("data")
class dataConfig(ob: Boolean, yb: Boolean) {
    @Config
    val OBEnder:Boolean = ob
    @Config
    val YBEnder:Boolean = yb
}

fun dataFile(file: File, ob:Boolean, yb:Boolean): dataConfig{
    val data = dataConfig(ob, yb)
    ConfigSupport.compute(data, file, true)
    return data
}