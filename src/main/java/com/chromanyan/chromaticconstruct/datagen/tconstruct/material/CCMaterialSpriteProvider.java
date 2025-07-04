package com.chromanyan.chromaticconstruct.datagen.tconstruct.material;

import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.client.data.material.AbstractMaterialSpriteProvider;
import slimeknights.tconstruct.library.client.data.spritetransformer.GreyToColorMapping;
import slimeknights.tconstruct.library.client.data.spritetransformer.GreyToSpriteTransformer;

import static com.chromanyan.chromaticconstruct.ChromaticConstruct.getResource;
import static slimeknights.tconstruct.tools.data.sprite.TinkerPartSpriteProvider.SLIMESUIT;

public class CCMaterialSpriteProvider extends AbstractMaterialSpriteProvider {

    @Override
    public @NotNull String getName() {
        return "Chromatic Construct Materials";
    }

    @Override
    protected void addAllMaterials() {
        buildMaterial(CCMaterialIds.hamhide)
                .fallbacks("cloth")
                .repairKit().maille()
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF5C1B30).addARGB(102, 0xFF761C38).addARGB(140, 0xFFB02054).addARGB(178, 0xFFC72054).addARGB(216, 0xFFF22263).addARGB(255, 0xFFFF2E70).build());
        buildMaterial(CCMaterialIds.rejuvenite)
                .fallbacks("metal")
                .meleeHarvest().armor()
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF280129).addARGB(102, 0xFF720014).addARGB(140, 0xFF820B1B).addARGB(178, 0xFFAD1E23).addARGB(216, 0xFFE65B5B).addARGB(255, 0xFFED8975).build());

        buildMaterial(CCMaterialIds.cosmite)
                .meleeHarvest().maille()
                .fallbacks("crystal", "metal")
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF050811).addARGB(102, 0xFF1D1C35).addARGB(140, 0xFF29234D).addARGB(178, 0xFF362A63).addARGB(216, 0xFF603BAE).addARGB(255, 0xFF7C4EC4).build());
        buildMaterial(CCMaterialIds.infernium)
                .meleeHarvest()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFFE22F69).addARGB(102, 0xFFED6956).addARGB(140, 0xFFF17E63).addARGB(178, 0xFFF9AB80).addARGB(216, 0xFFFDD1AA).addARGB(255, 0xFFFFF0CC).build());
        buildMaterial(CCMaterialIds.etherium)
                .meleeHarvest().armor()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF339468).addARGB(102, 0xFF48D1AC).addARGB(140, 0xFF55FFFF).addARGB(178, 0xFFB0FFFF).addARGB(216, 0xFFC4FFFF).addARGB(255, 0xFFFFFFFF).build());

        ResourceLocation baseTexture = getResource("item/materials/generator/chroma");
        ResourceLocation highlightTexture = getResource("item/materials/generator/chroma_highlight");
        ResourceLocation borderTexture = getResource("item/materials/generator/chroma_border");
        buildMaterial(CCMaterialIds.chroma)
                .meleeHarvest()
                .ranged()
                .statType(SLIMESUIT)
                .fallbacks("crystal", "metal")
                .transformer(GreyToSpriteTransformer.builderFromBlack()
                        .addTexture( 63, borderTexture,    0xFFC8C8C8).addTexture(102, borderTexture)
                        .addTexture(140, baseTexture,      0xFFE1E1E1).addTexture(178, baseTexture)
                        .addTexture(216, highlightTexture, 0xFFE1E1E1).addTexture(255, highlightTexture)
                        .build());

        buildMaterial(CCMaterialIds.energizedSteel)
                .meleeHarvest().ranged()
                .fallbacks("metal")
                .colorMapper(GreyToColorMapping.builderFromBlack().addARGB(63, 0xFF584834).addARGB(102, 0xFF76644A).addARGB(140, 0xFF857155).addARGB(178, 0xFFBA9566).addARGB(216, 0xFFD0B28C).addARGB(255, 0xFFD6D39E).build());
    }
}
