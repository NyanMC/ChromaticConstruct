package com.chromanyan.chromaticconstruct.tools.modules.armor;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import com.chromanyan.chromaticconstruct.init.CCMobEffects;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.client.TooltipKey;
import slimeknights.mantle.data.loadable.record.SingletonLoader;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.armor.OnAttackedModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.display.TooltipModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.modifiers.modules.technical.SlotInChargeModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.tools.modules.armor.KineticModule;


import java.util.List;

public enum PanicModule implements ModifierModule, OnAttackedModifierHook, TooltipModifierHook {
    INSTANCE;

    private static final Component MOVEMENT_SPEED = ChromaticConstruct.makeTranslation("modifier", "panic.movement_speed");

    private static final List<ModuleHook<?>> DEFAULT_HOOKS = HookProvider.<KineticModule>defaultHooks(ModifierHooks.ON_ATTACKED, ModifierHooks.TOOLTIP);
    private static final TinkerDataCapability.TinkerDataKey<SlotInChargeModule.SlotInCharge> SLOT_IN_CHARGE = ChromaticConstruct.createKey("panic");
    public static final SingletonLoader<PanicModule> LOADER = new SingletonLoader<>(INSTANCE);

    @Override
    public @NotNull SingletonLoader<PanicModule> getLoader() {
        return LOADER;
    }

    @Override
    public @NotNull List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }

    @Override
    public void addModules(ModuleHookMap.Builder builder) {
        builder.addModule(new SlotInChargeModule(SLOT_IN_CHARGE));
    }

    @Override
    public void onAttacked(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, @NotNull EquipmentContext context, @NotNull EquipmentSlot slotType, DamageSource source, float amount, boolean isDirectDamage) {
        // require the damage to be entity caused, but does not strictly need to be melee damage
        if (source.getEntity() != null) {
            int level = SlotInChargeModule.getLevel(context.getTinkerData(), SLOT_IN_CHARGE, slotType);
            if (level > 0) {
                CCMobEffects.panicEffect.get().apply(context.getEntity(), 5 * 20, level - 1, true);
            }
        }
    }

    @Override
    public void addTooltip(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, @Nullable Player player, @NotNull List<Component> tooltip, @NotNull TooltipKey tooltipKey, @NotNull TooltipFlag tooltipFlag) {
        TooltipModifierHook.addPercentBoost(modifier.getModifier(), MOVEMENT_SPEED, modifier.getLevel() * 0.10, tooltip);
    }
}
