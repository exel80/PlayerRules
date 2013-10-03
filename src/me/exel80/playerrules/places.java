package me.exel80.playerrules;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

public class places implements Listener {
	public static main plugin;
	public places(main instance) {
		plugin = instance;
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void PlaceEvent(BlockPlaceEvent event)
	{
		Player p = event.getPlayer();
		if(!p.hasPermission("pr.bypass.place"))
		{
			int b = event.getBlock().getTypeId();
			if(plugin.getBlacklist("Blockplace", p.getWorld().getName(), String.valueOf(b)) == true)
			{
				event.setCancelled(true);
				event.getItemInHand().setAmount(0);
				if(plugin.getMessages("NoAllowPlace", String.valueOf(event.getBlock().getType())) != "")
					p.sendMessage(plugin.getMessages("NoAllowPlace", String.valueOf(event.getBlock().getType())));
			}
		}
	}
}
