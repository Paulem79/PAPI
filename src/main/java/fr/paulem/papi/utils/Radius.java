package fr.paulem.papi.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class Radius {
    public static Collection<Block> getBlocksInRadius(Location location, int radius) {
        return getBlocksInRadius(location, radius, radius, radius);
    }

    public static Collection<Block> getBlocksInRadius(Location location, int radiusx, int radiusy, int radiusz) {
        Collection<Block> blockList = new ArrayList<>();

        World world = location.getWorld();
        if(world == null) throw new IllegalStateException("Location must contain a world");

        int minX = location.getBlockX() - radiusx;
        int minY = location.getBlockY() - radiusy;
        int minZ = location.getBlockZ() - radiusz;

        int maxX = location.getBlockX() + radiusx;
        int maxY = location.getBlockY() + radiusy;
        int maxZ = location.getBlockZ() + radiusz;

        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                for (int z = minZ; z <= maxZ; z++) {
                    Block block = world.getBlockAt(x, y, z);
                    blockList.add(block);
                }
            }
        }

        return blockList;
    }

    public static Collection<Entity> getEntitiesAroundLocation(World world, double x, double y, double z, double radius) {
        return getEntitiesAroundLocation(new Location(world, x, y, z), radius, radius, radius);
    }

    public static Collection<Entity> getEntitiesAroundLocation(World world, double x, double y, double z, double radiusx, double radiusy, double radiusz) {
        return getEntitiesAroundLocation(new Location(world, x, y, z), radiusx, radiusy, radiusz);
    }

    public static Collection<Entity> getEntitiesAroundLocation(Location loc, double radius) {
        return getEntitiesAroundLocation(loc, radius, radius, radius);
    }

    public static Collection<Entity> getEntitiesAroundLocation(Location loc, double radiusx, double radiusy, double radiusz) {
        return Objects.requireNonNull(loc.getWorld()).getNearbyEntities(loc, radiusx, radiusy, radiusz);
    }

    public static boolean isTherePlayerNearby(Entity entity) {
        return entity.getNearbyEntities(20, 255, 20).stream().anyMatch(ent -> ent instanceof Player);
    }

    public static boolean isTherePlayerNearby(Location location) {
        return getEntitiesAroundLocation(location, 20, 255, 20).stream().anyMatch(ent -> ent instanceof Player);
    }

    public static Player getNearestPlayer(Entity entity) {
        return entity.getWorld().getPlayers()
                .stream()
                .map(Player.class::cast)
                .min(Comparator.comparingDouble(player -> entity.getLocation().distance(player.getLocation())))
                .orElse(null);
    }

    public static <T> Stream<T> getEntitiesInAllWorlds(Predicate<? super Entity> filter, Function<? super Entity, ? extends T> mapper) {
        return getEntitiesInAllWorlds(filter)
                .map(mapper);
    }

    public static Stream<Entity> getEntitiesInAllWorlds(Predicate<? super Entity> filter) {
        return getEntitiesInAllWorlds()
                .filter(filter);
    }

    public static Stream<Entity> getEntitiesInAllWorlds() {
        return Bukkit.getWorlds().stream()
                .flatMap(world -> world.getEntities().stream());
    }
}
