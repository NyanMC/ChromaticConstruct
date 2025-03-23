package com.chromanyan.chromaticconstruct.init;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import net.minecraft.world.item.Item;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;

public class CCItems {

    public static final ItemDeferredRegisterExtension ITEMS = new ItemDeferredRegisterExtension(ChromaticConstruct.MODID);

    private static final Item.Properties GENERAL_PROPS = new Item.Properties();

    public static final ItemObject<Item> glassReinforcement = ITEMS.register("glass_reinforcement", GENERAL_PROPS);
    public static final ItemObject<Item> hamhide = ITEMS.register("hamhide", GENERAL_PROPS);
}
