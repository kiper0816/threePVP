package xyz.umeo.threePVP.data

import org.bukkit.entity.Player
import org.bukkit.scoreboard.Scoreboard

//Active game
var active = false

//Score
var score = mutableMapOf<Player?,Int>()

//Scoreboard
var scoreboard: Scoreboard? = null
