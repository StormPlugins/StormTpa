package com.fabricioaquiles.stormtpa.utils.misc.config.registry;

import org.bukkit.Bukkit;

import com.fabricioaquiles.stormtpa.StormTpa;
import com.fabricioaquiles.stormtpa.utils.misc.config.General;
import com.fabricioaquiles.stormtpa.utils.misc.config.Menus;
import com.fabricioaquiles.stormtpa.utils.misc.config.Messages;
import com.henryfabio.minecraft.configinjector.bukkit.injector.BukkitConfigurationInjector;
import lombok.Data;

@Data(staticConstructor = "of")
public class ConfigurationRegistry {

    private final StormTpa plugin;

    public void registry() {
    	try {
    		BukkitConfigurationInjector bukkitConfigurationInjector = new BukkitConfigurationInjector(plugin);

            bukkitConfigurationInjector.saveDefaultConfiguration(plugin,
                    "mensagens.yml");
            
            bukkitConfigurationInjector.saveDefaultConfiguration(plugin,
                    "menus.yml");

            bukkitConfigurationInjector.injectConfiguration(General.instance(), Menus.instance(), Messages.instance());
            
            Bukkit.getConsoleSender().sendMessage("§b[StormTpa] [ConfigurationRegistry]§f configurações carregadas com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			Bukkit.getConsoleSender().sendMessage("§c[StormTpa] [ConfigurationRegistry]§f não foi possível carregar as configurações.");
		}
        
    }

}