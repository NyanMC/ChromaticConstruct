package com.chromanyan.chromaticconstruct.datagen;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import com.chromanyan.chromaticconstruct.init.CCFluids;
import net.minecraft.data.PackOutput;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.mantle.fluid.texture.AbstractFluidTextureProvider;
import slimeknights.mantle.fluid.texture.FluidTexture;

import static com.chromanyan.chromaticconstruct.ChromaticConstruct.getResource;

public class CCFluidTextureProvider extends AbstractFluidTextureProvider {

    public CCFluidTextureProvider(PackOutput out) {
        super(out, ChromaticConstruct.MODID);
    }

    @Override
    public void addTextures() {
        moltenFolder(CCFluids.moltenRejuvenite, "ore");
        moltenFolder(CCFluids.moltenCosmite, "compat_ore");
        moltenFolder(CCFluids.moltenInfernium, "compat_ore");
        moltenFolder(CCFluids.moltenEtherium, "compat_ore");
        moltenFolder(CCFluids.moltenChroma, "compat_ore");
        moltenFolder(CCFluids.moltenEnergizedSteel, "compat_ore");
    }

    private FluidTexture.Builder named(FluidObject<?> fluid, String name) {
        return texture(fluid).textures(getResource("fluid/"+name+"/"), false, false);
    }

    private FluidTexture.Builder moltenFolder(FluidObject<?> fluid, String folder) {
        return named(fluid, "molten/"+folder+"/"+withoutMolten(fluid));
    }

    private static final int MOLTEN_LENGTH = "molten_".length();

    private static String withoutMolten(FluidObject<?> fluid) {
        return fluid.getId().getPath().substring(MOLTEN_LENGTH);
    }

    @Override
    public @NotNull String getName() {
        return "Chromatic Construct Fluid Texture Providers";
    }
}
