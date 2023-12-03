package fr.paulem.papi.customs.block.types;

import fr.paulem.papi.customs.block.CustomBlockType;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class BrownMushroomCustomBlock extends MushroomCustomBlock {
    public BrownMushroomCustomBlock(JavaPlugin plugin, boolean putInList) {
        super(plugin, CustomBlockType.BROWN_MUSHROOM_BLOCK, putInList);
    }
}
