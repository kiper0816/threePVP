package xyz.umeo.threePVP

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import xyz.umeo.threePVP.util.StartEnd

class Command: CommandExecutor{
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (sender is Player){
            when (command.name){
                "start" -> {
                    StartEnd().startMsg()
                    xyz.umeo.threePVP.data.active = true
                }
                "reset" -> {
                    StartEnd().reset()
                }
            }
        }
        else {
            sender.sendMessage("You can't run this command in console.")
        }
        return true
    }
}