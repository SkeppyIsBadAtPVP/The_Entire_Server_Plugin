package com.aadhavan.the_entire_server_plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Kick_Command implements CommandExecutor {
    // /kick <Player Name> <Reason>
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(args.length >=2){

            if(Bukkit.getPlayerExact(args[0]) != null){
                Player target = Bukkit.getPlayerExact(args[0]);

                String reason = "";
                for (int i = 1 ; i < args.length ; i++) {
                    reason += (args[i] + " ");
                }

                sender.sendMessage(ChatColor.GREEN + "You have kicked " + args[0] + ". Reason: " + reason + ".");
                target.kickPlayer(ChatColor.RED + "You have been kicked by " + ChatColor.GREEN + sender.getName() + ChatColor.RED + "\nReason: " + reason);

            } else {
                sender.sendMessage(ChatColor.RED + "That Player is not on the server right now!");
            }

        } else {
            sender.sendMessage(ChatColor.RED + "Invalid Usage! Please type \"/kick <Player Name> <Reason>\"");
        }

        return false;
    }
}
