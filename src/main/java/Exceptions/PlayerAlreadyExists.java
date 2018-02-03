package Exceptions;

import Appearance.Gui;
import Misc.Utils;

public class PlayerAlreadyExists extends Exception {

    public PlayerAlreadyExists() {
        super("Player already exists");
        Utils.logger.error("Ivalid login");
        Gui.createInfoMessage("Player already exists", "REGISTRATION ERROR");
    }
}
