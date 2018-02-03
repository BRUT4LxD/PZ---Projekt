package Exceptions;

import Appearance.Gui;
import Misc.Utils;

public class GameOverException extends Exception {
    public GameOverException(String who) {
        super("GAME OVER!!!" + who + " wins");
        Utils.logger.info(who + " wins the game!");
        Gui.createInfoMessage("GAME OVER!!! " + who + " wins!", "GAMEOVER");
    }
}
