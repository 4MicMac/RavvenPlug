package com.gmail.ravvenlord.ravvenplug;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.entity.*;

public class ArrowListener implements Listener {
 
	@EventHandler
	public void onShoot(ProjectileLaunchEvent event) {
		if(event.getEntity() instanceof Arrow) {
			Arrow arrow = (Arrow) event.getEntity();
			if(arrow.getShooter() instanceof Player) {
				Player shooter = (Player) arrow.getShooter();
				if(shooter.getItemInHand().getType() == Material.BOW) {
					event.setCancelled(true);
					shooter.launchProjectile(Arrow.class).setVelocity(arrow.getVelocity().multiply(4));
				}
			}
		}
	}
	
}

