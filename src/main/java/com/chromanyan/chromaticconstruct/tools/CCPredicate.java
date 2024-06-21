package com.chromanyan.chromaticconstruct.tools;

import net.minecraft.world.entity.monster.Monster;
import slimeknights.mantle.data.predicate.entity.LivingEntityPredicate;

/** Additional living predicates added by Chromatic Construct to allow more modifiers to be fully data-driven */
public class CCPredicate {

    /** Entities that extend {@link Monster}. Used by Slayer */
    public static LivingEntityPredicate MONSTER = LivingEntityPredicate.simple(entity -> entity instanceof Monster);

    /** Entities with less than 40% of their health remaining. Used by Emergency Protection */
    public static LivingEntityPredicate BELOW_40 = LivingEntityPredicate.simple(entity -> entity.getHealth() < entity.getMaxHealth() * 0.4);

}
