package com.fabricioaquiles.stormtpa.utils.misc.config;

import com.henryfabio.minecraft.configinjector.common.annotations.ConfigField;
import com.henryfabio.minecraft.configinjector.common.annotations.ConfigFile;
import com.henryfabio.minecraft.configinjector.common.annotations.TranslateColors;
import com.henryfabio.minecraft.configinjector.common.injector.ConfigurationInjectable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.function.Function;

@Getter
@TranslateColors
@Accessors(fluent = true)
@ConfigFile("config.yml")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class General implements ConfigurationInjectable {

    @Getter private static final General instance = new General();

    @ConfigField("Geral.sqlType") private String sqlType;
    @ConfigField("Geral.tpaTimeExpire") private Integer tpaTimeExpire;
    @ConfigField("Geral.tpaCooldownTime") private Integer tpaCooldownTime;
    @ConfigField("Geral.tpaNoDelayPermission") private String tpaNoDelayPermission;

    public static <T> T get(Function<General, T> function) {
        return function.apply(instance);
    }

}