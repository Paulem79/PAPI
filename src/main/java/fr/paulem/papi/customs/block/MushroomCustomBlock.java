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

public abstract class MushroomCustomBlock extends CustomBlock {
    public MushroomCustomBlock(@NotNull CustomBlockType blockType, boolean putInList) {
        super(blockType, putInList);
    }

    @Override
    public abstract @NotNull Block blockProperties();

    @Override
    public abstract @Nullable Item droppedProperties();

    @Override
    public abstract void onInteract(Player player, Action action);

    @Override
    public abstract void onBreak(BlockBreakEvent event);

    @Override
    public abstract void onPickup(LivingEntity entity, int remaining);

    @Override
    public abstract void onDrop(Entity entity);
}