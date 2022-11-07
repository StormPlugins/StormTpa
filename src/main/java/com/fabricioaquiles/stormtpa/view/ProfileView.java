package com.fabricioaquiles.stormtpa.view;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.fabricioaquiles.stormtpa.enums.MenuType;
import com.fabricioaquiles.stormtpa.model.User;
import com.fabricioaquiles.stormtpa.utils.GuiHolder;
import com.fabricioaquiles.stormtpa.utils.ItemBuilder;
import com.fabricioaquiles.stormtpa.utils.Utils;
import com.fabricioaquiles.stormtpa.utils.misc.config.Menus;
import com.fabricioaquiles.stormtpa.utils.misc.data.UserData;

public class ProfileView {
	
	public static void openInventory(
			Player p
	) {
		
		User user = UserData.get().getUser(p.getName());
		Inventory inv = Bukkit.createInventory(new GuiHolder(MenuType.TPA_PROFILE_VIEW), Menus.get(Menus::profileSize), Menus.get(Menus::profileName));
		
		inv.setItem(Menus.get(Menus::profileTpaSlot), new ItemBuilder(Utils.getUtils().getItem(p, Menus.get(Menus::profileTpaItem)))
				.setName(Menus.get(Menus::profileTpaName))
				.setLore(Menus.get(Menus::profileTpaLore))
				.toItemStack());
		
		if(user.getToggle()) {
			inv.setItem(Menus.get(Menus::profileTpaToggleSlot), new ItemBuilder(Material.STAINED_GLASS_PANE,1,(short)5)
					.setName("§aAtivado")
					.toItemStack());
		} else {
			inv.setItem(Menus.get(Menus::profileTpaToggleSlot), new ItemBuilder(Material.STAINED_GLASS_PANE,1,(short)14)
					.setName("§cDesativado")
					.toItemStack());
		}
		
		p.openInventory(inv);
		
	}

}
