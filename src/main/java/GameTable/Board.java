package GameTable;

import Appearance.Skin;
import Misc.Utils;

import javax.swing.*;
import java.awt.*;

public class Board extends JPanel {

    public static Tile[][] board;
    public static boolean[][] tilesAttackedByEnemy;
    public static boolean[][] tilesAttackedByBlack;

    public Board() {

        tilesAttackedByBlack = new boolean[8][8];
        tilesAttackedByEnemy = new boolean[8][8];
        board = new Tile[8][8];
        for( int i = 0 ;i < 8 ; i++){
            for( int j = 0 ; j < 8 ; j++){
                tilesAttackedByEnemy[i][j] = false;
                tilesAttackedByBlack[i][j] = false;
                JPanel square = new JPanel();
                square.setSize(new Dimension(Utils.TILE_WIDTH, Utils.TILE_WIDTH));
                if((i+j) % 2 == 0 )
                    square.setBackground(Skin.white);
                else
                    square.setBackground(Skin.black);


                board[i][j] = new Tile(square, false );
            }
        }
    }

    public Tile[][]getBoardTiles() {
        return board;
    }

    public void setBoardTiles(Tile[][] boardTiles) {
        this.board = boardTiles;
    }
    private void setPieces(){

    }
    public void clearIfAttackedTiles(){
        for(int i = 0; i < tilesAttackedByEnemy.length; i++){
            for(int j = 0; j < tilesAttackedByEnemy[i].length; j++){
                tilesAttackedByEnemy[i][j] = false;
                tilesAttackedByBlack[i][j] = false;
            }
        }
    }

    public void printThreatenTiles(){
        System.out.println(" Positions attacked by White: ");
        for(int i = 0; i < tilesAttackedByEnemy.length; i++){
            for(int j = 0; j < tilesAttackedByEnemy[i].length; j++){
                if(tilesAttackedByEnemy[i][j]){
                    System.out.println(j+1 + " " + (i+1));
                }
            }
        }
        System.out.println(" Positions attacked by Black: ");
        for(int i = 0 ;i < tilesAttackedByBlack.length;i++){
            for( int j  = 0 ;  j < tilesAttackedByBlack[i].length; j++){
                if(tilesAttackedByBlack[i][j]){
                    System.out.println(j+1 + " " + (i+1));
                }
            }
        }
    }
    public void printAvailableTiles(){
        System.out.println("AVAILABLE SQUARES");
        for(int i = 0; i < tilesAttackedByEnemy.length; i++){
            for(int j = 0; j < tilesAttackedByEnemy[i].length; j++){
                if(!tilesAttackedByEnemy[i][j]){
                    System.out.println(j+1 + " " + (i+1));
                }
            }
        }
    }
    public void flipBoard(){

    }
}
