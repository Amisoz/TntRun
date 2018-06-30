package fr.amisoz.tntrun.game.task;

import fr.amisoz.tntrun.TntRun;
import fr.amisoz.tntrun.game.GameManager;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.scheduler.BukkitRunnable;
import java.util.Map;

public class BlockTask extends BukkitRunnable {

    private TntRun tntRun;

    public BlockTask(TntRun tntRun) {
        this.tntRun = tntRun;
    }

    @Override
    public void run(){
        for(Map.Entry<Block, Long> block : GameManager.blockWalked.entrySet()) {
            Long timeInMillis = System.currentTimeMillis();
            if((timeInMillis - block.getValue()) >= 200){
                block.getKey().setType(Material.AIR);
                GameManager.blockWalked.remove(block);
            }
        }
    }
}
