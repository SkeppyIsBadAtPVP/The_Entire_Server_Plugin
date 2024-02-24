package com.aadhavan.the_entire_server_plugin.listeners;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.Calendar;

import static com.aadhavan.the_entire_server_plugin.Main.getTimeUntilCalendarInSeconds;
import static com.aadhavan.the_entire_server_plugin.Main.muted;
import static java.lang.Integer.parseInt;

public class Mute_Listener implements Listener {
    @EventHandler
    public void onTalk(AsyncPlayerChatEvent e){
        Player player = e.getPlayer();
        if(muted.containsKey(player.getUniqueId())){
            e.setCancelled(true);
            if(muted.get(player.getUniqueId()) == null){
                player.sendMessage(ChatColor.RED + "You have been muted permanently.");
            } else {
                Calendar cal = muted.get(player.getUniqueId());
                // s, m, h, d, w, mo, y
                long secUntilMute = getTimeUntilCalendarInSeconds(cal);
                if (secUntilMute>0 && secUntilMute<60){
                    player.sendMessage(ChatColor.RED + "You have been muted for " + secUntilMute + " seconds.");
                    if(secUntilMute==1){player.sendMessage(ChatColor.RED + "You have been muted for 1 second.");}
                } else if (secUntilMute>=60 && secUntilMute<3600) {
                    int to_min = parseInt(String.valueOf(secUntilMute))/60;
                    int min_rem_sec_thing = parseInt(String.valueOf(secUntilMute))%60;
                    player.sendMessage(ChatColor.RED + "You have been muted for " + to_min + " minutes " + min_rem_sec_thing + " seconds.");
                }
            }
            
        }
    }

}
