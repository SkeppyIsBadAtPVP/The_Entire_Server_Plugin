package com.aadhavan.the_entire_server_plugin.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Gun_Command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(commandSender instanceof Player){
            Player player = (Player) commandSender;


            ItemStack fireball_launcher = new ItemStack(Material.NETHERITE_HOE);
            ItemMeta fireball_launcherItemMeta = fireball_launcher.getItemMeta();
            fireball_launcherItemMeta.setDisplayName(ChatColor.RED +  "Fireball Launcher" + ChatColor.BOLD);
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

            player.getInventory().addItem(fireball_launcher, trident_launcher, snowball_launcher);

        }

        return false;
    }
}
