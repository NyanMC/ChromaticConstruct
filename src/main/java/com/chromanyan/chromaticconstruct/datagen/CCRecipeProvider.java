package com.chromanyan.chromaticconstruct.datagen;

import com.aizistral.enigmaticlegacy.registries.EnigmaticBlocks;
import com.aizistral.enigmaticlegacy.registries.EnigmaticItems;
import com.chromanyan.chromaticarsenal.init.ModItems;
import com.chromanyan.chromaticconstruct.init.CCFluids;
import com.chromanyan.chromaticconstruct.init.CCItems;
import com.chromanyan.chromaticconstruct.tools.CCFluidValues;
import com.chromanyan.meaningfulmaterials.init.MMItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.recipe.data.ICommonRecipeHelper;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.library.recipe.casting.ItemCastingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.melting.MeltingRecipeBuilder;
import slimeknights.tconstruct.smeltery.data.Byproduct;
import slimeknights.tconstruct.tables.TinkerTables;

import java.util.function.Consumer;

public class CCRecipeProvider extends CCBaseRecipeProvider implements ISmelteryRecipeHelper, ICommonRecipeHelper {

    public CCRecipeProvider(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        this.addMeltingRecipes(consumer);
        this.addCastingRecipes(consumer);
    }

    private void addMeltingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        String folder = "smeltery/melting/";
        Consumer<FinishedRecipe> mmConsumer = withCondition(consumer, new ModLoadedCondition("meaningfulmaterials"));

        gemMelting(mmConsumer, CCFluids.moltenCosmite.get(), "cosmite", true, 9, folder, true, Byproduct.AMETHYST);

        MeltingRecipeBuilder.melting(Ingredient.of(MMItems.COSMITE_BOOTS.get()), CCFluids.moltenCosmite.get(), FluidValues.GEM * 4)
                .setDamagable(FluidValues.GEM)
                .save(mmConsumer, location(folder + "cosmite/boots"));

        Consumer<FinishedRecipe> elConsumer = withCondition(consumer, new ModLoadedCondition("enigmaticlegacy"));

        // Because SOMEONE doesn't properly tag their items, I have to do this the long way.
        MeltingRecipeBuilder.melting(Ingredient.of(EnigmaticItems.ETHERIUM_INGOT), CCFluids.moltenEtherium.get(), FluidValues.INGOT)
                .save(elConsumer, location(folder + "etherium/ingot"));
        MeltingRecipeBuilder.melting(Ingredient.of(EnigmaticItems.ETHERIUM_NUGGET), CCFluids.moltenEtherium.get(), FluidValues.NUGGET)
                .save(elConsumer, location(folder + "etherium/nugget"));
        MeltingRecipeBuilder.melting(Ingredient.of(EnigmaticItems.ETHERIUM_ORE), CCFluids.moltenEtherium.get(), FluidValues.INGOT * 2)
                .save(elConsumer, location(folder + "etherium/ore"));
        MeltingRecipeBuilder.melting(Ingredient.of(EnigmaticBlocks.ETHERIUM_BLOCK), CCFluids.moltenEtherium.get(), FluidValues.METAL_BLOCK)
                .save(elConsumer, location(folder + "etherium/block"));

        MeltingRecipeBuilder.melting(Ingredient.of(EnigmaticItems.IRON_RING), TinkerFluids.moltenIron.get(), CCFluidValues.ENIGMATIC_RING)
                .save(elConsumer, location(folder + "iron/ring_enigmaticlegacy"));
        MeltingRecipeBuilder.melting(Ingredient.of(EnigmaticItems.GOLDEN_RING), TinkerFluids.moltenGold.get(), CCFluidValues.ENIGMATIC_RING)
                .addByproduct(new FluidStack(TinkerFluids.moltenIron.get(), CCFluidValues.ENIGMATIC_RING))
                .save(elConsumer, location(folder + "gold/ring_enigmaticlegacy"));
        // the most valuable resource on the magnet ring is that diamond, so make that the primary fluid
        MeltingRecipeBuilder.melting(Ingredient.of(EnigmaticItems.MAGNET_RING), TinkerFluids.moltenDiamond.get(), FluidValues.GEM)
                .addByproduct(new FluidStack(TinkerFluids.moltenIron.get(), CCFluidValues.ENIGMATIC_RING + FluidValues.INGOT))
                .addByproduct(new FluidStack(TinkerFluids.moltenGold.get(), FluidValues.INGOT))
                .save(elConsumer, location(folder + "enigmticlegacy/magnet_ring"));
        MeltingRecipeBuilder.melting(Ingredient.of(EnigmaticItems.SUPER_MAGNET_RING), TinkerFluids.moltenDiamond.get(), FluidValues.GEM)
                .addByproduct(new FluidStack(TinkerFluids.moltenIron.get(), CCFluidValues.ENIGMATIC_RING + FluidValues.INGOT))
                .addByproduct(new FluidStack(TinkerFluids.moltenGold.get(), FluidValues.INGOT * 4))
                .addByproduct(new FluidStack(TinkerFluids.moltenEnder.get(), FluidValues.SLIMEBALL))
                .save(elConsumer, location(folder + "enigmticlegacy/super_magnet_ring"));
        MeltingRecipeBuilder.melting(Ingredient.of(EnigmaticItems.ENDER_RING), TinkerFluids.moltenGold.get(), (FluidValues.INGOT * 2) + (FluidValues.NUGGET * 2))
                .addByproduct(new FluidStack(TinkerFluids.moltenIron.get(), CCFluidValues.ENIGMATIC_RING))
                .addByproduct(new FluidStack(TinkerFluids.moltenEnder.get(), FluidValues.SLIMEBALL * 2))
                .addByproduct(new FluidStack(TinkerFluids.moltenObsidian.get(), FluidValues.GLASS_BLOCK * 8))
                .save(elConsumer, location(folder + "enigmticlegacy/ender_ring"));

        Consumer<FinishedRecipe> caConsumer = withCondition(consumer, new ModLoadedCondition("chromaticarsenal"));

        gemMelting(caConsumer, CCFluids.moltenChroma.get(), "chroma", false, 9, folder, true);

        MeltingRecipeBuilder.melting(Ingredient.of(CCItems.glassReinforcement), TinkerFluids.moltenGlass.get(), FluidValues.GLASS_BLOCK)
                .save(consumer, location(folder + "glass/reinforcement"));
    }

    private void addCastingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        String folder = "smeltery/casting/";

        Consumer<FinishedRecipe> mmConsumer = withCondition(consumer, new ModLoadedCondition("meaningfulmaterials"));

        this.gemCasting(mmConsumer, CCFluids.moltenCosmite, MMItems.COSMITE.get(), folder + "cosmite/gem");

        ItemCastingRecipeBuilder.basinRecipe(MMItems.COSMITE_BLOCK_ITEM.get())
                .setFluidAndTime(CCFluids.moltenCosmite, false, FluidValues.LARGE_GEM_BLOCK)
                .save(mmConsumer, location(folder + "cosmite/block"));

        ItemCastingRecipeBuilder.tableRecipe(MMItems.COSMIC_ARROW.get())
                .setCast(Items.ARROW, true)
                .setFluidAndTime(CCFluids.moltenCosmite, false, 10)
                .save(mmConsumer, location(folder + "cosmite/arrow"));

        Consumer<FinishedRecipe> elConsumer = withCondition(consumer, new ModLoadedCondition("enigmaticlegacy"));

        this.ingotCasting(elConsumer, CCFluids.moltenEtherium, EnigmaticItems.ETHERIUM_INGOT, folder + "etherium/ingot");
        this.nuggetCasting(elConsumer, CCFluids.moltenEtherium, false, EnigmaticItems.ETHERIUM_NUGGET, folder + "etherium/nugget");

        ItemCastingRecipeBuilder.basinRecipe(EnigmaticBlocks.ETHERIUM_BLOCK)
                .setFluidAndTime(CCFluids.moltenEtherium, false, FluidValues.METAL_BLOCK)
                .save(elConsumer, location(folder + "etherium/block"));

        ItemCastingRecipeBuilder.tableRecipe(EnigmaticItems.GOLDEN_RING)
                .setCast(EnigmaticItems.IRON_RING, true)
                .setFluidAndTime(TinkerFluids.moltenGold, true, CCFluidValues.ENIGMATIC_RING)
                .save(elConsumer, location(folder + "gold/ring_enigmaticlegacy"));

        Consumer<FinishedRecipe> caConsumer = withCondition(consumer, new ModLoadedCondition("chromaticarsenal"));

        this.gemCasting(caConsumer, CCFluids.moltenChroma, ModItems.CHROMA_SHARD.get(), folder + "chroma/gem");

        ItemCastingRecipeBuilder.basinRecipe(ModItems.CHROMA_BLOCK_ITEM.get())
                .setFluidAndTime(CCFluids.moltenChroma, false, FluidValues.LARGE_GEM_BLOCK)
                .save(caConsumer, location(folder + "chroma/block"));

        ItemCastingRecipeBuilder.tableRecipe(ModItems.MAGIC_GARLIC_BREAD.get())
                .setFluidAndTime(CCFluids.moltenChroma, false, FluidValues.GEM_SHARD)
                .setCast(Items.BREAD, true)
                .save(caConsumer, location(folder + "chroma/bread"));

        ItemCastingRecipeBuilder.tableRecipe(CCItems.glassReinforcement)
                .setFluidAndTime(TinkerFluids.moltenGlass, false, FluidValues.GLASS_BLOCK)
                .setCast(TinkerTables.pattern, true)
                .save(consumer, prefix(CCItems.glassReinforcement, folder));
    }

    @Override
    public @NotNull String getName() {
        return "Chromatic Construct Recipes";
    }
}
