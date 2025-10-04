package uk.co.nikodem.dFInhibition.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import uk.co.nikodem.dFInhibition.DFInhibition;
import uk.co.nikodem.dFInhibition.Handlers.ConfigManager;

import java.util.logging.Level;

public class PrepSMP implements CommandExecutor {
    public final DFInhibition plugin;
    public final FileConfiguration config;

    public PrepSMP(DFInhibition plugin) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Only the console is permitted to run this command!"));
            return true;
        }

        ConfigManager.write("locked", true);
        ConfigManager.write("pvp", true);
        ConfigManager.write("nether", true);
        ConfigManager.write("end", true);

        plugin.getLogger().log(Level.INFO, "#### >>>> remember to reduce the world border!!!!");
        Bukkit.dispatchCommand(commandSender, "kill @e[type=!minecraft:player,type=!minecraft:villager]");
        Bukkit.dispatchCommand(commandSender, "kill @e[type=!minecraft:player,type=!minecraft:villager]");
        Bukkit.dispatchCommand(commandSender, "xp set @a 0 points");
        Bukkit.dispatchCommand(commandSender, "xp set @a 0 levels");
        Bukkit.dispatchCommand(commandSender, "clear @a");
        Bukkit.dispatchCommand(commandSender, "effect give @a minecraft:regeneration 1 255");
        Bukkit.dispatchCommand(commandSender, "effect give @a minecraft:saturation 1 255");
        Bukkit.dispatchCommand(commandSender, "effect give @a minecraft:resistance 5 255");
        // tp to world origin, not spawn - //Bukkit.dispatchCommand(commandSender, "execute in minecraft:overworld run tp @a 0 100 0");
        Bukkit.dispatchCommand(commandSender, "time set day");
        Bukkit.dispatchCommand(commandSender, "weather clear");
        Bukkit.dispatchCommand(commandSender, "gamerule keepInventory true");
        Bukkit.dispatchCommand(commandSender, "gamerule doDaylightCycle false");
        Bukkit.dispatchCommand(commandSender, "gamerule doWeatherCycle false");

        commandSender.sendMessage("Prepped");
        return true;
    }
}
