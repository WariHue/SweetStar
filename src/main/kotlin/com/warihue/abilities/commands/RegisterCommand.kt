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
                then("use"){
                    executes {
                        sender.sendMessage("§4 Please Select Ability")
                    }

                    then("aqua_man"){
                        executes {
                            changeAbility(sender as Player, File(Main.datafolder, "/players/${player.uniqueId}.yml"), Ability.AQUAMAN)
                            player.sendActionBar(text("§l §b 아쿠아 맨의 힘이 당신에게 깃들었습니다"))
                            //endregion
                        }
                    }
                }
                //endregion

                //region /wap disuse
                then("disuse"){
                    executes {
                        changeAbility(sender as Player, File(Main.datafolder, "/players/${player.uniqueId}.yml"), NONE)
                        player.sendActionBar(text("§l §c 능력 사라짐"))
                        openAnvilTable(player)
                    }
                }
                //endregion
            }
        }
    }


    private fun changeAbility(player: Player, file: File, ability: Ability){
        Main.playerManager?.let {
            it.changePlayerData(player, UserPlayer(player.uniqueId, player.name, ability))
            player.sendMessage(text("${player.name}'s Ability = ${it.getPlayerData(player)?.ability}"))
        }
        //region change data
        deleteFile(file)
        makeFile(file, ability)
    }
}