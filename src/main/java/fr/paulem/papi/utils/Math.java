package fr.paulem.papi.utils;

import org.bukkit.World;

import java.util.concurrent.ThreadLocalRandom;

public class Math {
    public static int RandomBtw(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    public static double RandomBtw(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max + 1);
    }

    public static boolean isDay(World world) {
        long time = world.getTime();

        return time < 12300 || time > 23850;
    }
}
