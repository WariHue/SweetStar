package com.warihue.abilities.core

import java.util.UUID


enum class Ability {
    NONE,
    AQUAMAN
}
data class UserPlayer(
    val uuid: UUID,
    var name: String,
    var ability: Ability
)
