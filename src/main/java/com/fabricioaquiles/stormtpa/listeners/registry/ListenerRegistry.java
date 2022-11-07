package com.fabricioaquiles.stormtpa.listeners.registry;

import lombok.Data;
import org.bukkit.Bukkit;

import com.fabricioaquiles.stormtpa.StormTpa;
import com.fabricioaquiles.stormtpa.listeners.InventoryClickListener;
import com.fabricioaquiles.stormtpa.listeners.PlayerLoginListener;

@Data(staticConstructor = "of")
public class ListenerRegistry {

    private final StormTpa plugin;

    public void registry() {
        try {
            Bukkit.getPluginManager().registerEvents(new PlayerLoginListener(), plugin);
            Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), plugin);

            Bukkit.getConsoleSender().sendMessage("§b[StormTpa] [Eventos]§f eventos carregados com sucesso.");
        } catch (Throwable t) {
            t.printStackTrace();
            Bukkit.getConsoleSender().sendMessage("§b[StormTpa] [Eventos]§f não foi possível fazer o carregamento dos eventos.");
        }
    }

}