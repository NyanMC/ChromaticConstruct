package com.chromanyan.chromaticconstruct.datagen.tconstruct.material;

import com.chromanyan.chromaticconstruct.datagen.CCBaseRecipeProvider;
import com.chromanyan.chromaticconstruct.init.CCFluids;
import com.chromanyan.meaningfulmaterials.init.MMTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;

import java.util.function.Consumer;

public class CCMaterialRecipeProvider extends CCBaseRecipeProvider implements IMaterialRecipeHelper {

    public CCMaterialRecipeProvider(DataGenerator p_125973_) {
        super(p_125973_);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        addMaterialItems(consumer);
        addMaterialSmeltery(consumer);
    }

    private void addMaterialItems(Consumer<FinishedRecipe> consumer) {
        String folder = "tools/materials/";

        materialRecipe(consumer, CCMaterialIds.cosmite, Ingredient.of(MMTags.Items.GEMS_COSMITE), 1, 1, folder + "cosmite");
        materialRecipe(consumer, CCMaterialIds.cosmite, Ingredient.of(MMTags.Items.STORAGE_BLOCKS_COSMITE), 9, 1, folder + "cosmite_block");
    }

    private void addMaterialSmeltery(Consumer<FinishedRecipe> consumer) {
        String folder = "tools/materials/";

        materialMeltingCasting(consumer, CCMaterialIds.cosmite, CCFluids.moltenCosmite, false, 100, folder);
    }

    @Override
    public @NotNull String getName() {
        return "Chromatic Construct Material Recipe";
    }
}
