package me.nologic.plugin.util;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.BukkitCommandManager;
import co.aikar.commands.annotation.CommandAlias;
import co.aikar.commands.annotation.CommandPermission;
import co.aikar.commands.annotation.Optional;
import me.nologic.plugin.TemplateBukkitPlugin;
import org.bukkit.entity.Player;

public class CommandProvider {

    private final TemplateBukkitPlugin plugin;
    private final BukkitCommandManager manager;

    public CommandProvider(final TemplateBukkitPlugin plugin) {
        this.plugin = plugin;
        this.manager = new BukkitCommandManager(this.plugin);
        this.registerCommands();
    }

    private void registerCommands() {
        this.manager.registerCommand(new DefaultCommand());
    }

    @CommandAlias("example")
    private static class DefaultCommand extends BaseCommand {

        @CommandAlias("hello|hi|sup|greetings")
        @CommandPermission("example.hello")
        private void hello(final Player player, @Optional final String name) {
            if (name == null) player.sendMessage("Hi! How are you?");
            else player.sendMessage(String.format("Hi, %s! How are you?", name));
        }

    }


}