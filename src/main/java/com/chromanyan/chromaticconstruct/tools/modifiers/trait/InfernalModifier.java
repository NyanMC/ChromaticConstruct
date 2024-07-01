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
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.mining.BreakSpeedModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.utils.Util;
import slimeknights.tconstruct.tools.modifiers.traits.melee.ConductingModifier;

import java.util.List;

// this is basically just conducting for mining so a lot of this is taken from that class
public class InfernalModifier extends Modifier implements BreakSpeedModifierHook, TooltipModifierHook {

    private static final Component BOOST = ChromaticConstruct.makeTranslation("modifier", "infernal.boost");
    private static final float PERCENT_PER_LEVEL = 0.15f;

    @Override
    protected void registerHooks(ModuleHookMap.@NotNull Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.BREAK_SPEED, ModifierHooks.TOOLTIP);
    }

    @Override
    public void addTooltip(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, @Nullable Player player, @NotNull List<Component> tooltip, @NotNull TooltipKey tooltipKey, @NotNull TooltipFlag tooltipFlag) {
        float bonus = (PERCENT_PER_LEVEL) * modifier.getLevel();
        // client only knows if the player is on fire or not, not the amount of fire, so just show full if on fire
        if (player != null && tooltipKey == TooltipKey.SHIFT && player.getRemainingFireTicks() == 0) {
            bonus = 0;
        }
        tooltip.add(applyStyle(Component.literal(Util.PERCENT_BOOST_FORMAT.format(bonus) + " ").append(BOOST)));
    }

    @Override
    public void onBreakSpeed(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, PlayerEvent.@NotNull BreakSpeed event, @NotNull Direction sideHit, boolean isEffective, float miningSpeedModifier) {
        if (isEffective) {
            event.setNewSpeed(event.getNewSpeed() * ((ConductingModifier.bonusScale(event.getEntity()) * PERCENT_PER_LEVEL) + 1));
        }
    }
}
