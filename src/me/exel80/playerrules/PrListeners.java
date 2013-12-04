package me.exel80.PlayerRules;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PrListeners implements Listener {
	public static PrMain plugin;
	public PrListeners(PrMain instance) {
		plugin = instance;
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void BreakEvent(BlockBreakEvent event)
	{
		Player p = event.getPlayer();
		if(!p.hasPermission("pr.bypass.break"))
		{
			int b = event.getBlock().getTypeId();
			
			if(plugin.getConfig().getBoolean("debug") == true)
				p.sendMessage("§c§lBREAK: §7Breaked block id: " + b);
			
			if(plugin.getBlacklist("Break", p.getWorld().getName(), b) == true)
			{
				event.setCancelled(true);
				if(plugin.getMessages("NoAllowBreak", String.valueOf(event.getBlock().getType())) != "")
					p.sendMessage(plugin.getMessages("NoAllowBreak", String.valueOf(event.getBlock().getType())));
				
				plugin.warning(p);
			}
		}
	}

	@EventHandler(priority = EventPriority.LOW)
	public void PlaceEvent(BlockPlaceEvent event)
	{
		Player p = event.getPlayer();
		if(!p.hasPermission("pr.bypass.place"))
		{
			int b = event.getBlock().getTypeId();
			
			if(plugin.getConfig().getBoolean("debug") == true)
				p.sendMessage("§c§lPLACE: §7Placed block id: " + b);
			
			if(plugin.getBlacklist("Place", p.getWorld().getName(), b) == true)
			{
				event.setCancelled(true);
				if(plugin.getMessages("NoAllowPlace", String.valueOf(event.getBlock().getType())) != "")
					p.sendMessage(plugin.getMessages("NoAllowPlace", String.valueOf(event.getBlock().getType())));
				
				plugin.warning(p);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void DropEvent(PlayerDropItemEvent event)
	{
		Player p = event.getPlayer();
		if(!p.hasPermission("pr.bypass.drop"))
		{
			int b = event.getItemDrop().getItemStack().getTypeId();
			
			if(plugin.getConfig().getBoolean("debug") == true)
				p.sendMessage("§c§lDROP: §7Droped item/block id: " + b);
			
			if(plugin.getBlacklist("Drop", p.getWorld().getName(), b) == true)
			{
				event.getItemDrop().remove();
				if(plugin.getMessages("NoAllowDrop", String.valueOf(event.getItemDrop().getItemStack().getType())) != "")
					p.sendMessage(plugin.getMessages("NoAllowDrop", String.valueOf(event.getItemDrop().getItemStack().getType())));
				
				plugin.warning(p);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void UseEvent(PlayerInteractEvent event)
	{
		Player p = event.getPlayer();
		if(!p.hasPermission("pr.bypass.use"))
		{
			try
			{
				int b, h;
				try { b = event.getClickedBlock().getTypeId(); } catch (Exception ex) { b = 0; ex.getStackTrace(); }
				try { h = event.getItem().getType().getId(); } catch (Exception ex) { h = 0; ex.getStackTrace(); }
				
				if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction().equals(Action.PHYSICAL))
				{
					if(plugin.getConfig().getBoolean("debug") == true)
						p.sendMessage("§c§lUSE: §7Clicked block id: " + b + ", item/block on your hand id: " + h);

					if(b == 72 || b == 64 || b == 69 || b == 77 || b == 70 || b == 117 || b == 118 || b == 154 || b == 96 || b == 138 ||
							b == 58 || b == 146 || b == 158 || b == 54 || b == 130 || b == 145 || b == 116 || b == 23 || b == 61 || b == 107)
					{
						if(plugin.getBlacklist("Use", p.getWorld().getName(), b) == true)
						{
							event.setCancelled(true);
							if(plugin.getMessages("NoAllowUse", String.valueOf(b)) != "")
								p.sendMessage(plugin.getMessages("NoAllowUse", String.valueOf(event.getClickedBlock().getType())));
							
							plugin.warning(p);
						}
					}
					if (plugin.getBlacklist("Use", p.getWorld().getName(), h) == true)
					{
						event.setCancelled(true);
						if(plugin.getMessages("NoAllowUse", String.valueOf(h)) != "")
							p.sendMessage(plugin.getMessages("NoAllowUse", String.valueOf(event.getItem().getType())));
						
						plugin.warning(p);
					}
				}
			}
			catch (Exception ex) { ex.getStackTrace(); plugin.log.info(ex.toString()); }
		}
	}
}
