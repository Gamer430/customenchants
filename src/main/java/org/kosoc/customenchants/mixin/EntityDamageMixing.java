package org.kosoc.customenchants.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.kosoc.customenchants.handlers.JackpotHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class EntityDamageMixing {
    @Inject(method = "damage", at = @At("HEAD"))
    public void onEntityDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        LivingEntity entity = (LivingEntity) (Object) this;
        System.out.println("Hit!");

        // Check if the entity is a player
        if (entity instanceof PlayerEntity) {
            JackpotHandler.rollJackpot((PlayerEntity) entity);

            // Add custom logic here, e.g., applying a custom effect or modifying damage
        }
    }
}
