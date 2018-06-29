package fr.amisoz.tntrun.game;

public enum GameState {

    WAITING(true),
    LOADING(false),
    GAME(false),
    FINISH(false);

    private boolean isJoinable;

    GameState(boolean isJoinable) {
        this.isJoinable = isJoinable;
    }

    public boolean isJoinable() {
        return isJoinable;
    }
}
