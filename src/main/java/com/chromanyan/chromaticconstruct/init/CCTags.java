package com.chromanyan.chromaticconstruct.init;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class CCTags {

    private static final String COMMON_NAMESPACE = "forge"; // in 1.21, change this to "c"

    public static class Items {
        private static TagKey<Item> commonTag(String name) {
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(COMMON_NAMESPACE, name));
        }

        public static final TagKey<Item> STORAGE_BLOCKS_REJUVENITE = CCMaterials.rejuvenite.getBlockItemTag();
        public static final TagKey<Item> STORAGE_BLOCKS_RAW_REJUVENITE = commonTag("storage_blocks/raw_rejuvenite");
        public static final TagKey<Item> INGOTS_REJUVENITE = CCMaterials.rejuvenite.getIngotTag();
        public static final TagKey<Item> NUGGETS_REJUVENITE = CCMaterials.rejuvenite.getNuggetTag();
        public static final TagKey<Item> RAW_MATERIALS_REJUVENITE = commonTag("raw_materials/rejuvenite");
        public static final TagKey<Item> ORES_REJUVENITE = commonTag("ores/rejuvenite");
    }

    public static class Blocks {
        @SuppressWarnings("SameParameterValue")
        private static TagKey<Block> commonTag(String name) {
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(COMMON_NAMESPACE, name));
        }

        public static final TagKey<Block> STORAGE_BLOCKS_REJUVENITE = CCMaterials.rejuvenite.getBlockTag();
        public static final TagKey<Block> STORAGE_BLOCKS_RAW_REJUVENITE = commonTag("storage_blocks/raw_rejuvenite");
        public static final TagKey<Block> ORES_REJUVENITE = commonTag("ores/rejuvenite");
        public static final TagKey<Block> REJUVENITE_CAN_REPLACE = BlockTags.create(ChromaticConstruct.getResource("rejuvenite_can_replace"));
    }
}
