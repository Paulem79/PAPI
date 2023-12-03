package fr.paulem.papi.customs.block.types;

import fr.paulem.papi.customs.block.CustomBlock;
import fr.paulem.papi.customs.block.CustomBlockType;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public abstract class NoteBlockCustomBlock extends CustomBlock {
    public NoteBlockCustomBlock(JavaPlugin plugin, boolean putInList) {
        super(plugin, CustomBlockType.NOTE_BLOCK, putInList);
    }

    @Override
    public abstract @NotNull Block placedBlockProps();

    @Override
    public abstract @Nullable ItemStack droppedItemProps();

    @Override
    public abstract @NotNull NamespacedKey getIdentifier();

    @Override
    public abstract void onInteract(PlayerInteractEvent event);

    @Override
    public abstract void onPlace(BlockPlaceEvent event);

    @Override
    public abstract void onBreak(BlockBreakEvent event);

    @Override
    public abstract void onPickup(EntityPickupItemEvent event);

    @Override
    public abstract void onDrop(EntityDropItemEvent event);
}