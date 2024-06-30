package com.chromanyan.chromaticconstruct.datagen;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import com.chromanyan.chromaticconstruct.datagen.tconstruct.CCModifierIds;
import net.minecraft.data.DataGenerator;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.data.tinkering.AbstractEnchantmentToModifierProvider;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.data.ModifierIds;

public class CCEnchantmentToModifierProvider extends AbstractEnchantmentToModifierProvider {

    public CCEnchantmentToModifierProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void addEnchantmentMappings() {
        addCompat(CCModifierIds.nemesis);
        addCompat(CCModifierIds.sorrow);
        addCompat(TinkerModifiers.crystalshot.getId());
        addCompat(ModifierIds.power);
        addCompat(ModifierIds.cooling);
    }

    private void addCompat(ModifierId modifier) {
        add(ChromaticConstruct.getResource("modifier_like/" + modifier.getPath()), modifier);
    }

    @Override
    public @NotNull String getName() {
        return "Chromatic Construct Enchantment to Modifier Mapping";
    }
}
