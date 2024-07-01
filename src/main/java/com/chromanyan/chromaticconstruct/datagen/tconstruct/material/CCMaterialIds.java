package com.chromanyan.chromaticconstruct.datagen.tconstruct.material;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import slimeknights.tconstruct.library.materials.definition.MaterialId;

public class CCMaterialIds {
    public static final MaterialId hamhide = id("hamhide");

    public static final MaterialId cosmite = id("cosmite");
    public static final MaterialId infernium = id("infernium");
    public static final MaterialId etherium = id("etherium");
    public static final MaterialId chroma = id("chroma");

    private static MaterialId id(String name) {
        return new MaterialId(ChromaticConstruct.MODID, name);
    }
}
