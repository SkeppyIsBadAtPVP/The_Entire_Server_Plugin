package com.aadhavan.the_entire_server_plugin;

import com.aadhavan.the_entire_server_plugin.listeners.Gun_Listener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic

        Bukkit.getPluginManager().registerEvents(new Gun_Listener(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic



    }
}
