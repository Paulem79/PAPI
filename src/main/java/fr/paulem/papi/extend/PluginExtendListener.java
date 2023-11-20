package fr.paulem.papi.extend;

import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * listener
 */
public class PluginExtendListener extends PluginExtend implements Listener {
    public PluginExtendListener(JavaPlugin main){
        super(main);
    }
}
