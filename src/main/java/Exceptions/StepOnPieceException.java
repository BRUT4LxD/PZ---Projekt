package Exceptions;

import Misc.Utils;

public class StepOnPieceException extends Exception  {
    public StepOnPieceException() {
        super("You cannot step on your piece");
        Utils.logger.info(Utils.playerColor.getName() + " wanted to step on its piece");
    }
}
