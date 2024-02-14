package com.warihue.sweetstar.core

import java.util.UUID


enum class Team {
    NONE,
    OB,
    YB
}
data class UserPlayer(
    val uuid: UUID,
    var name: String,
    var team: Team
)