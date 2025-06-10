package com.chromanyan.chromaticconstruct.datagen;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import com.chromanyan.chromaticconstruct.datagen.tconstruct.CCModifierIds;
import net.minecraft.data.PackOutput;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.data.tinkering.AbstractEnchantmentToModifierProvider;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.tools.data.ModifierIds;

public class CCEnchantmentToModifierProvider extends AbstractEnchantmentToModifierProvider {

    public CCEnchantmentToModifierProvider(PackOutput out) {
        super(out);
    }

    @Override
    protected void addEnchantmentMappings() {
        addOptionalCompat(CCModifierIds.nemesis);
        addOptionalCompat(CCModifierIds.sorrow);
        addCompat(ModifierIds.crystalshot);
        addCompat(ModifierIds.power);
        addCompat(ModifierIds.cooling);
    }

    private void addCompat(ModifierId modifier) {
        add(ChromaticConstruct.getResource("modifier_like/" + modifier.getPath()), modifier);
    }

    private void addOptionalCompat(ModifierId modifier) { // thanks knightminer
        add(ChromaticConstruct.getResource("modifier_like/" + modifier.getPath()), modifier, true);
    }

    @Override
    public @NotNull String getName() {
        return "Chromatic Construct Enchantment to Modifier Mapping";
    }
}
