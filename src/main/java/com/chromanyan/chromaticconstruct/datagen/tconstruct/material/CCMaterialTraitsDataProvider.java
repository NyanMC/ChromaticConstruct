package com.chromanyan.chromaticconstruct.datagen.tconstruct.material;

import com.chromanyan.chromaticconstruct.datagen.tconstruct.CCModifierIds;
import com.chromanyan.chromaticconstruct.init.CCModifiers;
import net.minecraft.data.DataGenerator;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialTraitDataProvider;
import slimeknights.tconstruct.library.materials.MaterialRegistry;

public class CCMaterialTraitsDataProvider extends AbstractMaterialTraitDataProvider {
    public CCMaterialTraitsDataProvider(DataGenerator gen, AbstractMaterialDataProvider materials) {
        super(gen, materials);
    }

    @Override
    protected void addMaterialTraits() {
        addTraits(CCMaterialIds.hamhide, MaterialRegistry.ARMOR, CCModifierIds.panic);

        addDefaultTraits(CCMaterialIds.cosmite, CCModifierIds.antiair, CCModifierIds.antigravity);
        addTraits(CCMaterialIds.cosmite, MaterialRegistry.ARMOR, CCModifierIds.moonbound, CCModifierIds.antigravity);

        addDefaultTraits(CCMaterialIds.infernium, CCModifiers.infernal);

        addDefaultTraits(CCMaterialIds.etherium, CCModifiers.backstep);
        addTraits(CCMaterialIds.etherium, MaterialRegistry.ARMOR, CCModifierIds.emergencyProtection);

        addDefaultTraits(CCMaterialIds.chroma, CCModifiers.variety);
    }

    @Override
    public @NotNull String getName() {
        return "Chromatic Construct Material Traits";
    }
}
