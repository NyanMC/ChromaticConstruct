package com.chromanyan.chromaticconstruct.datagen.tconstruct.material;

import net.minecraft.data.DataGenerator;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;

public class CCMaterialDataProvider extends AbstractMaterialDataProvider {

    public CCMaterialDataProvider(DataGenerator gen) {
        super(gen);
    }

    @Override
    protected void addMaterials() {
        addCompatMaterial(CCMaterialIds.cosmite, 2, ORDER_COMPAT + ORDER_WEAPON, "gems/cosmite", false);
    }

    @Override
    public @NotNull String getName() {
        return "Chromatic Construct Materials";
    }
}
