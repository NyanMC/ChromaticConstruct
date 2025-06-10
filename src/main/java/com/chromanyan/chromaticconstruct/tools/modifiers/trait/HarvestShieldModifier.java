package com.chromanyan.chromaticconstruct.tools.modifiers.trait;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.mining.BlockBreakModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.special.PlantHarvestModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.DurabilityShieldModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class HarvestShieldModifier extends DurabilityShieldModifier implements BlockBreakModifierHook, PlantHarvestModifierHook {

    @Override
    protected void registerHooks(ModuleHookMap.@NotNull Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.BLOCK_BREAK, ModifierHooks.PLANT_HARVEST);
    }

    @Override
    public int getShieldCapacity(IToolStackView tool, ModifierEntry modifier) {
        return (int)(modifier.getEffectiveLevel() * 100 * tool.getMultiplier(ToolStats.DURABILITY));
    }

    @Override
    public @Nullable Boolean showDurabilityBar(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier) {
        return getShield(tool) > 0 ? true : null;
    }

    @Override
    public int getPriority() {
        // same priority as stoneshield. while it's unlikely a tool has both, it doesn't matter which is used first
        return 175;
    }

    @Override
    public int getDurabilityRGB(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier) {
        if (getShield(tool) > 0) {
            return 0xDA4F4F;
        }
        return -1;
    }

    private void handleHarvest(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, BlockState state) {
        if (!(state.getBlock() instanceof CropBlock cropBlock) || cropBlock.getAge(state) != cropBlock.getMaxAge()) return;

        float chance = modifier.getEffectiveLevel() * 0.20f;
        if (RANDOM.nextFloat() < chance) {
            addShield(tool, modifier, 3);
        }
    }

    @Override
    public void afterBlockBreak(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, @NotNull ToolHarvestContext context) {
        handleHarvest(tool, modifier, context.getState());
    }

    @Override
    public void afterHarvest(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, @NotNull UseOnContext context, @NotNull ServerLevel world, @NotNull BlockState state, @NotNull BlockPos pos) {
        handleHarvest(tool, modifier, state);
    }
}
