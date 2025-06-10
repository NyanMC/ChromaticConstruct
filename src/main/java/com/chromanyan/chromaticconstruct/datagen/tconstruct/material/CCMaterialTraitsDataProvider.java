package com.chromanyan.chromaticconstruct.datagen.tconstruct.material;

import com.chromanyan.chromaticconstruct.datagen.tconstruct.CCModifierIds;
import com.chromanyan.chromaticconstruct.init.CCModifiers;
import net.minecraft.data.PackOutput;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialTraitDataProvider;
import slimeknights.tconstruct.library.materials.MaterialRegistry;
import slimeknights.tconstruct.tools.data.ModifierIds;

public class CCMaterialTraitsDataProvider extends AbstractMaterialTraitDataProvider {
    public CCMaterialTraitsDataProvider(PackOutput out, AbstractMaterialDataProvider materials) {
        super(out, materials);
    }

    @Override
    protected void addMaterialTraits() {
        addTraits(CCMaterialIds.hamhide, MaterialRegistry.ARMOR, CCModifierIds.panic);
        addDefaultTraits(CCMaterialIds.rejuvenite, CCModifiers.harvestShield);
        addTraits(CCMaterialIds.rejuvenite, MaterialRegistry.ARMOR, CCModifierIds.solarProtection);

        addDefaultTraits(CCMaterialIds.cosmite, CCModifierIds.antiair, CCModifierIds.antigravity);
        //TODO 1.21: remove cosmite maille traits and texture generation
        addTraits(CCMaterialIds.cosmite, MaterialRegistry.ARMOR, ModifierIds.skyfall, CCModifierIds.antigravity);

        addDefaultTraits(CCMaterialIds.infernium, CCModifiers.infernal);

        addDefaultTraits(CCMaterialIds.etherium, CCModifiers.backstep);
        addTraits(CCMaterialIds.etherium, MaterialRegistry.ARMOR, CCModifierIds.emergencyProtection);

        addDefaultTraits(CCMaterialIds.chroma, CCModifiers.variety);
        addDefaultTraits(CCMaterialIds.energizedSteel, CCModifierIds.shocking);
    }

    @Override
    public @NotNull String getName() {
        return "Chromatic Construct Material Traits";
    }
}
