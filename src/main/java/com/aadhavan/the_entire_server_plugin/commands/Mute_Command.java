package com.aadhavan.the_entire_server_plugin.commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Calendar;

import static com.aadhavan.the_entire_server_plugin.Main.mutePlayer;

public class Mute_Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        // /mute <Player Name> <Time for Mute> <Reason>

        if (args.length>=3){
            if(Bukkit.getOfflinePlayer(args[0]) != null){
                Player target = (Player) Bukkit.getOfflinePlayer(args[0]);

                StringBuilder reason = new StringBuilder();
                for (int i = 2; i < args.length; i++){
                    reason.append(args[i]).append(" ");
                }
                // s, m, h, d, w, mo, y
                Calendar cal = Calendar.getInstance();
                String st = args[1];
                String letters = st.replaceAll("[^A-Za-z]", "");
                String number = st.replaceAll("[^0-9]", "");
                if (letters.equalsIgnoreCase("s")) {
                    cal.add(Calendar.SECOND, Integer.parseInt(number));
                    mutePlayer(target, reason.toString(), sender, args, st, cal);
                } else if (letters.equalsIgnoreCase("m")) {
                    cal.add(Calendar.MINUTE, Integer.parseInt(number));
                    mutePlayer(target, reason.toString(), sender, args, st, cal);
                } else if (letters.equalsIgnoreCase("h")) {
                    cal.add(Calendar.HOUR, Integer.parseInt(number));
                    mutePlayer(target, reason.toString(), sender, args, st, cal);
                } else if (letters.equalsIgnoreCase("d")) {
                    cal.add(Calendar.HOUR, Integer.parseInt(number)*24);
                    mutePlayer(target, reason.toString(), sender, args, st, cal);
                } else if (letters.equalsIgnoreCase("w")) {
                    cal.add(Calendar.HOUR, Integer.parseInt(number)*168);
                    mutePlayer(target, reason.toString(), sender, args, st, cal);
                } else if (letters.equalsIgnoreCase("mo")) {
                    cal.add(Calendar.MONTH, Integer.parseInt(number));
                    mutePlayer(target, reason.toString(), sender, args, st, cal);
                } else if (letters.equalsIgnoreCase("y")) {
                    cal.add(Calendar.YEAR, Integer.parseInt(number));
                    mutePlayer(target, reason.toString(), sender, args, st, cal);
                } else if (letters.equalsIgnoreCase("f")) {
                    mutePlayer(target, reason.toString(), sender, args, "∞", null);
                } else {
                    sender.sendMessage(ChatColor.RED + "Invalid Time. Please use \"s\" or \"m\" or \"h\" or \"d\" or \"w\" or \"mo\" or \"y\" or \"f\" (forever).");
                }

            } else {
                sender.sendMessage(ChatColor.RED + "That Player has never been on the server before!");
            }
        } else {
            sender.sendMessage(ChatColor.RED + "Invalid Usage! Please type \"/mute <Player Name> <Time> <Reason>\"");
        }

        return false;
    }
}
