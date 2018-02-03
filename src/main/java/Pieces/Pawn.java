package Pieces;

import GameTable.HintCircle;
import GameTable.Table;
import GameTable.Tile;
import Misc.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private final static Point[] CANDIDATE_TAKE_MOVES_PLAYER = {
            new Point(1,-1),
            new Point(-1,-1)
    };
    private final static Point[] CANDIDATE_FORWARD_MOVES_PLAYER = {
            new Point(0,-1),
            new Point(0,-2)
    };
    private final static Point[] CANDIDATE_TAKE_MOVES_ENEMY = {
            new Point(1,1),
            new Point(-1,1)
    };
    private final static Point[] CANDIDATE_FORWARD_MOVES_ENEMY = {
            new Point(0,1),
            new Point(0,2)
    };


    private List<HintCircle> hints ;

    public Pawn(PieceType pieceType, JLabel image, Point position, PlayerColor playerColor, Table table) {
        super(pieceType, image, position, playerColor, table);

        LEGAL_MOVES = new ArrayList<Point>();
        TAKE_MOVES = new ArrayList<Point>();
        calculateLegalMoves();

    }

    public void calculateLegalMoves(){
        LEGAL_MOVES.clear();
        clearThreatenTiles();
        if(playerColor == PlayerColor.Black){
            calculateLegalMovesForPlayer();
        }
        else{
            calculateLegalMovesForEnemy();
        }
    }

    private void calculateLegalMovesForPlayer(){
        boolean forward = true;
        for( int i = 0 ; i <CANDIDATE_TAKE_MOVES_PLAYER.length; i++){
            if(checkIfInBoardMove(CANDIDATE_TAKE_MOVES_PLAYER[i])){
                checkTileOccupation(CANDIDATE_TAKE_MOVES_PLAYER[i],true);
            }
        }
        Point p = CANDIDATE_FORWARD_MOVES_PLAYER[0];
        if(checkIfInBoardMove(CANDIDATE_FORWARD_MOVES_PLAYER[0])){
            Tile t = this.table.board.board[(int) position.getY() + (int) p.getY()][(int) position.getX()];
            if(t.isOccupied()){
                //System.out.println("TILE : " + position.getX() + " " + (position.getY() + p.getY()) + " is occupied!");
                forward = false;
            }
            else{
                LEGAL_MOVES.add(p);
            }
        }
        if(forward){

            if( position.getY() == 6){
                p = CANDIDATE_FORWARD_MOVES_PLAYER[1];
                Tile t = this.table.board.board[(int) position.getY() + (int) p.getY()][(int) position.getX() + (int) p.getX()];
                if(!t.isOccupied())
                    LEGAL_MOVES.add(p);
            }
        }
    }

    private void calculateLegalMovesForEnemy(){
        boolean forward = true;
        for( int i = 0 ; i <CANDIDATE_TAKE_MOVES_ENEMY.length; i++){
            if(checkIfInBoardMove(CANDIDATE_TAKE_MOVES_ENEMY[i])){
                checkTileOccupation(CANDIDATE_TAKE_MOVES_ENEMY[i],true);
            }
        }
        Point p = CANDIDATE_FORWARD_MOVES_ENEMY[0];
        if(checkIfInBoardMove(CANDIDATE_FORWARD_MOVES_ENEMY[0])){
            Tile t = this.table.board.board[(int) position.getY() + (int) p.getY()][(int) position.getX()];
            if(t.isOccupied()){
                forward = false;
            }
            else{
                LEGAL_MOVES.add(p);
            }
        }
        if(forward){

            if( position.getY() == 1){
                p = CANDIDATE_FORWARD_MOVES_ENEMY[1];
                Tile t = this.table.board.board[(int) position.getY() + (int) p.getY()][(int) position.getX() + (int) p.getX()];
                if(!t.isOccupied())
                    LEGAL_MOVES.add(p);
            }
        }
    }

    private boolean checkIfInBoardMove(Point p){
        if(position.getX() + p.getX() < 0 || position.getX() + p.getX() > 7) return false;

        if(position.getY() + p.getY() < 0 || position.getY() + p.getY() > 7) return false;

        return true;
    }

    private int checkTileOccupation(Point p, boolean toTake){

        //returns 1 if tile is occupied by enemy
        //returns 2 if tile is occupied by ally
        //returns 3 if tile is not occupied

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
                return 1;
            }
            return 2;
        }
        if(!toTake)
        LEGAL_MOVES.add(p);

        return 3;
    }

    public void moveTo() {

    }

    public void hideHint() {
        hints.clear();
    }

    public void showHint() {

        hints = new ArrayList<HintCircle>();
        for( int i = 0 ; i < LEGAL_MOVES.size(); i++){
            if(LEGAL_MOVES.get(i).equals(TAKE_MOVES.get(i)) ){
                continue;
            }
            Point p = new Point();
            p.setLocation(position.getX() + LEGAL_MOVES.get(i).getX() + 5, position.getY() + LEGAL_MOVES.get(i).getY() + 1);

            hints.add(new HintCircle(new Point((int) p.getX()* Utils.TILE_WIDTH, (int) p.getY()* Utils.TILE_WIDTH)));
        }
        for( HintCircle h : hints)
            Table.gameTable.add(h);

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
}
