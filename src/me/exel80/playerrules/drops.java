package me.exel80.playerrules;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

public class drops implements Listener {
	public static main plugin;
	public drops(main instance) {
		plugin = instance;
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void DropEvent(PlayerDropItemEvent event)
	{
		Player p = event.getPlayer();
		if(!p.hasPermission("pr.bypass.drop"))
		{
			int b = event.getItemDrop().getItemStack().getTypeId();
			if(plugin.getBlacklist("Dropitem", p.getWorld().getName(), String.valueOf(b)) == true)
			{
				event.getItemDrop().remove();
				if(plugin.getMessages("NoAllowDrop", String.valueOf(event.getItemDrop().getItemStack().getType())) != "")
					p.sendMessage(plugin.getMessages("NoAllowDrop", String.valueOf(event.getItemDrop().getItemStack().getType())));
			}
		}
	}
}
