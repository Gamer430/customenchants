package org.kosoc.customenchants.handlers;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import org.kosoc.customenchants.Customenchants;
import org.kosoc.customenchants.IPlayerData;
import org.kosoc.customenchants.utils.JackpotData;

public class JackpotHandler {

    public static void rollJackpot(PlayerEntity player){
        IPlayerData playerData = (IPlayerData) player;
        ItemStack item = player.getEquippedStack(EquipmentSlot.CHEST);
        int enchantLevel = EnchantmentHelper.getLevel(Customenchants.JACKPOT, item);
        NbtCompound nbt = playerData.getPersistantData();


        if(enchantLevel > 0){
            boolean isCharged = nbt.getBoolean("jackpotCharged");
            boolean inJackpot = nbt.getBoolean("isInJackpot");

            if(isCharged && !inJackpot){
                JackpotData.useJackpot(playerData);
                player.addStatusEffect(new StatusEffectInstance(Customenchants.JACKPOTEFFECT));
            }
        }else return;

    }

    public void updateTick(PlayerEntity player){
        IPlayerData playerData = (IPlayerData) player;
        ItemStack item = player.getEquippedStack(EquipmentSlot.CHEST);
        int enchantLevel = EnchantmentHelper.getLevel(Customenchants.JACKPOT, item);
        NbtCompound nbt = playerData.getPersistantData();

        if(enchantLevel > 0){
            boolean inJackpot = nbt.getBoolean("isInJackpot");
            int cooldown = nbt.getInt("jackpotCooldown");
        }
    }
}
