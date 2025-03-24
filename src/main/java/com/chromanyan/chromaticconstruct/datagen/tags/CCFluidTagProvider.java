package com.chromanyan.chromaticconstruct.datagen.tags;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import com.chromanyan.chromaticconstruct.init.CCFluids;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.registration.object.FlowingFluidObject;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.common.TinkerTags;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@SuppressWarnings("unchecked")
public class CCFluidTagProvider extends FluidTagsProvider {
    public CCFluidTagProvider(PackOutput out, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(out, lookupProvider, ChromaticConstruct.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider pProvider) {
        fluidTag(CCFluids.moltenCosmite);
        fluidTag(CCFluids.moltenInfernium);
        fluidTag(CCFluids.moltenEtherium);
        fluidTag(CCFluids.moltenChroma);
        fluidTag(CCFluids.moltenEnergizedSteel);

        this.tag(TinkerTags.Fluids.LARGE_GEM_TOOLTIPS).addTags(CCFluids.moltenCosmite.getLocalTag(), CCFluids.moltenChroma.getLocalTag());
        this.tag(TinkerTags.Fluids.METAL_TOOLTIPS).addTags(CCFluids.moltenInfernium.getLocalTag(), CCFluids.moltenEtherium.getLocalTag(), CCFluids.moltenEnergizedSteel.getLocalTag());
    }

    /** Adds tags for an unplacable fluid */
    private void fluidTag(FluidObject<?> fluid) {
        tag(Objects.requireNonNull(fluid.getCommonTag())).add(fluid.get());
    }

    /** Adds tags for a placable fluid */
    private void fluidTag(FlowingFluidObject<?> fluid) {
        tag(fluid.getLocalTag()).add(fluid.getStill(), fluid.getFlowing());
        TagKey<Fluid> tag = fluid.getCommonTag();
        if (tag != null) {
            tag(tag).addTag(fluid.getLocalTag());
        }
    }
}
