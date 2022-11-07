package com.fabricioaquiles.stormtpa.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import com.fabricioaquiles.stormtpa.utils.misc.data.UserData;

public class PlayerLoginListener implements Listener {
	
	@EventHandler
	public void PlayerLogin(PlayerLoginEvent e) {
		
		UserData.get().registryUser(e.getPlayer().getName());
		
	}

}
