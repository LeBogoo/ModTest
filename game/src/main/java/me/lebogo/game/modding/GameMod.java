package me.lebogo.game.modding;

import me.lebogo.game.Game;

public interface GameMod {
    void init(Game game);

    void update();
}
