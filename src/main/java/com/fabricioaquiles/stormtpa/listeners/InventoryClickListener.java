package com.fabricioaquiles.stormtpa.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryHolder;

import com.fabricioaquiles.stormtpa.enums.MenuType;
import com.fabricioaquiles.stormtpa.model.User;
import com.fabricioaquiles.stormtpa.utils.GuiHolder;
import com.fabricioaquiles.stormtpa.utils.misc.config.Menus;
import com.fabricioaquiles.stormtpa.utils.misc.data.UserData;
import com.fabricioaquiles.stormtpa.view.ProfileView;

public class InventoryClickListener implements Listener {
	
	@EventHandler
	public void InventoryClick(InventoryClickEvent e) {
		
		Player p = (Player)e.getWhoClicked();
		InventoryHolder holder = e.getInventory().getHolder();
		
		if(!(holder instanceof GuiHolder)) return;
		
		MenuType menu = ((GuiHolder) holder).getType();
		
		if(menu == MenuType.TPA_PROFILE_VIEW) {
			e.setCancelled(true);
			
			User user = UserData.get().getUser(p.getName());
			
			if(e.getSlot() == Menus.get(Menus::profileTpaToggleSlot)) {
				user.setToggle(!user.getToggle());
				ProfileView.openInventory(p);
				return;
			}
		}
		
		
	}

}
