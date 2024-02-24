package com.aadhavan.the_entire_server_plugin.tabcompleters;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BanTab implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {
        // /ban <Player Name> <Time for Ban> <Reason>
        if(args.length==1){
            List<String> names = new ArrayList<>();
            for (Player player : Bukkit.getOnlinePlayers()){
                names.add(player.getName());
            }
            return StringUtil.copyPartialMatches(args[0], names, new ArrayList<>());
        } else if (args.length==2){
            // s, m, h, d, w, mo, y
            return StringUtil.copyPartialMatches(args[1], Arrays.asList("1s","1m","1h","1d","1w","1mo","1y"), new ArrayList<>());

        } else if (args.length==3){
            return StringUtil.copyPartialMatches(args[2], Arrays.asList("None"), new ArrayList<>());
        } else {
        return StringUtil.copyPartialMatches(args[1], Arrays.asList(""), new ArrayList<>());
    }

    }
}
