package com.chromanyan.chromaticconstruct.datagen.tconstruct;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import com.chromanyan.chromaticconstruct.init.CCFluids;
import net.minecraft.data.PackOutput;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.data.tinkering.AbstractFluidEffectProvider;
import slimeknights.tconstruct.library.modifiers.fluid.FluidMobEffect;
import slimeknights.tconstruct.library.modifiers.fluid.TimeAction;
import slimeknights.tconstruct.library.modifiers.fluid.entity.FireFluidEffect;

public class CCFluidEffectProvider extends AbstractFluidEffectProvider {

    public CCFluidEffectProvider(PackOutput out) {
        super(out, ChromaticConstruct.MODID);
    }

    @Override
    protected void addFluids() {
        // infernium - even more damage, same fire as lava, and a bit of haste for your troubles
        addMetal(CCFluids.moltenInfernium)
                .fireDamage(3f)
                .addEntityEffect(new FireFluidEffect(TimeAction.ADD, 4))
                .addEntityEffects(FluidMobEffect.builder().effect(MobEffects.DIG_SPEED, 20*5).buildEntity(TimeAction.ADD))
                .placeFire()
                // i'd love to use compatMetal but for some accursed reason TagFilledCondition won't cooperate with me
                .addCondition(new ModLoadedCondition("meaningfulmaterials"));
        // etherium - strength and resistance. the source mod's OP and this is a limited resource, what'd you expect
        addMetal(CCFluids.moltenEtherium).magicDamage(3)
                .addEffect(FluidMobEffect.builder().effect(MobEffects.DAMAGE_BOOST, 20 * 7), TimeAction.SET)
                .addEffect(FluidMobEffect.builder().effect(MobEffects.DAMAGE_RESISTANCE, 20 * 7), TimeAction.SET)
                .addCondition(new ModLoadedCondition("enigmaticlegacy"));
    }

    @Override
    public @NotNull String getName() {
        return "Chromatic Construct Spilling Fluid Provider";
    }
}
