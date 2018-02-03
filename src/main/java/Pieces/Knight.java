package Pieces;

import GameTable.Table;
import GameTable.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {
    public Knight(PieceType pieceType, JLabel image, Point position, PlayerColor playerColor, Table table) {
        super(pieceType, image, position, playerColor, table);
        LEGAL_MOVES = new ArrayList<Point>();
        TAKE_MOVES = new ArrayList<Point>();
        calculateLegalMoves();

    }

    public boolean checkIfLegalMove(int x, int y) {
        Point destination = new Point(x,y);
        Point currentPos = new Point();


        for( Point p: LEGAL_MOVES){
            currentPos.setLocation(position.getX() + 1 + p.getX(), position.getY() + 1 + p.getY());

            if(destination.equals(currentPos))return true;
        }
        return false;
    }
    public void hideHint() {

    }

    private final static Point[] CANDIDATE_MOVES = {
            new Point(-1,-2),
            new Point(-2,-1),
            new Point(1,-2),
            new Point(2,-1),
            new Point(-1,2),
            new Point(-2,1),
            new Point(1,2),
            new Point(2,1)};


    public void calculateLegalMoves(){
        clearThreatenTiles();
        LEGAL_MOVES.clear();
        for( Point p: CANDIDATE_MOVES){
            if(checkIfInBoardMove(p)){
                checkTileOccupation(p);
            }
        }
        showHint();
    }
    private boolean checkIfInBoardMove(Point p){
        if(position.getX() + p.getX() < 0 || position.getX() + p.getX() > 7) return false;

        if(position.getY() + p.getY() < 0 || position.getY() + p.getY() > 7) return false;

        return true;
    }
    private void checkTileOccupation(Point p){
        Tile t = this.table.board.board[(int) position.getY() + (int) p.getY()][(int) position.getX() + (int) p.getX()];

        if(this.playerColor == PlayerColor.Black){
            this.table.board.tilesAttackedByBlack[(int) position.getY() + (int) p.getY()][(int) position.getX() + (int) p.getX()] = true;
        }else{
            this.table.board.tilesAttackedByEnemy[(int) position.getY() + (int) p.getY()][(int) position.getX() + (int) p.getX()] = true;
        }

        threatTiles[(int) position.getY() + (int) p.getY()][(int) position.getX() + (int) p.getX()] = true;
        if(t.isOccupied()){
            if(t.getPiece().getPlayerColor() != this.playerColor){
                TAKE_MOVES.add(p);
                LEGAL_MOVES.add(p);
                return;
            }
            return;

        }
        LEGAL_MOVES.add(p);
    }

    public void moveTo() {

    }

    public void showHint() {

    }

}
