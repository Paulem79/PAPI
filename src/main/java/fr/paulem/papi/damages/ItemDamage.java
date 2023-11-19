package fr.paulem.papi.damages;

import fr.paulem.papi.compatibility.VersionMethod;
import fr.paulem.papi.compatibility.Version;
import org.bukkit.GameMode;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;

import java.util.Arrays;

import static fr.paulem.papi.compatibility.Version.getVersion;

public class ItemDamage {
    private static final Version bukkitVersion = getVersion(VersionMethod.BUKKIT);

    public static boolean isPost17() {
        return bukkitVersion.getMinor() > 17 || (bukkitVersion.getMinor() == 17 && bukkitVersion.getRevision() >= 1);
    }

    public static void dealDamage(Entity entity, ItemStack item, int damage){
        if(entity instanceof Player &&
                Arrays.asList(GameMode.CREATIVE, GameMode.SPECTATOR).contains(((Player) entity).getGameMode())) return;

        dealDamage(item, damage, true);
    }

    public void dealDamage(ItemStack item, int damage){
        dealDamage(item, damage, true);
    }

    public static void dealDamage(ItemStack item, int damage, boolean considerUnbreaking){
        int unbreakingLevel = considerUnbreaking ? item.getEnchantmentLevel(Enchantment.DURABILITY)+1 : 1;
        int toDeal = getDamage(item) + (damage/unbreakingLevel);
        setDamage(item, toDeal);
    }

    public static void setDamage(ItemStack item, int damage){
        if(isPost17()){
            if(item.getItemMeta() == null) throw new IllegalStateException("Item must have meta");
            Damageable damageable = (Damageable) item.getItemMeta();
            damageable.setDamage(damage);
            item.setItemMeta(damageable);
        } else item.setDurability((short) damage);
    }

    public static int getDamage(ItemStack item){
        if(isPost17()) {
            if(item.getItemMeta() == null) throw new IllegalStateException("Item must have meta");
            return ((Damageable) item.getItemMeta()).getDamage();
        } else
            return item.getDurability();
    }

    public static int getDurability(ItemStack item) {
        return item.getType().getMaxDurability() - getDamage(item);
    }

    public static int consumeUsage(Inventory inv, ItemStack item, int amount){
        if (inv.getHolder() instanceof Player && ((Player) inv.getHolder()).getGameMode() != GameMode.CREATIVE)
            item.setAmount(item.getAmount() - amount);
        if (item.getAmount() <= 0) inv.remove(item);
        return item.getAmount();
    }

    public static boolean hasDamage(ItemStack item){
        if(isPost17()) {
            if(item.getItemMeta() == null) throw new IllegalStateException("Item must have meta");
            return ((Damageable) item.getItemMeta()).hasDamage();
        } else
            return getDamage(item) < item.getType().getMaxDurability();
    }
}