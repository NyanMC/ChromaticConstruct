package com.chromanyan.chromaticconstruct.init;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

// compat tags for mods that just can't be bothered to do it themselves.
// this class can and will crash and burn if it's accessed outside of datagen, so...Don't:tm:
public class CompatTags {

    private static final String COMMON_NAMESPACE = "forge";

    public static class Items {
        private static TagKey<Item> commonTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(COMMON_NAMESPACE, name));
        }

        public static final TagKey<Item> INGOTS_ENERGIZED_STEEL = commonTag("ingots/energized_steel");
        public static final TagKey<Item> STORAGE_BLOCKS_ENERGIZED_STEEL = commonTag("storage_blocks/energized_steel");
    }

    public static class Blocks {
        @SuppressWarnings("SameParameterValue")
        private static TagKey<Block> commonTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(COMMON_NAMESPACE, name));
        }

        public static final TagKey<Block> STORAGE_BLOCKS_ENERGIZED_STEEL = commonTag("storage_blocks/energized_steel");
    }
}
