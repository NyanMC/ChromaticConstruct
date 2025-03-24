package com.chromanyan.chromaticconstruct.init;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import slimeknights.mantle.item.BlockTooltipItem;
import slimeknights.mantle.registration.deferred.BlockDeferredRegister;
import slimeknights.mantle.registration.object.ItemObject;

import java.util.function.Function;

public class CCBlocks {
    public static final BlockDeferredRegister BLOCKS = new BlockDeferredRegister(ChromaticConstruct.MODID);

    public static final Item.Properties ITEM_PROPS = new Item.Properties();
    protected static final Function<Block,? extends BlockItem> BLOCK_ITEM = (b) -> new BlockItem(b, ITEM_PROPS);
    public static final Function<Block,? extends BlockItem> TOOLTIP_BLOCK_ITEM = (b) -> new BlockTooltipItem(b, ITEM_PROPS);

    public static final ItemObject<Block> rawRejuveniteBlock = BLOCKS.register("raw_rejuvenite_block", () -> new Block(builder(MapColor.COLOR_RED, SoundType.STONE).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(5.0f, 6.0f)), BLOCK_ITEM);
    public static final ItemObject<Block> rejuveniteOre = BLOCKS.register("rejuvenite_ore", () -> new Block(builder(MapColor.COLOR_RED, SoundType.MOSS).instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(3.0f, 3.0f)), BLOCK_ITEM);

    protected static BlockBehaviour.Properties builder(SoundType soundType) {
        return Block.Properties.of().sound(soundType);
    }

    /** Same as above, but with a color */
    protected static BlockBehaviour.Properties builder(MapColor color, SoundType soundType) {
        return builder(soundType).mapColor(color);
    }
}
