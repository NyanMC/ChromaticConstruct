package com.chromanyan.chromaticconstruct.datagen;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class CCItemModelProvider extends ItemModelProvider {

    public CCItemModelProvider(PackOutput packOutput, ExistingFileHelper existingFileHelper) {
        super(packOutput, ChromaticConstruct.MODID, existingFileHelper);
    }

    private void basicModel(String name) {
        this.singleTexture(name,
                mcLoc("item/generated"),
                "layer0",
                modLoc("item/" + name));
    }

    private void blockModel(String name) {
        this.withExistingParent(name, modLoc("block/" + name));
    }

    @Override
    protected void registerModels() {
        basicModel("glass_reinforcement");
        basicModel("hamhide");
        basicModel("raw_rejuvenite");
        basicModel("rejuvenite_ingot");
        basicModel("rejuvenite_nugget");
        blockModel("rejuvenite_block");
        blockModel("raw_rejuvenite_block");
        blockModel("rejuvenite_ore");
    }
}
