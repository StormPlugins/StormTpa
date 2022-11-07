package com.fabricioaquiles.stormtpa.api.events;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

@Data
@EqualsAndHashCode(callSuper = true)
public class TpAcceptEvent extends Event {

    @Getter
    private static final HandlerList handlerList = new HandlerList();

    private final Player player, target;


    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

}