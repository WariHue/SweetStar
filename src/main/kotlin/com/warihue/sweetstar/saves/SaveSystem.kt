package com.warihue.sweetstar.saves

import com.warihue.sweetstar.core.Team
import io.github.monun.tap.config.Config
import io.github.monun.tap.config.ConfigSupport
import io.github.monun.tap.config.Name
import java.io.File

@Name("player")
class PlayerCfg(team: Team) {
    @Config
    val team = team
}

/** 데이터 삭제 함수 */
fun deleteFile(file: File) {
    file.delete()
}

/** 데이터 작성 및 호출 함수 */
fun makeFile(file: File, team: Team): PlayerCfg{
    val data = PlayerCfg(team)
    ConfigSupport.compute(data, file, true)
    return data
}
