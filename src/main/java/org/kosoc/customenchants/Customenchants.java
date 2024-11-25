package org.kosoc.customenchants;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.kosoc.customenchants.effects.JackpotEffect;
import org.kosoc.customenchants.enchants.*;
import org.kosoc.customenchants.handlers.JackpotHandler;
import org.kosoc.customenchants.handlers.XPMultHandler;


public class Customenchants implements ModInitializer {
    public static final String MOD_ID = "customenchants";
    private static KeyBinding dashKey;
    public static Enchantment DASH = new DashEnchantment();
    public static Enchantment XP_MULTT = new XPMultToolEnchantment();
    public static Enchantment XP_MULTW = new XPMultWeaponEnchantment();
    public static Enchantment VANILLA = new VanillaEnchant();
    public static Enchantment JACKPOT = new JackpotEnchant();
    public static StatusEffect JACKPOTS = new JackpotEffect();

    @Override
    public void onInitialize() {
        XPMultHandler.register();
        // Enchantment registration here
        Registry.register(Registries.ENCHANTMENT, new Identifier("customenchants", "dash"), DASH);
        Registry.register(Registries.ENCHANTMENT, new Identifier("customenchants", "xpmultt"), XP_MULTT);
        Registry.register(Registries.ENCHANTMENT, new Identifier("customenchants", "xpmultw"), XP_MULTW);
        Registry.register(Registries.ENCHANTMENT, new Identifier("customenchants", "vanilla"), VANILLA);
        Registry.register(Registries.ENCHANTMENT, new Identifier("customenchants", "jackpot"), JACKPOT);

        // Effect Registries
        Registry.register(Registries.STATUS_EFFECT, new Identifier("customenchants", "jackpot"), JACKPOTS);
        // TickListeners
        CustomEntityDamageEvent.EVENT.register((entity, source, amount) -> {
            if (entity instanceof PlayerEntity player) {
                JackpotHandler.useJackpot(player);
            }
        });
        ServerTickEvents.END_SERVER_TICK.register(this::onServerTick);
    }

    private void onServerTick(MinecraftServer server) {
        // Loop through all players on the server
        for (ServerPlayerEntity player : server.getPlayerManager().getPlayerList()) {
            JackpotHandler.updateTick((IPlayerData) player, player);
        }
    }

}
