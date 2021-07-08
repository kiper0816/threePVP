package xyz.umeo.threePVP.util

import net.kyori.adventure.text.Component
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class Score {
    private val list: List<Player> = ArrayList(Bukkit.getOnlinePlayers())
    fun addScore(killer: Player?) {
        if (xyz.umeo.threePVP.data.score.containsKey(killer)){
            xyz.umeo.threePVP.data.score[killer] = xyz.umeo.threePVP.data.score[killer]!! + 1
        }
        else {
            xyz.umeo.threePVP.data.score[killer] = 1
        }
        checkScore()
    }

    private fun checkScore() {
        if (xyz.umeo.threePVP.data.score.containsValue(2)) {
            StartEnd().win(xyz.umeo.threePVP.data.score.filter{(_, value) -> value == 2 })
        }
    }

    fun relateScoreBoard() {
        xyz.umeo.threePVP.data.scoreboard!!.registerNewObjective("Score", "Kills", Component.text("점수"))
        for(players in list){
            players.scoreboard = xyz.umeo.threePVP.data.scoreboard!!
        }
    }
}