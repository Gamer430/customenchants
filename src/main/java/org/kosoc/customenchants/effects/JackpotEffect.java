package org.kosoc.customenchants.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class JackpotEffect extends StatusEffect {
    public JackpotEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0x55FF55); // You can change the color code as per your preference
    }
    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        // Apply regeneration effect (Regeneration 6)
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 5020, 5, false, false));

        // Apply strength effect (Strength 2)
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 5020, 1, false, false));

        // Apply speed effect (Speed 2)
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 5020, 1, false, false));
    }

    @Override
    public boolean isInstant() {
        // This effect is not instant, as it has a duration and can apply over time
        return false;
    }
}
