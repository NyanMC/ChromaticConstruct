package com.chromanyan.chromaticconstruct.datagen.tconstruct;

import com.chromanyan.chromaticconstruct.tools.CCVolatileFlags;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.data.predicate.entity.LivingEntityPredicate;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider;
import slimeknights.tconstruct.library.modifiers.modules.behavior.AttributeModule;
import slimeknights.tconstruct.library.modifiers.modules.build.VolatileFlagModule;
import slimeknights.tconstruct.library.modifiers.modules.combat.ConditionalMeleeDamageModule;
import slimeknights.tconstruct.library.modifiers.util.ModifierLevelDisplay;

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
        buildModifier(CCModifierIds.antigravity)
                .levelDisplay(ModifierLevelDisplay.NO_LEVELS)
                .addModule(new VolatileFlagModule(CCVolatileFlags.NOGRAVITY_ENTITY));
        buildModifier(CCModifierIds.moonbound).addModule(
                AttributeModule.builder(ForgeMod.ENTITY_GRAVITY.get(), AttributeModifier.Operation.MULTIPLY_TOTAL)
                        .uniqueFrom(CCModifierIds.moonbound)
                        .eachLevel(-0.25f));
    }

    @Override
    public @NotNull String getName() {
        return "Chromatic Construct Modifiers";
    }
}
