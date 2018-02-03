package Pieces;

import GameTable.Table;
import GameTable.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Bishop extends Piece {
    public Bishop(PieceType pieceType, JLabel image, Point position, PlayerColor playerColor, Table table) {
        super(pieceType, image, position, playerColor, table);
        LEGAL_MOVES = new ArrayList<Point>();
        TAKE_MOVES = new ArrayList<Point>();
    }

    public void hideHint() {

    }

    public final static Point[] CANDIDATE_MOVES = {
            new Point(1,1), new Point(1,-1), new Point(-1,1), new Point(-1,-1),
            new Point(2,2), new Point(2,-2), new Point(-2,2), new Point(-2,-2),
            new Point(3,3), new Point(3,-3), new Point(-3,3), new Point(-3,-3),
            new Point(4,4), new Point(4,-4), new Point(-4,4), new Point(-4,-4),
            new Point(5,5), new Point(5,-5), new Point(-5,5), new Point(-5,-5),
            new Point(6,6), new Point(6,-6), new Point(-6,6), new Point(-6,-6),
            new Point(7,7), new Point(7,-7), new Point(-7,7), new Point(-7,-7)
    };
    public void moveTo() {

    }

    public void showHint() {

    }
    public void calculateLegalMoves(){

        boolean[] directions = {true,true,true,true};

        clearThreatenTiles();
        LEGAL_MOVES.clear();
        int occupation = 0;
        for( int i = 0 ; i < CANDIDATE_MOVES.length; i++){

            if(directions[i%4]){
                if(checkIfInBoardMove(CANDIDATE_MOVES[i])){
                    occupation = checkTileOccupation(CANDIDATE_MOVES[i]);
                    if(occupation != 3){
                        directions[i%4] = false;
                    }
                }
                else{
                    directions[i%4] = false;
                }
            }
        }
        showHint();
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
    public int checkTileOccupation(Point p) {

        //returns 1 if tile is occupied by enemy
        //returns 2 if tile is occupied by ally
        //returns 3 if tile is not occupied

        if(this.playerColor == PlayerColor.Black){
            this.table.board.tilesAttackedByBlack[(int) position.getY() + (int) p.getY()][(int) position.getX() + (int) p.getX()] = true;
        }else{
            this.table.board.tilesAttackedByEnemy[(int) position.getY() + (int) p.getY()][(int) position.getX() + (int) p.getX()] = true;
        }
        threatTiles[(int) position.getY() + (int) p.getY()][(int) position.getX() + (int) p.getX()] = true;
        Tile t = this.table.board.board[(int) position.getY() + (int) p.getY()][(int) position.getX() + (int) p.getX()];
        int x = (int) position.getX() + (int) p.getX();
        int y = (int) position.getY() + (int) p.getY();
        if(t.isOccupied()){
            //System.out.println("Occupied : " + x + " "  + y );
            if(t.getPiece().getPlayerColor() != this.playerColor){
                TAKE_MOVES.add(p);
                LEGAL_MOVES.add(p);
                if(t.getPiece().pieceType == PieceType.King){
                    return 3;
                }

                return 1;
            }
            return 2;
        }
        LEGAL_MOVES.add(p);
        return 3;
    }

    private boolean checkIfInBoardMove(Point p){

        if(position.getX() + p.getX() < 0 || position.getX() + p.getX() > 7) return false;

        if(position.getY() + p.getY() < 0 || position.getY() + p.getY() > 7) return false;

        return true;

    }



}
