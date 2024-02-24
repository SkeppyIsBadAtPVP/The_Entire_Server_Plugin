package com.aadhavan.the_entire_server_plugin.listeners;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import static com.aadhavan.the_entire_server_plugin.Main.togglecarry;

public class ToggleCarry_Listener implements Listener {
    @EventHandler
    public void onEntityInteract(PlayerInteractEntityEvent e){
        Player player = e.getPlayer();
        if (togglecarry.contains(player.getUniqueId())){
            player.addPassenger(e.getRightClicked());
        }
    }
    @EventHandler
    public void onEntityLeftClick(PlayerInteractEvent e){
        Player player = e.getPlayer();
        if(e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK){
            if (togglecarry.contains(player.getUniqueId())){
                if (!player.getPassengers().isEmpty()){
                    Entity passenger = player.getPassenger();
                    player.removePassenger(passenger);
                    passenger.setVelocity(player.getLocation().getDirection().multiply(3));
                }
            }
        }
    }
}
