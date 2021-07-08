package xyz.umeo.threePVP

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.EntityType
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityPickupItemEvent
import org.bukkit.event.entity.PlayerDeathEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerRespawnEvent
import org.bukkit.inventory.ItemStack
import org.bukkit.plugin.java.JavaPlugin
import xyz.umeo.threePVP.util.DiamondSword
import xyz.umeo.threePVP.util.Score

class EventListener: Listener {
    @EventHandler
    fun onPlayerJoin(event: PlayerJoinEvent) {
        event.player.sendMessage("➜ Plugin made by U.meo!")
        event.player.sendMessage("➜ Check my work in my Github!")
        Bukkit.broadcast(Component.text("➜ Welcome to my Server! ${event.player.name}"))
        event.joinMessage(null) //default joinMessage disable.
    }

    @EventHandler
    fun onPlayerDie(event: PlayerDeathEvent) { //Include EntitygetItemEvent
        try{
            if (event.entity.type == EntityType.PLAYER && xyz.umeo.threePVP.data.active) {
                //item related
                val invList = event.drops
                val i = invList.iterator()
                while(i.hasNext()){
                    val item: ItemStack = i.next()
                    if(item.type == Material.IRON_SWORD || item.type == Material.BOW) i.remove()
                    if(item.type == Material.DIAMOND_SWORD) DiamondSword().dropMsg(event)
                }
                //Score related
                if (event.entity.killer!!.type == EntityType.PLAYER) {
                    val killer = event.entity.killer
                    Score().addScore(killer)
                }
            }
        } catch (error:NullPointerException) {
            //killed by command
            return
        }

    }


    @EventHandler
    fun onPlayerRespawn(event: PlayerRespawnEvent) {
        if (event.player.type == EntityType.PLAYER && xyz.umeo.threePVP.data.active) {
            event.player.inventory.addItem(ItemStack(Material.IRON_SWORD))
            event.player.inventory.addItem(ItemStack(Material.BOW))
            for(i in 1..10) event.player.inventory.addItem(ItemStack(Material.ARROW))
        }
    }

    @EventHandler
    fun onPlayerGetDiamondSword(event: EntityPickupItemEvent) {
        if(event.entity.type == EntityType.PLAYER && event.item.itemStack.type == Material.DIAMOND_SWORD  && xyz.umeo.threePVP.data.active) {
            DiamondSword().pickupMsg(event)
        }
    }
}