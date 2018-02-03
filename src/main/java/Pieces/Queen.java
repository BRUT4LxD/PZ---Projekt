package Pieces;

import GameTable.Table;
import GameTable.Tile;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece {


    //ROOK + BISHOP

    public Queen(PieceType pieceType, JLabel image, Point position, PlayerColor playerColor, Table table) {
        super(pieceType, image, position, playerColor, table);
        LEGAL_MOVES = new ArrayList<Point>();
        TAKE_MOVES = new ArrayList<Point>();

    }

    public void hideHint() {

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
    public void moveTo() {

    }

    public void showHint() {

    }

    public void calculateLegalMoves(){

        LEGAL_MOVES.clear();

        clearThreatenTiles();
        //BISHOP MOVES
        boolean[] directions = {true,true,true,true};

        int occupation = 0;
        for(int i = 0; i < Bishop.CANDIDATE_MOVES.length; i++){

            if(directions[i%4]){
                if(checkIfInBoardMove(Bishop.CANDIDATE_MOVES[i])){
                    occupation = checkTileOccupation(Bishop.CANDIDATE_MOVES[i]);
                    if(occupation != 3){
                        directions[i%4] = false;
                    }
                }
                else{
                    directions[i%4] = false;
                }
            }
        }

        //ROOK MOVES


        occupation = 0;
        for( int i = 0 ; i < directions.length; i++ ){
            directions[i] = true;
        }
        for( int i = 0 ; i < Rook.CANDIDATE_MOVES.length; i++){

            if(directions[i%4]){
                if(checkIfInBoardMove(Rook.CANDIDATE_MOVES[i])){
                    occupation = checkTileOccupation(Rook.CANDIDATE_MOVES[i]);
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

    private int checkTileOccupation(Point p) {

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
        if(t.isOccupied()){
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
