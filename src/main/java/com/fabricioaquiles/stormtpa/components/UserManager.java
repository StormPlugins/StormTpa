package com.fabricioaquiles.stormtpa.components;

import com.fabricioaquiles.stormtpa.model.User;
import com.fabricioaquiles.stormtpa.model.adapter.UserAdapter;
import com.fabricioaquiles.stormtpa.utils.misc.data.UserData;
import com.henryfabio.sqlprovider.executor.SQLExecutor;
import lombok.RequiredArgsConstructor;

import java.util.Set;

import org.bukkit.Bukkit;

@RequiredArgsConstructor
public class UserManager {

    private final String TABLE = "StormTpa";

    private final SQLExecutor sqlExecutor;
    
    public void setup() {
    	createTable();
    	getUsers().forEach(e -> {
    		UserData.get().getUserData().put(e.getName(), e);
    	});
    	
    	Bukkit.getConsoleSender().sendMessage("§b[StormTpa] [UserData]§f foram carregados §b"+UserData.get().getUserData().size()+"§f usuários.");
    }

    public void createTable() {
        sqlExecutor.updateQuery("CREATE TABLE IF NOT EXISTS " + TABLE + "(" +
                "name VARCHAR(16)," +
                "toggle TEXT" +
                ");"
        );
    }

    public void createUser(User user) {
        sqlExecutor.updateQuery(
                "INSERT INTO " + TABLE + " VALUES(?,?);",
                statement -> {
                    statement.set(1, user.getName());
                    statement.set(2, user.getToggle().toString());
                }
        );
    }

    public User getUser(String name) {
        return sqlExecutor.resultOneQuery(
                "SELECT * FROM " + TABLE + " WHERE name = ?",
                statement -> statement.set(1, name),
                UserAdapter.class

        );
    }

    public Set<User> getUsers() {
        return sqlExecutor.resultManyQuery(
                "SELECT * FROM " + TABLE,
                statement -> {},
                UserAdapter.class
        );
    }

    public void saveUser(User user) {
        sqlExecutor.updateQuery(
                "UPDATE " + TABLE + " SET toggle = ? WHERE name = ?",
                statement -> {
                    statement.set(1, user.getToggle().toString());
                    statement.set(2, user.getName());
                }
        );
    }

}