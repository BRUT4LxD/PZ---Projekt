package Exceptions;

import Appearance.Gui;
import Misc.Utils;

public class PlayerNotFound extends Exception {

    public PlayerNotFound() {
        super("Player not found!");
        Utils.logger.info("Inputed player not found");
        Gui.createInfoMessage("Invalid player name or password!", "LOGIN ERROR");
    }
}
