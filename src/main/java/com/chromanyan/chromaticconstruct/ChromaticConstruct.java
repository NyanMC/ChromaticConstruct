package com.chromanyan.chromaticconstruct;

import com.chromanyan.chromaticconstruct.datagen.CCEnchantmentTagProvider;
import com.chromanyan.chromaticconstruct.datagen.tconstruct.CCFluidEffectProvider;
import com.chromanyan.chromaticconstruct.datagen.CCRecipeProvider;
import com.chromanyan.chromaticconstruct.datagen.tconstruct.material.*;
import com.chromanyan.chromaticconstruct.event.CCEvents;
import com.chromanyan.chromaticconstruct.init.CCFluids;
import com.chromanyan.chromaticconstruct.init.CCItems;
import com.chromanyan.chromaticconstruct.init.CCMobEffects;
import com.chromanyan.chromaticconstruct.init.CCModifiers;
import com.chromanyan.chromaticconstruct.tools.CCPredicate;
import com.mojang.logging.LogUtils;
import net.minecraft.core.Registry;
import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import org.slf4j.Logger;
import slimeknights.mantle.data.predicate.entity.LivingEntityPredicate;
import slimeknights.tconstruct.library.client.data.material.GeneratorPartTextureJsonGenerator;
import slimeknights.tconstruct.library.client.data.material.MaterialPartTextureGenerator;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;
import slimeknights.tconstruct.tools.data.sprite.TinkerPartSpriteProvider;

import java.util.function.Supplier;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ChromaticConstruct.MODID)
public class ChromaticConstruct {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "chromatic_construct";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    public ChromaticConstruct() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        CCItems.ITEMS.register(modEventBus);
        CCMobEffects.MOB_EFFECTS.register(modEventBus);
        CCFluids.FLUIDS.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(this::gatherData);
        modEventBus.addListener(this::registerEvent);

        modEventBus.register(new CCFluids());
        modEventBus.register(new CCModifiers());

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new CCEvents());
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

        gen.addProvider(event.includeServer(), new CCRecipeProvider(gen));
        gen.addProvider(event.includeServer(), new CCFluidEffectProvider(gen));
        gen.addProvider(event.includeServer(), new CCEnchantmentTagProvider(gen, efh));

        CCMaterialSpriteProvider materialSprites = new CCMaterialSpriteProvider();
        TinkerPartSpriteProvider partSprites = new TinkerPartSpriteProvider();
        gen.addProvider(event.includeClient(), new CCMaterialRenderInfoProvider(gen, materialSprites, efh));
        gen.addProvider(event.includeClient(), new GeneratorPartTextureJsonGenerator(gen, ChromaticConstruct.MODID, partSprites));
        gen.addProvider(event.includeClient(), new MaterialPartTextureGenerator(gen, efh, partSprites, materialSprites));
    }

    @SubscribeEvent
    public void registerEvent(RegisterEvent event) {
        if (event.getRegistryKey() == Registry.RECIPE_SERIALIZER_REGISTRY) {
            LivingEntityPredicate.LOADER.register(getResource("monster"), CCPredicate.MONSTER.getLoader());
            LivingEntityPredicate.LOADER.register(getResource("below_40"), CCPredicate.BELOW_40.getLoader());
        }
    }

    public static String makeDescriptionId(String type, String name) {
        return type + "." + MODID + "." + name;
    }

    public static ResourceLocation getResource(String name) {
        return new ResourceLocation(MODID, name);
    }

    public static <T> TinkerDataCapability.TinkerDataKey<T> createKey(String name) {
        return TinkerDataCapability.TinkerDataKey.of(getResource(name));
    }

    public static <T> TinkerDataCapability.ComputableDataKey<T> createKey(String name, Supplier<T> constructor) {
        return TinkerDataCapability.ComputableDataKey.of(getResource(name), constructor);
    }
}
