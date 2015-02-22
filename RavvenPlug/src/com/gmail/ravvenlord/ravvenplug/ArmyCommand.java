package com.gmail.ravvenlord.ravvenplug;


import java.util.List;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;

public class ArmyCommand implements CommandExecutor {
    private final RavvenBase plugin;
    private Entity myMob;
    
    public ArmyCommand(RavvenBase plugin) {
        this.plugin = plugin;
    }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] split) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            World actWorld = player.getWorld();
            Entity target = getNearestPlayer(player);
            if (target!=null) {
         	    
            }
            return true;
        } else {
            return false;
        }
    }
    
    public Entity getNearestPlayer(Player p) {
   
    	int radius = 100;
    	double closest = Double.MAX_VALUE;
    	Entity thisOne=null;
    	Location pLoc = p.getLocation();
    	
    	List<Entity> nearbyEntities = p.getNearbyEntities(radius, radius, radius);
    	for (Entity cur : nearbyEntities) {
    		if (cur instanceof Monster) {
    			if (cur.getLocation().distance(pLoc) < closest) {
	    		    closest = cur.getLocation().distance(pLoc);
	    		    thisOne = cur;
    			}
    		}
    	}
    	return thisOne;
    }
}