package com.warihue.sweetstar.core

import com.warihue.sweetstar.Main
import com.warihue.sweetstar.saves.makeFile
import org.bukkit.entity.Player
import java.io.File

class PlayerManager {
    companion object{
        private var onlinePlayerData = HashMap<Player, UserPlayer>()
    }
    /** 플레이어 데이터 추가 함수 */
    fun addPlayer(player: Player){
        val newUserPlayer = UserPlayer(player.uniqueId, player.name, makeFile(File(Main.datafolder, "/players/${player.uniqueId}.yml"), Team.NONE).team)
        onlinePlayerData[player] = newUserPlayer
        player.sendMessage("Your team is " + getPlayerData(player)?.team.toString())
    }

    /** 플레이어 데이터 삭제 함수 */
    fun deletePlayer(player: Player){
        onlinePlayerData.remove(player)
    }

    /** 플레이어 데이터 변경 함수 */
    fun changePlayerData(player: Player, data:UserPlayer){
        onlinePlayerData.remove(player)
        onlinePlayerData[player] = data
    }

    /** 플레이어 데이터 호출 함수 */
    fun getPlayerData(player: Player) : UserPlayer? {
        return onlinePlayerData[player]
    }
}