package uk.co.nikodem.dFInhibition.Inhibition;

import org.bukkit.World;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import uk.co.nikodem.dFInhibition.Handlers.InhibitManager;

import java.util.Objects;

import static uk.co.nikodem.dFInhibition.Handlers.InhibitManager.*;

public class Player implements Listener {
    @EventHandler
    public void Interact(PlayerInteractEvent e) {
        if (!isWorldLocked()) return;
        userInteractError(e.getPlayer());
        e.setCancelled(true);
    }

    @EventHandler
    public void DropItem(PlayerDropItemEvent e) {
        if (!isWorldLocked()) return;
        userInteractError(e.getPlayer());
        e.setCancelled(true);
    }

    @EventHandler
    public void SwapItems(PlayerSwapHandItemsEvent e) {
        if (!isWorldLocked()) return;
        userInteractError(e.getPlayer());
        e.setCancelled(true);
    }

    @EventHandler
    public void EnterPortal(PlayerPortalEvent e) {
        if (isWorldLocked()) {
            userInteractError(e.getPlayer());
            e.setCancelled(true);
            return;
        }
        World.Environment environment = Objects.requireNonNull(Objects.requireNonNull(e.getTo()).getWorld()).getEnvironment();
         if ((environment.equals(World.Environment.NETHER) && isNetherLocked())) {
            userNetherError(e.getPlayer());
            e.setCancelled(true);
        } else if ((environment.equals(World.Environment.THE_END) && isEndLocked())) {
             userEndError(e.getPlayer());
             e.setCancelled(true);
         }
    }

    @EventHandler
    public void Pvp(EntityDamageByEntityEvent e) {
        if (e.getDamager().getType().equals(EntityType.PLAYER) && e.getEntityType().equals(EntityType.PLAYER)) {
            org.bukkit.entity.Player plr = (org.bukkit.entity.Player) e.getDamager();
            if (isWorldLocked()) {
                userInteractError(plr);
                e.setCancelled(true);
            } else if (isPvpLocked()) {
                userPvpError(plr);
                e.setCancelled(true);
            }
        } else if (isWorldLocked()) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void Damage(EntityDamageEvent e) {
        if (!isWorldLocked()) return;
        if (e.getEntity() instanceof org.bukkit.entity.Player plr) {
            userInteractError(plr);
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void Damage(EntityDamageByBlockEvent e) {
        if (!isWorldLocked()) return;
        if (e.getEntity() instanceof org.bukkit.entity.Player plr) {
            userInteractError(plr);
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void Food(FoodLevelChangeEvent e) {
        if (!isWorldLocked()) return;
        if (e.getEntity() instanceof org.bukkit.entity.Player plr) {
            userInteractError(plr);
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void Pickup(EntityPickupItemEvent e) {
        if (!isWorldLocked()) return;
        if (e.getEntity() instanceof org.bukkit.entity.Player plr) {
            userInteractError(plr);
        }
        e.setCancelled(true);
    }

    @EventHandler
    public void InformUser(PlayerJoinEvent e) {
        if (!isWorldLocked()) return;
        InhibitManager.userJoinMessage(e.getPlayer());
    }

    @EventHandler
    public void ExecuteCommand(PlayerCommandPreprocessEvent e) {
        if (!isWorldLocked()) return;
        if (e.getMessage().startsWith("/lobby")) return;
        if (e.getPlayer().isOp()) return;
        e.setCancelled(true);
        userCommandError(e.getPlayer());
    }
}
