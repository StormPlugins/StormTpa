package com.fabricioaquiles.stormtpa.api;

import java.util.List;
import java.util.Map;

import com.fabricioaquiles.stormtpa.StormTpa;
import com.fabricioaquiles.stormtpa.components.UserManager;
import com.fabricioaquiles.stormtpa.model.User;
import com.fabricioaquiles.stormtpa.utils.misc.data.UserData;

import lombok.Getter;

public class StormTpaAPI {
	
	@Getter private static final StormTpaAPI api = new StormTpaAPI();
	
	public User getUser(String name) {
		return UserData.get().getUser(name);
	}
	
	public UserData getUserData() {
		return UserData.get();
	}
	
	public Map<String, List<String>> getEnviadosData() {
		return UserData.get().getEnviadosData();
	}
	
	public Map<String, List<String>> getRecebidosData() {
		return UserData.get().getRecebidosData();
	}
	
	public UserManager getUserManager() {
		return StormTpa.getInstance().getUserManager();
	}

}
