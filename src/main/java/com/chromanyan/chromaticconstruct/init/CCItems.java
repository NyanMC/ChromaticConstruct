package com.chromanyan.chromaticconstruct.init;

import com.chromanyan.chromaticconstruct.ChromaticConstruct;
import net.minecraft.world.item.Item;
import slimeknights.mantle.registration.object.ItemObject;
import slimeknights.tconstruct.common.registration.ItemDeferredRegisterExtension;

// someone is going to kill me for this
import static slimeknights.tconstruct.common.TinkerModule.TAB_GENERAL;

public class CCItems {

    public static final ItemDeferredRegisterExtension ITEMS = new ItemDeferredRegisterExtension(ChromaticConstruct.MODID);

    private static final Item.Properties GENERAL_PROPS = new Item.Properties().tab(TAB_GENERAL);

    public static final ItemObject<Item> glassReinforcement = ITEMS.register("glass_reinforcement", GENERAL_PROPS);

}
