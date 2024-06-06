package com.chromanyan.chromaticconstruct.datagen.tconstruct.material;

import net.minecraft.data.DataGenerator;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialStatsDataProvider;
import slimeknights.tconstruct.tools.stats.HandleMaterialStats;
import slimeknights.tconstruct.tools.stats.HeadMaterialStats;
import slimeknights.tconstruct.tools.stats.StatlessMaterialStats;

import static net.minecraft.world.item.Tiers.IRON;

public class CCMaterialStatsDataProvider extends AbstractMaterialStatsDataProvider {

    public CCMaterialStatsDataProvider(DataGenerator gen, AbstractMaterialDataProvider materials) {
        super(gen, materials);
    }

    @Override
    protected void addMaterialStats() {
        addMeleeHarvest();
    }

    private void addMeleeHarvest() {
        addMaterialStats(CCMaterialIds.cosmite,
                new HeadMaterialStats(200, 5f, IRON, 2.25f),
                HandleMaterialStats.multipliers().durability(0.9f).attackSpeed(1.05f).attackDamage(1.05f).build(),
                StatlessMaterialStats.BINDING);
    }

    @Override
    public @NotNull String getName() {
        return "Chromatic Construct Material Stats";
    }
}
