package dev.zanckor.cobblemonridingfabric.event;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.google.gson.Gson;
import dev.zanckor.cobblemonridingfabric.CobblemonRidingFabric;
import dev.zanckor.cobblemonridingfabric.config.PokemonJsonObject;
import dev.zanckor.cobblemonridingfabric.network.SendPacket;
import dev.zanckor.cobblemonridingfabric.network.packet.ConfigS2CPacket;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.event.player.UseEntityCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.util.ActionResult;

import static dev.zanckor.cobblemonridingfabric.CobblemonRidingFabric.LOGGER;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ServerPlayerEvent {

    public static void playerJoin() {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            PokemonJsonObject config = loadConfig();

            LOGGER.info("[RIDE] Sending config to player " + handler.player.getName().getString());
            LOGGER.info("[RIDE] Config: " + config);

            if (config != null) {
                String json = new Gson().toJson(config);

                int max = 32000; // Maximum chunk size
                int total = (int) Math.ceil((double) json.length() / max);

                for (int i = 0; i < total; i++) {
                    int start = i * max;
                    int end = Math.min(start + max, json.length());
                    String chunk = json.substring(start, end);

                    SendPacket.TO_CLIENT(handler.player, new ConfigS2CPacket(chunk, i, total));
                }
            }
        });
    }

    public static PokemonJsonObject loadConfig() {
        File pokemonRideConfigFile = CobblemonRidingFabric.PokemonRideConfigFile;
        String pokemonRideConfig = null;

        LOGGER.info("[RIDE] Loading cobblemon pokemon ride config file" + pokemonRideConfigFile);

        try {
            if (pokemonRideConfigFile != null) {
                LOGGER.info("[RIDE] Pokemon ride config file exists" + pokemonRideConfigFile);
                pokemonRideConfig = new String(Files.readAllBytes(pokemonRideConfigFile.toPath()));
                LOGGER.info("[RIDE] Pokemon ride config loaded" + pokemonRideConfig);
            }
        } catch (IOException e) {
            CobblemonRidingFabric.LOGGER
                    .info("Error reading cobblemon pokemon ride config file" + pokemonRideConfigFile);

            return null;
        }

        LOGGER.info("[RIDE] Pokemon ride config: " + pokemonRideConfig);

        return pokemonRideConfig != null ? new Gson().fromJson(pokemonRideConfig, PokemonJsonObject.class) : null;
    }
}