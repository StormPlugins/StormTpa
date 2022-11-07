package com.fabricioaquiles.stormtpa.utils;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import lombok.Getter;

public class Utils {
	
	@Getter private static final Utils utils = new Utils();

	public ItemStack getItem(Player p, String key) {
        if (key.contains(":")) {
            ItemStack item = new ItemStack(Material.getMaterial(key.split(":")[0]));
            item.setDurability(Short.parseShort(key.split(":")[1]));
            return item;
        } else if (key.contains("%jogador%")) {
            ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
            SkullMeta meta = (SkullMeta) item.getItemMeta();
            meta.setOwner(p.getName());
            item.setItemMeta(meta);
            return item;
        } else {
            ItemStack item = new ItemStack(Heads.getSkull(key));
            return item;
        }
    }

}
