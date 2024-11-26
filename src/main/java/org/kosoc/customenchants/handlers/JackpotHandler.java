package org.kosoc.customenchants.handlers;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import org.kosoc.customenchants.Customenchants;
import org.kosoc.customenchants.IPlayerData;
import org.kosoc.customenchants.utils.JackpotData;

import java.util.Random;

public class JackpotHandler {
    public static void updateTick(IPlayerData playerData, PlayerEntity player) {
        ItemStack item = player.getEquippedStack(EquipmentSlot.CHEST);
        int level = EnchantmentHelper.getLevel(Customenchants.JACKPOT, item);
        NbtCompound nbt = playerData.getPersistantData();
        boolean inJackpot = nbt.getBoolean("inJackpot");
        int timer = nbt.getInt("jackpotTimer");
        if (level > 0) {
            if (!inJackpot) {
                JackpotData.rechargeJackpot(playerData);
            } else if (timer > 0) {
                JackpotData.tickJackpot(playerData);
            } else return;
        }
    }
    public static void useJackpot(PlayerEntity player){
        IPlayerData playerData = (IPlayerData) player;
        NbtCompound nbt = playerData.getPersistantData();
        boolean inJackpot = nbt.getBoolean("inJackpot");
        boolean isCharged = nbt.getBoolean("isCharged");
        if(isCharged && !inJackpot){
            Random random = new Random();
            int randomNumber = random.nextInt(100) + 1; // Generates a number between 1 and 100
            System.out.println("Random Number: " + randomNumber);
            JackpotData.useJackpot(playerData);
            player.addStatusEffect(new StatusEffectInstance(Customenchants.JACKPOTS, 5020, 0, false, true));
        }
    }
}
