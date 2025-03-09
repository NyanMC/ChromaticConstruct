package com.chromanyan.chromaticconstruct;

import com.chromanyan.chromaticconstruct.compat.PowahCompatHelper;
import com.chromanyan.chromaticconstruct.datagen.CCBlockTagProvider;
import com.chromanyan.chromaticconstruct.datagen.CCEnchantmentTagProvider;
import com.chromanyan.chromaticconstruct.datagen.CCItemTagProvider;
import com.chromanyan.chromaticconstruct.datagen.CCRecipeProvider;
import com.chromanyan.chromaticconstruct.datagen.tconstruct.CCFluidEffectProvider;
import com.chromanyan.chromaticconstruct.datagen.tconstruct.material.*;
import com.chromanyan.chromaticconstruct.event.CCEvents;
import com.chromanyan.chromaticconstruct.init.CCFluids;
import com.chromanyan.chromaticconstruct.init.CCItems;
import com.chromanyan.chromaticconstruct.init.CCMobEffects;
import com.chromanyan.chromaticconstruct.init.CCModifiers;
import com.chromanyan.chromaticconstruct.network.CCPacketHandler;
import com.chromanyan.chromaticconstruct.network.client.PacketRemainingFireTicks;
import com.chromanyan.chromaticconstruct.tools.CCPredicate;
import com.chromanyan.chromaticconstruct.tools.modules.armor.FragileProtectionModule;
import com.chromanyan.chromaticconstruct.tools.modules.armor.PanicModule;
import com.chromanyan.chromaticconstruct.tools.modules.armor.ShockingModule;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegisterEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import slimeknights.mantle.data.predicate.entity.LivingEntityPredicate;
import slimeknights.tconstruct.library.client.data.material.GeneratorPartTextureJsonGenerator;
import slimeknights.tconstruct.library.client.data.material.MaterialPartTextureGenerator;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.modifiers.modules.ModifierModule;
import slimeknights.tconstruct.library.tools.capability.TinkerDataCapability;
import slimeknights.tconstruct.library.utils.Util;
import slimeknights.tconstruct.tools.data.sprite.TinkerPartSpriteProvider;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ChromaticConstruct.MODID)
public class ChromaticConstruct {

    // Define mod id in a common place for everything to reference
    public static final String MODID = "chromatic_construct";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogManager.getLogger();

    @SuppressWarnings("removal") // if it doesn't concern tinkers it doesn't concern me
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

        if (ModList.get().isLoaded("powah")) {
            PowahCompatHelper.initialize();
        }

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new CCEvents());

        CCPacketHandler.INSTANCE.registerMessage(0, PacketRemainingFireTicks.class, PacketRemainingFireTicks::encode, PacketRemainingFireTicks::decode, PacketRemainingFireTicks::handle);
    }

    @SubscribeEvent
    public void gatherData(final GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput out = gen.getPackOutput();
        ExistingFileHelper efh = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> provider = event.getLookupProvider();

        AbstractMaterialDataProvider materials = new CCMaterialDataProvider(out);
        gen.addProvider(event.includeServer(), materials);
        gen.addProvider(event.includeServer(), new CCMaterialTraitsDataProvider(out, materials));
        gen.addProvider(event.includeServer(), new CCMaterialStatsDataProvider(out, materials));
        gen.addProvider(event.includeServer(), new CCMaterialRecipeProvider(out));

        gen.addProvider(event.includeServer(), new CCRecipeProvider(out));
        gen.addProvider(event.includeServer(), new CCFluidEffectProvider(out));
        gen.addProvider(event.includeServer(), new CCEnchantmentTagProvider(out, provider, efh));

        CCBlockTagProvider blockTagProvider = new CCBlockTagProvider(out, provider, efh);
        gen.addProvider(event.includeServer(), blockTagProvider);
        gen.addProvider(event.includeServer(), new CCItemTagProvider(out, provider, blockTagProvider.contentsGetter(), efh));

        CCMaterialSpriteProvider materialSprites = new CCMaterialSpriteProvider();
        TinkerPartSpriteProvider partSprites = new TinkerPartSpriteProvider();
        gen.addProvider(event.includeClient(), new CCMaterialRenderInfoProvider(out, materialSprites, efh));
        gen.addProvider(event.includeClient(), new GeneratorPartTextureJsonGenerator(out, ChromaticConstruct.MODID, partSprites));
        gen.addProvider(event.includeClient(), new MaterialPartTextureGenerator(out, efh, partSprites, materialSprites));
    }

    @SubscribeEvent
    public void registerEvent(RegisterEvent event) {
        if (event.getRegistryKey() == Registries.RECIPE_SERIALIZER) {
            LivingEntityPredicate.LOADER.register(getResource("monster"), CCPredicate.MONSTER.getLoader());
            LivingEntityPredicate.LOADER.register(getResource("below_40"), CCPredicate.BELOW_40.getLoader());

            ModifierModule.LOADER.register(getResource("panic"), PanicModule.LOADER);
            ModifierModule.LOADER.register(getResource("shocking"), ShockingModule.LOADER);
            ModifierModule.LOADER.register(getResource("fragile_protection"), FragileProtectionModule.LOADER);
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

    @SuppressWarnings("unused")
    public static <T> TinkerDataCapability.ComputableDataKey<T> createKey(String name, Supplier<T> constructor) {
        return TinkerDataCapability.ComputableDataKey.of(getResource(name), constructor);
    }

    public static MutableComponent makeTranslation(String base, String name) {
        return Component.translatable(makeTranslationKey(base, name));
    }
    public static String makeTranslationKey(String base, String name) {
        return Util.makeTranslationKey(base, getResource(name));
    }
}
