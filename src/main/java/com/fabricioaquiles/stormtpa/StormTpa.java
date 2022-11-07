package com.fabricioaquiles.stormtpa;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.fabricioaquiles.stormtpa.commands.registry.CommandRegistry;
import com.fabricioaquiles.stormtpa.components.UserManager;
import com.fabricioaquiles.stormtpa.listeners.registry.ListenerRegistry;
import com.fabricioaquiles.stormtpa.utils.misc.config.registry.ConfigurationRegistry;
import com.fabricioaquiles.stormtpa.utils.misc.data.UserData;
import com.fabricioaquiles.stormtpa.utils.misc.sql.SQLProvider;
import com.henryfabio.sqlprovider.connector.SQLConnector;
import com.henryfabio.sqlprovider.executor.SQLExecutor;

import lombok.Getter;

@Getter
public final class StormTpa extends JavaPlugin {
	
	@Getter
	private static StormTpa instance;
	
	private SQLConnector sqlConnector;
	public SQLExecutor sqlExecutor;
	private UserManager userManager;
	
	public void onEnable() {
		instance = this;
		saveDefaultConfig();
		
		ConfigurationRegistry.of(instance).registry();
		
		sqlConnector = SQLProvider.of(instance).setup();
		sqlExecutor = new SQLExecutor(sqlConnector);
		
		userManager = new UserManager(sqlExecutor);
		userManager.setup();
		
		CommandRegistry.of(instance).registry();
		ListenerRegistry.of(instance).registry();
		
		Bukkit.getConsoleSender().sendMessage("§b[StormTpa]§f plugin iniciado com sucesso.");
		
	}
	
	public void onDisable() {
		
		UserData.get().getUserData().values().forEach(e -> {
			userManager.saveUser(e);
		});
		
	}

}
