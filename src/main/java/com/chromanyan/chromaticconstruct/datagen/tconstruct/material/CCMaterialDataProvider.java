package com.chromanyan.chromaticconstruct.datagen.tconstruct.material;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;

public class CCMaterialDataProvider extends AbstractMaterialDataProvider {

    public CCMaterialDataProvider(PackOutput out) {
        super(out);
    }

    @Override
    protected void addMaterials() {
        addMaterial(CCMaterialIds.hamhide, 2, ORDER_BINDING, true);

        addCompatMaterial(CCMaterialIds.cosmite, 2, ORDER_WEAPON, false, "gems/cosmite");
        addCompatMaterial(CCMaterialIds.infernium, 3, ORDER_WEAPON, false, "ingots/infernium"); // infernium is tier 3 since that's the tier cobalt ended up in
        addMaterial(CCMaterialIds.etherium, 4, ORDER_WEAPON, false, false, new ModLoadedCondition("enigmaticlegacy"));
        addCompatMaterial(CCMaterialIds.chroma, 2, ORDER_HARVEST, false, "gems/chroma");
    }

    @Override
    public @NotNull String getName() {
        return "Chromatic Construct Materials";
    }
}
