package com.chromanyan.chromaticconstruct.datagen.tconstruct.material;

import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialRenderInfoProvider;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;

public class CCMaterialRenderInfoProvider extends AbstractMaterialRenderInfoProvider {
    public CCMaterialRenderInfoProvider(PackOutput out, @Nullable AbstractMaterialSpriteProvider materialSprites, @Nullable ExistingFileHelper existingFileHelper) {
        super(out, materialSprites, existingFileHelper);
    }

    @Override
    protected void addMaterialRenderInfo() {
        buildRenderInfo(CCMaterialIds.hamhide).color(0xFF7272);
        buildRenderInfo(CCMaterialIds.rejuvenite).color(0xDA4F4F).fallbacks("metal");

        buildRenderInfo(CCMaterialIds.cosmite).color(0x513593).fallbacks("crystal", "metal");
        buildRenderInfo(CCMaterialIds.infernium).color(0xF9AB80).fallbacks("metal");
        buildRenderInfo(CCMaterialIds.etherium).color(0x96FFFF).fallbacks("metal");

        buildRenderInfo(CCMaterialIds.chroma);

        buildRenderInfo(CCMaterialIds.energizedSteel).color(0xBA9566).fallbacks("metal");
    }

    @Override
    public @NotNull String getName() {
        return "Chromatic Construct Material Render Info Provider";
    }
}
