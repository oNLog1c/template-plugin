package me.nologic.plugin.util;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PlaceholderProvider extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "templatebukkitplugin";
    }

    @Override
    public @NotNull String getAuthor() {
        return "NoLogic";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public String onRequest(OfflinePlayer offlinePlayer, @NotNull String params) {

        final Player player = offlinePlayer.getPlayer();

        if (player != null) {
            return switch (params.toLowerCase()) {
                case "player_name" -> player.getName();
                default -> null;
            };
        }

        return null;
    }


}
