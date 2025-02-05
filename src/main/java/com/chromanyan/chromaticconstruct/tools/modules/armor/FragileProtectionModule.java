package com.chromanyan.chromaticconstruct.tools.modules.armor;

import com.chromanyan.chromaticconstruct.init.CCMobEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.mantle.data.predicate.damage.DamageSourcePredicate;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.armor.ProtectionModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.modifiers.modules.armor.ProtectionModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

import java.util.List;

public record FragileProtectionModule(LevelingValue amount) implements ModifierModule, ProtectionModifierHook, ModifyDamageModifierHook, TooltipModifierHook {
    private static final List<ModuleHook<?>> DEFAULT_HOOKS = HookProvider.<FragileProtectionModule>defaultHooks(ModifierHooks.PROTECTION, ModifierHooks.MODIFY_DAMAGE, ModifierHooks.TOOLTIP);
    public static final RecordLoadable<FragileProtectionModule> LOADER = RecordLoadable.create(
            LevelingValue.LOADABLE.directField(FragileProtectionModule::amount),
    FragileProtectionModule::new);

    @Override
    public @NotNull RecordLoadable<FragileProtectionModule> getLoader() {
        return LOADER;
    }

    @Override
    public @NotNull List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }

    @Override
    public float modifyDamageTaken(@NotNull IToolStackView iToolStackView, @NotNull ModifierEntry modifierEntry, @NotNull EquipmentContext context, @NotNull EquipmentSlot equipmentSlot, @NotNull DamageSource damageSource, float amount, boolean b) {
        if (!context.getEntity().hasEffect(CCMobEffects.fragileCooldownEffect.get())) {
            CCMobEffects.fragileCooldownEffect.get().apply(context.getEntity(), 5 * 20, 0, true);
        }

        return amount;
    }

    @Override
    public float getProtectionModifier(@NotNull IToolStackView iToolStackView, @NotNull ModifierEntry modifierEntry, @NotNull EquipmentContext equipmentContext, @NotNull EquipmentSlot equipmentSlot, @NotNull DamageSource damageSource, float v) {
        if (DamageSourcePredicate.CAN_PROTECT.matches(damageSource) && !equipmentContext.getEntity().hasEffect(CCMobEffects.fragileCooldownEffect.get())) {
            v += amount.compute(modifierEntry.getEffectiveLevel());
        }

        return v;
    }

    @Override
    public void addTooltip(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, @Nullable Player player, @NotNull List<Component> tooltip, @NotNull TooltipKey tooltipKey, @NotNull TooltipFlag tooltipFlag) {
        float bonus = amount.compute(modifier.getEffectiveLevel());
        if (bonus > 0 && (player == null || tooltipKey != TooltipKey.SHIFT || !player.hasEffect(CCMobEffects.fragileCooldownEffect.get()))) {
            ProtectionModule.addResistanceTooltip(tool, modifier.getModifier(), bonus, player, tooltip);
        }
    }
}
