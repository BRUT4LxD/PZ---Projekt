package Exceptions;

import Misc.Utils;

public class IllegalMoveException extends Exception  {
    public IllegalMoveException() {
        super("Illegal move!");
        Utils.logger.info(Utils.playerColor.getName() + " made illegal move");
    }
}
