package com.chromanyan.chromaticconstruct.datagen;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import com.chromanyan.chromaticconstruct.init.CompatTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import owmii.powah.block.Blcks;

import java.util.concurrent.CompletableFuture;

public class CCBlockTagProvider extends BlockTagsProvider {
    public CCBlockTagProvider(PackOutput out, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(out, lookupProvider, ChromaticConstruct.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(CompatTags.Blocks.STORAGE_BLOCKS_ENERGIZED_STEEL).add(Blcks.ENERGIZED_STEEL.get());
    }
}
