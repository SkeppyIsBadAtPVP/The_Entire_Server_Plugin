package com.aadhavan.the_entire_server_plugin;

import com.aadhavan.the_entire_server_plugin.commands.*;
import com.aadhavan.the_entire_server_plugin.listeners.Gun_Listener;
import com.aadhavan.the_entire_server_plugin.listeners.Mute_Listener;
import com.aadhavan.the_entire_server_plugin.listeners.ToggleCarry_Listener;
import com.aadhavan.the_entire_server_plugin.tabcompleters.*;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.util.*;

public final class Main extends JavaPlugin implements Listener{
    private BossBar bossBar;
    public static List<UUID> togglecarry = new ArrayList<>();
    public static HashMap<UUID,Calendar> muted =  new HashMap<>();
    private HashMap<UUID, UUID> recentMessages;
    @Override
    public void onEnable() {


        Bukkit.getPluginManager().registerEvents(new Gun_Listener(), this);
        Bukkit.getPluginManager().registerEvents(new ToggleCarry_Listener(), this);
        Bukkit.getPluginManager().registerEvents(new Mute_Listener(), this);
        Bukkit.getPluginManager().registerEvents(this, this);


        getCommand("gun").setExecutor(new Gun_Command());
        getCommand("ban").setExecutor(new Ban_Command());
        getCommand("ipban").setExecutor(new IpBan_Command());
        getCommand("mute").setExecutor(new Mute_Command());
        getCommand("kick").setExecutor(new Kick_Command());
        getCommand("unban").setExecutor(new Unban_Command());
        getCommand("vanish").setExecutor(new Vanish_Command(this));
        getCommand("togglecarry").setExecutor(new ToggleCarry_Command());
        getCommand("message").setExecutor(new Message_Command(this));
        getCommand("reply").setExecutor(new Reply_Command(this));
        getCommand("spawnhologram").setExecutor(new Hologram_Command());

        getCommand("ban").setTabCompleter(new BanTab());
        getCommand("ipban").setTabCompleter(new IpBanTab());
        getCommand("mute").setTabCompleter(new MuteTab());
        getCommand("kick").setTabCompleter(new KickTab());
        getCommand("unban").setTabCompleter(new UnbanTab());
        getCommand("message").setTabCompleter(new MessageTab());

        bossBar = Bukkit.createBossBar(ChatColor.GREEN + "Welcome to the server!",
                BarColor.BLUE,
                BarStyle.SOLID);

        Bukkit.getWorld("world").setGameRule(GameRule.DO_DAYLIGHT_CYCLE, false);
        Bukkit.getWorld("world").setGameRule(GameRule.DO_WEATHER_CYCLE, false);
        Bukkit.getWorld("world").setTime(6000);

        recentMessages = new HashMap<>();

    }

    public HashMap<UUID, UUID> getRecentMessages() {return recentMessages;}

    @EventHandler
    public void onPing(ServerListPingEvent e){
        e.setMaxPlayers(2147483647);
        e.setMotd(ChatColor.AQUA + "The best server. Ever.\nJOIN NOW!!!");
        try {
            e.setServerIcon(Bukkit.loadServerIcon(new File("skep.png")));
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();

        player.setPlayerListHeader(ChatColor.RED + "The most amazing people :");

        bossBar.addPlayer(player);

        player.sendTitle(ChatColor.GREEN + "Welcome!", ChatColor.GREEN + "Hope you enjoy!", 20, 10, 20);

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, () -> {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText("§a§l§nYou are currently §4§l§nOwner§a§l§n rank!"));
        }, 1, 20);

        Firework firework = player.getWorld().spawn(player.getLocation(), Firework.class);
        FireworkMeta fireworkMeta = (FireworkMeta) firework.getFireworkMeta();
        fireworkMeta.addEffect(FireworkEffect.builder().withColor(Color.RED).withColor(Color.LIME).withColor(Color.BLUE).with(Type.BALL).withTrail().build());
        fireworkMeta.setPower(1);
        firework.setFireworkMeta(fireworkMeta);

        player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1.0f, 1.0f);

        Bukkit.getScheduler().runTaskTimer(this, () -> {
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 20, 0, true, false, true));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 20, 99, true, false, true));
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 20, 2, true, false, true));
        }, 1, 10);

    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        Player player = e.getPlayer();
        recentMessages.remove(player.getUniqueId());
    }


    public static void banPlayer(Player target, String reason, CommandSender sender, String[] args, String time, Calendar cal){
        if (time.equals("∞")) {
            Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), ChatColor.RED + reason, null, sender.getName());
        } else {
            Bukkit.getBanList(BanList.Type.NAME).addBan(target.getName(), ChatColor.RED + reason, cal.getTime(), sender.getName());
        }
            sender.sendMessage(ChatColor.GREEN + "You have banned" + target +". Time: " + time + ". Reason: " + reason + ".");
            target.kickPlayer(ChatColor.RED + "You have been banned by " + ChatColor.GREEN + sender.getName() + ChatColor.RED + "\nTime: " + ChatColor.GREEN + time + ChatColor.RED + "\nReason: " + reason);

    }

    public static void ipbanPlayer(Player target, String reason, CommandSender sender, String[] args, String time, Calendar cal){
        if (time.equals("∞")) {
            Bukkit.getBanList(BanList.Type.IP).addBan(target.getAddress().getAddress().getHostAddress(), ChatColor.RED + reason, null, sender.getName());
        } else {
            Bukkit.getBanList(BanList.Type.IP).addBan(target.getAddress().getAddress().getHostAddress(), ChatColor.RED + reason, cal.getTime(), sender.getName());
        }
        sender.sendMessage(ChatColor.GREEN + "You have IP banned" + target +". Time: " + time + ". Reason: " + reason + ".");
        target.kickPlayer(ChatColor.RED + "You have been IP banned by " + ChatColor.GREEN + sender.getName() + ChatColor.RED + "\nTime: " + ChatColor.GREEN + time + ChatColor.RED + "\nReason: " + reason);

    }



    public static void mutePlayer(Player target, String reason, CommandSender sender, String[] args, String time, Calendar cal){
        UUID uuid = target.getUniqueId();
        if(muted.containsKey(uuid)){muted.replace(uuid, muted.get(uuid), cal);}
        else {muted.put(uuid, cal);}

        sender.sendMessage(ChatColor.GREEN + "You have muted " + ChatColor.RED + target.getName() + ChatColor.GREEN +". Time: " + time + ". Reason: " + reason + ".");
        target.sendMessage(ChatColor.RED + "You have been muted by " + ChatColor.GREEN + sender.getName() + ChatColor.RED + "\nTime: " + ChatColor.GREEN + time + ChatColor.RED + "\nReason: " + reason);

    }


     public static long getTimeUntilCalendarInSeconds(Calendar calendar) {
        // Get the current system time
        Date currentTime = Calendar.getInstance().getTime();

        // Get the calendar time
        Date calendarTime = calendar.getTime();

        // Calculate the time difference in milliseconds
        long timeDifference = calendarTime.getTime() - currentTime.getTime();

        // Convert milliseconds to seconds
        long secondsUntilMute = timeDifference / 1000;

        // Return the time difference in seconds
        return secondsUntilMute;
    }

    public static void unbanPlayer(UUID uuid, CommandSender sender, String reason){
        OfflinePlayer target = Bukkit.getOfflinePlayer(uuid);
        if(isPlayerBanned(uuid)){
            Bukkit.getBanList(BanList.Type.NAME).pardon(target.getName());
//        } else if (isPlayerIPBanned(uuid)){
//            Bukkit.getBanList(BanList.Type.IP).pardon(String.valueOf(uuid));
        }

        sender.sendMessage(ChatColor.GREEN + "You have unbanned " + target.getName() +". Reason: " + reason);

    }
    public static boolean isPlayerBanned(UUID playeruuid) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playeruuid);

        for (OfflinePlayer banneds : Bukkit.getBannedPlayers()){
            if(offlinePlayer.equals(banneds)){
                return true;
            }
        }
        return false;
    }

    // Method to check if a player's IP is banned
    public static boolean isPlayerIPBanned(UUID playeruuid) {
        OfflinePlayer offlinePlayer = Bukkit.getOfflinePlayer(playeruuid);
//        Player player = offlinePlayer.;
//        String address = player.getAddress().getAddress().getHostAddress();
//        for (String addresses : Bukkit.getIPBans()){
//            if(address.equals(addresses)){
//                return true;
//            }
//        }
//        return false;

        BanList banList = Bukkit.getBanList(BanList.Type.IP);
        return banList.isBanned(offlinePlayer.getPlayer().getAddress().getAddress().getHostAddress());



    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }
}
