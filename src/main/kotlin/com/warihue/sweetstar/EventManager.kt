package com.warihue.sweetstar

import com.warihue.sweetstar.events.BossEvents
import com.warihue.sweetstar.events.PlayerEvents
import com.warihue.sweetstar.events.RiptideEvent

object EventManager {
    fun registerEvents(){
        Main.instance?.let {
            it.server.pluginManager.run {
                registerEvents(RiptideEvent, it)
                registerEvents(PlayerEvents, it)
                registerEvents(BossEvents, it)
            }
        }
    }
}