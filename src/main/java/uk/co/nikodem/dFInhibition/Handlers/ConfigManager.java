package uk.co.nikodem.dFInhibition.Handlers;

import uk.co.nikodem.dFInhibition.DFInhibition;

public class ConfigManager {
    private static DFInhibition plugin;

    private static void assignPlugin(DFInhibition plugin) {
        ConfigManager.plugin = plugin;
    }

    private static void setDefaults() {
        plugin.getConfig().addDefault("locked", true);
        plugin.getConfig().addDefault("pvp", true);
        plugin.getConfig().addDefault("nether", true);
        plugin.getConfig().addDefault("end", true);
        plugin.getConfig().options().copyDefaults(true);
        plugin.saveConfig();
    }

    public static void setup(DFInhibition plugin) {
        assignPlugin(plugin);
        setDefaults();
    }

    public static void write(String path, boolean data) {
        plugin.getConfig().set(path, data);
        plugin.saveConfig();
    }

    public static boolean get(String path) {
        return plugin.getConfig().getBoolean(path);
    }
}
