package com.chromanyan.chromaticconstruct.tools.modifiers.defense;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import com.chromanyan.chromaticconstruct.init.CCMobEffects;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.data.predicate.damage.DamageSourcePredicate;
import slimeknights.mantle.data.predicate.entity.LivingEntityPredicate;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.data.ModifierMaxLevel;
import slimeknights.tconstruct.library.modifiers.hook.armor.ModifyDamageModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.armor.ProtectionModule;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;
import slimeknights.tconstruct.library.tools.context.EquipmentContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.tools.modifiers.defense.AbstractProtectionModifier;

public class FragileProtectionModifier extends AbstractProtectionModifier<ModifierMaxLevel> implements ModifyDamageModifierHook {

    private static final TinkerDataCapability.ComputableDataKey<ModifierMaxLevel> FRAGILE_PROTECTION = ChromaticConstruct.createKey("fragile_protection", ModifierMaxLevel::new);

    public FragileProtectionModifier() {
        super(FRAGILE_PROTECTION);
    }

    @Override
    protected void registerHooks(ModuleHookMap.@NotNull Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.MODIFY_DAMAGE);
        hookBuilder.addModule(ProtectionModule.builder().source(DamageSourcePredicate.CAN_PROTECT).entity(LivingEntityPredicate.simple(entity -> entity.hasEffect(CCMobEffects.fragileCooldownEffect.get())).inverted()).eachLevel(2.5f));
    }


    // we don't need to actually modify damage taken but it seems this takes effect AFTER protection gets calculated
    @Override
    public float modifyDamageTaken(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, @NotNull EquipmentContext context, @NotNull EquipmentSlot slotType, @NotNull DamageSource source, float amount, boolean isDirectDamage) {
        if (amount == 0) return amount; // don't go on cooldown if you didn't even reduce damage at all
        if (context.getEntity().hasEffect(CCMobEffects.fragileCooldownEffect.get())) return amount;

        context.getEntity().getLevel().playSound(null, context.getEntity().blockPosition(), SoundEvents.GLASS_BREAK, SoundSource.PLAYERS, 0.5f, 1.0f);
        CCMobEffects.fragileCooldownEffect.get().apply(context.getEntity(), 5 * 20, 0, true);

        return amount;
    }
}
