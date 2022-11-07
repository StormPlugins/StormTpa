package com.fabricioaquiles.stormtpa.commands.registry;

import lombok.Data;
import me.saiintbrisson.bukkit.command.BukkitFrame;
import org.bukkit.Bukkit;

import com.fabricioaquiles.stormtpa.StormTpa;
import com.fabricioaquiles.stormtpa.commands.TpaCommand;
import com.fabricioaquiles.stormtpa.commands.TpacceptCommand;
import com.fabricioaquiles.stormtpa.commands.TpcancelCommand;
import com.fabricioaquiles.stormtpa.commands.TpdenyCommand;
import com.fabricioaquiles.stormtpa.commands.TptoggleCommand;

@Data(staticConstructor = "of")
public class CommandRegistry {

    private final StormTpa plugin;

    public void registry() {
        try {
            BukkitFrame bukkitFrame = new BukkitFrame(plugin);

            bukkitFrame.registerCommands(
                    new TpacceptCommand(),
                    new TpaCommand(),
                    new TpcancelCommand(),
                    new TpdenyCommand(),
                    new TptoggleCommand()
            );

            Bukkit.getConsoleSender().sendMessage("§b[StormTpa] [Comandos] §fcomandos carregados com sucesso.");
        } catch (Throwable t) {
            Bukkit.getConsoleSender().sendMessage("§b[StormTpa] [Comandos] §fnão foi possível fazer o carregamento dos comandos.");
        }
    }

}