package com.chromanyan.chromaticconstruct.mixin;

import com.chromanyan.chromaticconstruct.datagen.tconstruct.CCModifierIds;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;

@Pseudo
@Mixin(targets = "com.aizistral.enigmaticlegacy.handlers.SuperpositionHandler")
public class MixinSuperpositionHandler {

    // The Scroll of a Thousand Curses doesn't like modifiers, but that's nothing a little mixin couldn't fix
    // Unfortunately hardcoded because I didn't know how to do this properly
    @Inject(method = "getCurseAmount(Lnet/minecraft/world/item/ItemStack;)I", at = @At("RETURN"), cancellable = true)
    private static void getCurseAmount(ItemStack stack, CallbackInfoReturnable<Integer> cir) {
        int bonusCurses = 0;

        bonusCurses += ModifierUtil.getModifierLevel(stack, CCModifierIds.nemesis);

        cir.setReturnValue(cir.getReturnValue() + bonusCurses);
    }
}
