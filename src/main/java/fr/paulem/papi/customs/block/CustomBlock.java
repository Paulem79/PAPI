package fr.paulem.papi.customs.block;

import fr.paulem.papi.extend.PluginExtend;
import org.bukkit.Material;
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

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class CustomBlock extends PluginExtend {
    /**
     * The list of all registered custom blocks
     */
    public static final List<CustomBlock> CUSTOM_BLOCKS = new ArrayList<>();

    /**
     * The list of all registered custom blocks' materials.<br>
     * Uses {@link org.bukkit.Material}.
     */
    public static final Set<Material> CUSTOM_BLOCKS_MATERIALS = new HashSet<>();

    private final CustomBlockType blockType;
    private final Block customBlock;
    private final ItemStack droppedItem;
    private final boolean putInList;

    public CustomBlock(JavaPlugin plugin, @NotNull CustomBlockType blockType, boolean putInList){
        super(plugin);
        Block properties = placedBlockProps();
        if(properties.getType() != blockType.getMaterial())
            throw new IllegalArgumentException("Block material must be one of CustomBlockType!");

        Block blockProps = placedBlockProps();

        this.blockType = blockType;
        this.putInList = putInList;
        this.customBlock = blockProps;
        if(droppedItemProps() == null){
            ItemStack item = new ItemStack(blockProps.getType());
            item.setData(blockProps.getState().getData());
            this.droppedItem = item;
        } else this.droppedItem = droppedItemProps();

        if(putInList) CUSTOM_BLOCKS.add(this);
    }

    /**
     * Set the custom block properties here.<br><br>
     * To set the custom block's dropped item properties see {@link #droppedItemProps()}.
     */
    public abstract @NotNull Block placedBlockProps();

    /**
     * Set the custom block's dropped item properties here.<br>
     * If null, the dropped item is the custom block.<br><br>
     * To set the custom block properties see {@link #placedBlockProps()}.
     */
    public abstract @Nullable ItemStack droppedItemProps();

    public abstract @NotNull NamespacedKey getIdentifier();

    /**
     * Triggered when the custom block is right/left-clicked
     * @param event The involved event
     */
    public abstract void onInteract(PlayerInteractEvent event);

    /**
     * Triggered when the custom block is placed
     * @param event The involved event
     */
    public abstract void onPlace(BlockPlaceEvent event);

    /**
     * Triggered when the custom block is broke
     * @param event The involved event
     */
    public abstract void onBreak(BlockBreakEvent event);

    /**
     * Triggered when the custom block's item is picked up by an entity
     * @param event The involved event
     */
    public abstract void onPickup(EntityPickupItemEvent event);

    /**
     * Triggered when the custom block's item is dropped
     * @param event The involved event
     */
    public abstract void onDrop(EntityDropItemEvent event);

    /**
     * Get the block type
     * @see CustomBlockType
     */
    public CustomBlockType getBlockType() {
        return blockType;
    }

    /**
     * @see #CUSTOM_BLOCKS
     * @return if the custom block is put by default in the list
     */
    public boolean isPutByDefaultInList() {
        return putInList;
    }

    public Block getCustomBlock() {
        return customBlock;
    }

    public ItemStack getDropItem() {
        return droppedItem;
    }
}
