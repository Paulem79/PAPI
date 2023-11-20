package fr.paulem.papi.customs.block.blocks;

import fr.paulem.papi.customs.block.CustomBlock;
import fr.paulem.papi.customs.block.CustomBlockType;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TestBlock extends CustomBlock {
    public TestBlock(CustomBlockType blockType, boolean putInList) {
        super(blockType, putInList);
    }

    @Override
    public @NotNull Block blockProperties() {
        return null;
    }

    @Override
    public @Nullable Item droppedProperties() {
        return null;
    }

    @Override
    public void onInteract(Player player, Action action) {

    }

    @Override
    public void onBreak(BlockBreakEvent event) {

    }

    @Override
    public void onPickup(LivingEntity entity, int remaining) {

    }

    @Override
    public void onDrop(Entity entity) {

    }
}
