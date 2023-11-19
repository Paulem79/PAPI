package fr.paulem.papi.compatibility;

import net.md_5.bungee.api.ChatColor;

import static fr.paulem.papi.compatibility.Version.getVersion;

public class ColorCompatibility {
    private final ChatColor color;

    public ColorCompatibility(ChatColor color, ChatColor elseColor) {
        this.color = getVersion(VersionMethod.BUKKIT).getMinor() >= 16 ? color : elseColor;
    }

    public ChatColor getColor() {
        return color;
    }
}
