package me.exel80.playerrules;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class uses implements Listener {
	public static main plugin;
	public uses(main instance) {
		plugin = instance;
	}
	
	@EventHandler(priority = EventPriority.LOW)
	public void UseEvent(PlayerInteractEvent event)
	{
		Player p = event.getPlayer();
		if(!p.hasPermission("pr.bypass.use"))
		{
			try
			{
				if(event.getClickedBlock().getTypeId() != 0)
				{
					int b = event.getClickedBlock().getTypeId();
					int h; try { h = event.getItem().getType().getId(); } catch (Exception ex) { h = 0; ex.getStackTrace(); }
					
					if(plugin.getBlacklist("Useitem", p.getWorld().getName(), String.valueOf(b)) == true && event.getAction() == Action.RIGHT_CLICK_BLOCK)
					{
						event.setCancelled(true);
						if(plugin.getMessages("NoAllowUse", String.valueOf(event.getClickedBlock().getType())) != "")
							p.sendMessage(plugin.getMessages("NoAllowUse", String.valueOf(event.getClickedBlock().getType())));
					}
					else if (plugin.getBlacklist("Useitem", p.getWorld().getName(), String.valueOf(h)) == true && event.getAction() == Action.RIGHT_CLICK_BLOCK)
					{
						event.setCancelled(true);
						if(plugin.getMessages("NoAllowUse", String.valueOf(event.getClickedBlock().getType())) != "")
							p.sendMessage(plugin.getMessages("NoAllowUse", String.valueOf(event.getItem().getType())));
					}
				}
			}
			catch (Exception ex) { ex.getStackTrace(); }
		}
	}
	
	/*@EventHandler(priority = EventPriority.LOW)
	public void UseEvent(PlayerInteractEntityEvent event)
	{
		Player p = event.getPlayer();
		if(!p.hasPermission("pr.bypass.use"))
		{
			p.sendMessage(event.getRightClicked()+"");
			try
			{
				if(event.getRightClicked().getType().getTypeId() != 0)
				{
					int b = event.getRightClicked().getType().getTypeId();
					//int h; try { h = event.getItem().getType().getId(); } catch (Exception ex) { h = 0; ex.getStackTrace(); }
					
					if(plugin.getBlacklist("Useitem", p.getWorld().getName(), String.valueOf(b)) == true)
					{
						event.setCancelled(true);
						if(plugin.getMessages("NoAllowUse", String.valueOf(event.getRightClicked().getType())) != "")
							p.sendMessage(plugin.getMessages("NoAllowUse", String.valueOf(event.getRightClicked().getType())));
					}
					/*else if (plugin.getBlacklist("Useitem", p.getWorld().getName(), String.valueOf(h)) == true && event.getAction() == Action.RIGHT_CLICK_BLOCK)
					{
						event.setCancelled(true);
						if(plugin.getMessages("NoAllowUse", String.valueOf(event.getClickedBlock().getType())) != "")
							p.sendMessage(plugin.getMessages("NoAllowUse", String.valueOf(event.getItem().getType())));
					}
				}
			}
			catch (Exception ex) { ex.getStackTrace(); }
		}*/
	//}
}
