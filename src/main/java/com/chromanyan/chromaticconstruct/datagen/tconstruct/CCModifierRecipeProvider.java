package com.chromanyan.chromaticconstruct.datagen.tconstruct;

import com.chromanyan.chromaticconstruct.datagen.CCBaseRecipeProvider;
import com.chromanyan.chromaticconstruct.init.CCModifiers;
import com.chromanyan.meaningfulmaterials.init.MMTags;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.recipe.modifiers.adding.ModifierRecipeBuilder;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.shared.TinkerMaterials;

import java.util.function.Consumer;

public class CCModifierRecipeProvider extends CCBaseRecipeProvider {
    public CCModifierRecipeProvider(DataGenerator p_125973_) {
        super(p_125973_);
    }

    @Override
    protected void buildCraftingRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        addModifierRecipes(consumer);
    }

    private void addModifierRecipes(Consumer<FinishedRecipe> consumer) {
        String upgradeFolder = "tools/modifiers/upgrade/";
        String abilityFolder = "tools/modifiers/ability/";
        String slotlessFolder = "tools/modifiers/slotless/";
        String upgradeSalvage = "tools/modifiers/salvage/upgrade/";
        String abilitySalvage = "tools/modifiers/salvage/ability/";
        String defenseFolder = "tools/modifiers/defense/";
        String defenseSalvage = "tools/modifiers/salvage/defense/";
        String compatFolder = "tools/modifiers/compat/";
        String compatSalvage = "tools/modifiers/salvage/compat/";
        String worktableFolder = "tools/modifiers/worktable/";

        ModifierRecipeBuilder.modifier(CCModifierIds.antigravity)
                .addInput(MMTags.Items.GEMS_COSMITE)
                .setMaxLevel(1)
                .save(withCondition(consumer, modLoaded("meaningfulmaterials")), prefix(CCModifierIds.antigravity, slotlessFolder));

        ModifierRecipeBuilder.modifier(CCModifiers.snowball)
                .setTools(TinkerTags.Items.STAFFS)
                .addInput(Items.SNOWBALL)
                .addInput(Items.SNOW_BLOCK)
                .addInput(Items.SNOWBALL)
                .addInput(Items.SNOWBALL)
                .addInput(Items.SNOWBALL)
                .setSlots(SlotType.ABILITY, 1)
                .setMaxLevel(1)
                .saveSalvage(consumer, prefix(CCModifiers.snowball, abilitySalvage))
                .save(consumer, prefix(CCModifiers.snowball, abilityFolder));

        ModifierRecipeBuilder.modifier(CCModifiers.riding)
                .setTools(TinkerTags.Items.BOWS)
                .addInput(Tags.Items.LEATHER)
                .addInput(Items.SADDLE)
                .addInput(Tags.Items.LEATHER)
                .addInput(TinkerMaterials.hepatizon.getIngotTag())
                .addInput(TinkerMaterials.hepatizon.getIngotTag())
                .setSlots(SlotType.ABILITY, 1)
                .setMaxLevel(1)
                .saveSalvage(consumer, prefix(CCModifiers.riding, abilitySalvage))
                .save(consumer, prefix(CCModifiers.riding, abilityFolder));
    }

    @Override
    public @NotNull String getName() {
        return "Chromatic Construct Modifier Recipes";
    }
}
