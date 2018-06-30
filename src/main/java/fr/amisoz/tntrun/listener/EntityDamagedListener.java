package fr.amisoz.tntrun.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamagedListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent event){
        event.setCancelled(true);
    }
}
