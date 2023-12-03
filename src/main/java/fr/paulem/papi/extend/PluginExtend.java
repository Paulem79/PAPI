package fr.paulem.papi.extend;

import org.bukkit.plugin.java.JavaPlugin;

public class PluginExtend {
    private final JavaPlugin plugin;

    public PluginExtend(JavaPlugin plugin){
        this.plugin = plugin;
    }

    public JavaPlugin getPlugin() {
        return plugin;
    }
}
