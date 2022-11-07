package com.fabricioaquiles.stormtpa.utils.misc.sql;

import com.fabricioaquiles.stormtpa.utils.misc.config.General;
import com.henryfabio.sqlprovider.connector.SQLConnector;
import com.henryfabio.sqlprovider.connector.type.SQLDatabaseType;
import com.henryfabio.sqlprovider.connector.type.impl.MySQLDatabaseType;
import com.henryfabio.sqlprovider.connector.type.impl.SQLiteDatabaseType;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

@Data(staticConstructor = "of")
public class SQLProvider {

    private final JavaPlugin plugin;

    public SQLConnector setup() {
        FileConfiguration config = plugin.getConfig();

        SQLConnector sqlConnector;

        if(General.get(General::sqlType).equalsIgnoreCase("MYSQL")) {
            ConfigurationSection mysql = config.getConfigurationSection("MySQL");
            sqlConnector = mysqlDatabaseType(mysql).connect();
            Bukkit.getConsoleSender().sendMessage("§b[StormTpa] [MySQL]§f conexão com o MySQL efetuada com sucesso, criando tabelas...");
        } else if(General.get(General::sqlType).equalsIgnoreCase("SQLITE")) {
            ConfigurationSection sqlite = config.getConfigurationSection("SQLite");
            sqlConnector = sqliteDatabaseType(sqlite).connect();
            Bukkit.getConsoleSender().sendMessage("§b[StormTpa] [SQLite]§f conexão com o SQLite efetuada com sucesso, criando tabelas...");
        } else {
            return null;
        }

        return sqlConnector;
    }

    private SQLDatabaseType sqliteDatabaseType(ConfigurationSection section) {
        return SQLiteDatabaseType.builder()
                .file(new File(plugin.getDataFolder(), section.getString("file")))
                .build();
    }

    private SQLDatabaseType mysqlDatabaseType(ConfigurationSection section) {
        return MySQLDatabaseType.builder()
                .address(section.getString("host"))
                .username(section.getString("user"))
                .password(section.getString("password"))
                .database(section.getString("database"))
                .build();
    }
}
