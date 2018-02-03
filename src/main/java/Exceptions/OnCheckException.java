package Exceptions;

import Misc.Utils;

public class OnCheckException extends Exception  {
    public OnCheckException() {
        super("You cannot move when you are in check!");
        Utils.logger.info(Utils.playerColor.getName() + " tried to move on check");
    }

}
