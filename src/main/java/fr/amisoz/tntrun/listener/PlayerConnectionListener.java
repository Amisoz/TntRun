package fr.amisoz.tntrun.listener;

import fr.amisoz.tntrun.TntRun;
import fr.amisoz.tntrun.game.GameState;
import fr.amisoz.tntrun.game.task.StartTask;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerConnectionListener implements Listener {

    private TntRun tntRun;

    public PlayerConnectionListener(TntRun tntRun) {
        this.tntRun = tntRun;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();
        tntRun.addPlayerInGame(player);
        player.teleport(new Location(Bukkit.getWorlds().get(0), 0, 10, 0));
        player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
        player.setFoodLevel(20);
        int slotsActuel = tntRun.getPlayerInGame().size();
        event.setJoinMessage(tntRun.prefix + player.getName() + ChatColor.GRAY  +" a rejoint la partie. " + ChatColor.YELLOW + "(" + slotsActuel + "/" + tntRun.getSlots() + ")");
        if(slotsActuel == tntRun.getSlots()){
            TntRun.getGameManager().setGameState(GameState.LOADING);
            tntRun.logToConsole("GameState : LOADING");
            Bukkit.broadcastMessage(tntRun.prefix + "La partie va démarrée dans " + tntRun.startDuration + " secondes.");
            tntRun.startTask = new StartTask(tntRun);
            tntRun.startTask.runTaskTimer(tntRun, 0, 20);
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        tntRun.removePlayerInGame(player);
        int slotsActuel = (Bukkit.getOnlinePlayers().size() -1);
        if(TntRun.getGameManager().getGameState() == GameState.GAME){
            if(slotsActuel == 1){
                Bukkit.getScheduler().cancelAllTasks();
                TntRun.getGameManager().setGameState(GameState.FINISH);
                Bukkit.getOnlinePlayers().forEach(winner -> winner.kickPlayer("§aVous avez gagné !"));
            }
        }
        event.setQuitMessage(tntRun.prefix + player.getName() + ChatColor.GRAY  +" a quitté la partie. " + ChatColor.YELLOW + "(" + slotsActuel + "/" + tntRun.getSlots() + ")");
        if(TntRun.getGameManager().getGameState() == GameState.LOADING) {
            tntRun.startTask.cancel();
            TntRun.getGameManager().setGameState(GameState.WAITING);
            Bukkit.broadcastMessage(tntRun.prefix + "Attente de joueur supplémentaire...");
        }
    }

    @EventHandler
    public void onPreLogin(AsyncPlayerPreLoginEvent event){
        if(!TntRun.getGameManager().getGameState().isJoinable()){
            event.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
            event.setKickMessage(ChatColor.RED + "Vous ne pouvez pas rejoindre le jeu.");
        }
    }
}
