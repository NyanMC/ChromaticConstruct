package com.chromanyan.chromaticconstruct.tools;

import net.minecraft.world.entity.monster.Monster;
import slimeknights.mantle.data.predicate.entity.LivingEntityPredicate;

/** Additional living predicates added by Chromatic Construct to allow more modifiers to be fully data-driven */
public class CCPredicate {

    /** Entities that extend {@link Monster} */
    public static LivingEntityPredicate MONSTER = LivingEntityPredicate.simple(entity -> entity instanceof Monster);

}
