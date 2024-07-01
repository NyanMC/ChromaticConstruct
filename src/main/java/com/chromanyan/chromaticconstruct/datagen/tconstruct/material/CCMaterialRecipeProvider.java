package com.chromanyan.chromaticconstruct.datagen.tconstruct.material;

import com.aizistral.enigmaticlegacy.registries.EnigmaticBlocks;
import com.aizistral.enigmaticlegacy.registries.EnigmaticItems;
import com.chromanyan.chromaticarsenal.init.ModTags;
import com.chromanyan.chromaticconstruct.datagen.CCBaseRecipeProvider;
import com.chromanyan.chromaticconstruct.init.CCFluids;
import com.chromanyan.chromaticconstruct.init.CCItems;
import com.chromanyan.meaningfulmaterials.init.MMTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.recipe.IMaterialRecipeHelper;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.tools.data.material.MaterialIds;

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

        Consumer<FinishedRecipe> mmConsumer = withCondition(consumer, new ModLoadedCondition("meaningfulmaterials"));
        Consumer<FinishedRecipe> elConsumer = withCondition(consumer, new ModLoadedCondition("enigmaticlegacy"));
        Consumer<FinishedRecipe> caConsumer = withCondition(consumer, new ModLoadedCondition("chromaticarsenal"));

        materialRecipe(consumer, CCMaterialIds.hamhide, Ingredient.of(CCItems.hamhide), 1, 1, folder + "hamhide");

        materialRecipe(mmConsumer, CCMaterialIds.cosmite, Ingredient.of(MMTags.Items.GEMS_COSMITE), 1, 1, folder + "cosmite");
        materialRecipe(mmConsumer, CCMaterialIds.cosmite, Ingredient.of(MMTags.Items.STORAGE_BLOCKS_COSMITE), 9, 1, folder + "cosmite_block");

        materialRecipe(mmConsumer, CCMaterialIds.infernium, Ingredient.of(MMTags.Items.INGOTS_INFERNIUM), 1, 1, folder + "infernium");
        materialRecipe(mmConsumer, CCMaterialIds.infernium, Ingredient.of(MMTags.Items.STORAGE_BLOCKS_INFERNIUM), 9, 1, folder + "infernium_block");

        materialRecipe(elConsumer, CCMaterialIds.etherium, Ingredient.of(EnigmaticItems.ETHERIUM_NUGGET), 1, 9, folder + "etherium_nugget");
        materialRecipe(elConsumer, CCMaterialIds.etherium, Ingredient.of(EnigmaticItems.ETHERIUM_INGOT), 1, 1, folder + "etherium_ingot");
        materialRecipe(elConsumer, CCMaterialIds.etherium, Ingredient.of(EnigmaticBlocks.ETHERIUM_BLOCK), 9, 1, folder + "etherium_block");

        materialRecipe(caConsumer, CCMaterialIds.chroma, Ingredient.of(ModTags.Items.GEMS_CHROMA), 1, 1, folder + "chroma_shard");
        materialRecipe(caConsumer, CCMaterialIds.chroma, Ingredient.of(ModTags.Items.STORAGE_BLOCKS_CHROMA), 9, 1, folder + "chroma_block");
    }

    private void addMaterialSmeltery(Consumer<FinishedRecipe> consumer) {
        String folder = "tools/materials/";

        Consumer<FinishedRecipe> mmConsumer = withCondition(consumer, new ModLoadedCondition("meaningfulmaterials"));
        Consumer<FinishedRecipe> elConsumer = withCondition(consumer, new ModLoadedCondition("enigmaticlegacy"));
        Consumer<FinishedRecipe> caConsumer = withCondition(consumer, new ModLoadedCondition("chromaticarsenal"));

        materialComposite(consumer, MaterialIds.leather, CCMaterialIds.hamhide, TinkerFluids.meatSoup, false, FluidValues.BOWL, folder);

        materialMeltingCasting(mmConsumer, CCMaterialIds.cosmite, CCFluids.moltenCosmite, false, FluidValues.GEM, folder);
        materialMeltingCasting(mmConsumer, CCMaterialIds.infernium, CCFluids.moltenInfernium, false, FluidValues.INGOT, folder);
        materialMeltingCasting(elConsumer, CCMaterialIds.etherium, CCFluids.moltenEtherium, false, FluidValues.INGOT, folder);
        materialMeltingCasting(caConsumer, CCMaterialIds.chroma, CCFluids.moltenChroma, false, FluidValues.GEM, folder);
    }

    @Override
    public @NotNull String getName() {
        return "Chromatic Construct Material Recipe";
    }
}
