package com.chromanyan.chromaticconstruct.init;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.registries.RegistryObject;
import slimeknights.tconstruct.common.TinkerEffect;
import slimeknights.tconstruct.common.registration.EnumDeferredRegister;
import slimeknights.tconstruct.tools.modifiers.effect.NoMilkEffect;

public class CCMobEffects {

    public static final EnumDeferredRegister<MobEffect> MOB_EFFECTS = new EnumDeferredRegister<>(Registries.MOB_EFFECT, ChromaticConstruct.MODID);

    public static final RegistryObject<TinkerEffect> fragileCooldownEffect = MOB_EFFECTS.register("fragile_cooldown", () ->
            new NoMilkEffect(MobEffectCategory.HARMFUL, 0xCCCCFF, true));

    public static final RegistryObject<TinkerEffect> panicEffect = MOB_EFFECTS.register("panic", () ->
            new NoMilkEffect(MobEffectCategory.BENEFICIAL, 0xFF7272, true)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED, "26541d75-f7e1-4147-bf44-9cf7db883b50", 0.15f, AttributeModifier.Operation.MULTIPLY_TOTAL));

}
