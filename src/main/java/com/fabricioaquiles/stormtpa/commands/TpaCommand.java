package com.fabricioaquiles.stormtpa.commands;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.chat.*;

import com.fabricioaquiles.stormtpa.StormTpa;
import com.fabricioaquiles.stormtpa.utils.misc.config.General;
import com.fabricioaquiles.stormtpa.utils.misc.config.Messages;
import com.fabricioaquiles.stormtpa.utils.misc.data.UserData;

import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;

public class TpaCommand {
	
	@Command(
            name = "tpa",
            usage = "tpa <jogador>",
            target = CommandTarget.PLAYER
    ) public void tpa(Context<Player> sender, String targetName) {

        Player p = sender.getSender();
        Player target = Bukkit.getPlayerExact(targetName);
        
        if(target == null) {
            p.sendMessage("§cNão foi encontrado nenhum jogador com esse nickname.");
            return;
        }

        if(target.getName() == p.getName()) {
            p.sendMessage("§cVocê não pode enviar um pedido de teleporte, para você mesmo.");
            return;
        }
        
        if(!UserData.get().getUser(target.getName()).getToggle()) {
        	p.sendMessage(Messages.get(Messages::tpaJogadorDesativado).replace("%player%", target.getName()));
        	return;
        }

        if (UserData.get().getEnviadosData().containsKey(p.getName())) {

            if (UserData.get().getEnviadosData().get(p.getName()).contains(target.getName())) {
            	p.sendMessage(Messages.get(Messages::tpaJaPossui).replace("%player%", target.getName()));
                return;
            }

        } else {
            UserData.get().getEnviadosData().put(p.getName(), new ArrayList<>());
        }

        if (UserData.get().getCooldownData().containsKey(p.getName())) {
            if (System.currentTimeMillis() < UserData.get().getCooldownData().get(p.getName())) {
                p.sendMessage(Messages.get(Messages::tpaCooldown));
                return;
            }
        }
        
        if (!UserData.get().getRecebidosData().containsKey(target.getName())) UserData.get().getRecebidosData().put(target.getName(), new ArrayList<>());
        UserData.get().getRecebidosData().get(target.getName()).add(p.getName());
        UserData.get().getEnviadosData().get(p.getName()).add(target.getName());
        
        UserData.get().getCooldownData().put(p.getName(), (System.currentTimeMillis() + ((1000L * 30)*General.get(General::tpaCooldownTime))));
        
        TextComponent textRecebido = new TextComponent("");
        TextComponent textEnviado = new TextComponent("");
        
        Messages.get(Messages::tpaRecebido).forEach(m -> {
        	
        	if(m.contains("%aqui_aceitar%")) {
        		
        		TextComponent text = new TextComponent(m.replace("%aqui_aceitar%", "§a§lAQUI"));
        		textRecebido.addExtra(text);
            	
            	BaseComponent[] textos = (new ComponentBuilder("§aCllique para aceitar.")).create();
                HoverEvent passarMouse = new HoverEvent(HoverEvent.Action.SHOW_TEXT, textos);
                
                text.setHoverEvent(passarMouse);
                text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpaccept " + p.getName()));
        		return;
        	} else if(m.contains("%aqui_negar%")) {
        		TextComponent text = new TextComponent(m.replace("%aqui_negar%", "§c§lAQUI"));
        		textRecebido.addExtra(text);
            	
            	BaseComponent[] textos = (new ComponentBuilder("§cCllique para negar.")).create();
                HoverEvent passarMouse = new HoverEvent(HoverEvent.Action.SHOW_TEXT, textos);
                
                text.setHoverEvent(passarMouse);
                text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpdeny " + p.getName()));
        		return;
        	}
        	
        	TextComponent text = new TextComponent(m.replace("%player%", p.getName()));
        	textRecebido.addExtra(text);
        });
        
        Messages.get(Messages::tpaEnviado).forEach(m -> {
        	
        	if(m.contains("%aqui%")) {
        		
        		TextComponent text = new TextComponent(m.replace("%aqui%", "§c§lAQUI"));
        		textEnviado.addExtra(text);
            	
            	BaseComponent[] textos = (new ComponentBuilder("§cCllique para cancelar.")).create();
                HoverEvent passarMouse = new HoverEvent(HoverEvent.Action.SHOW_TEXT, textos);
                
                text.setHoverEvent(passarMouse);
                text.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tpcancel " + target.getName()));
        		return;
        	}
        	
        	TextComponent text = new TextComponent(m.replace("%player%", target.getName()));
        	textEnviado.addExtra(text);
        });
        
        p.spigot().sendMessage(textEnviado);
        target.spigot().sendMessage(textRecebido);
        
        new BukkitRunnable() {
            @Override
            public void run() {

                if (UserData.get().getEnviadosData().get(p.getName()).contains(target.getName())) {

                	UserData.get().getEnviadosData().get(p.getName()).remove(target.getName());
                    UserData.get().getRecebidosData().get(target.getName()).remove(p.getName());

                    if (p != null && target != null) {
                        p.sendMessage(Messages.get(Messages::tpaJogadorExpirou).replace("%player%", target.getName()));
                        target.sendMessage(Messages.get(Messages::tpaAlvoExpirou).replace("%player%", p.getName()));
                    }
                }
            }
        }.runTaskLater(StormTpa.getInstance(), 20L * 60 * General.get(General::tpaTimeExpire));
        
	}

}
