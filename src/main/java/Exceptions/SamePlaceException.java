package Exceptions;

import Misc.Utils;

public class SamePlaceException extends Exception  {
    public SamePlaceException() {
        super("You cant move to the same place!");
        Utils.logger.info(Utils.playerColor.getName() + " wanted to step on same place");
    }
}
