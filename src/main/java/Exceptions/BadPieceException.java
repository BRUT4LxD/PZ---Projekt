package Exceptions;

import Misc.Utils;

public class BadPieceException extends Exception {
    public BadPieceException() {
        super(Utils.playerColor.getName() + " touched wrong piece!");
        Utils.logger.info(Utils.playerColor.getName() + " touched wrong piece!");
    }
}
