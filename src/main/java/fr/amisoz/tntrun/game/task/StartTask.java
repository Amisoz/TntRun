package fr.amisoz.tntrun.game.task;

import fr.amisoz.tntrun.TntRun;
import fr.amisoz.tntrun.game.GameState;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;

public class StartTask extends BukkitRunnable {

    private TntRun tntRun;
    private int startDuration;

    public StartTask(TntRun tntRun) {
        this.tntRun = tntRun;
        startDuration = tntRun.startDuration;
    }

    @Override
    public void run() {

        Bukkit.broadcastMessage(tntRun.prefix + "Lancement du jeu dans " + startDuration + " seconde" + ((startDuration == 1) ? ("") : ("s")));
        Bukkit.getOnlinePlayers().forEach(player -> player.playSound(player.getLocation(), Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f));

        if(startDuration == 0){
            cancel();
            Bukkit.broadcastMessage(tntRun.prefix + "Lancement du jeu. Bonne chance ! ");
            TntRun.getGameManager().setGameState(GameState.GAME);
        }

        startDuration--;
    }
}
