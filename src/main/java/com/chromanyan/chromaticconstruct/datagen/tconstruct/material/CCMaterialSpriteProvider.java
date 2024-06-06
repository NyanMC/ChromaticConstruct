package com.chromanyan.chromaticconstruct.datagen.tconstruct.material;

import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;
import slimeknights.tconstruct.library.client.data.spritetransformer.GreyToColorMapping;

public class CCMaterialSpriteProvider extends AbstractMaterialSpriteProvider {

    @Override
    public @NotNull String getName() {
        return "Chromatic Construct Materials";
    }

    @Override
    protected void addAllMaterials() {
        buildMaterial(CCMaterialIds.cosmite)
                .meleeHarvest()
                .fallbacks("crystal", "metal")
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF050811).addARGB(102, 0xFF1D1C35).addARGB(140, 0xFF29234D).addARGB(178, 0xFF362A63).addARGB(216, 0xFF603BAE).addARGB(255, 0xFF7C4EC4).build());
    }
}
