package fr.amisoz.tntrun.listener;

import fr.amisoz.tntrun.TntRun;
import fr.amisoz.tntrun.game.GameManager;
import fr.amisoz.tntrun.game.GameState;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    private TntRun tntRun;

    public PlayerMoveListener(TntRun tntRun) {
        this.tntRun = tntRun;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        if(TntRun.getGameManager().getGameState() == GameState.GAME){
            Block block = player.getLocation().subtract(0, 1, 0).getBlock();
            if(block.getType().equals(Material.TNT)){
                GameManager.blockWalked.put(block, System.currentTimeMillis());
            }

            if(player.getLocation().getBlockY() <= -5){
                tntRun.removePlayerInGame(player);
                Bukkit.broadcastMessage(tntRun.prefix + player.getName() + " est mort !");
                player.kickPlayer(ChatColor.RED + "Vous avez perdu !");
            }
        }
    }
}
