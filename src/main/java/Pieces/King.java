package Pieces;

import Exceptions.OnCheckException;
import GameTable.Table;
import GameTable.Tile;
import Misc.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class King extends Piece {


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
    public King(PieceType pieceType, final JLabel image, final Point position, PlayerColor playerColor, final Table table) {
        super(pieceType, image, position, playerColor, table);
        LEGAL_MOVES = new ArrayList<Point>();
        TAKE_MOVES = new ArrayList<Point>();

        image.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {

            }

            public void mousePressed(MouseEvent e) {
                calculateLegalMoves();
                previousPosition = position;
                isPressed = !isPressed;
            }

            public void mouseReleased(MouseEvent e) {
                int x = (image.getX() + image.getWidth()/2)/ Utils.TILE_WIDTH;
                int y = (image.getY()+ image.getWidth()/2)/ Utils.TILE_WIDTH;


                if(checkIfLegalMove(x,y)){
                    table.board.board[(int)position.getY()][(int)position.getX()].setOccupied(false);
                    position.setLocation(x-1,y-1);
                    image.setLocation((((int)image.getX() + image.getWidth()/2)/ Utils.TILE_WIDTH) * Utils.TILE_WIDTH, (((int)image.getY()+ image.getWidth()/2)/ Utils.TILE_WIDTH) * Utils.TILE_WIDTH);
                    previousPosition = position;
                    table.board.board[x - 1][y - 1].setOccupied(true);
                }
                else
                {
                    image.setLocation(((int)position.getX() + 1) * Utils.TILE_WIDTH, ((int)position.getY() + 1)* Utils.TILE_WIDTH);

                }
            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {

            }
        });
    }
    public final static Point[] CANDIDATE_MOVES = {
            new Point(-1,-1), new Point(0,-1), new Point(1,-1),
            new Point(-1,0),                         new Point(1,0),
            new Point(-1,1),   new Point(0,1), new Point(1,1),
    };

    public static Point[] CASTLE_MOVE = {
            new Point(-2,0), new Point(2,0)
    };

    public void calculateLegalMoves() {
        LEGAL_MOVES.clear();
        clearThreatenTiles();
        for( Point p: CANDIDATE_MOVES){
            if(checkIfInBoardMove(p)){
                if(!checkIfTileThreat(p)){
                    checkTileOccupation(p);
                }
            }
        }
        // CASTLE KINGSIDE

        boolean rookMoved = false;
        if(playerColor == PlayerColor.Black){
            rookMoved = table.blackPieces.get(0).wasMoved;
        }else{
            rookMoved = table.whitePieces.get(0).wasMoved;
        }

        if(!wasMoved && !rookMoved){
            if(checkIfInBoardMove(CANDIDATE_MOVES[3]) && checkIfInBoardMove(CASTLE_MOVE[0])) {
                if (!checkIfTileThreat(CANDIDATE_MOVES[3]) && !checkIfTileThreat(CASTLE_MOVE[0])) {
                    if (!checkTileOccupation(CANDIDATE_MOVES[3]) && !checkTileOccupation(CASTLE_MOVE[0])) {
                        LEGAL_MOVES.add(CASTLE_MOVE[0]);
                    }
                }
            }
        }
        // CASTLE QUEENSIDE

        if(playerColor == PlayerColor.Black){
            rookMoved = table.blackPieces.get(7).wasMoved;
        }else{
            rookMoved = table.whitePieces.get(7).wasMoved;
        }
        if(!wasMoved && !rookMoved){
            if(checkIfInBoardMove(CANDIDATE_MOVES[4]) && checkIfInBoardMove(CASTLE_MOVE[1])) {
                if (!checkIfTileThreat(CANDIDATE_MOVES[4]) && !checkIfTileThreat(CASTLE_MOVE[1])) {
                    if (!checkTileOccupation(CANDIDATE_MOVES[4]) && !checkTileOccupation(CASTLE_MOVE[1]) && checkTileOccupation(new Point(3,0))) {
                        LEGAL_MOVES.add(CASTLE_MOVE[1]);
                    }
                }
            }
        }

        showHint();

    }
    private boolean checkIfInBoardMove(Point p){
        if(position.getX() + p.getX() < 0 || position.getX() + p.getX() > 7) return false;

        if(position.getY() + p.getY() < 0 || position.getY() + p.getY() > 7) return false;

        return true;
    }
    private boolean checkTileOccupation(Point p){
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
            }
            return true;
        }
        LEGAL_MOVES.add(p);

        return false;
    }
    private boolean checkIfTileThreat(Point p){
        int x = ( int ) (p.getX() + this.position.getX());
        int y = ( int ) (p.getY() + this.position.getY());
        boolean isThreat;
        if(playerColor == PlayerColor.White){
            isThreat = this.table.board.tilesAttackedByBlack[y][x];
        }
        else{
            isThreat = this.table.board.tilesAttackedByEnemy[y][x];
        }


        if(isThreat){
            try{
                throw new OnCheckException();
            } catch (OnCheckException e) {
            }
        }

        return isThreat;
    }

    public void moveTo() {

    }

    public void showHint() {

    }

}
