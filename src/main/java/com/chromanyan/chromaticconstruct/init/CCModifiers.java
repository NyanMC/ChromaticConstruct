package com.chromanyan.chromaticconstruct.init;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import com.chromanyan.chromaticconstruct.datagen.CCEnchantmentToModifierProvider;
import com.chromanyan.chromaticconstruct.datagen.tconstruct.CCModifierProvider;
import com.chromanyan.chromaticconstruct.datagen.tconstruct.CCModifierRecipeProvider;
import com.chromanyan.chromaticconstruct.datagen.tconstruct.CCModifierTagProvider;
import com.chromanyan.chromaticconstruct.tools.modifiers.ability.interaction.SnowballModifier;
import com.chromanyan.chromaticconstruct.tools.modifiers.ability.ranged.RidingModifier;
import com.chromanyan.chromaticconstruct.tools.modifiers.trait.BackstepModifier;
import com.chromanyan.chromaticconstruct.tools.modifiers.trait.HarvestShieldModifier;
import com.chromanyan.chromaticconstruct.tools.modifiers.trait.InfernalModifier;
import com.chromanyan.chromaticconstruct.tools.modifiers.trait.VarietyModifier;
import com.chromanyan.chromaticconstruct.tools.modifiers.upgrades.melee.HeartstopperModifier;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import slimeknights.tconstruct.library.modifiers.util.ModifierDeferredRegister;
import slimeknights.tconstruct.library.modifiers.util.StaticModifier;

@SuppressWarnings("removal") // if it doesn't concern tinkers it doesn't concern me
public class CCModifiers {
    public static final ModifierDeferredRegister MODIFIERS = ModifierDeferredRegister.create(ChromaticConstruct.MODID);

    public CCModifiers() {
        MODIFIERS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    public static final StaticModifier<SnowballModifier> snowball = MODIFIERS.register("snowball", SnowballModifier::new);
    public static final StaticModifier<RidingModifier> riding = MODIFIERS.register("riding", RidingModifier::new);
    public static final StaticModifier<HeartstopperModifier> heartstopper = MODIFIERS.register("heartstopper", HeartstopperModifier::new);

    public static final StaticModifier<InfernalModifier> infernal = MODIFIERS.register("infernal", InfernalModifier::new);
    public static final StaticModifier<BackstepModifier> backstep = MODIFIERS.register("backstep", BackstepModifier::new);
    public static final StaticModifier<VarietyModifier> variety = MODIFIERS.register("variety", VarietyModifier::new);
    public static final StaticModifier<HarvestShieldModifier> harvestShield = MODIFIERS.register("harvest_shield", HarvestShieldModifier::new);

    @SubscribeEvent
    void gatherData(final GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput out = gen.getPackOutput();

        gen.addProvider(event.includeServer(), new CCModifierProvider(out));
        gen.addProvider(event.includeServer(), new CCModifierRecipeProvider(out));
        gen.addProvider(event.includeServer(), new CCModifierTagProvider(out, event.getExistingFileHelper()));
        gen.addProvider(event.includeServer(), new CCEnchantmentToModifierProvider(out));
    }
}
