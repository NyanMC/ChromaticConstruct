package com.chromanyan.chromaticconstruct.datagen;

import com.aizistral.enigmaticlegacy.registries.EnigmaticBlocks;
import com.aizistral.enigmaticlegacy.registries.EnigmaticItems;
import com.chromanyan.chromaticarsenal.init.ModItems;
import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import com.chromanyan.chromaticconstruct.init.*;
import com.chromanyan.chromaticconstruct.tools.CCFluidValues;
import com.chromanyan.meaningfulmaterials.init.MMItems;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.recipe.data.ICommonRecipeHelper;
import slimeknights.mantle.registration.object.FluidObject;
import slimeknights.tconstruct.fluids.TinkerFluids;
import slimeknights.tconstruct.library.data.recipe.ISmelteryRecipeHelper;
import slimeknights.tconstruct.library.data.recipe.SmelteryRecipeBuilder;
import slimeknights.tconstruct.library.recipe.FluidValues;
import slimeknights.tconstruct.library.recipe.casting.ItemCastingRecipeBuilder;
import slimeknights.tconstruct.library.recipe.melting.MeltingRecipeBuilder;
import slimeknights.tconstruct.tables.TinkerTables;

import java.util.function.Consumer;

public class CCRecipeProvider extends CCBaseRecipeProvider implements ISmelteryRecipeHelper, ICommonRecipeHelper {

    public CCRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        this.addCommonRecipes(consumer);
        this.addMeltingRecipes(consumer);
        this.addCastingRecipes(consumer);
        this.addSmelteryRecipes(consumer);
    }

    @Override
    public @NotNull String getName() {
        return "Chromatic Construct Recipes";
    }

    private void addCommonRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        String folder = "common/materials/";

        metalCrafting(consumer, CCMaterials.rejuvenite, folder);
        packingRecipe(consumer, RecipeCategory.MISC, "raw_block", CCBlocks.rawRejuveniteBlock, "raw", CCItems.rawRejuvenite, CCTags.Items.RAW_MATERIALS_REJUVENITE, folder);

        Item rejuveniteIngot = CCMaterials.rejuvenite.getIngot();
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(CCItems.rawRejuvenite, CCBlocks.rawRejuveniteBlock), RecipeCategory.MISC, rejuveniteIngot, 1.5f, 200)
                .unlockedBy("has_item", has(CCItems.rawRejuvenite))
                .save(consumer, wrap(id(rejuveniteIngot), folder, "_smelting"));
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(CCItems.rawRejuvenite, CCBlocks.rawRejuveniteBlock), RecipeCategory.MISC, rejuveniteIngot, 1.5f, 100)
                .unlockedBy("has_item", has(CCItems.rawRejuvenite))
                .save(consumer, wrap(id(rejuveniteIngot), folder, "_blasting"));
    }

    private void addSmelteryRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        // maybe consider giving some of these byproducts again? infernium could byproduct lava

        molten(consumer, CCFluids.moltenCosmite)
                .optional()
                .ore()
                .largeGem();

        metal(consumer, CCFluids.moltenInfernium)
                .optional()
                .ore()
                .metal()
                .dust();

        SmelteryRecipeBuilder.fluid(consumer, ChromaticConstruct.getResource("chroma"), CCFluids.moltenChroma.get())
                .optional()
                .largeGem();

        metal(consumer, CCFluids.moltenEnergizedSteel)
                .optional()
                .metal();
    }

    private void addMeltingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        String folder = "smeltery/melting/";
        Consumer<FinishedRecipe> mmConsumer = withCondition(consumer, new ModLoadedCondition("meaningfulmaterials"));

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
        MeltingRecipeBuilder.melting(Ingredient.of(EnigmaticItems.ENDER_RING), TinkerFluids.moltenObsidian.get(), FluidValues.GLASS_BLOCK * 8)
                .addByproduct(new FluidStack(TinkerFluids.moltenIron.get(), CCFluidValues.ENIGMATIC_RING))
                .addByproduct(new FluidStack(TinkerFluids.moltenEnder.get(), FluidValues.SLIMEBALL * 2))
                .addByproduct(new FluidStack(TinkerFluids.moltenGold.get(), (FluidValues.INGOT * 2) + (FluidValues.NUGGET * 2)))
                .save(elConsumer, location(folder + "enigmticlegacy/ender_ring"));

        Consumer<FinishedRecipe> caConsumer = withCondition(consumer, new ModLoadedCondition("chromaticarsenal"));

        MeltingRecipeBuilder.melting(Ingredient.of(ModItems.COPPER_RING.get()), TinkerFluids.moltenCopper.get(), FluidValues.INGOT * 4)
                .save(caConsumer, location(folder + "copper/ring_chromaticarsenal"));
        MeltingRecipeBuilder.melting(Ingredient.of(ModItems.AMETHYST_RING.get()), TinkerFluids.moltenAmethyst.get(), FluidValues.GEM * 4)
                .addByproduct(new FluidStack(TinkerFluids.moltenCopper.get(), FluidValues.INGOT * 4))
                .save(caConsumer, location(folder + "amethyst/ring_chromaticarsenal"));
        MeltingRecipeBuilder.melting(Ingredient.of(ModItems.OMNI_RING.get()), TinkerFluids.moltenAmethyst.get(), FluidValues.GEM * 4)
                .addByproduct(new FluidStack(TinkerFluids.moltenIron.get(), CCFluidValues.ENIGMATIC_RING))
                .addByproduct(new FluidStack(TinkerFluids.moltenGold.get(), CCFluidValues.ENIGMATIC_RING))
                .addByproduct(new FluidStack(TinkerFluids.moltenCopper.get(), FluidValues.INGOT * 4))
                .addByproduct(new FluidStack(CCFluids.moltenChroma.get(), FluidValues.GEM))
                .save(withCondition(caConsumer, new ModLoadedCondition("enigmaticlegacy")), location(folder + "chromaticarsenal/omni_ring"));

        MeltingRecipeBuilder.melting(Ingredient.of(CCItems.glassReinforcement), TinkerFluids.moltenGlass.get(), FluidValues.GLASS_BLOCK)
                .save(consumer, location(folder + "glass/reinforcement"));
    }

    private void addCastingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        String folder = "smeltery/casting/";

        ItemCastingRecipeBuilder.tableRecipe(CCItems.glassReinforcement)
                .setFluidAndTime(TinkerFluids.moltenGlass, FluidValues.GLASS_BLOCK)
                .setCast(TinkerTables.pattern, true)
                .save(consumer, prefix(CCItems.glassReinforcement, folder));

        ItemCastingRecipeBuilder.tableRecipe(CCItems.hamhide)
                .setFluidAndTime(TinkerFluids.meatSoup, FluidValues.BOWL)
                .setCast(Items.LEATHER, true)
                .save(consumer, prefix(CCItems.hamhide, folder));

        Consumer<FinishedRecipe> mmConsumer = withCondition(consumer, new ModLoadedCondition("meaningfulmaterials"));

        ItemCastingRecipeBuilder.tableRecipe(MMItems.COSMIC_ARROW.get())
                .setCast(Items.ARROW, true)
                .setFluidAndTime(CCFluids.moltenCosmite, 10)
                .save(mmConsumer, location(folder + "cosmite/arrow"));

        Consumer<FinishedRecipe> elConsumer = withCondition(consumer, new ModLoadedCondition("enigmaticlegacy"));

        this.ingotCasting(elConsumer, CCFluids.moltenEtherium, EnigmaticItems.ETHERIUM_INGOT, folder + "etherium/ingot");
        this.nuggetCasting(elConsumer, CCFluids.moltenEtherium, EnigmaticItems.ETHERIUM_NUGGET, folder + "etherium/nugget");

        ItemCastingRecipeBuilder.basinRecipe(EnigmaticBlocks.ETHERIUM_BLOCK)
                .setFluidAndTime(CCFluids.moltenEtherium, FluidValues.METAL_BLOCK)
                .save(elConsumer, location(folder + "etherium/block"));

        ItemCastingRecipeBuilder.tableRecipe(EnigmaticItems.GOLDEN_RING)
                .setCast(EnigmaticItems.IRON_RING, true)
                .setFluidAndTime(TinkerFluids.moltenGold, CCFluidValues.ENIGMATIC_RING)
                .save(elConsumer, location(folder + "gold/ring_enigmaticlegacy"));

        Consumer<FinishedRecipe> caConsumer = withCondition(consumer, new ModLoadedCondition("chromaticarsenal"));

        ItemCastingRecipeBuilder.tableRecipe(ModItems.MAGIC_GARLIC_BREAD.get())
                .setFluidAndTime(CCFluids.moltenChroma, FluidValues.GEM_SHARD)
                .setCast(Items.BREAD, true)
                .save(caConsumer, location(folder + "chroma/bread"));
    }

    public SmelteryRecipeBuilder metal(Consumer<FinishedRecipe> consumer, String name, TagKey<Fluid> fluid) {
        return SmelteryRecipeBuilder.fluid(consumer, location(name), fluid).castingFolder("smeltery/casting/metal").meltingFolder("smeltery/melting/metal");
    }

    /** Creates a smeltery builder for a metal fluid */
    public SmelteryRecipeBuilder metal(Consumer<FinishedRecipe> consumer, FluidObject<?> fluid) {
        return molten(consumer, fluid).castingFolder("smeltery/casting/metal").meltingFolder("smeltery/melting/metal");
    }
}
