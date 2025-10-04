package uk.co.nikodem.dFInhibition.Commands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import uk.co.nikodem.dFInhibition.DFInhibition;
import uk.co.nikodem.dFInhibition.Handlers.ConfigManager;

import java.util.Objects;

public class EnableFeature implements CommandExecutor {
    public final DFInhibition plugin;
    public final FileConfiguration config;
    public final String featureString;

    public EnableFeature(DFInhibition plugin, String featureString) {
        this.plugin = plugin;
        this.config = plugin.getConfig();
        this.featureString = featureString;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (commandSender instanceof Player) {
            commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Only the console is permitted to run this command!"));
            return true;
        }

        if (Objects.equals(featureString, "inv")) {
            Bukkit.dispatchCommand(commandSender, "gamerule keepInventory false");
        } else if (Objects.equals(featureString, "wb1")) {
            Bukkit.dispatchCommand(commandSender, "worldborder set 5000 10");
        } else if (Objects.equals(featureString, "wb2")) {
            Bukkit.dispatchCommand(commandSender, "worldborder set 7000 10");
        } else if (Objects.equals(featureString, "wb3")) {
            Bukkit.dispatchCommand(commandSender, "worldborder set 10000 10");
        } else {
            ConfigManager.write(featureString, false);
        }

        String title = "error occured lol ¯\\_(ツ)_/¯";
        String subtitle = "good luck :)";

        if (featureString.equals("pvp")) {
            title = "PVP has been enabled!";
        } else if (featureString.equals("nether")) {
            title = "The Nether has been enabled!";
        } else if (featureString.equals("end")) {
            title = "The End has been enabled!";
        } else if (featureString.equals("inv")) {
            title = "Keep Inventory has been disabled!";
        } else if (featureString.equals("wb1")) {
            title = "The world border has expanded to 5000 blocks!";
        } else if (featureString.equals("wb2")) {
            title = "The world border has expanded to 7000 blocks!";
        } else if (featureString.equals("wb3")) {
            title = "The world border has expanded to 10000 blocks!";
        }

        for(Player plr : Bukkit.getOnlinePlayers()){
            plr.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2"+title));
            plr.playSound(plr, Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
            plr.sendTitle(ChatColor.translateAlternateColorCodes('&', title), subtitle);
        }

        return true;
    }
}
