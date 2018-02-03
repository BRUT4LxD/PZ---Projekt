package Exceptions;

import Appearance.Gui;
import Misc.Utils;

public class PlayerAlreadyExistsException extends Exception {

    public PlayerAlreadyExistsException() {
        super("Player already exists");
        Utils.logger.error("Ivalid login");
        Gui.createInfoMessage("Player already exists", "REGISTRATION ERROR");
    }
}
