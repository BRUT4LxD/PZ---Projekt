package Pieces;

public interface Move {
    void moveTo();
    void showHint();
    void hideHint();
    void calculateLegalMoves();
    boolean checkIfLegalMove(int x, int y);

}
