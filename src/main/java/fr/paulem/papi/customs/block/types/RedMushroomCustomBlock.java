package fr.paulem.papi.customs.block.types;

import fr.paulem.papi.customs.block.CustomBlockType;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class RedMushroomCustomBlock extends MushroomCustomBlock {
    public RedMushroomCustomBlock(JavaPlugin plugin, boolean putInList) {
        super(plugin, CustomBlockType.RED_MUSHROOM_BLOCK, putInList);
    }
}
