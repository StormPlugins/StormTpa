package com.fabricioaquiles.stormtpa.commands;

import org.bukkit.entity.Player;

import com.fabricioaquiles.stormtpa.view.ProfileView;

import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;

public class TptoggleCommand {
	
	@Command(
            name = "tptoggle",
            target = CommandTarget.PLAYER
    ) public void tpToggle(Context<Player> sender) {
		
		ProfileView.openInventory(sender.getSender());
		
	}

}
