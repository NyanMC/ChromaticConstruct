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
        addCompatMaterial(CCMaterialIds.infernium, 3, ORDER_WEAPON, false, "ingots/infernium"); // tier 3 since it's early nether

        addMaterial(CCMaterialIds.etherium, 4, ORDER_WEAPON, false, false, new ModLoadedCondition("enigmaticlegacy"));

        addCompatMaterial(CCMaterialIds.chroma, 2, ORDER_HARVEST, false, "gems/chroma");

        addCompatMaterial(CCMaterialIds.energizedSteel, 3, ORDER_HARVEST, false, "ingots/energized_steel"); // tier 3 because it's an "alloy"
    }

    @Override
    public @NotNull String getName() {
        return "Chromatic Construct Materials";
    }
}
