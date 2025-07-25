package com.chromanyan.chromaticconstruct.tools.modifiers.ability.interaction;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Snowball;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.build.ConditionalStatModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.GeneralInteractionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.interaction.InteractionSource;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.capability.PersistentDataCapability;
import slimeknights.tconstruct.library.tools.helper.ToolDamageUtil;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;
import slimeknights.tconstruct.library.tools.stat.ToolStats;

public class SnowballModifier extends NoLevelsModifier implements GeneralInteractionModifierHook {
    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.GENERAL_INTERACT);
    }

    @Override
    public int getPriority() {
        return 65; // want to run after all block interaction modifiers have had their chance to work
    }

    @Override
    public @NotNull InteractionResult onToolUse(@NotNull IToolStackView tool, @NotNull ModifierEntry modifierEntry, @NotNull Player player, @NotNull InteractionHand interactionHand, @NotNull InteractionSource interactionSource) {
        if (interactionSource == InteractionSource.RIGHT_CLICK && !tool.isBroken()) {
            Level level = player.getCommandSenderWorld();
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SNOWBALL_THROW, SoundSource.NEUTRAL, 0.5F, 0.4F / (level.getRandom().nextFloat() * 0.4F + 0.8F));
            if (level.isClientSide) return InteractionResult.SUCCESS;

            Snowball snowball = new Snowball(level, player);
            snowball.setItem(new ItemStack(Items.SNOWBALL));

            snowball.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F * ConditionalStatModifierHook.getModifiedStat(tool, player, ToolStats.VELOCITY), 1.0F);

            ModDataNBT arrowData = PersistentDataCapability.getOrWarn(snowball);
            for (ModifierEntry entry : tool.getModifierList()) {
                entry.getHook(ModifierHooks.PROJECTILE_LAUNCH).onProjectileLaunch(tool, modifierEntry, player, player.getItemInHand(interactionHand), snowball, null, arrowData, true);
            }

            level.addFreshEntity(snowball);

            ToolDamageUtil.damageAnimated(tool, 5, player, interactionHand);

            return InteractionResult.CONSUME;
        }
        return InteractionResult.PASS;
    }
}
