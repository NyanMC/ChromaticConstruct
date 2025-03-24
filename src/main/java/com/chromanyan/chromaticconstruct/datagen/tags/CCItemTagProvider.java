package com.chromanyan.chromaticconstruct.datagen.tags;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import com.chromanyan.chromaticconstruct.init.CCItems;
import com.chromanyan.chromaticconstruct.init.CCMaterials;
import com.chromanyan.chromaticconstruct.init.CCTags;
import com.chromanyan.chromaticconstruct.init.CompatTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class CCItemTagProvider extends ItemTagsProvider {

    public CCItemTagProvider(PackOutput out, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(out, lookupProvider, p_275322_, ChromaticConstruct.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.@NotNull Provider provider) {
        copy(CCTags.Blocks.STORAGE_BLOCKS_REJUVENITE, CCTags.Items.STORAGE_BLOCKS_REJUVENITE);
        copy(CompatTags.Blocks.STORAGE_BLOCKS_ENERGIZED_STEEL, CompatTags.Items.STORAGE_BLOCKS_ENERGIZED_STEEL);
        copy(Tags.Blocks.STORAGE_BLOCKS, Tags.Items.STORAGE_BLOCKS);
        copy(CCTags.Blocks.STORAGE_BLOCKS_RAW_REJUVENITE, CCTags.Items.STORAGE_BLOCKS_RAW_REJUVENITE);
        copy(CCTags.Blocks.ORES_REJUVENITE, CCTags.Items.ORES_REJUVENITE);
        copy(Tags.Blocks.ORES, Tags.Items.ORES);
        copy(Tags.Blocks.ORE_RATES_SINGULAR, Tags.Items.ORE_RATES_SINGULAR);

        tag(CompatTags.Items.INGOTS_ENERGIZED_STEEL).addOptional(new ResourceLocation("powah", "steel_energized"));
        tag(CCTags.Items.INGOTS_REJUVENITE).add(CCMaterials.rejuvenite.getIngot());
        tag(CCTags.Items.NUGGETS_REJUVENITE).add(CCMaterials.rejuvenite.getNugget());
        tag(CCTags.Items.RAW_MATERIALS_REJUVENITE).add(CCItems.rawRejuvenite.get());

        tag(Tags.Items.INGOTS)
                .add(CCMaterials.rejuvenite.getIngot())
                .addOptional(new ResourceLocation("powah", "steel_energized"));
        tag(Tags.Items.NUGGETS).add(CCMaterials.rejuvenite.getNugget());
        tag(Tags.Items.RAW_MATERIALS).add(CCItems.rawRejuvenite.get());
    }
}
