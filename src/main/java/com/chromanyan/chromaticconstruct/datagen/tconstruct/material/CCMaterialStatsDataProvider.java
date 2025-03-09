package com.chromanyan.chromaticconstruct.datagen.tconstruct.material;

import net.minecraft.data.PackOutput;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.data.material.AbstractMaterialDataProvider;
import slimeknights.tconstruct.library.data.material.AbstractMaterialStatsDataProvider;
import slimeknights.tconstruct.tools.stats.*;

import static net.minecraft.world.item.Tiers.*;

public class CCMaterialStatsDataProvider extends AbstractMaterialStatsDataProvider {

    public CCMaterialStatsDataProvider(PackOutput out, AbstractMaterialDataProvider materials) {
        super(out, materials);
    }

    @Override
    protected void addMaterialStats() {
        addMeleeHarvest();
        addRanged();
        addArmor();
    }

    private void addMeleeHarvest() {
        addMaterialStats(CCMaterialIds.cosmite,
                new HeadMaterialStats(200, 5f, IRON, 2.25f),
                HandleMaterialStats.multipliers().durability(0.9f).attackSpeed(1.05f).attackDamage(1.05f).build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(CCMaterialIds.infernium,
                new HeadMaterialStats(700, 7f, DIAMOND, 1f),
                HandleMaterialStats.multipliers().durability(1.1f).miningSpeed(1.05f).attackSpeed(0.95f).attackDamage(0.9f).build(),
                StatlessMaterialStats.BINDING);
        addMaterialStats(CCMaterialIds.etherium,
                new HeadMaterialStats(1500, 6f, NETHERITE, 3f),
                HandleMaterialStats.multipliers().durability(1.25f).attackSpeed(0.85f).attackDamage(1.15f).miningSpeed(0.95f).build(),
                StatlessMaterialStats.BINDING);

        addMaterialStats(CCMaterialIds.chroma,
                new HeadMaterialStats(150, 6.5f, IRON, 1.5f),
                HandleMaterialStats.multipliers().durability(0.8f).miningSpeed(1.1f).attackDamage(0.95f).build(),
                StatlessMaterialStats.BINDING);

        addMaterialStats(CCMaterialIds.energizedSteel,
                new HeadMaterialStats(300, 7f, IRON, 1.5f),
                HandleMaterialStats.multipliers().durability(0.9f).miningSpeed(1.05f).build(),
                StatlessMaterialStats.BINDING);
    }

    private void addRanged() {
        addMaterialStats(CCMaterialIds.chroma,
                new LimbMaterialStats(150, 0.1f, 0f, -0.1f),
                new GripMaterialStats(-0.2f, 0.05f, 1.25f));

        addMaterialStats(CCMaterialIds.energizedSteel,
                new LimbMaterialStats(300, 0.1f, -0.05f, -0.05f),
                new GripMaterialStats(-0.1f, 0.05f, 1.5f));
    }

    private void addArmor() {
        addMaterialStats(CCMaterialIds.hamhide, StatlessMaterialStats.MAILLE);

        addMaterialStats(CCMaterialIds.cosmite, StatlessMaterialStats.MAILLE);
        addArmorShieldStats(CCMaterialIds.etherium, PlatingMaterialStats.builder().durabilityFactor(40).armor(2, 5, 7, 2).toughness(2), StatlessMaterialStats.MAILLE);
    }

    @Override
    public @NotNull String getName() {
        return "Chromatic Construct Material Stats";
    }
}
