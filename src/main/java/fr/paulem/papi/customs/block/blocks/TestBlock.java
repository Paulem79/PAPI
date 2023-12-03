package fr.paulem.papi.customs.block.blocks;

import fr.paulem.papi.customs.block.types.BrownMushroomCustomBlock;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

/**
 * For test purposes
 */
public class TestBlock extends BrownMushroomCustomBlock {
    public TestBlock(JavaPlugin plugin, boolean putInList) {
        super(plugin, putInList);
    }

    @Override
    public @NotNull Block placedBlockProps() {
        return null;
    }

    @Override
    public @Nullable ItemStack droppedItemProps() {
        return null;
    }

    @Override
    public @NotNull NamespacedKey getIdentifier() {
        return null;
    }

    @Override
    public @NotNull Set<BlockFace> getFaces() {
        return null;
    }

    @Override
    public void onInteract(PlayerInteractEvent event) {

    }

    @Override
    public void onPlace(BlockPlaceEvent event) {

    }

    @Override
    public void onBreak(BlockBreakEvent event) {

    }

    @Override
    public void onPickup(EntityPickupItemEvent event) {

    }

    @Override
    public void onDrop(EntityDropItemEvent event) {

    }
}
