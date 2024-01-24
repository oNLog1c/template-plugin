package me.nologic.plugin;

import lombok.Getter;
import me.nologic.minority.MinorityExtension;
import me.nologic.plugin.util.CommandProvider;
import me.nologic.plugin.util.PlaceholderProvider;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.jetbrains.annotations.Nullable;

public final class TemplateBukkitPlugin extends MinorityExtension {

    private           @Getter CommandProvider     commandProvider;
    private @Nullable @Getter PlaceholderProvider placeholderProvider;
    private @Nullable @Getter Economy             economyProvider;

    @Override
    public void onEnable() {
        instance = this;
        this.initializeProviders();
    }

    @Override
    public void onDisable() {
        if (placeholderProvider != null) placeholderProvider.unregister();
    }

    private void initializeProviders() {

        this.commandProvider = new CommandProvider(this);

        // PAPI check
        if (super.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
            (this.placeholderProvider = new PlaceholderProvider()).register();
            this.getLogger().info("PlaceholderAPI plugin detected, placeholder support enabled.");
        }

        // Economy check
        if (super.getServer().getPluginManager().getPlugin("Vault") != null) {

            final RegisteredServiceProvider<Economy> rsp = super.getServer().getServicesManager().getRegistration(Economy.class);
            if (rsp != null) {
                this.getLogger().info("Vault plugin detected, economy support enabled.");
                this.economyProvider = rsp.getProvider();
            }

        }

    }

    @Getter
    private static TemplateBukkitPlugin instance;

}