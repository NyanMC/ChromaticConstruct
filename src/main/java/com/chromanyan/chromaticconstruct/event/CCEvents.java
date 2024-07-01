package com.chromanyan.chromaticconstruct.event;

import com.chromanyan.chromaticconstruct.network.CCPacketHandler;
import com.chromanyan.chromaticconstruct.network.client.PacketRemainingFireTicks;
import com.chromanyan.chromaticconstruct.tools.CCVolatileFlags;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.network.PacketDistributor;
import slimeknights.tconstruct.library.tools.helper.ModifierUtil;

import java.util.HashMap;
import java.util.UUID;

public class CCEvents {

    public static HashMap<UUID, Integer> remainingFireTicksMap = new HashMap<>();

    @SubscribeEvent
    public void onEntityJoinLevel(EntityJoinLevelEvent event) {
        if (!(event.getEntity() instanceof ItemEntity itemEntity)) return;

        if (ModifierUtil.checkVolatileFlag(itemEntity.getItem(), CCVolatileFlags.NOGRAVITY_ENTITY)) {
            itemEntity.setNoGravity(true);
            itemEntity.setDeltaMovement(itemEntity.getDeltaMovement().multiply(1, 0, 1));
        }
    }

    @SubscribeEvent
    public void onItemPickup(EntityItemPickupEvent event) {
        for (ItemStack itemStack : event.getEntity().getArmorSlots()) {
            if (ModifierUtil.checkVolatileFlag(itemStack, CCVolatileFlags.PREVENT_PICKUPS)) {
                event.setCanceled(true);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.side == LogicalSide.CLIENT)
            return;
        Player player = event.player;
        UUID uuid = player.getUUID();

        if (!(player instanceof ServerPlayer serverPlayer)) return;

        int playerRemainingFireTicks = player.getRemainingFireTicks();

        if (!remainingFireTicksMap.containsKey(uuid)) {
            remainingFireTicksMap.put(uuid, playerRemainingFireTicks);
            CCPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new PacketRemainingFireTicks(playerRemainingFireTicks));
        } else if (remainingFireTicksMap.get(uuid) != playerRemainingFireTicks) {
            remainingFireTicksMap.replace(uuid, playerRemainingFireTicks);
            CCPacketHandler.INSTANCE.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new PacketRemainingFireTicks(playerRemainingFireTicks));
        }
    }
}
