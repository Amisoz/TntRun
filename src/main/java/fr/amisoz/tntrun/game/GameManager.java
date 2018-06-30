package fr.amisoz.tntrun.game;

import org.bukkit.block.Block;

import java.util.HashMap;

public class GameManager {

    private GameState gameState;
    public static HashMap<Block, Long> blockWalked = new HashMap<>();

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }
}
