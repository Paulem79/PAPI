package fr.paulem.papi.compatibility;

import org.bukkit.Bukkit;

public enum VersionMethod {
    BUKKIT(Bukkit.getBukkitVersion()),
    SERVER(Bukkit.getVersion());

    final String version;

    VersionMethod(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }
}