package com.chromanyan.chromaticconstruct.datagen;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import com.chromanyan.chromaticconstruct.init.CompatTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import owmii.powah.item.Itms;

import java.util.concurrent.CompletableFuture;

public class CCItemTagProvider extends ItemTagsProvider {

    public CCItemTagProvider(PackOutput out, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(out, lookupProvider, p_275322_, ChromaticConstruct.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        copy(CompatTags.Blocks.STORAGE_BLOCKS_ENERGIZED_STEEL, CompatTags.Items.STORAGE_BLOCKS_ENERGIZED_STEEL);
        tag(CompatTags.Items.INGOTS_ENERGIZED_STEEL).add(Itms.ENERGIZED_STEEL.get());
    }
}
