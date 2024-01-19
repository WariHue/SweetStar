package com.warihue.abilities.commands

import com.warihue.abilities.Main
import com.warihue.abilities.core.Ability
import com.warihue.abilities.core.Ability.NONE
import com.warihue.abilities.core.UserPlayer
import com.warihue.abilities.core.item.OpenGUI.openAnvilTable
import com.warihue.abilities.saves.deleteFile
import com.warihue.abilities.saves.makeFile
import io.github.monun.kommand.kommand
import net.kyori.adventure.text.Component.text
import org.bukkit.entity.Player
import java.io.File

object RegisterCommand {
    fun registerCommands(){
        Main.instance?.kommand {
            register("ability","wap"){
                requires { isOp && isPlayer }
                executes {
                    sender.sendMessage(text("§4 Please Select works"))
                }
                //region /wap use
                //endregion

                //region /wap disuse
                then("anvil"){
                    executes {
                        openAnvilTable(player)
                    }
                }
                //endregion
            }
        }
    }
}