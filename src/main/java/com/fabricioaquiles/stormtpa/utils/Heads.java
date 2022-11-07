package com.fabricioaquiles.stormtpa.utils;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.stormplugins.manager.utils.Reflection;
import de.tr7zw.nbtapi.NBTCompound;
import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.NBTListCompound;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Base64;
import java.util.UUID;

public class Heads {

    public static ItemStack getSkull(String s) {
        final String texture = "http://textures.minecraft.net/texture/"+s, version = Reflection.getVersion();
        final GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        final byte[] encodedData = Base64.getEncoder().encode(String.format("{textures:{SKIN:{url:\"%s\"}}}", texture).getBytes());
        final Property textures = new Property("textures", new String(encodedData));
        profile.getProperties().put("textures", textures);

        final ItemStack skull = new ItemStack((version.contains("1_8") || version.contains("1_9") || version.contains("1_10") || version.contains("1_11") || version.contains("1_12")) ? Material.valueOf("SKULL_ITEM") : Material.valueOf("LEGACY_SKULL_ITEM"), 1, (short) 3);
        final NBTItem nbtItem = new NBTItem(skull);
        final NBTCompound skullCompound = nbtItem.addCompound("SkullOwner");
        final NBTListCompound textureCompound = skullCompound.addCompound("Properties").getCompoundList("textures").addCompound();

        skullCompound.setString("Name", profile.getName());
        skullCompound.setString("Id", profile.getId().toString());
        textureCompound.setString("Signature", textures.getSignature());
        textureCompound.setString("Value", textures.getValue());
        return nbtItem.getItem();
    }


}
