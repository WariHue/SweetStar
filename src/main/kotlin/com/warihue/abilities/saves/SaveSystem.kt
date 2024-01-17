package com.warihue.abilities.saves

import com.warihue.abilities.core.Ability
import io.github.monun.tap.config.Config
import io.github.monun.tap.config.ConfigSupport
import io.github.monun.tap.config.Name
import org.bukkit.enchantments.Enchantment
import java.io.File

@Name("player")
class PlayerCfg(ability: Ability) {
    @Config
    val ability = ability
}

/** 데이터 삭제 함수 */
fun deleteFile(file: File) {
    file.delete()
}

/** 데이터 작성 및 호출 함수 */
fun makeFile(file: File, ability: Ability): PlayerCfg{
    val data = PlayerCfg(ability)
    ConfigSupport.compute(data, file, true)
    return data
}
