package com.chromanyan.chromaticconstruct.datagen.tconstruct;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import slimeknights.tconstruct.library.modifiers.ModifierId;

public class CCModifierIds {
    public static final ModifierId antiair = id("anti_air");

    private static ModifierId id(String name) {
        return new ModifierId(ChromaticConstruct.MODID, name);
    }
}
