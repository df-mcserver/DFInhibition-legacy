package uk.co.nikodem.dFInhibition.Handlers;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class InhibitManager {
    public static void userInteractError(Player plr) {
//        plr.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4The SMP hasn't started yet!"));
    }

    public static void userCommandError(Player plr) {
        plr.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4Commands are currently disabled!"));
    }

    public static void userPvpError(Player plr) {
        plr.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4PVP is currently disabled!"));
    }

    public static void userNetherError(Player plr) {
        plr.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4The Nether is currently disabled!"));
    }

    public static void userEndError(Player plr) {
        plr.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4The End is currently disabled!"));
    }

    public static void userJoinMessage(Player plr) {
        plr.sendMessage(ChatColor.translateAlternateColorCodes('&', "&3The SMP is currently in a Read-only state, as the SMP hasn't started yet."));
    }

    public static boolean isWorldLocked() {
        return ConfigManager.get("locked");
    }

    public static boolean isPvpLocked() {
        if (isWorldLocked()) return true;
        return ConfigManager.get("pvp");
    }

    public static boolean isNetherLocked() {
        if (isWorldLocked()) return true;
        return ConfigManager.get("nether");
    }

    public static boolean isEndLocked() {
        if (isWorldLocked()) return true;
        return ConfigManager.get("end");
    }
}
