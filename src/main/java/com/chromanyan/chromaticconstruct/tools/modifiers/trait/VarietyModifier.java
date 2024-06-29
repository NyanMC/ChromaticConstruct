package com.chromanyan.chromaticconstruct.tools.modifiers.trait;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraftforge.event.entity.player.PlayerEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.tconstruct.library.materials.definition.MaterialId;
import slimeknights.tconstruct.library.materials.definition.MaterialVariant;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.mining.BreakSpeedModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.MaterialNBT;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VarietyModifier extends Modifier implements BreakSpeedModifierHook, TooltipModifierHook {

    private static final Component MINING_SPEED = ChromaticConstruct.makeTranslation("modifier", "variety.mining_speed");

    @Override
    protected void registerHooks(ModuleHookMap.@NotNull Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.BREAK_SPEED, ModifierHooks.TOOLTIP);
    }

    private static float getBonus(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier) {
        MaterialNBT materialNBT = tool.getMaterials();

        Set<MaterialId> materialIds = new HashSet<>();
        float bonus = 0;

        for (MaterialVariant material : materialNBT.getList()) {
            if (materialIds.contains(material.getId())) continue;

            bonus += 0.05f;
            materialIds.add(material.getId());
        }

        return bonus * modifier.getEffectiveLevel();
    }

    @Override
    public void addTooltip(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, @Nullable Player player, @NotNull List<Component> tooltip, @NotNull TooltipKey tooltipKey, @NotNull TooltipFlag tooltipFlag) {
        TooltipModifierHook.addPercentBoost(this, MINING_SPEED, getBonus(tool, modifier), tooltip);
    }

    @Override
    public void onBreakSpeed(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, PlayerEvent.@NotNull BreakSpeed event, @NotNull Direction sideHit, boolean isEffective, float miningSpeedModifier) {
        if (isEffective) {
            event.setNewSpeed(event.getNewSpeed() * (getBonus(tool, modifier) + 1));
        }
    }
}
