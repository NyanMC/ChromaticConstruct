package com.chromanyan.chromaticconstruct.init;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import net.minecraft.core.Registry;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.tconstruct.common.TinkerEffect;
import slimeknights.tconstruct.common.registration.EnumDeferredRegister;
import slimeknights.tconstruct.tools.modifiers.effect.NoMilkEffect;

public class CCMobEffects {

    public static final EnumDeferredRegister<MobEffect> MOB_EFFECTS = new EnumDeferredRegister<>(Registry.MOB_EFFECT_REGISTRY, ChromaticConstruct.MODID);

    public static final RegistryObject<TinkerEffect> fragileCooldownEffect = MOB_EFFECTS.register("fragile_cooldown", () -> new NoMilkEffect(MobEffectCategory.HARMFUL, 0xCCCCFF, true));

}
