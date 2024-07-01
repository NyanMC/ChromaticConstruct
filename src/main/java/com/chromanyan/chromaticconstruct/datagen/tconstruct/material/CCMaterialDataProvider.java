package com.chromanyan.chromaticconstruct.datagen.tconstruct.material;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;

public class CCMaterialDataProvider extends AbstractMaterialDataProvider {

    public CCMaterialDataProvider(DataGenerator gen) {
        super(gen);
    }

    @Override
    protected void addMaterials() {
        addMaterial(CCMaterialIds.hamhide, 2, ORDER_BINDING, true);

        addCompatMaterial(CCMaterialIds.cosmite, 2, ORDER_COMPAT + ORDER_WEAPON, "gems/cosmite", false);
        addCompatMaterial(CCMaterialIds.infernium, 3, ORDER_COMPAT + ORDER_WEAPON, "ingots/infernium", false); // infernium is tier 3 since that's the tier cobalt ended up in
        addMaterial(CCMaterialIds.etherium, 4, ORDER_COMPAT + ORDER_WEAPON, false, false, new ModLoadedCondition("enigmaticlegacy"));
        addCompatMaterial(CCMaterialIds.chroma, 2, ORDER_COMPAT + ORDER_HARVEST, "gems/chroma", false);
    }

    @Override
    public @NotNull String getName() {
        return "Chromatic Construct Materials";
    }
}
