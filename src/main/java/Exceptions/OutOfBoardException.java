package Exceptions;

import Misc.Utils;

public class OutOfBoardException extends Exception  {
    public OutOfBoardException() {
        super("You cannot step out of the chess board!");
        Utils.logger.info(Utils.playerColor.getName() + " wanted to step out of the chessboard");
    }
}
