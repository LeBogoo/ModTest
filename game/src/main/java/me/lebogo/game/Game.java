package me.lebogo.game;

import me.lebogo.game.modding.ModLoader;

public class Game {
    private final ModLoader modLoader;
    private int gameCounter = 0;
    private boolean running = true;

    public Game() {
        modLoader = new ModLoader("mods", this);
        modLoader.load();
    }

    public void run() {
        while (running) {
            try {
                update();

            } catch (InterruptedException ignored) {
            }
        }
    }

    public void stop() {
        running = false;
    }

    private void update() throws InterruptedException {
        System.out.println("GAME: Updating Game - Counter is currently " + gameCounter);
        modLoader.update();

        Thread.sleep(1000);
    }

    public int getGameCounter() {
        return gameCounter;
    }

    public void setGameCounter(int gameCounter) {
        this.gameCounter = gameCounter;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
