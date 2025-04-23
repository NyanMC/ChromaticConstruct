package com.chromanyan.chromaticconstruct.tools.modules;

import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.data.loadable.record.RecordLoadable;
import slimeknights.mantle.data.loadable.record.SingletonLoader;
import slimeknights.mantle.data.registry.GenericLoaderRegistry;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.mining.BlockBreakModifierHook;
import slimeknights.tconstruct.library.modifiers.hook.ranged.ProjectileLaunchModifierHook;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.module.HookProvider;
import slimeknights.tconstruct.library.module.ModuleHook;
import slimeknights.tconstruct.library.tools.context.ToolHarvestContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;
import slimeknights.tconstruct.library.tools.nbt.ModDataNBT;

import java.util.List;
import java.util.Random;

public enum ShockingModule implements ModifierModule, BlockBreakModifierHook, ProjectileLaunchModifierHook {
    INSTANCE;

    private static final Random RANDOM = new Random();

    private static final List<ModuleHook<?>> DEFAULT_HOOKS = HookProvider.<ShockingModule>defaultHooks(ModifierHooks.BLOCK_BREAK, ModifierHooks.PROJECTILE_LAUNCH);
    public static final SingletonLoader<ShockingModule> LOADER = new SingletonLoader<>(INSTANCE);

    @Override
    public @NotNull RecordLoadable<? extends GenericLoaderRegistry.IHaveLoader> getLoader() {
        return LOADER;
    }

    private static void hurtIfRolledBad(int chance, LivingEntity entity) {
        if (RANDOM.nextInt(chance) == 0) {
            entity.hurt(entity.getCommandSenderWorld().damageSources().lightningBolt(), 2);
        }
    }

    @Override
    public void onProjectileLaunch(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, @NotNull LivingEntity shooter, @NotNull Projectile projectile, @Nullable AbstractArrow arrow, @NotNull ModDataNBT persistentData, boolean primary) {
        hurtIfRolledBad(Mth.ceil(15 / modifier.getEffectiveLevel()), shooter);
    }

    @Override
    public void afterBlockBreak(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, @NotNull ToolHarvestContext context) {
        hurtIfRolledBad(Mth.ceil(30 / modifier.getEffectiveLevel()), context.getLiving());
    }

    @Override
    public @NotNull List<ModuleHook<?>> getDefaultHooks() {
        return DEFAULT_HOOKS;
    }
}
