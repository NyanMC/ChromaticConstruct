package com.chromanyan.chromaticconstruct.datagen.tconstruct.material;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

public class CCMaterialIds {
    public static final MaterialId cosmite = id("cosmite");

    private static MaterialId id(String name) {
        return new MaterialId(ChromaticConstruct.MODID, name);
    }
}