package com.fabricioaquiles.stormtpa.commands;

import me.saiintbrisson.minecraft.command.annotation.Command;
import me.saiintbrisson.minecraft.command.annotation.Optional;
import me.saiintbrisson.minecraft.command.command.Context;
import me.saiintbrisson.minecraft.command.target.CommandTarget;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.fabricioaquiles.stormtpa.api.events.TpDenyEvent;
import com.fabricioaquiles.stormtpa.utils.misc.config.Messages;
import com.fabricioaquiles.stormtpa.utils.misc.data.UserData;

import java.util.List;

public class TpdenyCommand {

    @Command(
            name = "tpdeny",
            target = CommandTarget.PLAYER
    ) public void tpdeny(Context<Player> sender, @Optional String targetName) {

        Player p = sender.getSender();

        if (!UserData.get().getRecebidosData().containsKey(p.getName())) {
            p.sendMessage(Messages.get(Messages::tpaSemPendentes));
            return;
        }

        List<String> tpas = UserData.get().getRecebidosData().get(p.getName());

        if (tpas.size() == 0) {
            p.sendMessage(Messages.get(Messages::tpaSemPendentes));
            return;
        }

        if(targetName == null) {

        	String ultimoTpa = tpas.get(tpas.size()-1);

            UserData.get().getEnviadosData().get(ultimoTpa).remove(p.getName());
            UserData.get().getRecebidosData().get(p.getName()).remove(ultimoTpa);

            Player s = Bukkit.getPlayer(ultimoTpa);
            p.sendMessage(Messages.get(Messages::tpdenySucesso).replace("%player%", ultimoTpa));
            if (s != null) s.sendMessage(Messages.get(Messages::tpdenyRecusou).replace("%player%", p.getName()));
            Bukkit.getPluginManager().callEvent(new TpDenyEvent(p, s));
            
        } else {

            if(p.getName().equalsIgnoreCase(targetName)) {
                p.sendMessage(Messages.get(Messages::tpdenyVoce));
                return;
            }

            if(!tpas.contains(targetName)) {
                p.sendMessage(Messages.get(Messages::tpaSemPendentesPlayer).replace("%player%", targetName));
                return;
            }

            UserData.get().getEnviadosData().get(targetName).remove(p.getName());
            UserData.get().getRecebidosData().get(p.getName()).remove(targetName);

            Player s = Bukkit.getPlayer(targetName);
            p.sendMessage(Messages.get(Messages::tpdenySucesso).replace("%player%", targetName));
            if (s != null) s.sendMessage(Messages.get(Messages::tpdenyRecusou).replace("%player%", p.getName()));
            Bukkit.getPluginManager().callEvent(new TpDenyEvent(p, s));
            
        }

    }

}