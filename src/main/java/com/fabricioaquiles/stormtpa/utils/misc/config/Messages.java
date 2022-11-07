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
@ConfigFile("mensagens.yml")
@ConfigSection("Mensagens")
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Messages implements ConfigurationInjectable {

    @Getter private static final Messages instance = new Messages();
    
    @ConfigField("tpaJaPossui") private String tpaJaPossui;
    @ConfigField("tpaCooldown") private String tpaCooldown;
    @ConfigField("tpaJogadorDesativado") private String tpaJogadorDesativado;
    @ConfigField("tpaAlvoExpirou") private String tpaAlvoExpirou;
    @ConfigField("tpaJogadorExpirou") private String tpaJogadorExpirou;
    @ConfigField("tpaSemPendentes") private String tpaSemPendentes;
    @ConfigField("tpaSemPendentesPlayer") private String tpaSemPendentesPlayer;
    @ConfigField("tpacceptOffline") private String tpacceptOffline;
    @ConfigField("tpacceptVoce") private String tpacceptVoce;
    @ConfigField("tpacceptSucesso") private String tpacceptSucesso;
    @ConfigField("tpdenySucesso") private String tpdenySucesso;
    @ConfigField("tpdenyRecusou") private String tpdenyRecusou;
    @ConfigField("tpdenyVoce") private String tpdenyVoce;
    @ConfigField("tpcancelSucesso") private String tpcancelSucesso;
    @ConfigField("tpcancelCancelou") private String tpcancelCancelou;
    @ConfigField("tpcancelVoce") private String tpcancelVoce;
    @ConfigField("teleportado") private String teleportado;
    @ConfigField("aceitouTpa") private List<String> aceitouTpa;
    @ConfigField("tpaRecebido") private List<String> tpaRecebido;
    @ConfigField("tpaEnviado") private List<String> tpaEnviado;

    public static <T> T get(Function<Messages, T> function) {
        return function.apply(instance);
    }

}