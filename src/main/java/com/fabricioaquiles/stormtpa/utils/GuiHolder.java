package com.fabricioaquiles.stormtpa.utils;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import com.fabricioaquiles.stormtpa.enums.MenuType;

public class GuiHolder implements InventoryHolder {

    private MenuType type;

    public GuiHolder(MenuType type) {
        this.type = type;
    }

    public MenuType getType() {
        return this.type;
    }

    public Inventory getInventory() {
        return null;
    }
}
