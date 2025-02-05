package com.chromanyan.chromaticconstruct.tools.modifiers.trait;

import com.aizistral.enigmaticlegacy.objects.Vector3;
import com.aizistral.enigmaticlegacy.registries.EnigmaticItems;
import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.fml.ModList;
import org.jetbrains.annotations.NotNull;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.modifiers.ModifierEntry;
import slimeknights.tconstruct.library.modifiers.ModifierHooks;
import slimeknights.tconstruct.library.modifiers.hook.combat.MeleeHitModifierHook;
import slimeknights.tconstruct.library.module.ModuleHookMap;
import slimeknights.tconstruct.library.tools.context.ToolAttackContext;
import slimeknights.tconstruct.library.tools.nbt.IToolStackView;

public class BackstepModifier extends Modifier implements MeleeHitModifierHook {

    @Override
    protected void registerHooks(ModuleHookMap.@NotNull Builder hookBuilder) {
        super.registerHooks(hookBuilder);
        hookBuilder.addHook(this, ModifierHooks.MELEE_HIT);
    }

    @Override
    public void afterMeleeHit(@NotNull IToolStackView tool, @NotNull ModifierEntry modifier, @NotNull ToolAttackContext context, float damageDealt) {
        if (!ModList.get().isLoaded("enigmaticlegacy")) {
            ChromaticConstruct.LOGGER.error("Enigmatic legacy not installed. Backstep modifier cannot function. This should not be happening");
            return;
        }

        if (!context.isExtraAttack()) {
            LivingEntity attacker = context.getAttacker();
            Vector3 lookAngle = new Vector3(attacker.getLookAngle());

            try {
                EnigmaticItems.ETHERIUM_SWORD.getConfig().knockBack(attacker, modifier.getLevel() / 2F, lookAngle.x, lookAngle.z);
                attacker.getCommandSenderWorld().playSound(null, attacker.blockPosition(), SoundEvents.SKELETON_SHOOT, SoundSource.PLAYERS, 1.0F, (float) (0.6F + (Math.random() * 0.1D)));
            } catch (Exception e) {
                ChromaticConstruct.LOGGER.error("Couldn't perform Backstep modifier: ", e);
            }
        }
    }
}
