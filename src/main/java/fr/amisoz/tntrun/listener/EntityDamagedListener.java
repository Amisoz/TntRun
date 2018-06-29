package fr.amisoz.tntrun.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class EntityDamagedListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event){
        event.setCancelled(true);
    }
}
