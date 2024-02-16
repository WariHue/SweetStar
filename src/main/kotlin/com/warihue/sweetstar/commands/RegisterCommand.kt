package com.warihue.sweetstar.commands

import com.warihue.sweetstar.Main
import com.warihue.sweetstar.core.Team
import com.warihue.sweetstar.core.UserPlayer
import com.warihue.sweetstar.core.item.OpenGUI.openAnvilTable
import com.warihue.sweetstar.core.item.OpenGUI.openEnchantTable
import com.warihue.sweetstar.core.item.OpenGUI.openMenu
import com.warihue.sweetstar.saves.deleteFile
import com.warihue.sweetstar.saves.makeFile
import io.github.monun.kommand.getValue
import io.github.monun.kommand.kommand
import net.kyori.adventure.text.Component.text
import org.bukkit.entity.Entity
import org.bukkit.entity.Player
import java.io.File

object RegisterCommand {


    fun registerCommands(){

        Main.instance?.kommand {
            register("menu"){
                requires { isPlayer }
                executes {
                    openMenu(player)
                }
            }
            register("sweet","star"){
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
                then("enchant"){
                    executes {
                        openEnchantTable(player)
                    }
                }

                then("team"){
                    executes {
                        player.sendMessage(text(Main.playerManager?.getPlayerData(player)?.team.toString()))
                    }
                }
                //endregion
            }
        }
    }
//    private fun changeTeam(player: Player, file: File, team: Team){
//        Main.playerManager?.let {
//            it.changePlayerData(player, UserPlayer(player.uniqueId, player.name, team))
//            player.sendMessage(text("${player.name}'s team = ${it.getPlayerData(player)?.team}"))
//        }
//        //region change data
//        deleteFile(file)
//        makeFile(file, team)
//    }
}