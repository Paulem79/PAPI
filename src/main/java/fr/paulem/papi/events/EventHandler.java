package fr.paulem.papi.events;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class EventHandler {
    public static void registerEvents(JavaPlugin plugin, Listener listener) {
        plugin.getServer().getPluginManager().registerEvents(listener, plugin);
    }
}
