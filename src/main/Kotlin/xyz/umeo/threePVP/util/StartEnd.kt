package xyz.umeo.threePVP.util

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.block.Chest
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

class StartEnd {
    private val list: List<Player> = ArrayList(Bukkit.getOnlinePlayers())
    fun win(winner: Map<Player?, Int>) {
        var winPlayer: Player? = null
        for(key in winner.keys) {winPlayer = key}
        for(players in list){
            players.sendTitle("Winner!","${ChatColor.YELLOW}" + "${winPlayer?.name}가 우승했습니다!", 10, 80, 10)
            players.playSound(players.location, org.bukkit.Sound.UI_TOAST_CHALLENGE_COMPLETE, 1f, 1f)
            reset()
            players.inventory.clear()
        }
    }

    fun startMsg(){
        for(players in list){
            players.sendTitle("게임 시작!", "상대를 죽여 점수를 획득하세요!", 10, 40, 10)
            players.playSound(players.location, org.bukkit.Sound.BLOCK_ANVIL_USE, 1f, 1f)
            players.inventory.clear()
            giveItem(players)
        }
        fillChest()
    }

    fun reset(){
        xyz.umeo.threePVP.data.active = false
        xyz.umeo.threePVP.data.score.clear()
        xyz.umeo.threePVP.data.scoreboard = null
        val chest = Bukkit.getWorld("world")!!.getBlockAt(-179,93,242).state as Chest
        chest.blockInventory.clear()
    }

    private fun giveItem(player: Player) {
        player.inventory.addItem(ItemStack(Material.IRON_SWORD))
        player.inventory.addItem(ItemStack(Material.BOW))
        for(i in 1..10) player.inventory.addItem(ItemStack(Material.ARROW))
    }

    private fun fillChest() {
        val chest = Bukkit.getWorld("world")!!.getBlockAt(-179,93,242).state as Chest
        chest.blockInventory.clear()
        chest.blockInventory.addItem(ItemStack(Material.DIAMOND_SWORD))
        for(i in 1..64) chest.blockInventory.addItem(ItemStack(Material.ARROW))
    }
}