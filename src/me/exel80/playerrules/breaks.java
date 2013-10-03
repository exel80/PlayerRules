package me.exel80.playerrules;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class breaks implements Listener {
	public static main plugin;
	public breaks(main instance) {
		plugin = instance;
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void BreakEvent(BlockBreakEvent event)
	{
		Player p = event.getPlayer();
		if(!p.hasPermission("pr.bypass.break"))
		{
			int b = event.getBlock().getTypeId();
			if(plugin.getBlacklist("Blockbreak", p.getWorld().getName(), String.valueOf(b)) == true)
			{
				event.setCancelled(true);
				if(plugin.getMessages("NoAllowBreak", String.valueOf(event.getBlock().getType())) != "")
					p.sendMessage(plugin.getMessages("NoAllowBreak", String.valueOf(event.getBlock().getType())));
			}
		}
	}
}