package com.chromanyan.chromaticconstruct.datagen;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import com.chromanyan.chromaticconstruct.init.CCFluids;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.FluidTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import slimeknights.mantle.registration.object.FlowingFluidObject;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.common.TinkerTags;

@SuppressWarnings("unchecked")
public class CCFluidTagProvider extends FluidTagsProvider {
    public CCFluidTagProvider(DataGenerator p_126523_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_126523_, ChromaticConstruct.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tagAll(CCFluids.moltenCosmite);
        tagAll(CCFluids.moltenEtherium);
        tagAll(CCFluids.moltenChroma);

        this.tag(TinkerTags.Fluids.LARGE_GEM_TOOLTIPS).addTags(CCFluids.moltenCosmite.getLocalTag(), CCFluids.moltenChroma.getLocalTag());
        this.tag(TinkerTags.Fluids.METAL_TOOLTIPS).addTags(CCFluids.moltenEtherium.getLocalTag());
    }

    /** Tags this fluid using local tags */
    private void tagLocal(FlowingFluidObject<?> fluid) {
        tag(fluid.getLocalTag()).add(fluid.getStill(), fluid.getFlowing());
    }

    /** Tags this fluid with local and forge tags */
    private void tagAll(FlowingFluidObject<?> fluid) {
        tagLocal(fluid);
        tag(fluid.getForgeTag()).addTag(fluid.getLocalTag());
    }

    /** Tags this fluid with local and forge tags */
    private void tagForge(FluidObject<?> fluid) {
        tag(fluid.getForgeTag()).add(fluid.get());
    }
}
