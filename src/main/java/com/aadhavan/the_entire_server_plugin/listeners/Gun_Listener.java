package com.aadhavan.the_entire_server_plugin.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Trident;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Gun_Listener implements Listener {

    // NETHERITE HOE = FIREBALL LAUNCHER
    // DIAMOND HOE = TRIDENT LAUNCHER
    // GOLD HOE = SNOWBALL LAUNCHER

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e){

        if(e.hasItem()){
            Player player = e.getPlayer();
            ItemStack fireball_launcher = new ItemStack(Material.NETHERITE_HOE);
            ItemMeta fireball_launcherItemMeta = fireball_launcher.getItemMeta();
            fireball_launcherItemMeta.setDisplayName(ChatColor.RED  + "Fireball Launcher" );
            fireball_launcherItemMeta.addEnchant(Enchantment.DURABILITY, 100, true);
            fireball_launcherItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            fireball_launcherItemMeta.isUnbreakable();
            fireball_launcher.setItemMeta(fireball_launcherItemMeta);

            ItemStack trident_launcher = new ItemStack(Material.DIAMOND_HOE);
            ItemMeta trident_launcherItemMeta = trident_launcher.getItemMeta();
            trident_launcherItemMeta.setDisplayName(ChatColor.GREEN +  "Trident Launcher" + ChatColor.BOLD);
            trident_launcherItemMeta.addEnchant(Enchantment.DURABILITY, 100, true);
            trident_launcherItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            trident_launcherItemMeta.isUnbreakable();
            trident_launcher.setItemMeta(trident_launcherItemMeta);

            ItemStack snowball_launcher = new ItemStack(Material.GOLDEN_HOE);
            ItemMeta snowball_launcherItemMeta = snowball_launcher.getItemMeta();
            snowball_launcherItemMeta.setDisplayName(ChatColor.RED +  "Snowball Launcher" + ChatColor.BOLD);
            snowball_launcherItemMeta.addEnchant(Enchantment.DURABILITY, 100, true);
            snowball_launcherItemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            snowball_launcherItemMeta.isUnbreakable();
            snowball_launcher.setItemMeta(snowball_launcherItemMeta);


            if (e.getItem().equals(trident_launcher)) {
                player.launchProjectile(Trident.class);
                player.sendMessage(ChatColor.GREEN + "BOOM!");
            } else if(e.getItem().equals(fireball_launcher)){
                player.launchProjectile(Fireball.class);
                player.sendMessage(ChatColor.GREEN + "BOOM!");
            } else if (e.getItem().equals(snowball_launcher)){
                player.launchProjectile(Snowball.class);
                player.sendMessage(ChatColor.GREEN + "BOOM!");
            }
        }

    }

}
