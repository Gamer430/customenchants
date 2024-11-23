package org.kosoc.customenchants.client;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import org.kosoc.customenchants.Customenchants;
import org.kosoc.customenchants.IPlayerData;
import org.kosoc.customenchants.utils.DashData;
import org.kosoc.customenchants.utils.RechargeData;

public class HandleDash {


    public static void performDash(PlayerEntity player) {
        int dashlevel = DashData.getCharges((IPlayerData) player);
        if (dashlevel <= 0) return;

        Vec3d lookVector = player.getRotationVector();
        double velocityMagnitude = 2.75; // Fixed dash velocity
        Vec3d currentVelocity = player.getVelocity();

// Normalize the lookVector to maintain the direction
        Vec3d normalizedLookVector = lookVector.normalize();

// Apply the fixed velocity in the direction of the player's view
        currentVelocity = currentVelocity.add(normalizedLookVector.x * velocityMagnitude, normalizedLookVector.y * velocityMagnitude, normalizedLookVector.z * velocityMagnitude);
        player.setVelocity(currentVelocity);
        player.velocityModified = true;

        DashData.removeCharges(((IPlayerData) player));
        RechargeData.setRecharge(((IPlayerData) player), 0);


    }


    public static void updateRechargeTimer(PlayerEntity player) {
        int chargecount = EnchantmentHelper.getLevel(Customenchants.DASH, player.getEquippedStack(EquipmentSlot.FEET));
        if(player.isOnGround()) {
            RechargeData.rechargeTick(((IPlayerData) player), chargecount);
        }else return;
    }


}
