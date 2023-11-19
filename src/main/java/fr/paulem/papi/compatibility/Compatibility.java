package fr.paulem.papi.compatibility;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class Compatibility {
    public static boolean isServerPaperBased() {
        try {
            Class.forName("com.destroystokyo.paper.ParticleBuilder");
            return true;
        } catch (ClassNotFoundException ignored) {
            return false;
        }
    }

    public static boolean areDisplaysSupported() {
        Version bukkitVersion = Version.getVersion(VersionMethod.BUKKIT);
        return bukkitVersion.getMinor() >= 20 || (bukkitVersion.getMinor() == 19 && bukkitVersion.getRevision() == 4);
    }

    public static FileConfiguration reloadConfig(JavaPlugin plugin) {
        plugin.reloadConfig();

        plugin.saveDefaultConfig();
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveConfig();

        return plugin.getConfig();
    }
}
