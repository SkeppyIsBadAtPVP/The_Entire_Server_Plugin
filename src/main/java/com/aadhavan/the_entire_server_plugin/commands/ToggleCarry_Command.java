package com.aadhavan.the_entire_server_plugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static com.aadhavan.the_entire_server_plugin.Main.togglecarry;

public class ToggleCarry_Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (sender instanceof Player){
            Player player = (Player) sender;

            if (!togglecarry.contains(player.getUniqueId())) {
                player.sendMessage(ChatColor.GREEN + "Toggle Carry ON!");
                togglecarry.add(player.getUniqueId());
            } else {
                player.sendMessage(ChatColor.GREEN + "Toggle Carry OFF!");
                togglecarry.remove(player.getUniqueId());
            }
        } else {
            sender.sendMessage(ChatColor.RED + "You cannot use this command on console!");
        }


        return false;
    }
}
