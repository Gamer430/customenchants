package org.kosoc.customenchants.handlers;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.kosoc.customenchants.Customenchants;

public class XPMultHandler {
    public static void onEntityDeath(LivingEntity entity) {
        if (entity.getWorld().isClient) return; // Ignore client side

        if (entity instanceof PlayerEntity) {
            return; // Skip if the player kills itself
        }

        // Check if the player has the XP Multiplier Enchantment
        PlayerEntity player = (PlayerEntity) entity.getAttacker();
        if (player != null) {
            ItemStack weapon = player.getMainHandStack();
            int enchantmentLevel = EnchantmentHelper.getLevel(Customenchants.XP_MULTW, weapon);

            if (enchantmentLevel > 0) {
                // Get the original XP
                int originalXP = entity.getXpToDrop();
                // Multiply the XP by (1 + enchantment level)
                int newXP = (int) (originalXP * (1 + enchantmentLevel));
                entity.disableExperienceDropping();
                player.addExperience(newXP);
            }
        }
    }
    public static void register() {
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            handleBlockBreak(world, player, pos, state);
        });
    }
    private static void handleBlockBreak(World world, PlayerEntity player, BlockPos pos, BlockState state) {
        // Check if the player has the XP Multiplier enchantment
        ItemStack tool = player.getMainHandStack();
        int enchantmentLevel = EnchantmentHelper.getLevel(Customenchants.XP_MULTT, tool);

        if (enchantmentLevel > 0) {
            // Add XP for specific blocks (e.g., ores)
            int baseXP = getBaseXpForBlock(state);
            if (baseXP > 0) {
                int multipliedXP = baseXP * (1 + enchantmentLevel);
                player.addExperience(multipliedXP);
            }
        }
    }

    private static int getBaseXpForBlock(BlockState state) {
        // Custom logic for XP based on block type
        if (state.isOf(Blocks.COAL_ORE) || state.isOf(Blocks.DEEPSLATE_COAL_ORE)) {
            return 2;
        } else if (state.isOf(Blocks.DIAMOND_ORE) || state.isOf(Blocks.DEEPSLATE_DIAMOND_ORE)) {
            return 7;
        } else if (state.isOf(Blocks.EMERALD_ORE) || state.isOf(Blocks.DEEPSLATE_EMERALD_ORE)) {
            return 7;
        } else if (state.isOf(Blocks.LAPIS_ORE) || state.isOf(Blocks.DEEPSLATE_LAPIS_ORE)) {
            return 4;
        } else if (state.isOf(Blocks.REDSTONE_ORE) || state.isOf(Blocks.DEEPSLATE_REDSTONE_ORE)) {
            return 3;
        } else if (state.isOf(Blocks.NETHER_QUARTZ_ORE)) {
            return 2;
        } else if (state.isOf(Blocks.NETHER_GOLD_ORE)) {
            return 1;
        }
        return 0; // Non-XP blocks
    }
}
