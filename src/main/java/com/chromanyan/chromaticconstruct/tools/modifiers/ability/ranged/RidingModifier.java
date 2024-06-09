package com.chromanyan.chromaticconstruct.tools.modifiers.ability.ranged;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileHitModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileLaunchModifierHook;
import slimeknights.tconstruct.library.modifiers.impl.NoLevelsModifier;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModifierNBT;
import slimeknights.tconstruct.library.tools.nbt.NamespacedNBT;

public class RidingModifier extends NoLevelsModifier implements ProjectileLaunchModifierHook, ProjectileHitModifierHook {

    @Override
    protected void registerHooks(ModuleHookMap.Builder hookBuilder) {
        hookBuilder.addHook(this, ModifierHooks.PROJECTILE_HIT, ModifierHooks.PROJECTILE_LAUNCH);
    }

    @Override
    public boolean onProjectileHitBlock(@NotNull ModifierNBT modifiers, @NotNull NamespacedNBT persistentData, @NotNull ModifierEntry modifier, @NotNull Projectile projectile, @NotNull BlockHitResult hit, @Nullable LivingEntity attacker) {
        projectile.ejectPassengers();
        return ProjectileHitModifierHook.super.onProjectileHitBlock(modifiers, persistentData, modifier, projectile, hit, attacker);
    }

    @Override
    public void onProjectileLaunch(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, @NotNull LivingEntity shooter, @NotNull Projectile projectile, @Nullable AbstractArrow arrow, @NotNull NamespacedNBT persistentData, boolean primary) {
        if (arrow == null || !primary) return;
        if (shooter.isShiftKeyDown() || !shooter.isOnGround()) return;
        shooter.startRiding(arrow);
        shooter.hurt(DamageSource.arrow(arrow, shooter), (float) (arrow.getBaseDamage() * arrow.getDeltaMovement().length()));
    }
}
