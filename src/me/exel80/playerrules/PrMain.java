package me.exel80.PlayerRules;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class PrMain extends JavaPlugin {
	public Logger log = Logger.getLogger("Minecraft");
	private final me.exel80.PlayerRules.PrListeners LS = new me.exel80.PlayerRules.PrListeners(this);
	private HashMap<String, Integer> XWARNS = new HashMap<String, Integer>();
	private HashMap<String, Integer> WARNS = new HashMap<String, Integer>();
	
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
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(LS, this);
	}
	
	// OTHER THINGS!
	public void warning(Player p)
	{
		if(getWarns("WarnEnable") == "true")
		{
			if(!XWARNS.containsKey(p.getName()))
			{
				XWARNS.put(p.getName(), 1);
			}
			else
			{
				int Wnew = 1 + XWARNS.get(p.getName());
				XWARNS.put(p.getName(), Wnew);
			}
			
			if(XWARNS.get(p.getName()) == Integer.parseInt(getWarns("XWarn")))
			{
				XWARNS.put(p.getName(), 0);
				if(!WARNS.containsKey(p.getName()))
				{
					WARNS.put(p.getName(), 1);
					if(WARNS.get(p.getName()) == Integer.parseInt(getWarns("MaxWarn")))
					{
						WARNS.put(p.getName(), 0);
						p.kickPlayer("Too much spamming!");
					}
				}
				else
				{
					int Wnew = 1 + WARNS.get(p.getName());
					WARNS.put(p.getName(), Wnew);
					if(WARNS.get(p.getName()) == Integer.parseInt(getWarns("MaxWarn")) || Integer.parseInt(getWarns("MaxWarn")) == 0 )
					{
						WARNS.put(p.getName(), 0);
						p.kickPlayer("Too much spamming!");
					}
				}
				p.sendMessage(getWarns("Message").replace("%WARNSTATUS", WARNS.get(p.getName()).toString()).replace("%MAXWARN", getWarns("MaxWarn"))  );
			}
		}
	}
	
	public Boolean getBlacklist(String name, String world, int id)
	{
		File path = new File(getDataFolder(), "config.yml");
		try	{ this.getConfig().load(path); }
		catch (Exception ex) { log.info("Cant load config.yml!!!"); ex.getStackTrace(); }
		
		if(this.getConfig().contains("World." + world))
		{
			if(this.getConfig().getStringList("World." + world + "." + name).contains(String.valueOf(id)) || this.getConfig().getStringList("World." + world + "." + name).contains("all"))
			{
				return true;
			}
		}
		else if(this.getConfig().getStringList("Global." + name).contains(String.valueOf(id)) || this.getConfig().getStringList("Global." + name).contains("all"))
		{
			return true;
		}
		return false;
	}
	public String getWarns(String name)
	{
		File path = new File(getDataFolder(), "config.yml");
		try	{ this.getConfig().load(path); }
		catch (Exception ex) { log.info("Cant load config.yml!!!"); ex.getStackTrace(); }
		return this.getConfig().getString("Warning." + name);
	}
	public String getMessages(String name, String IBname)
	{
		File path = new File(getDataFolder(), "config.yml");
			try	{ this.getConfig().load(path); }
			catch (Exception ex) { log.info("Cant load config.yml!!!"); ex.getStackTrace(); }
		if(this.getConfig().getBoolean("Messages.Display") == true && this.getConfig().getBoolean("Messages.Prefix") == false)
			return this.getConfig().getString("Messages." + name).replace("%NAME", IBname);
		else if(this.getConfig().getBoolean("Messages.Display") == true)
			return "§2[PlayerRules]§f " + this.getConfig().getString("Messages." + name).replace("%NAME", IBname);
		else
			return "";
	}
}
