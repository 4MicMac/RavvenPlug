package com.gmail.ravvenlord.ravvenplug;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.HashMap;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;

public class RavvenBase extends JavaPlugin {
//		private final PlayerListener playerListener = new PlayerListener(this);
	    private final BlockListener blockListener = new BlockListener();
	    private final ArrowListener arrowListener = new ArrowListener();
	    private final HashMap<Player, Boolean> debugees = new HashMap<Player, Boolean>();

	    @Override
	    public void onDisable() {
	        getLogger().info("Goodbye world!");
	    }
	
	    @Override
	    public void onEnable() {
	        // TODO: Place any custom enable code here including the registration of any events
	        // Register our events
	        PluginManager pm = getServer().getPluginManager();
//	        pm.registerEvents(playerListener, this);
	        pm.registerEvents(blockListener, this);
	        pm.registerEvents(arrowListener, this);
	
	        // Register our commands
	        getCommand("pos").setExecutor(new PosCommand()); 
	        getCommand("debug").setExecutor(new DebugCommand(this));
	        getCommand("army").setExecutor(new ArmyCommand(this));
	
	        // EXAMPLE: Custom code, here we just output some info so we can check all is well
	        PluginDescriptionFile pdfFile = this.getDescription();
	        getLogger().info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
	        loadConfiguration();
	        String itemListPath = "RavvenBase.Items";
	        for (String path : getConfig().getConfigurationSection(itemListPath).getKeys(false)) {
	        	String itemPath = itemListPath + "." + path;
	        	String name = getConfig().getString(itemPath + ".Name");
	        	int id = getConfig().getInt(itemPath + ".Slot");
	        	int count = getConfig().getInt(itemPath + ".Object");
	        	String cmd = getConfig().getString(itemPath + ".Command");
		        getLogger().info( "{Objects] "+ name + "(" + id + "/" + count + ") = " + cmd );
	        }
	    }
		
	    public boolean isDebugging(final Player player) {
	        if (debugees.containsKey(player)) {
	            return debugees.get(player);
	        } else {
	            return false;
	        }
	    }
	
	    public void setDebugging(final Player player, final boolean value) {
	        debugees.put(player, value);
	    }
	    

	    public void loadConfiguration(){
	    	//See "Creating you're defaults"
	    	// getConfig().options().copyDefaults(false);
	    	// NOTE: You do not have to use "plugin." if the class extends the java plugin
	    	//Save the config whenever you manipulate it
	    	// saveConfig();
	    }
	}
