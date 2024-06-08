package com.chromanyan.chromaticconstruct.init;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import com.chromanyan.chromaticconstruct.datagen.tconstruct.CCModifierProvider;
import com.chromanyan.chromaticconstruct.datagen.tconstruct.CCModifierRecipeProvider;
import com.chromanyan.chromaticconstruct.datagen.tconstruct.CCModifierTagProvider;
import com.chromanyan.chromaticconstruct.tools.modifiers.ability.interaction.SnowballModifier;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

public class CCModifiers {
    public static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(ChromaticConstruct.MODID);

    public CCModifiers() {
        MODIFIERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final StaticModifier<SnowballModifier> snowball = MODIFIERS.register("snowball", SnowballModifier::new);

    @SubscribeEvent
    void gatherData(final GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();

        gen.addProvider(event.includeServer(), new CCModifierProvider(gen));
        gen.addProvider(event.includeServer(), new CCModifierRecipeProvider(gen));
        gen.addProvider(event.includeServer(), new CCModifierTagProvider(gen, event.getExistingFileHelper()));
    }
}
