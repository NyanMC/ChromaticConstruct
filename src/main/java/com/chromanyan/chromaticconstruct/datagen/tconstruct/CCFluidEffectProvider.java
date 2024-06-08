package com.chromanyan.chromaticconstruct.datagen.tconstruct;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import com.chromanyan.chromaticconstruct.init.CCFluids;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.level.material.Fluid;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.data.tinkering.AbstractFluidEffectProvider;
import slimeknights.tconstruct.library.modifiers.fluid.FluidMobEffect;
import slimeknights.tconstruct.library.modifiers.fluid.TimeAction;
import slimeknights.tconstruct.library.recipe.FluidValues;

public class CCFluidEffectProvider extends AbstractFluidEffectProvider {

    public CCFluidEffectProvider(DataGenerator generator) {
        super(generator, ChromaticConstruct.MODID);
    }

    @Override
    protected void addFluids() {
        metalborn(CCFluids.moltenCosmite.getForgeTag(), 2f).addEffect(TimeAction.SET, FluidMobEffect.builder().effect(MobEffects.LEVITATION, 20*3));
    }

    /** Builder for an effect based metal */
    private Builder metalborn(TagKey<Fluid> tag, float damage) {
        return burningFluid(tag.location().getPath(), tag, FluidValues.NUGGET, damage, 0);
    }

    @Override
    public @NotNull String getName() {
        return "Chromatic Construct Spilling Fluid Provider";
    }
}
