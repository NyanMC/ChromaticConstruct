package com.chromanyan.chromaticconstruct.datagen.tconstruct;

import com.aizistral.enigmaticlegacy.registries.EnigmaticItems;
import com.chromanyan.chromaticarsenal.init.ModItems;
import com.chromanyan.chromaticconstruct.datagen.CCBaseRecipeProvider;
import com.chromanyan.chromaticconstruct.datagen.tconstruct.material.CCMaterialIds;
import com.chromanyan.chromaticconstruct.init.CCItems;
import com.chromanyan.chromaticconstruct.init.CCModifiers;
import com.chromanyan.meaningfulmaterials.init.MMTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.CompoundIngredient;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.common.TinkerTags;
import slimeknights.tconstruct.library.recipe.modifiers.adding.IncrementalModifierRecipeBuilder;
import slimeknights.tconstruct.library.recipe.modifiers.adding.ModifierRecipeBuilder;
import slimeknights.tconstruct.library.recipe.modifiers.adding.SwappableModifierRecipeBuilder;
import slimeknights.tconstruct.library.tools.SlotType;
import slimeknights.tconstruct.shared.TinkerMaterials;
import slimeknights.tconstruct.tools.TinkerModifiers;

import java.util.function.Consumer;

public class CCModifierRecipeProvider extends CCBaseRecipeProvider {
    public CCModifierRecipeProvider(PackOutput out) {
        super(out);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {
        addModifierRecipes(consumer);
    }

    @Override
    public @NotNull String getName() {
        return "Chromatic Construct Modifier Recipes";
    }

    private void addModifierRecipes(Consumer<FinishedRecipe> consumer) {
        String upgradeFolder = "tools/modifiers/upgrade/";
        String abilityFolder = "tools/modifiers/ability/";
        String slotlessFolder = "tools/modifiers/slotless/";
        String upgradeSalvage = "tools/modifiers/salvage/upgrade/";
        String abilitySalvage = "tools/modifiers/salvage/ability/";
        String defenseFolder = "tools/modifiers/defense/";
        String defenseSalvage = "tools/modifiers/salvage/defense/";

        Ingredient protectableTools = ingredientFromTags(TinkerTags.Items.ARMOR, TinkerTags.Items.HELD);

        ModifierRecipeBuilder.modifier(CCModifierIds.antigravity)
                .addInput(MMTags.Items.GEMS_COSMITE)
                .setMaxLevel(1)
                .save(withCondition(consumer, modLoaded("meaningfulmaterials")), prefix(CCModifierIds.antigravity, slotlessFolder));

        ModifierRecipeBuilder.modifier(CCModifierIds.nemesis)
                .setTools(TinkerTags.Items.MELEE_PRIMARY) // nemesis would be jank if you could put it on a bow
                .addInput(ItemTags.SWORDS)
                .addInput(EnigmaticItems.EVIL_ESSENCE)
                .addInput(ItemTags.SWORDS)
                .setMaxLevel(1)
                .save(withCondition(consumer, modLoaded("enigmaticlegacy")), prefix(CCModifierIds.nemesis, slotlessFolder));

        ModifierRecipeBuilder.modifier(CCModifierIds.sorrow)
                .setTools(TinkerTags.Items.WORN_ARMOR)
                .addInput(Tags.Items.ARMORS_CHESTPLATES)
                .addInput(EnigmaticItems.EVIL_ESSENCE)
                .addInput(Tags.Items.ARMORS_CHESTPLATES)
                .setMaxLevel(1)
                .save(withCondition(consumer, modLoaded("enigmaticlegacy")), prefix(CCModifierIds.sorrow, slotlessFolder));

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

        IncrementalModifierRecipeBuilder.modifier(CCModifierIds.fragileProtection)
                .setInput(CCItems.glassReinforcement, 1, 5)
                .setSlots(SlotType.DEFENSE, 1)
                .setTools(protectableTools)
                .saveSalvage(consumer, prefix(CCModifierIds.fragileProtection, defenseSalvage))
                .save(consumer, prefix(CCModifierIds.fragileProtection, defenseFolder));

        ModifierRecipeBuilder.modifier(CCModifierIds.encumberment)
                .setTools(TinkerTags.Items.LEGGINGS)
                .addInput(Tags.Items.STORAGE_BLOCKS_IRON)
                .addInput(Items.ANVIL)
                .addInput(Tags.Items.STORAGE_BLOCKS_IRON)
                .setSlots(SlotType.UPGRADE, 1)
                .setMaxLevel(1)
                .saveSalvage(consumer, prefix(CCModifierIds.encumberment, upgradeSalvage))
                .save(consumer, prefix(CCModifierIds.encumberment, upgradeFolder));

        ModifierRecipeBuilder.modifier(CCModifiers.heartstopper)
                .setTools(ingredientFromTags(TinkerTags.Items.MELEE, TinkerTags.Items.BOWS))
                .addInput(Items.FERMENTED_SPIDER_EYE)
                .addInput(Items.GLISTERING_MELON_SLICE)
                .addInput(TinkerMaterials.venombone)
                .setSlots(SlotType.UPGRADE, 1)
                .setMaxLevel(3)
                .saveSalvage(consumer, prefix(CCModifiers.heartstopper, upgradeSalvage))
                .save(consumer, prefix(CCModifiers.heartstopper, upgradeFolder));

        SwappableModifierRecipeBuilder.modifier(TinkerModifiers.embellishment, CCMaterialIds.chroma.toString())
                .setTools(TinkerTags.Items.EMBELLISHMENT_SLIME)
                .addInput(ModItems.CHROMA_SHARD.get()).addInput(ModItems.CHROMA_SHARD.get()).addInput(ModItems.CHROMA_SHARD.get())
                .save(withCondition(consumer, new ModLoadedCondition("chromaticarsenal")), wrap(TinkerModifiers.embellishment, slotlessFolder, "_chroma"));
    }

    @SafeVarargs
    private static Ingredient ingredientFromTags(TagKey<Item>... tags) {
        Ingredient[] tagIngredients = new Ingredient[tags.length];
        for (int i = 0; i < tags.length; i++) {
            tagIngredients[i] = Ingredient.of(tags[i]);
        }
        return CompoundIngredient.of(tagIngredients);
    }
}
