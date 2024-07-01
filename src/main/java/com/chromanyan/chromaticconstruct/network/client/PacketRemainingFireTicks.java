package com.chromanyan.chromaticconstruct.network.client;

import com.chromanyan.chromaticconstruct.tools.modifiers.trait.InfernalModifier;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/*
 When you're faced with a value that doesn't sync to the client, you have two options:
 Give up, or find a dumb solution that takes more effort than it's worth.
 I want you to take a guess which one I did.
*/
public class PacketRemainingFireTicks {

    @SuppressWarnings("FieldMayBeFinal")
    private int fireTicks;

    public PacketRemainingFireTicks(int fireTicks) {
        this.fireTicks = fireTicks;
    }

    public static void encode(PacketRemainingFireTicks msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.fireTicks);
    }

    public static PacketRemainingFireTicks decode(FriendlyByteBuf buf) {
        return new PacketRemainingFireTicks(buf.readInt());
    }

    public static void handle(PacketRemainingFireTicks msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> InfernalModifier.setClientRemainingFireTicks(msg.fireTicks));
        ctx.get().setPacketHandled(true);
    }

}
