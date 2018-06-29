package fr.amisoz.tntrun;

import fr.amisoz.tntrun.game.GameManager;
import fr.amisoz.tntrun.game.GameState;
import fr.amisoz.tntrun.listener.BreakBlockListener;
import fr.amisoz.tntrun.listener.EntityDamagedListener;
import fr.amisoz.tntrun.listener.FoodLevelChangeListener;
import fr.amisoz.tntrun.listener.PlayerConnectionListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class TntRun extends JavaPlugin {

    private static GameManager gameManager;
    private List<Player> playerInGame;
    public String prefix = ChatColor.GRAY + "[" + ChatColor.YELLOW + "TNTRUN" + ChatColor.GRAY + "] " + ChatColor.YELLOW;
    private int slots;
    public int startDuration = 10;

    @Override
    public void onEnable() {
        registerListener();
        playerInGame = new ArrayList<>();
        slots = Bukkit.getServer().getMaxPlayers();
        gameManager = new GameManager();

        gameManager.setGameState(GameState.WAITING);

        logToConsole("TntRun loaded.");
    }

    private void registerListener(){
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new FoodLevelChangeListener(), this);
        pluginManager.registerEvents(new EntityDamagedListener(), this);
        pluginManager.registerEvents(new PlayerConnectionListener(this), this);
        pluginManager.registerEvents(new BreakBlockListener(), this);
    }

    public int getSlots() {
        return slots;
    }

    public static GameManager getGameManager() {
        return gameManager;
    }

    public void logToConsole(String log){
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[TNTRUN - INFO] >> " + log);
    }

    public boolean isPlayerInGame(Player player) {
        return this.playerInGame.contains(player);
    }

    public void addPlayerInGame(Player player) {
        this.playerInGame.add(player);
    }

    public void removePlayerInGame(Player player){
        this.playerInGame.remove(player);
    }

    public List<Player> getPlayerInGame() {
        return playerInGame;
    }
}
