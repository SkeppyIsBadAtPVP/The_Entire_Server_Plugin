package com.aadhavan.the_entire_server_plugin.commands;

import com.aadhavan.the_entire_server_plugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Message_Command implements CommandExecutor {
    private Main main;
    public Message_Command(Main main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if (sender instanceof Player){
            Player player = (Player) sender;

            if (args.length >= 2){
                if (Bukkit.getPlayerExact(args[0]) != null){
                    Player target = Bukkit.getPlayerExact(args[0]);

                    StringBuilder builder = new StringBuilder();
                    for (int i = 1; i < args.length; i++){
                        builder.append(args[i]).append(" ");
                    }

                    player.sendMessage(ChatColor.GREEN + "You" + ChatColor.GRAY + " -> " + ChatColor.GREEN + target.getName() + ChatColor.GRAY + ": " + builder);
                    target.sendMessage(ChatColor.GREEN + player.getName() + ChatColor.GRAY + " -> " + ChatColor.GREEN + "You" + ChatColor.GRAY + ": " + builder);
                    main.getRecentMessages().put(player.getUniqueId(), target.getUniqueId());

                } else {
                    sender.sendMessage(ChatColor.RED + "This player is not online!");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Invalid Usage! Please type \"/msg <Player Name> <Message>\"");
            }

        } else {

            if (args.length >= 2){
                if (Bukkit.getPlayerExact(args[0]) != null){
                    Player target = Bukkit.getPlayerExact(args[0]);

                    StringBuilder builder = new StringBuilder();
                    for (int i = 1; i < args.length; i++){
                        builder.append(args[i]).append(" ");
                    }

                    sender.sendMessage(ChatColor.GREEN + "You" + ChatColor.GRAY + " -> " + ChatColor.GREEN + target.getName() + ChatColor.GRAY + ": " + builder);
                    target.sendMessage(ChatColor.GREEN + "Console" + ChatColor.GRAY + " -> " + ChatColor.GREEN + "You" + ChatColor.GRAY + ": " + builder);

                } else {
                    sender.sendMessage(ChatColor.RED + "This player is not online!");
                }
            } else {
                sender.sendMessage(ChatColor.RED + "Invalid Usage! Please type \"/msg <Player Name> <Message>\"");
            }
        }

        return false;
    }
}
