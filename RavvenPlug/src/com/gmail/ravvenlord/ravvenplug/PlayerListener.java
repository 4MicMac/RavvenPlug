package com.gmail.ravvenlord.ravvenplug;

import org.bukkit.Location;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.*;
import org.bukkit.inventory.*;
import org.bukkit.util.Vector;
import org.bukkit.Material;

public class PlayerListener implements Listener {
    private final RavvenBase plugin;

    public PlayerListener(RavvenBase instance) {
        plugin = instance;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        plugin.getLogger().info(event.getPlayer().getName() + " joined the server! :D");
    }


    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        plugin.getLogger().info(event.getPlayer().getName() + " left the server! :'(");
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        if (plugin.isDebugging(event.getPlayer())) {
            Location from = event.getFrom();
            Location to = event.getTo();
            plugin.getLogger().info(String.format("From %.2f,%.2f,%.2f to %.2f,%.2f,%.2f", from.getX(), from.getY(), from.getZ(), to.getX(), to.getY(), to.getZ()));
        }
    }
    
    @EventHandler
    public void PlayerInteractEvent(PlayerInteractEvent event){
        ItemStack Pistol = new ItemStack(Material.STICK);
        Player player = event.getPlayer();
        ShapelessRecipe recipe = new ShapelessRecipe(Pistol).addIngredient(Material.STICK).addIngredient(Material.FLINT_AND_STEEL);
        if(player.hasPermission("rw.pistol")){
        	if(event.getAction().equals(Action.RIGHT_CLICK_AIR) || (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) && recipe.equals(Pistol)){
        		Arrow thisArrow = player.launchProjectile(Arrow.class);
        		Vector thisVector = thisArrow.getVelocity();
        		thisArrow.remove();
        		thisVector.multiply(4);
        		double newDest = 1;

        		for (double i=-2; i<=2; i++) {
	        			
	        		Vector newVector = thisVector.clone().normalize();
	        		double nextDest = Math.toRadians(newDest*i);
	        		double sinDest = Math.sin(nextDest);
	        		double cosDest = Math.cos(nextDest);
	
	//        		plugin.getLogger().info(String.format("oVector %.2f,%.2f,%.2f", thisVector.getX(), thisVector.getY(), thisVector.getZ()));
	//              plugin.getLogger().info(String.format("nVector %.2f,%.2f,%.2f", newVector.getX(), newVector.getY(), newVector.getZ()));
	
	        		newVector.setX(newVector.getX()*cosDest-newVector.getZ()*sinDest);
	        		newVector.setZ(newVector.getX()*sinDest+newVector.getZ()*cosDest);
	//              plugin.getLogger().info(String.format("dVector %.2f,%.2f,%.2f", newVector.getX(), newVector.getY(), newVector.getZ()));
	        		newVector.multiply(thisVector.length());
	//                plugin.getLogger().info(String.format("rVector %.2f,%.2f,%.2f", newVector.getX(), newVector.getY(), newVector.getZ()));
	                player.launchProjectile(Arrow.class).setVelocity(newVector);
        		}
        	}
        }
    }
}