package com.warihue.abilities

import com.warihue.abilities.events.PlayerEvents
import com.warihue.abilities.events.RiptideEvent

object EventManager {
    fun registerEvents(){
        Main.instance?.let {
            it.server.pluginManager.run {
                registerEvents(RiptideEvent, it)
                registerEvents(PlayerEvents, it)
            }
        }
    }
}