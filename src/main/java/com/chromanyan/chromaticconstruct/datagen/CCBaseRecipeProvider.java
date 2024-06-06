package com.chromanyan.chromaticconstruct.datagen;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.recipe.data.IRecipeHelper;

import java.util.function.Consumer;

public abstract class CCBaseRecipeProvider extends RecipeProvider implements IConditionBuilder, IRecipeHelper {

    public CCBaseRecipeProvider(DataGenerator p_125973_) {
        super(p_125973_);
    }

    @Override
    protected abstract void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer);

    @Override
    public abstract @NotNull String getName();

    @Override
    public @NotNull String getModId() {
        return ChromaticConstruct.MODID;
    }
}
