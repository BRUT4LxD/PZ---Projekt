package Exceptions;

import Misc.Utils;

public class MoveAfterTimeException extends Exception {

    public MoveAfterTimeException(){
        super("You cannot move after the time is over");
        Utils.logger.info(Utils.playerColor.getName() + " moved after time exceded");
    }

}
