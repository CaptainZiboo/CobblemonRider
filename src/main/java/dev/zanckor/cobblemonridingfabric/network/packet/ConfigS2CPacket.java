package dev.zanckor.cobblemonridingfabric.network.packet;

import com.google.gson.Gson;
import dev.zanckor.cobblemonridingfabric.CobblemonRidingFabric;
import dev.zanckor.cobblemonridingfabric.config.PokemonJsonObject;
import dev.zanckor.cobblemonridingfabric.network.AbstractPacket;
import dev.zanckor.cobblemonridingfabric.network.NetworkHandler;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.util.Identifier;

public class ConfigS2CPacket extends AbstractPacket {
    private final String chunk;
    private final int index;
    private final int total;

    public ConfigS2CPacket(String chunk, int index, int total) {
        this.chunk = chunk;
        this.index = index;
        this.total = total;
    }

    @Override
    public PacketByteBuf encode() {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeInt(index);
        buf.writeInt(total);
        buf.writeString(chunk);
        return buf;
    }

    @Override
    public Identifier getID() {
        return NetworkHandler.CONFIG_PACKET;
    }

    public static void receive(MinecraftClient client, ClientPlayPacketListener handler, PacketByteBuf buffer, PacketSender responseSender) {
        int index = buffer.readInt();
        int total = buffer.readInt();
        String chunk = buffer.readString();

        client.execute(() -> {
            CobblemonRidingFabric.addConfigChunk(index, chunk, total);
        });
    }
}
