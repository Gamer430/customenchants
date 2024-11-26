package org.kosoc.customenchants.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.enchantment.EnchantmentHelper;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import org.kosoc.customenchants.Customenchants;
import org.kosoc.customenchants.utils.EquipmentSlotUtil;
import org.kosoc.customenchants.utils.VanillaModifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.jar.Attributes;


@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
    @ModifyReturnValue(method = "getMaxDamage", at = @At("RETURN"))
    private int modifyMaxDamage(int original) {
        ItemStack item = (ItemStack) (Object) this;
        int level = EnchantmentHelper.getLevel(Customenchants.VANILLA, item);
        if (level > 0) {
            return original * 2;
        }
        return original;
    }
    @ModifyReturnValue(method = "getMiningSpeedMultiplier", at = @At("RETURN"))
    private float modigyMiningSpeed(float original) {
        ItemStack item = (ItemStack) (Object) this;
        int level = EnchantmentHelper.getLevel(Customenchants.VANILLA, item);
        if (level > 0) {
            return original * 2;
        }
        return original;
    }


    @Inject(method = "addEnchantment", at = @At("RETURN"))
    public void addEnchantment(Enchantment enchantment, int level, CallbackInfo ci){
        ItemStack item = (ItemStack) (Object) this;
        EquipmentSlot equip = EquipmentSlotUtil.getMainEquipmentSlot(item);
        if(EnchantmentHelper.getLevel(Customenchants.VANILLA, item) > 0){
            VanillaModifier.applyAttributeMultipliers(item, equip, 2.0);
        }
    }
}