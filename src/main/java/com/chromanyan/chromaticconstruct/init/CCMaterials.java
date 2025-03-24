package com.chromanyan.chromaticconstruct.init;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import slimeknights.mantle.registration.deferred.BlockDeferredRegister;
import slimeknights.mantle.registration.object.MetalItemObject;

public class CCMaterials {
    public static final BlockDeferredRegister MATERIALS = new BlockDeferredRegister(ChromaticConstruct.MODID);

    public static final MetalItemObject rejuvenite = MATERIALS.registerMetal("rejuvenite", metalBuilder(MapColor.COLOR_RED), CCBlocks.TOOLTIP_BLOCK_ITEM, CCBlocks.ITEM_PROPS);

    protected static BlockBehaviour.Properties metalBuilder(MapColor color) {
        return CCBlocks.builder(color, SoundType.METAL).instrument(NoteBlockInstrument.IRON_XYLOPHONE).requiresCorrectToolForDrops().strength(5.0f);
    }
}
