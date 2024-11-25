package org.kosoc.customenchants.enchants;

import net.minecraft.enchantment.EfficiencyEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.enchantment.FrostWalkerEnchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.rmi.registry.Registry;

public class VanillaEnchant extends Enchantment {
    public VanillaEnchant(Enchantment.Rarity weight, EquipmentSlot... slotTypes){
        super(weight, EnchantmentTarget.BREAKABLE, slotTypes);
    }

    @Override
    public int getMinPower(int level){
        return 30;
    }
    @Override
    public int getMaxLevel() {
        return 1; // Maximum level of the enchantment
    }

    @Override
    public boolean isCursed(){
        return false;
    }

    @Override
    public boolean isAvailableForEnchantedBookOffer(){
        return true;
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack){
        Item item = stack.getItem();
        Identifier ItemID = Registries.ITEM.getId(item);
        if(ItemID.getNamespace().contains("minecraft:")){
            return true;
        }
        return false;
    }

    @Override
    public boolean canAccept(Enchantment other){
        return false;
    }

    public static void applySoloEnchantment(ItemStack itemStack, Enchantment soloEnchantment, int level) {
        // Clear all enchantments
        clearEnchantments(itemStack);

        // Add this enchantment
        itemStack.addEnchantment(soloEnchantment, level);
    }

    public static void clearEnchantments(ItemStack itemStack) {
        if (itemStack.hasEnchantments()) {
            NbtCompound nbt = itemStack.getNbt();
            if (nbt != null && nbt.contains("Enchantments")) {
                nbt.remove("Enchantments"); // Remove all enchantments
            }
        }
    }


}
