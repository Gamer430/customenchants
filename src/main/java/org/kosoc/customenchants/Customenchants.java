package org.kosoc.customenchants;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.kosoc.customenchants.enchants.DashEnchantment;
import org.kosoc.customenchants.enchants.XPMultToolEnchantment;
import org.kosoc.customenchants.enchants.XPMultWeaponEnchantment;
import org.kosoc.customenchants.handlers.XPMultHandler;
import org.lwjgl.glfw.GLFW;


public class Customenchants implements ModInitializer {
    public static final String MOD_ID = "customenchants";
    private static KeyBinding dashKey;
    public static Enchantment DASH = new DashEnchantment();
    public static Enchantment XP_MULTT = new XPMultToolEnchantment();
    public static Enchantment XP_MULTW = new XPMultWeaponEnchantment();

    @Override
    public void onInitialize() {
        XPMultHandler.register();
        // Enchantment registration here
        Registry.register(Registries.ENCHANTMENT, new Identifier("customenchants", "dash"), DASH);
        Registry.register(Registries.ENCHANTMENT, new Identifier("customenchants", "xpmultt"), XP_MULTT);
        Registry.register(Registries.ENCHANTMENT, new Identifier("customenchants", "xpmultw"), XP_MULTW);
    }


}