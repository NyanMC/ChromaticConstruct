package com.chromanyan.chromaticconstruct.event;

import com.chromanyan.chromaticconstruct.tools.CCVolatileFlags;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;

public class CCEvents {

    @SubscribeEvent
    public void onEntityJoinLevel(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof ItemEntity itemEntity)) return;

        if (ModifierUtil.checkVolatileFlag(itemEntity.getItem(), CCVolatileFlags.NOGRAVITY_ENTITY)) {
            itemEntity.setNoGravity(true);
            itemEntity.setDeltaMovement(itemEntity.getDeltaMovement().multiply(1, 0, 1));
        }
    }

}
