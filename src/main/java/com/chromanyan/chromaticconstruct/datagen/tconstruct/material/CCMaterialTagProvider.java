package com.chromanyan.chromaticconstruct.datagen.tconstruct.material;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.data.tinkering.AbstractMaterialTagProvider;

public class CCMaterialTagProvider extends AbstractMaterialTagProvider {

    public CCMaterialTagProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, ChromaticConstruct.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        tag(TinkerTags.Materials.NETHER).addOptional(
                CCMaterialIds.infernium
        );
        tag(TinkerTags.Materials.BARTERED).addOptional(
                CCMaterialIds.infernium
        );
        tag(TinkerTags.Materials.HARVEST).add(
                CCMaterialIds.rejuvenite
        ).addOptional(
                CCMaterialIds.infernium,
                CCMaterialIds.chroma,
                CCMaterialIds.energizedSteel
        );
        tag(TinkerTags.Materials.MELEE).addOptional(
                CCMaterialIds.cosmite,
                CCMaterialIds.etherium
        );

        tag(TinkerTags.Materials.LIGHT).addOptional(
                CCMaterialIds.chroma,
                CCMaterialIds.energizedSteel
        );
    }

    @Override
    public @NotNull String getName() {
        return "Chromatic Construct Material Tag Provider";
    }
}
