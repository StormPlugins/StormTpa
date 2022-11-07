package com.fabricioaquiles.stormtpa.model.adapter;

import com.fabricioaquiles.stormtpa.model.User;
import com.henryfabio.sqlprovider.executor.adapter.SQLResultAdapter;
import com.henryfabio.sqlprovider.executor.result.SimpleResultSet;

public class UserAdapter implements SQLResultAdapter<User> {

    @Override
    public User adaptResult(SimpleResultSet resultSet) {

        String name = resultSet.get("name");
        String toggle = resultSet.get("toggle");

        return User
                .builder()
                .name(name)
                .toggle(Boolean.parseBoolean(toggle))
                .build();
    }

   
}

