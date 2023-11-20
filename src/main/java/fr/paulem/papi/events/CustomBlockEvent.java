package fr.paulem.papi.events;

import fr.paulem.papi.customs.block.CustomBlock;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class CustomBlockEvent implements Listener {
    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        if(!e.hasBlock() || e.useInteractedBlock() == Event.Result.DENY) return;

        CustomBlock customBlock = CustomBlock.CUSTOM_BLOCKS.stream()
                .filter(cBlock -> cBlock.getCustomBlock() == e.getClickedBlock())
                .findFirst().orElse(null);

        if(customBlock == null) return;

        customBlock.onInteract(e.getPlayer(), e.getAction());
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
                .filter(cBlock -> cBlock.getDropItem() == e.getItem())
                .findFirst().orElse(null);

        if(customBlock == null || customBlock.getDropItem() == null) return;

        customBlock.onPickup(e.getEntity(), e.getRemaining());
    }

    @EventHandler
    public void onRelease(EntityDropItemEvent e){
        if(e.isCancelled()) return;

        CustomBlock customBlock = CustomBlock.CUSTOM_BLOCKS.stream()
                .filter(cBlock -> cBlock.getDropItem() == e.getItemDrop())
                .findFirst().orElse(null);

        if(customBlock == null || customBlock.getDropItem() == null) return;

        customBlock.onDrop(e.getEntity());
    }
}
