package org.kosoc.customenchants.utils;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import org.kosoc.customenchants.IPlayerData;

public class JackpotData {
    public static int rechargeJackpot(PlayerEntity player){
        IPlayerData playerData = (IPlayerData) player;
        NbtCompound nbt = playerData.getPersistantData();
        int cooldown = nbt.getInt("jackpotCooldown");
        boolean charges = nbt.getBoolean("jackpotCharged");
        boolean jackpot = nbt.getBoolean("isInJackpot");

        if(cooldown <= 1200 && !charges && !jackpot){
            cooldown += 1;
        } else if (cooldown >= 1200 && !charges) {
            cooldown = 0;
            charges = true;
        }else return cooldown;
        nbt.putBoolean("jackpotCharged", charges);
        nbt.putInt("jackpotCooldown", cooldown);
        return 0;
    }

    public static int isInJackpot(IPlayerData player){
        NbtCompound nbt = player.getPersistantData();
        int jackpotLength = nbt.getInt("jackpotLength");
        boolean jackpot = nbt.getBoolean("isInJackpot");

        if(jackpotLength > 0){
            jackpotLength -= 1;
        } else if (jackpotLength <= 0) {
            jackpot = false;
        }
        nbt.putInt("jackpotLength", jackpotLength);
        nbt.putBoolean("isInJackpot", jackpot);
        return 0;
    }

    public static boolean useJackpot(IPlayerData player){
        NbtCompound nbt = player.getPersistantData();
        boolean isCharged = nbt.getBoolean("jackpotCharged");
        boolean jackpot = nbt.getBoolean("isInJackpot");
        int jackpotLength = nbt.getInt("jackpotLength");
        if(isCharged == true && jackpot == false){
            jackpotLength = 5200;
            isCharged = false;
            jackpot = true;
        }
    }
}
