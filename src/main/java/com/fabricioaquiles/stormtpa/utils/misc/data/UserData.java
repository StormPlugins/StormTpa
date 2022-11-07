package com.fabricioaquiles.stormtpa.utils.misc.data;

import java.util.List;
import java.util.Map;

import com.fabricioaquiles.stormtpa.model.User;
import com.google.common.collect.Maps;

import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
public class UserData {
	
	@Getter @Accessors(fluent = true)
	private static final UserData get = new UserData();
	
	private Map<String, Long> cooldownData = Maps.newLinkedHashMap();
	private Map<String, List<String>> enviadosData = Maps.newLinkedHashMap();
	private Map<String, List<String>> recebidosData = Maps.newLinkedHashMap();
	
	private Map<String, User> userData = Maps.newLinkedHashMap();
	
	public User getUser(String name) {
		return getUserData().get(name);
	}
	
	public void registryUser(String name) {
		
		if(!getUserData().containsKey(name)) {
			
			User user = User.builder()
					.name(name)
					.toggle(true)
					.build();
			
			getUserData().put(name, user);
			
		}
		
	}
	
	

}
