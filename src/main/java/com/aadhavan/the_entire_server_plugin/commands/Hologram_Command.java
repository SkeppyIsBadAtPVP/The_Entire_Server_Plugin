package com.aadhavan.the_entire_server_plugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class Hologram_Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        if (sender instanceof Player) {
            if (args.length >= 1) {
                StringBuilder text = new StringBuilder();
                for (int i = 0 ; i < args.length ; i++) {
                    text.append(args[i]).append(" ");
                }

                Player player = (Player) sender;

                ArmorStand hologram = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
                hologram.setInvisible(true);
                hologram.setGravity(false);
                hologram.setHealth(20);
                hologram.setInvulnerable(true);

                hologram.setCustomNameVisible(true);
                hologram.setCustomName(ChatColor.translateAlternateColorCodes('&', text.toString()));
        }

        } else {
            sender.sendMessage(ChatColor.RED + "You cannot use this command on console!");
        }


        return false;
    }
}
