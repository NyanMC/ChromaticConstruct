package com.chromanyan.chromaticconstruct;

import com.chromanyan.chromaticconstruct.datagen.tconstruct.CCModifiers;
import com.chromanyan.chromaticconstruct.datagen.CCRecipeProvider;
import com.chromanyan.chromaticconstruct.datagen.tconstruct.material.*;
import com.chromanyan.chromaticconstruct.init.CCFluids;
import com.mojang.logging.LogUtils;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;
import slimeknights.tconstruct.library.client.data.material.GeneratorPartTextureJsonGenerator;
import slimeknights.tconstruct.library.client.data.material.MaterialPartTextureGenerator;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.tools.data.sprite.TinkerPartSpriteProvider;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ChromaticConstruct.MODID)
public class ChromaticConstruct {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "chromatic_construct";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public ChromaticConstruct() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::gatherData);
        modEventBus.register(new CCFluids());

        CCFluids.FLUIDS.register(modEventBus);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void gatherData(final GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        ExistingFileHelper efh = event.getExistingFileHelper();

        AbstractMaterialDataProvider materials = new CCMaterialDataProvider(gen);
        gen.addProvider(event.includeServer(), materials);
        gen.addProvider(event.includeServer(), new CCMaterialTraitsDataProvider(gen, materials));
        gen.addProvider(event.includeServer(), new CCMaterialStatsDataProvider(gen, materials));
        gen.addProvider(event.includeServer(), new CCMaterialRecipeProvider(gen));
        gen.addProvider(event.includeServer(), new CCModifiers(gen));

        gen.addProvider(event.includeServer(), new CCRecipeProvider(gen));

        CCMaterialSpriteProvider materialSprites = new CCMaterialSpriteProvider();
        TinkerPartSpriteProvider partSprites = new TinkerPartSpriteProvider();
        gen.addProvider(event.includeClient(), new CCMaterialRenderInfoProvider(gen, materialSprites, efh));
        gen.addProvider(event.includeClient(), new GeneratorPartTextureJsonGenerator(gen, ChromaticConstruct.MODID, partSprites));
        gen.addProvider(event.includeClient(), new MaterialPartTextureGenerator(gen, efh, partSprites, materialSprites));
    }

    public static String makeDescriptionId(String type, String name) {
        return type + "." + MODID + "." + name;
    }

    public static ResourceLocation getResource(String name) {
        return new ResourceLocation(MODID, name);
    }
}