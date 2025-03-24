package com.chromanyan.chromaticconstruct.datagen;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import com.chromanyan.chromaticconstruct.init.CCBlocks;
import com.chromanyan.chromaticconstruct.init.CCMaterials;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class CCBlockStateProvider extends BlockStateProvider {

    public CCBlockStateProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, ChromaticConstruct.MODID, existingFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(CCMaterials.rejuvenite.get());
        simpleBlock(CCBlocks.rawRejuveniteBlock.get(), models().cubeColumn("raw_rejuvenite_block",
                ChromaticConstruct.getResource("block/raw_rejuvenite_block_side"),
                ChromaticConstruct.getResource("block/raw_rejuvenite_block_end")));
        simpleBlock(CCBlocks.rejuveniteOre.get());
    }
}
