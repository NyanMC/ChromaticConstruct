package com.chromanyan.chromaticconstruct.datagen;

import com.chromanyan.chromaticconstruct.init.CCFluids;
import com.chromanyan.chromaticconstruct.init.CCItems;
import com.chromanyan.meaningfulmaterials.init.MMItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
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

        MeltingRecipeBuilder.melting(Ingredient.of(CCItems.glassReinforcement), TinkerFluids.moltenGlass.get(), FluidValues.GLASS_BLOCK)
                .save(consumer, location(folder + "glass_reinforcement"));
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
