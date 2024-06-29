package com.chromanyan.chromaticconstruct.datagen.tconstruct.material;

import net.minecraft.data.DataGenerator;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialStatsDataProvider;
import slimeknights.tconstruct.tools.stats.HandleMaterialStats;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;
import slimeknights.tconstruct.tools.stats.PlatingMaterialStats;
import slimeknights.tconstruct.tools.stats.StatlessMaterialStats;

import static net.minecraft.world.item.Tiers.IRON;
import static net.minecraft.world.item.Tiers.NETHERITE;

public class CCMaterialStatsDataProvider extends AbstractMaterialStatsDataProvider {

    public CCMaterialStatsDataProvider(DataGenerator gen, AbstractMaterialDataProvider materials) {
        super(gen, materials);
    }

    @Override
    protected void addMaterialStats() {
        addMeleeHarvest();
        addArmor();
    }

    private void addMeleeHarvest() {
        addMaterialStats(CCMaterialIds.cosmite,
                new HeadMaterialStats(200, 5f, IRON, 2.25f),
                HandleMaterialStats.multipliers().durability(0.9f).attackSpeed(1.05f).attackDamage(1.05f).build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(CCMaterialIds.etherium,
                new HeadMaterialStats(1500, 6f, NETHERITE, 3f),
                HandleMaterialStats.multipliers().durability(1.25f).attackSpeed(0.85f).attackDamage(1.15f).miningSpeed(0.95f).build(),
                StatlessMaterialStats.BINDING);

        addMaterialStats(CCMaterialIds.chroma,
                new HeadMaterialStats(150, 6.5f, IRON, 1.5f),
                HandleMaterialStats.multipliers().durability(0.8f).miningSpeed(1.1f).attackDamage(0.95f).build(),
                StatlessMaterialStats.BINDING);
    }

    private void addArmor() {
        addMaterialStats(CCMaterialIds.cosmite, StatlessMaterialStats.MAILLE);
        addArmorShieldStats(CCMaterialIds.etherium, PlatingMaterialStats.builder().durabilityFactor(40).armor(2, 5, 7, 2).toughness(2), StatlessMaterialStats.MAILLE);
    }

    @Override
    public @NotNull String getName() {
        return "Chromatic Construct Material Stats";
    }
}
