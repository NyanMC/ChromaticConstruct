package com.chromanyan.chromaticconstruct.datagen.loot;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import com.chromanyan.chromaticconstruct.init.CCBlocks;
import com.chromanyan.chromaticconstruct.init.CCItems;
import com.chromanyan.chromaticconstruct.init.CCMaterials;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.stream.Collectors;

public class CCBlockLootTableProvider extends BlockLootSubProvider {

    protected CCBlockLootTableProvider() {
        super(Collections.emptySet(), FeatureFlags.REGISTRY.allFlags());
    }

    @Nonnull
    @SuppressWarnings("deprecation")
    @Override
    protected Iterable<Block> getKnownBlocks() {
        return BuiltInRegistries.BLOCK.stream()
                .filter(block -> ChromaticConstruct.MODID.equals(BuiltInRegistries.BLOCK.getKey(block).getNamespace()))
                .collect(Collectors.toList());
    }

    @Override
    protected void generate() {
        dropSelf(CCMaterials.rejuvenite.get());
        dropSelf(CCBlocks.rawRejuveniteBlock.get());
        add(CCBlocks.rejuveniteOre.get(), block -> createOreDrop(block, CCItems.rawRejuvenite.get()));
    }
}
