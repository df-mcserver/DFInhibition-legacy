package uk.co.nikodem.dFInhibition;

import org.bukkit.plugin.java.JavaPlugin;
import uk.co.nikodem.dFInhibition.Commands.EnableFeature;
import uk.co.nikodem.dFInhibition.Commands.PrepSMP;
import uk.co.nikodem.dFInhibition.Commands.StartSMP;
import uk.co.nikodem.dFInhibition.Handlers.ConfigManager;
import uk.co.nikodem.dFInhibition.Handlers.InhibitManager;
import uk.co.nikodem.dFInhibition.Inhibition.Mobs;
import uk.co.nikodem.dFInhibition.Inhibition.Player;

import java.util.Objects;

public final class DFInhibition extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        ConfigManager.setup(this);

        createCommands();

        // inhibition
        getServer().getPluginManager().registerEvents(new Mobs(), this);
        getServer().getPluginManager().registerEvents(new Player(), this);
    }

    public void createCommands() {
        // starting
        Objects.requireNonNull(getCommand("startsmp")).setExecutor(new StartSMP(this));
        Objects.requireNonNull(getCommand("prepsmp")).setExecutor(new PrepSMP(this));

        // gradually enabling
        Objects.requireNonNull(getCommand("enablepvp")).setExecutor(new EnableFeature(this, "pvp"));
        Objects.requireNonNull(getCommand("enablenether")).setExecutor(new EnableFeature(this, "nether"));
        Objects.requireNonNull(getCommand("enableend")).setExecutor(new EnableFeature(this, "end"));
        Objects.requireNonNull(getCommand("disableinv")).setExecutor(new EnableFeature(this, "inv"));

        Objects.requireNonNull(getCommand("setwb1")).setExecutor(new EnableFeature(this, "wb1"));
        Objects.requireNonNull(getCommand("setwb2")).setExecutor(new EnableFeature(this, "wb2"));
        Objects.requireNonNull(getCommand("setwb3")).setExecutor(new EnableFeature(this, "wb3"));

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveConfig();
    }
}
