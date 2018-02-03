package Exceptions;

import Misc.Utils;

public class OutOfForkException extends Exception  {
    public OutOfForkException() {
        super("You cannot move when you are forked like this!");
        Utils.logger.info(Utils.playerColor.getName() + " wanted to move on fork");
    }
}
