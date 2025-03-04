package com.chromanyan.chromaticconstruct.init;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import com.chromanyan.chromaticconstruct.datagen.CCFluidTagProvider;
import com.chromanyan.chromaticconstruct.datagen.CCFluidTextureProvider;
import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.DispensibleContainerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraftforge.common.SoundActions;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.registration.deferred.FluidDeferredRegister;
import slimeknights.mantle.registration.object.FlowingFluidObject;
import slimeknights.tconstruct.fluids.data.FluidBlockstateModelProvider;
import slimeknights.tconstruct.fluids.data.FluidBucketModelProvider;

import java.util.concurrent.CompletableFuture;

import static slimeknights.tconstruct.fluids.block.BurningLiquidBlock.createBurning;

@SuppressWarnings("deprecation")
public class CCFluids {

    public static final FluidDeferredRegister FLUIDS = new FluidDeferredRegister(ChromaticConstruct.MODID);

    public static final FlowingFluidObject<ForgeFlowingFluid> moltenCosmite = FLUIDS.register("molten_cosmite").commonTag().type(hot("molten_cosmite").temperature(800).lightLevel(8)).block(createBurning(MapColor.COLOR_PURPLE, 8, 10, 5f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenInfernium = FLUIDS.register("molten_infernium").commonTag().type(hot("molten_infernium").temperature(1300).lightLevel(15)).block(createBurning(MapColor.COLOR_ORANGE, 15, 15, 3f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenEtherium = FLUIDS.register("molten_etherium").commonTag().type(hot("molten_etherium").temperature(1800).lightLevel(15)).block(createBurning(MapColor.COLOR_LIGHT_BLUE, 15, 15, 6f)).bucket().flowing();
    public static final FlowingFluidObject<ForgeFlowingFluid> moltenChroma = FLUIDS.register("molten_chroma").commonTag().type(hot("molten_chroma").temperature(1150).lightLevel(15)).block(createBurning(MapColor.COLOR_MAGENTA, 15, 10, 2f)).bucket().flowing();

    private static FluidType.Properties hot(String name) {
        return FluidType.Properties.create().density(2000).viscosity(10000).temperature(1000)
                .descriptionId(ChromaticConstruct.makeDescriptionId("fluid", name))
                .sound(SoundActions.BUCKET_FILL, SoundEvents.BUCKET_FILL_LAVA)
                .sound(SoundActions.BUCKET_EMPTY, SoundEvents.BUCKET_EMPTY_LAVA)
                // from forge lava type
                .motionScale(0.0023333333333333335D)
                .canSwim(false).canDrown(false)
                .pathType(BlockPathTypes.LAVA).adjacentPathType(null);
    }

    @SubscribeEvent
    void gatherData(final GatherDataEvent event) {
        DataGenerator datagenerator = event.getGenerator();
        PackOutput out = datagenerator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        boolean client = event.includeClient();

        datagenerator.addProvider(event.includeServer(), new CCFluidTagProvider(out, lookupProvider, event.getExistingFileHelper()));

        datagenerator.addProvider(client, new CCFluidTextureProvider(out));
        datagenerator.addProvider(client, new FluidBucketModelProvider(out, ChromaticConstruct.MODID));
        datagenerator.addProvider(client, new FluidBlockstateModelProvider(out, ChromaticConstruct.MODID));
    }

    @SubscribeEvent
    void commonSetup(final FMLCommonSetupEvent event) {
        DispenseItemBehavior dispenseBucket = new DefaultDispenseItemBehavior() {
            private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();

            @Override
            public @NotNull ItemStack execute(BlockSource source, ItemStack stack) {
                DispensibleContainerItem container = (DispensibleContainerItem)stack.getItem();
                BlockPos blockpos = source.getPos().relative(source.getBlockState().getValue(DispenserBlock.FACING));
                Level level = source.getLevel();
                if (container.emptyContents(null, level, blockpos, null)) {
                    container.checkExtraContent(null, level, stack, blockpos);
                    return new ItemStack(Items.BUCKET);
                } else {
                    return this.defaultDispenseItemBehavior.dispense(source, stack);
                }
            }
        };

        event.enqueueWork(() -> {
            DispenserBlock.registerBehavior(moltenCosmite, dispenseBucket);
            DispenserBlock.registerBehavior(moltenInfernium, dispenseBucket);
            DispenserBlock.registerBehavior(moltenEtherium, dispenseBucket);
            DispenserBlock.registerBehavior(moltenChroma, dispenseBucket);
        });
    }
}
