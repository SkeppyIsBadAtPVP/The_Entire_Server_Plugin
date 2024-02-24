package com.aadhavan.the_entire_server_plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static com.aadhavan.the_entire_server_plugin.Main.*;

public class Unban_Command implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if(args.length >= 1){

            if(Bukkit.getOfflinePlayer(args[0]) != null){
                OfflinePlayer target = Bukkit.getOfflinePlayer(args[0]);
                if (isPlayerBanned(target.getUniqueId()) || isPlayerIPBanned(target.getUniqueId())){
                    StringBuilder reason = new StringBuilder();
                    for (int i = 1 ; i < args.length ; i++) {
                        reason.append(args[i]).append(" ");
                    }
                    unbanPlayer(target.getUniqueId(), sender, reason.toString());

                } else {
                    sender.sendMessage(ChatColor.RED + "That Player is not banned!");
                }

            } else {
                sender.sendMessage(ChatColor.RED + "That Player has never been on the server before!");
            }

        } else {
            sender.sendMessage(ChatColor.RED + "Invalid Usage! Please type /help for usage.");
        }

        return false;
    }
}