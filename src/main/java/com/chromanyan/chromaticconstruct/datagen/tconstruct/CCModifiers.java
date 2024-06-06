package com.chromanyan.chromaticconstruct.datagen.tconstruct;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.data.predicate.entity.LivingEntityPredicate;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider;
import slimeknights.tconstruct.library.modifiers.modules.combat.ConditionalMeleeDamageModule;

public class CCModifiers extends AbstractModifierProvider implements IConditionBuilder {

    public CCModifiers(DataGenerator generator) {
        super(generator);
    }

    @Override
    protected void addModifiers() {
        buildModifier(CCModifierIds.antiair).addModule(
                ConditionalMeleeDamageModule.builder()
                        .target(LivingEntityPredicate.ON_GROUND.inverted())
                        .eachLevel(1.5f));
    }

    @Override
    public @NotNull String getName() {
        return "Chromatic Construct Modifiers";
    }
}
