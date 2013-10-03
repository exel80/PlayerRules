package me.exel80.playerrules;

import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

public class crafts implements Listener {
	public static main plugin;
	public crafts(main instance) {
		plugin = instance;
	}
	
	/*@EventHandler(priority = EventPriority.LOW)
	public void InventoryClick(InventoryClickEvent event)
	{
		if(!event.isCancelled())
		{
			HumanEntity ent = event.getWhoClicked();
			
			if(ent instanceof Player)
			{
				Inventory inv = event.getInventory();
					if(inv instanceof AnvilInventory)
					{
						InventoryView view = event.getView();
						int rawSlot = event.getRawSlot();
						if(rawSlot == view.convertSlot(rawSlot)){
							if(rawSlot == 2)
							{
							ItemStack item = event.getCurrentItem();
								if(item != null)
								{
									int b = item.getTypeId();
									Player p = (Player) event.getWhoClicked();
									plugin.log.info("ASD");
									if(plugin.getBlacklist("Enchantitem", p.getWorld().getName(), String.valueOf(b)) == true)
									{
										event.setCancelled(true);
										if(plugin.getMessages("NoAllowEnchant", String.valueOf(item.getType())) != "")
											p.sendMessage(plugin.getMessages("NoAllowEnchant", String.valueOf(item.getType())));
										item.setAmount(0);
										event.getInventory().remove(item.getType());
									}
								}
							}
						}
					}
			}
		}
	}*/
	
	/*@EventHandler(priority = EventPriority.LOW)
	public void CraftEvent(CraftItemEvent event)
	{
		Player p = (Player) event.getView().getPlayer();
		if(!p.hasPermission("pr.bypass.craft"))
		{
			int b = event.getCurrentItem().getType().getId();
			if(plugin.getBlacklist("Craftitem", p.getWorld().getName(), String.valueOf(b)) == true)
			{
				if(plugin.getMessages("NoAllowCraft", String.valueOf(event.getCurrentItem().getType())) != "")
					p.sendMessage(plugin.getMessages("NoAllowCraft", String.valueOf(event.getCurrentItem().getType())));
				event.setCancelled(true);
				event.getInventory().remove(b);
				event.getInventory().remove(b);
				event.getInventory().remove(b);
				event.getInventory().remove(b);
			}
		}
	}*/
}
