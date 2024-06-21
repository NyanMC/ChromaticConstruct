package com.chromanyan.chromaticconstruct.datagen.tconstruct;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import com.chromanyan.chromaticconstruct.init.CCModifiers;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierTagProvider;

public class CCModifierTagProvider extends AbstractModifierTagProvider {
    public CCModifierTagProvider(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, ChromaticConstruct.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(TinkerTags.Modifiers.GENERAL_SLOTLESS).add(CCModifierIds.antigravity, CCModifierIds.nemesis);
        this.tag(TinkerTags.Modifiers.INTERACTION_ABILITIES).add(CCModifiers.snowball.getId());
        this.tag(TinkerTags.Modifiers.RANGED_ABILITIES).add(CCModifiers.riding.getId());
        this.tag(TinkerTags.Modifiers.LEGGING_UPGRADES).add(CCModifierIds.encumberment);
        this.tag(TinkerTags.Modifiers.PROTECTION_DEFENSE).add(CCModifiers.fragileProtection.getId());
    }

    @Override
    public @NotNull String getName() {
        return "Chromatic Construct Modifier Tag Provider";
    }
}
