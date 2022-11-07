package com.fabricioaquiles.stormtpa.utils.misc.config;

import com.henryfabio.minecraft.configinjector.common.annotations.ConfigField;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigFile;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigSection;
import com.henryfabio.minecraft.configinjector.common.annotations.TranslateColors;
import com.henryfabio.minecraft.configinjector.common.injector.ConfigurationInjectable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.function.Function;

@Getter
@TranslateColors
@Accessors(fluent = true)
@ConfigFile("menus.yml")
@ConfigSection("Menus")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Menus implements ConfigurationInjectable {

    @Getter private static final Menus instance = new Menus();
    
    @ConfigField("profile.name") private String profileName;
    @ConfigField("profile.size") private Integer profileSize;
    
    @ConfigField("profile.tpa.slot") private Integer profileTpaSlot;
    @ConfigField("profile.tpa.item") private String profileTpaItem;
    @ConfigField("profile.tpa.name") private String profileTpaName;
    @ConfigField("profile.tpa.lore") private List<String> profileTpaLore;
    @ConfigField("profile.tpaToggle.slot") private Integer profileTpaToggleSlot;

    public static <T> T get(Function<Menus, T> function) {
        return function.apply(instance);
    }

}