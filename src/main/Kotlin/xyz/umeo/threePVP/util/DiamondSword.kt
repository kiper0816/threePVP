package xyz.umeo.threePVP.util

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.entity.Player
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.event.entity.PlayerDeathEvent

class DiamondSword {
    private val list: List<Player> = ArrayList(Bukkit.getOnlinePlayers())
    fun dropMsg(event: PlayerDeathEvent) {
        for(players in list){
            players.sendTitle("Drop!","${ChatColor.YELLOW}" + "${event.entity.name}가 다이아 검을 드롭했습니다.", 10, 40, 10)
            players.playSound(players.location, org.bukkit.Sound.ENTITY_PLAYER_LEVELUP, 1f, 0f)
        }
    }
    fun pickupMsg(event: EntityPickupItemEvent) {
        for(players in list){
            players.sendTitle("PickUp!","${ChatColor.YELLOW}" + "${event.entity.name}가 다이아 검을 획득했습니다.", 10, 40, 10)
            players.playSound(players.location, org.bukkit.Sound.ENTITY_PLAYER_LEVELUP, 1f, 0f)
        }
    }
}