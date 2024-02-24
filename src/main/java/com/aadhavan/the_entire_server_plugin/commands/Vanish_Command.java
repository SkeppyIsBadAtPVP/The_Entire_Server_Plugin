package com.aadhavan.the_entire_server_plugin.commands;

import com.aadhavan.the_entire_server_plugin.Main;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Vanish_Command implements CommandExecutor {
    private Main main;
    public Vanish_Command(Main main){
        this.main = main;
    }


    private List<UUID> vanished = new ArrayList<>();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player){
            Player player = (Player) sender;

            if (vanished.contains(player.getUniqueId())){
                vanished.remove(player.getUniqueId());
                for (Player target : Bukkit.getOnlinePlayers()){
                    target.hidePlayer(main, target);
                }
                player.sendMessage(ChatColor.GREEN + "You are now unvanished!");
            } else {
                vanished.add(player.getUniqueId());
                for (Player target : Bukkit.getOnlinePlayers()){
                    target.showPlayer(main, target);
                }
                player.sendMessage("You are now vanished!");
            }



        } else {
            sender.sendMessage(ChatColor.RED + "You cannot use this command on console!");
        }

        return false;
    }
}
