package me.exel80.playerrules;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin {
	public Logger log = Logger.getLogger("Minecraft");
	private final me.exel80.playerrules.breaks breaks = new me.exel80.playerrules.breaks(this);
	private final me.exel80.playerrules.places places = new me.exel80.playerrules.places(this);
	private final me.exel80.playerrules.drops drops = new me.exel80.playerrules.drops(this);
	private final me.exel80.playerrules.uses uses = new me.exel80.playerrules.uses(this);
	//private final me.exel80.playerrules.crafts crafts = new me.exel80.playerrules.crafts(this);
	
	@Override
	public void onDisable()
	{
		
	}
	@Override
	public void onEnable()
	{
		try {
		    Metrics metrics = new Metrics(this);
		    metrics.start();
		} catch (IOException e) {
		    // Failed to submit the stats :-(
		}
		listener();
	}
	
	private void listener()
	{
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(breaks, this);
		pm.registerEvents(places, this);
		pm.registerEvents(drops, this);
		pm.registerEvents(uses, this);
		//pm.registerEvents(crafts, this);
	}
	
	public Boolean getBlacklist(String name, String world, String id)
	{
		File path = new File(getDataFolder(), "config.yml");
			try	{ this.getConfig().load(path); }
			catch (Exception ex) { log.info("Cant load config.yml!!!"); ex.getStackTrace(); }
		
		if(this.getConfig().contains("World." + world))
		{
			if(this.getConfig().getStringList("World." + world + "." + name).contains(id) || this.getConfig().getStringList("World." + world + "." + name).contains("all"))
			{
				return true;
			}
		}
		else if(this.getConfig().getStringList("Global." + name).contains(id) || this.getConfig().getStringList("Global." + name).contains("all"))
		{
			return true;
		}
		return false;
	}
	public String getSettings(String name)
	{
		File path = new File(getDataFolder(), "config.yml");
			try	{ this.getConfig().load(path); }
			catch (Exception ex) { log.info("Cant load config.yml!!!"); ex.getStackTrace(); }
		return this.getConfig().getString("Settings." + name);
	}
	public String getMessages(String name, String IBname)
	{
		File path = new File(getDataFolder(), "config.yml");
			try	{ this.getConfig().load(path); }
			catch (Exception ex) { log.info("Cant load config.yml!!!"); ex.getStackTrace(); }
		if(this.getConfig().getBoolean("Messages.Display") == true && this.getConfig().getBoolean("Messages.Logo") == false)
			return this.getConfig().getString("Messages." + name).replace("%NAME", IBname);
		else if(this.getConfig().getBoolean("Messages.Display") == true)
			return "§2[PlayerRules]§f " + this.getConfig().getString("Messages." + name).replace("%NAME", IBname);
		else
			return "";
	}
}
