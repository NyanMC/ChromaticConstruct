package com.chromanyan.chromaticconstruct.datagen.tconstruct;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import com.chromanyan.chromaticconstruct.init.CCFluids;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.data.tinkering.AbstractFluidEffectProvider;
import slimeknights.tconstruct.library.modifiers.fluid.FluidMobEffect;
import slimeknights.tconstruct.library.modifiers.fluid.TimeAction;
import slimeknights.tconstruct.library.recipe.FluidValues;

public class CCFluidEffectProvider extends AbstractFluidEffectProvider {

    public CCFluidEffectProvider(PackOutput out) {
        super(out, ChromaticConstruct.MODID);
    }

    @Override
    protected void addFluids() {
        metalborn(CCFluids.moltenCosmite.getTag(), 2f).addEffect(TimeAction.SET, FluidMobEffect.builder().effect(MobEffects.LEVITATION, 20*3));
        metalborn(CCFluids.moltenEtherium.getTag(), 4f).addEffect(TimeAction.SET, FluidMobEffect.builder().effect(MobEffects.DAMAGE_RESISTANCE, 20*3));
        burningFluidWithAmount(CCFluids.moltenChroma.getTag(), FluidValues.GEM_SHARD, 2f).addEffect(TimeAction.SET, FluidMobEffect.builder().effect(MobEffects.LEVITATION, 20));
    }

    /** Builder for an effect based metal */
    private Builder metalborn(TagKey<Fluid> tag, float damage) {
        return burningFluid(tag.location().getPath(), tag, FluidValues.NUGGET, damage, 0);
    }

    @SuppressWarnings("SameParameterValue")
    private Builder burningFluidWithAmount(TagKey<Fluid> tag, int amount, float damage) {
        return burningFluid(tag.location().getPath(), tag, amount, damage, 0);
    }

    @Override
    public @NotNull String getName() {
        return "Chromatic Construct Spilling Fluid Provider";
    }
}
