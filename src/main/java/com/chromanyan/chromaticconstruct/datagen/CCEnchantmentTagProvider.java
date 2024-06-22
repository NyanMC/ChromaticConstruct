package com.chromanyan.chromaticconstruct.datagen;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import com.chromanyan.chromaticconstruct.datagen.tconstruct.CCModifierIds;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.tools.TinkerModifiers;
import slimeknights.tconstruct.tools.data.ModifierIds;

@SuppressWarnings("deprecation")
public class CCEnchantmentTagProvider extends TagsProvider<Enchantment> {

    public CCEnchantmentTagProvider(DataGenerator p_126546_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_126546_, Registry.ENCHANTMENT, ChromaticConstruct.MODID, existingFileHelper);
    }

    @Override
    protected void addTags() {
        modifierTag(CCModifierIds.nemesis, "enigmaticlegacy:nemesis");
        modifierTag(TinkerModifiers.crystalshot.getId(), "enigmaticlegacy:ceaseless");
        modifierTag(ModifierIds.power, "enigmaticlegacy:sharpshooter");
        modifierTag(ModifierIds.cooling, "enigmaticlegacy:torrent");
    }

    private void modifierTag(ModifierId modifier, String... ids) {
        TagsProvider.TagAppender<Enchantment> appender = tag(TagKey.create(Registry.ENCHANTMENT_REGISTRY, ChromaticConstruct.getResource("modifier_like/" + modifier.getPath())));
        for (String id : ids) {
            appender.addOptional(new ResourceLocation(id));
        }
    }

    @Override
    public @NotNull String getName() {
        return "Chromatic Construct Block Enchantment Tags";
    }
}
