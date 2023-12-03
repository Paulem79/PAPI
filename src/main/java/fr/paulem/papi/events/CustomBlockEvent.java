package fr.paulem.papi.events;

import fr.paulem.papi.customs.block.CustomBlock;
import fr.paulem.papi.customs.block.CustomBlockType;
import fr.paulem.papi.customs.block.types.MushroomCustomBlock;
import fr.paulem.papi.extend.PluginExtendListener;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.data.MultipleFacing;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockExplodeEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.Arrays;

import static fr.paulem.papi.customs.block.CustomBlock.CUSTOM_BLOCKS;
import static fr.paulem.papi.customs.block.CustomBlock.CUSTOM_BLOCKS_MATERIALS;

public class CustomBlockEvent extends PluginExtendListener {
    public CustomBlockEvent(JavaPlugin plugin) {
        super(plugin);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if(!e.hasBlock() || e.useInteractedBlock() == Event.Result.DENY) return;

        CustomBlock customBlock = CustomBlock.CUSTOM_BLOCKS.stream()
                .filter(cBlock -> cBlock.getCustomBlock() == e.getClickedBlock())
                .findFirst().orElse(null);

        if(customBlock == null) return;

        customBlock.onInteract(e);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        if(e.isCancelled()) return;

        CustomBlock customBlock = CustomBlock.CUSTOM_BLOCKS.stream()
                .filter(cBlock -> cBlock.getCustomBlock() == e.getBlock())
                .findFirst().orElse(null);

        if(customBlock == null) return;

        customBlock.onPlace(e);
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e){
        if(e.isCancelled()) return;

        CustomBlock customBlock = CustomBlock.CUSTOM_BLOCKS.stream()
                .filter(cBlock -> cBlock.getCustomBlock() == e.getBlock())
                .findFirst().orElse(null);

        if(customBlock == null) return;

        customBlock.onBreak(e);
    }

    @EventHandler
    public void onPickup(EntityPickupItemEvent e){
        if(e.isCancelled()) return;

        CustomBlock customBlock = CustomBlock.CUSTOM_BLOCKS.stream()
                .filter(cBlock -> cBlock.getDropItem() == e.getItem().getItemStack())
                .findFirst().orElse(null);

        if(customBlock == null || customBlock.getDropItem() == null) return;

        customBlock.onPickup(e);
    }

    @EventHandler
    public void onRelease(EntityDropItemEvent e){
        if(e.isCancelled()) return;

        CustomBlock customBlock = CustomBlock.CUSTOM_BLOCKS.stream()
                .filter(cBlock -> cBlock.getDropItem() == e.getItemDrop().getItemStack())
                .findFirst().orElse(null);

        if(customBlock == null || customBlock.getDropItem() == null) return;

        customBlock.onDrop(e);
    }

    @EventHandler
    public void onMushroomBlockUpdate(BlockPhysicsEvent e) {
        if (CUSTOM_BLOCKS_MATERIALS.contains(e.getBlock().getType())) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onMushroomBlockPush(BlockPistonExtendEvent e) {
        for (Block block : e.getBlocks()) cancelMushroomStateChange(block, e.getDirection());
    }

    @EventHandler
    public void onMushroomBlockRetract(BlockPistonRetractEvent e) {
        for (Block block : e.getBlocks()) cancelMushroomStateChange(block, e.getDirection());
    }

    @EventHandler
    public void onMushroomBreak(BlockBreakEvent e) {
        e.setDropItems(false);
        customBlockBreak(e.getBlock(), e.getPlayer().getGameMode() == GameMode.SURVIVAL);
    }

    @EventHandler
    public void onTntExplodeCustomBlock(BlockExplodeEvent e) {
        for (Block block : e.blockList()) {
            customBlockBreak(block, true);
        }
    }

    @EventHandler
    public void onCreeperExplodeCustomBlock(EntityExplodeEvent e) {
        for (Block block : e.blockList()) {
            customBlockBreak(block, true);
        }
    }

    public void customBlockBreak(Block block, boolean dropBlock) {
        if (!CUSTOM_BLOCKS_MATERIALS.contains(block.getType()) || !Arrays.asList(Material.BROWN_MUSHROOM_BLOCK, Material.RED_MUSHROOM_BLOCK).contains(block.getType())) return;
        MultipleFacing facing = (MultipleFacing) block.getBlockData();
        MushroomCustomBlock customBlock = CUSTOM_BLOCKS.stream()
                .filter(b -> (b.getBlockType() == CustomBlockType.BROWN_MUSHROOM_BLOCK ||
                        b.getBlockType() == CustomBlockType.RED_MUSHROOM_BLOCK) &&
                        b instanceof MushroomCustomBlock)
                .map(b -> (MushroomCustomBlock) b)
                .filter(b -> facing.getFaces().equals(b.getFaces()))
                .findFirst()
                .orElse(null);
        if (customBlock == null) return;
        if (dropBlock) block.getWorld().dropItemNaturally(block.getLocation(), customBlock.getDropItem());
    }

    public void cancelMushroomStateChange(Block mushroom, BlockFace pistonFace) {
        if (!CUSTOM_BLOCKS_MATERIALS.contains(mushroom.getType())) return;
        MultipleFacing facing = (MultipleFacing) mushroom.getBlockData();
        new BukkitRunnable() {
            @Override
            public void run() {
                mushroom.getRelative(pistonFace).setBlockData(facing, false);
            }
        }.runTaskLater(getPlugin(), 2L);
    }

    @EventHandler
    public void onMushroomBlockPlace(BlockPlaceEvent e) {
        if (!CUSTOM_BLOCKS_MATERIALS.contains(e.getBlock().getType())) return;
        BlockData data = e.getBlock().getBlockData();
        MultipleFacing facing = (MultipleFacing) data;
        MushroomCustomBlock block = CUSTOM_BLOCKS.stream()
                .filter(b -> e.getItemInHand().getItemMeta() != null &&
                        b.getDropItem().getItemMeta() != null &&
                        e.getItemInHand().getItemMeta().hasCustomModelData() &&
                        b.getDropItem().getItemMeta().getCustomModelData() == e.getItemInHand().getItemMeta().getCustomModelData() &&
                        e.getItemInHand().getType() == e.getBlock().getType() &&
                        b.getDropItem().getType() == e.getBlock().getType() &&
                        (b.getBlockType() == CustomBlockType.BROWN_MUSHROOM_BLOCK ||
                        b.getBlockType() == CustomBlockType.RED_MUSHROOM_BLOCK) &&
                        b instanceof MushroomCustomBlock)
                .map(b -> (MushroomCustomBlock) b)
                .findFirst()
                .orElse(null);
        if (block != null) {
            //Le bloc est bien custom
            for (BlockFace face : facing.getAllowedFaces()) {
                facing.setFace(face, block.getFaces().contains(face));
            }
            e.getBlock().setBlockData(facing, false);
        } else {
            //Le bloc n'est pas custom, mettre par défaut
            facing.setFace(BlockFace.DOWN, true);
            facing.setFace(BlockFace.EAST, true);
            facing.setFace(BlockFace.NORTH, true);
            facing.setFace(BlockFace.SOUTH, true);
            facing.setFace(BlockFace.UP, true);
            facing.setFace(BlockFace.WEST, true);
            e.getBlock().setBlockData(facing, false);
        }
    }
}
