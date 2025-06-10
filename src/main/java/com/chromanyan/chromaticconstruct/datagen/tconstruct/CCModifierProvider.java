package com.chromanyan.chromaticconstruct.datagen.tconstruct;

import com.aizistral.enigmaticlegacy.registries.EnigmaticEnchantments;
import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import com.chromanyan.chromaticconstruct.tools.CCPredicate;
import com.chromanyan.chromaticconstruct.tools.CCVolatileFlags;
import com.chromanyan.chromaticconstruct.tools.modules.armor.FragileProtectionModule;
import com.chromanyan.chromaticconstruct.tools.modules.armor.PanicModule;
import com.chromanyan.chromaticconstruct.tools.modules.ShockingModule;
import net.minecraft.data.PackOutput;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.common.crafting.conditions.ModLoadedCondition;
import org.jetbrains.annotations.NotNull;
import slimeknights.mantle.data.predicate.damage.DamageSourcePredicate;
import slimeknights.mantle.data.predicate.entity.LivingEntityPredicate;
import slimeknights.tconstruct.library.data.tinkering.AbstractModifierProvider;
import slimeknights.tconstruct.library.json.LevelingValue;
import slimeknights.tconstruct.library.modifiers.ModifierId;
import slimeknights.tconstruct.library.modifiers.modules.armor.ProtectionModule;
import slimeknights.tconstruct.library.modifiers.modules.behavior.AttributeModule;
import slimeknights.tconstruct.library.modifiers.modules.build.EnchantmentModule;
import slimeknights.tconstruct.library.modifiers.modules.build.StatBoostModule;
import slimeknights.tconstruct.library.modifiers.modules.build.VolatileFlagModule;
import slimeknights.tconstruct.library.modifiers.modules.combat.ConditionalMeleeDamageModule;
import slimeknights.tconstruct.library.modifiers.util.ModifierLevelDisplay;
import slimeknights.tconstruct.library.tools.stat.ToolStats;
import slimeknights.tconstruct.tools.data.ModifierIds;

public class CCModifierProvider extends AbstractModifierProvider implements IConditionBuilder {

    public CCModifierProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void addModifiers() {
        buildModifier(CCModifierIds.encumberment)
                .levelDisplay(ModifierLevelDisplay.NO_LEVELS)
                .addModule(new VolatileFlagModule(CCVolatileFlags.PREVENT_PICKUPS));
        buildModifier(CCModifierIds.panic)
                .levelDisplay(ModifierLevelDisplay.NO_LEVELS)
                .addModule(PanicModule.INSTANCE);
        buildModifier(CCModifierIds.fragileProtection)
                .addModule(new FragileProtectionModule(LevelingValue.eachLevel(2.5f)));
        buildModifier(CCModifierIds.solarProtection).addModule(
                ProtectionModule.builder()
                        .source(DamageSourcePredicate.CAN_PROTECT)
                        .entity(CCPredicate.IN_SUNLIGHT)
                        .eachLevel(1.25f));

        // compat: meaningful materials
        buildModifier(CCModifierIds.antiair).addModule(
                ConditionalMeleeDamageModule.builder()
                        .target(LivingEntityPredicate.ON_GROUND.inverted())
                        .eachLevel(1.5f));
        buildModifier(CCModifierIds.antigravity)
                .levelDisplay(ModifierLevelDisplay.NO_LEVELS)
                .addModule(new VolatileFlagModule(CCVolatileFlags.NOGRAVITY_ENTITY));

        // compat: enigmatic legacy
        buildModifier(CCModifierIds.slayer).addModule(
                ConditionalMeleeDamageModule.builder()
                        .target(CCPredicate.MONSTER)
                        .eachLevel(1.5f));
        buildModifier(CCModifierIds.emergencyProtection).addModule(
                ProtectionModule.builder()
                        .source(DamageSourcePredicate.CAN_PROTECT)
                        .entity(CCPredicate.BELOW_40)
                        .eachLevel(1.25f));
        // these ones need to be conditional because they might otherwise print an error if EL isn't installed
        buildModifier(CCModifierIds.nemesis, new ModLoadedCondition("enigmaticlegacy"))
                .levelDisplay(ModifierLevelDisplay.NO_LEVELS).addModule(
                EnchantmentModule.builder(EnigmaticEnchantments.NEMESIS).constant());
        buildModifier(CCModifierIds.sorrow, new ModLoadedCondition("enigmaticlegacy"))
                .levelDisplay(ModifierLevelDisplay.NO_LEVELS).addModule(
                        EnchantmentModule.builder(EnigmaticEnchantments.SORROW).constant());

        buildModifier(CCModifierIds.shocking)
                .addModule(ShockingModule.INSTANCE)
                .addModule(StatBoostModule.multiplyBase(ToolStats.MINING_SPEED).eachLevel(0.1f))
                .addModule(StatBoostModule.multiplyBase(ToolStats.DRAW_SPEED).eachLevel(0.07f));

        // skyfall does what moonbound used to with slightly different numbers
        addRedirect(id("moonbound"), redirect(ModifierIds.skyfall));
    }

    @Override
    public @NotNull String getName() {
        return "Chromatic Construct Modifiers";
    }

    private static ModifierId id(String name) {
        return new ModifierId(ChromaticConstruct.MODID, name);
    }
}
