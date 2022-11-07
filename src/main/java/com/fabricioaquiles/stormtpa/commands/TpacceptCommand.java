package com.fabricioaquiles.stormtpa.commands;

import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.annotation.Optional;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.fabricioaquiles.stormtpa.StormTpa;
import com.fabricioaquiles.stormtpa.api.events.TpAcceptEvent;
import com.fabricioaquiles.stormtpa.utils.misc.config.General;
import com.fabricioaquiles.stormtpa.utils.misc.config.Messages;
import com.fabricioaquiles.stormtpa.utils.misc.data.UserData;

import java.util.ArrayList;
import java.util.List;

public class TpacceptCommand {

    @Command(
            name = "tpaccept",
            target = CommandTarget.PLAYER
    ) public void tpaccept(Context<Player> sender, @Optional String targetName) {

        Player p = sender.getSender();

        if (!UserData.get().getRecebidosData().containsKey(p.getName())) {
            p.sendMessage(Messages.get(Messages::tpaSemPendentes));
            return;
        }

        List<String> tpas = new ArrayList<>(UserData.get().getRecebidosData().get(p.getName()));

        if (tpas.size() == 0) {
            p.sendMessage(Messages.get(Messages::tpaSemPendentes));
            return;
        }

        if(targetName == null) {

            String ultimoTpa = tpas.get((tpas.size()-1));

            UserData.get().getEnviadosData().get(ultimoTpa).remove(p.getName());
            UserData.get().getRecebidosData().get(p.getName()).remove(ultimoTpa);

            Player t = Bukkit.getPlayer(ultimoTpa);
            if (t == null) {
                p.sendMessage(Messages.get(Messages::tpacceptOffline).replace("%player%", ultimoTpa));
                return;
            }

            p.sendMessage(Messages.get(Messages::tpacceptSucesso).replace("%player%", ultimoTpa));

            if (!p.hasPermission(General.get(General::tpaNoDelayPermission))) {
                
            	Messages.get(Messages::aceitouTpa).forEach(m -> {
            		t.sendMessage(m.replace("%player%", p.getName()));
            	});
            	
                new BukkitRunnable() {
                    @Override
                    public void run() {

                    	Bukkit.getPluginManager().callEvent(new TpAcceptEvent(p, t));
                        t.teleport(p, PlayerTeleportEvent.TeleportCause.COMMAND);
                        t.sendMessage(Messages.get(Messages::teleportado).replace("%player%", p.getName()));

                    }
                }.runTaskLater(StormTpa.getInstance(), 20L * 3);
                return;
            }

            Bukkit.getPluginManager().callEvent(new TpAcceptEvent(p, t));
            t.teleport(p, PlayerTeleportEvent.TeleportCause.COMMAND);
            t.sendMessage(Messages.get(Messages::teleportado).replace("%player%", p.getName()));

        } else {

            if(p.getName().equalsIgnoreCase(targetName)) {
                p.sendMessage(Messages.get(Messages::tpacceptVoce));
                return;
            }

            if(!tpas.contains(targetName)) {
                p.sendMessage(Messages.get(Messages::tpaSemPendentesPlayer).replace("%player%", targetName));
                return;
            }

            UserData.get().getEnviadosData().get(targetName).remove(p.getName());
            UserData.get().getRecebidosData().get(p.getName()).remove(targetName);

            Player t = Bukkit.getPlayer(targetName);
            if (t == null) {
                p.sendMessage(Messages.get(Messages::tpacceptOffline).replace("%player%", targetName));
                return;
            }
            
            p.sendMessage(Messages.get(Messages::tpacceptSucesso).replace("%player%", p.getName()));

            if (!t.hasPermission(General.get(General::tpaNoDelayPermission))) {
            	
            	Messages.get(Messages::aceitouTpa).forEach(m -> {
            		t.sendMessage(m.replace("%player%", p.getName()));
            	});
            	
                new BukkitRunnable() {
                    @Override
                    public void run() {

                    	Bukkit.getPluginManager().callEvent(new TpAcceptEvent(p, t));
                        t.teleport(p, PlayerTeleportEvent.TeleportCause.COMMAND);
                        t.sendMessage(Messages.get(Messages::teleportado).replace("%player%", p.getName()));

                    }
                }.runTaskLater(StormTpa.getInstance(), 20L * 3);
                return;
            }

            Bukkit.getPluginManager().callEvent(new TpAcceptEvent(p, t));
            t.teleport(p, PlayerTeleportEvent.TeleportCause.COMMAND);
            t.sendMessage(Messages.get(Messages::teleportado).replace("%player%", p.getName()));

        }

    }
}