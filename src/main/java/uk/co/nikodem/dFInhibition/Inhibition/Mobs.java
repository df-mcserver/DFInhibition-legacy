package uk.co.nikodem.dFInhibition.Inhibition;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;

import static uk.co.nikodem.dFInhibition.Handlers.InhibitManager.*;

public class Mobs implements Listener {
    @EventHandler
    public void MobSpawn(EntitySpawnEvent e) {
        if (!isWorldLocked()) return;
        e.setCancelled(true);
    }
}
