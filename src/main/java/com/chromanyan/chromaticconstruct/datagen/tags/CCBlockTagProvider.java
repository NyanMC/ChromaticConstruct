package com.chromanyan.chromaticconstruct.datagen.tags;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import com.chromanyan.chromaticconstruct.init.CCBlocks;
import com.chromanyan.chromaticconstruct.init.CCMaterials;
import com.chromanyan.chromaticconstruct.init.CCTags;
import com.chromanyan.chromaticconstruct.init.CompatTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class CCBlockTagProvider extends BlockTagsProvider {
    public CCBlockTagProvider(PackOutput out, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(out, lookupProvider, ChromaticConstruct.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(CCMaterials.rejuvenite.get(), CCBlocks.rawRejuveniteBlock.get());
        tag(BlockTags.MINEABLE_WITH_HOE).add(CCBlocks.rejuveniteOre.get());
        tag(BlockTags.NEEDS_DIAMOND_TOOL).add(CCMaterials.rejuvenite.get(), CCBlocks.rawRejuveniteBlock.get(), CCBlocks.rejuveniteOre.get());

        tag(CCTags.Blocks.STORAGE_BLOCKS_REJUVENITE).add(CCMaterials.rejuvenite.get());
        tag(CompatTags.Blocks.STORAGE_BLOCKS_ENERGIZED_STEEL).addOptional(new ResourceLocation("powah", "energized_steel_block"));
        tag(Tags.Blocks.STORAGE_BLOCKS)
                .add(CCMaterials.rejuvenite.get())
                .addOptional(new ResourceLocation("powah", "energized_steel_block"));

        tag(CCTags.Blocks.STORAGE_BLOCKS_RAW_REJUVENITE).add(CCBlocks.rawRejuveniteBlock.get());
        tag(CCTags.Blocks.ORES_REJUVENITE).add(CCBlocks.rejuveniteOre.get());
        tag(Tags.Blocks.ORES).add(CCBlocks.rejuveniteOre.get());
        tag(Tags.Blocks.ORE_RATES_SINGULAR).add(CCBlocks.rejuveniteOre.get());

        tag(CCTags.Blocks.REJUVENITE_CAN_REPLACE).add(Blocks.MOSS_BLOCK, Blocks.CLAY);
    }
}
