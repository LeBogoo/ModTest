package me.lebogo.game;


import me.lebogo.game.modding.GameMod;

public class Mod implements GameMod {
    private Game game;

    @Override
    public void init(Game game) {
        System.out.println("MOD: Initializing Mod");
        this.game = game;
    }

    @Override
    public void update() {
        int gameCounter = game.getGameCounter();
        game.setGameCounter(gameCounter + 1);

        System.out.println("MOD: Mod increased game counter to " + game.getGameCounter());
    }
}