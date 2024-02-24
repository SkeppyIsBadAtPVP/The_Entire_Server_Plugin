package com.aadhavan.the_entire_server_plugin.commands;

import com.aadhavan.the_entire_server_plugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Reply_Command implements CommandExecutor {
    private Main main;
    public Reply_Command(Main main){
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;

            if (args.length >= 1){
                if (main.getRecentMessages().containsKey(player.getUniqueId())){
                    UUID uuid = main.getRecentMessages().get(player.getUniqueId());
                    if (Bukkit.getPlayer(uuid) != null){
                        Player target = Bukkit.getPlayer(uuid);

                        StringBuilder builder = new StringBuilder();
                        for (int i = 0; i < args.length; i++){
                            builder.append(args[i]).append(" ");
                        }

                        player.sendMessage(ChatColor.GREEN + "You" + ChatColor.GRAY + " -> " + ChatColor.GREEN + target.getName() + ChatColor.GRAY + ": " + builder);
                        target.sendMessage(ChatColor.GREEN + player.getName() + ChatColor.GRAY + " -> " + ChatColor.GREEN + "You" + ChatColor.GRAY + ": " + builder);

                    } else {
                        player.sendMessage(ChatColor.RED + "They are not online!");
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "You haven't messaged anyone yet!");
                }
            } else {
                player.sendMessage(ChatColor.RED + "Invalid Usage! Please type \"/reply <Message>\"");
            }

        } else {
            sender.sendMessage(ChatColor.RED + "You cannot use /reply on console!");
        }

        return false;
    }
}
