package com.chromanyan.chromaticconstruct.datagen.tags;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import com.chromanyan.chromaticconstruct.datagen.tconstruct.CCModifierIds;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.tools.data.ModifierIds;

import java.util.concurrent.CompletableFuture;

public class CCEnchantmentTagProvider extends TagsProvider<Enchantment> {

    public CCEnchantmentTagProvider(PackOutput out, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(out, Registries.ENCHANTMENT, lookupProvider, ChromaticConstruct.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        modifierTag(CCModifierIds.nemesis, "enigmaticlegacy:nemesis");
        modifierTag(CCModifierIds.sorrow, "enigmaticlegacy:sorrow");
        modifierTag(ModifierIds.crystalshot, "enigmaticlegacy:ceaseless");
        modifierTag(ModifierIds.power, "enigmaticlegacy:sharpshooter");
        modifierTag(ModifierIds.cooling, "enigmaticlegacy:torrent");
    }

    private void modifierTag(ModifierId modifier, String... ids) {
        TagsProvider.TagAppender<Enchantment> appender = tag(TagKey.create(Registries.ENCHANTMENT, ChromaticConstruct.getResource("modifier_like/" + modifier.getPath())));
        for (String id : ids) {
            appender.addOptional(new ResourceLocation(id));
        }
    }

    @Override
    public @NotNull String getName() {
        return "Chromatic Construct Block Enchantment Tags";
    }
}
