package fr.paulem.papi.customs.block;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class CustomBlock {
    /**
     * The list of all registered custom blocks
     */
    public static final List<CustomBlock> CUSTOM_BLOCKS = new ArrayList<>();

    private final CustomBlockType blockType;
    private final Block customBlock;
    private final Item droppedItem;
    private final boolean putInList;

    public CustomBlock(@NotNull CustomBlockType blockType, boolean putInList){
        Block properties = blockProperties();
        if(properties.getType() != blockType.getMaterial())
            throw new IllegalArgumentException("Block material must be one of CustomBlockType!");

        this.blockType = blockType;
        this.putInList = putInList;
        this.customBlock = blockProperties();
        this.droppedItem = droppedProperties();

        if(putInList) CUSTOM_BLOCKS.add(this);
    }

    /**
     * Set the custom block properties here.<br><br>
     * To set the custom block's dropped item properties see {@link #droppedProperties()}.
     */
    public abstract @NotNull Block blockProperties();

    /**
     * Set the custom block's dropped item properties here.<br><br>
     * To set the custom block properties see {@link #blockProperties()}.
     */
    public abstract @Nullable Item droppedProperties();

    /**
     * Triggered when the custom block is right/left-clicked
     * @param player The player who interacted
     * @param action The type of click
     */
    public abstract void onInteract(Player player, Action action);

    /**
     * Triggered when the custom block is broke
     * @param event The event containing parameters
     */
    public abstract void onBreak(BlockBreakEvent event);

    /**
     * Triggered when the custom block's item is picked up by an entity
     * @param entity The entity who picked up the custom block's item
     * @param remaining The number of remaining items of this type on ground
     */
    public abstract void onPickup(LivingEntity entity, int remaining);

    /**
     * Triggered when the custom block's item is dropped
     * @param entity The entity who dropped the custom block's item
     */
    public abstract void onDrop(Entity entity);

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

    public Item getDropItem() {
        return droppedItem;
    }
}
