package fr.paulem.papi.customs.block;

import org.bukkit.Material;

public enum CustomBlockType {
    GENERIC(Material.AIR),
    NOTE_BLOCK(Material.NOTE_BLOCK),
    BROWN_MUSHROOM_BLOCK(Material.BROWN_MUSHROOM_BLOCK),
    RED_MUSHROOM_BLOCK(Material.RED_MUSHROOM_BLOCK);

    private final Material material;

    CustomBlockType(Material material){
        this.material = material;
    }

    public Material getMaterial() {
        return material;
    }
}
