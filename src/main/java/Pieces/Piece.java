package Pieces;

import javax.rmi.CORBA.Util;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Exceptions.*;
import GameTable.Field;
import GameTable.Table;
import GameTable.Tile;
import Misc.Utils;
import Windows.PromotionWindow;
import Windows.StartWindow;
import javafx.print.PageLayout;

public abstract class Piece extends Field implements Move {

    protected PieceType pieceType;
    protected JLabel image;
    protected Point position;
    protected Point previousPosition;
    protected PlayerColor playerColor;
    protected boolean isPressed;
    protected Table table;
    protected Piece thisPiece;
    protected List<Point> LEGAL_MOVES;
    protected List<Point> TAKE_MOVES;
    protected boolean taken = false;
    protected boolean wasMoved = false;
    protected boolean[][] threatTiles;

    public Piece(PieceType pieceType, final JLabel image, final Point position, final PlayerColor playerColor, final Table table){

        this.pieceType = pieceType;
        this.image = image;
        this.position = position;
        this.playerColor = playerColor;
        this.previousPosition = position;
        this.isPressed = false;
        this.table = table;
        this.thisPiece = this;
        this.threatTiles = new boolean[8][8];

        this.image.addMouseMotionListener(new MouseMotionListener() {
            public void mouseDragged(MouseEvent e) {
                if(taken) return;
                if(thisPiece.playerColor == Utils.playerColor)
                image.setLocation(image.getX() - image.getWidth()/2 +e.getX(), image.getY() - image.getWidth()/2 +e.getY());
            }

            public void mouseMoved(MouseEvent e) {}
        });

        this.image.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {}

            public void mousePressed(MouseEvent e) {
                try{
                    if(taken) return;
                    if(Utils.timeIsOver) throw new MoveAfterTimeException();

                    if(thisPiece.playerColor != Utils.playerColor) throw new BadPieceException();

                    //table.board.printAvailableTiles();
                    //table.board.printThreatenTiles();
                    //calculateLegalMoves();
                    previousPosition = position;
                    isPressed = !isPressed;
                } catch (MoveAfterTimeException e1) {
                } catch (BadPieceException e1) {
                }

            }
            public void mouseReleased(MouseEvent e) {
                if(taken) return;
                if( Utils.timeIsOver) return;
                if(thisPiece.playerColor != Utils.playerColor) return;
                int x = (image.getX() + image.getWidth()/2)/ Utils.TILE_WIDTH;
                int y = (image.getY()+ image.getWidth()/2)/ Utils.TILE_WIDTH;

                checkMove(x,y);
                if(checkIfLegalMove(x,y) && checkIfNotForked(x,y)){

                    checkIfTakeMove(x,y);

                    processMove(x,y);

                    if(checkPromotion()){
                        new PromotionWindow(thisPiece, table.gameTable);

                    }

                    changeTurn();
                    changeClock();

                    if(playerColor == PlayerColor.Black){
                        updateThreats(false);
                    }
                    else{
                        updateThreats(true);
                    }

                    if(checkIfGameOver()){
                        Utils.gameOver = true;
                        printWinMessage();
                    }

                }
                else
                {
                    image.setLocation(((int)position.getX() + 1) * Utils.TILE_WIDTH, ((int)position.getY() + 1)* Utils.TILE_WIDTH);
                    try{
                        throw new IllegalMoveException();
                    } catch (IllegalMoveException e1) {
                    }
                }


            }

            public void mouseEntered(MouseEvent e) {}

            public void mouseExited(MouseEvent e) {}
        });

    }

    private void printWinMessage(){
        try{
            if(playerColor == PlayerColor.Black){
                throw new GameOverException("White");
            }
            if(playerColor == PlayerColor.White){
                throw new GameOverException("Black");
            }
        } catch (GameOverException e) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            Utils.match.setEnd(dateFormat.format(date));
            Utils.match.setPgn(Utils.moves);
            table.gameTable.dispose();
            new Windows.StartWindow();
        }
    }

    private void changeClock() {

    }

    private void processMove(int x, int y){

        checkCastle(x,y);
        table.board.board[(int)position.getY()][(int)position.getX()].setOccupied(false);
        table.board.board[(int)position.getY()][(int)position.getX()].setPiece(null);
        position.setLocation(x-1,y-1);
        image.setLocation((((int)image.getX() + image.getWidth()/2)/ Utils.TILE_WIDTH) * Utils.TILE_WIDTH, (((int)image.getY()+ image.getWidth()/2)/ Utils.TILE_WIDTH) * Utils.TILE_WIDTH);
        previousPosition = position;
        table.board.board[y - 1][x - 1].setOccupied(true);
        table.board.board[y - 1][x - 1].setPiece(thisPiece);
        addMoveToString(x-1, y-1, thisPiece.pieceType );
        wasMoved = true;

    }

    private void checkCastle(int x , int y){

        if(thisPiece.pieceType != PieceType.King) return;
        if(wasMoved) return;


        if(Math.abs((int)position.getX() + 1 - x ) > 1){

            // set ROOK
            if(playerColor == PlayerColor.Black){
                if((int)position.getX() + 1 - x < -1){
                    table.board.board[7][7].setOccupied(false);
                    table.board.board[7][7].setPiece(null);
                    table.blackPieces.get(7).position.setLocation(4,7);
                    table.blackPieces.get(7).image.setLocation(5 * Utils.TILE_WIDTH, 8*Utils.TILE_WIDTH);
                    table.board.board[7][4].setOccupied(true);
                    table.board.board[7][4].setPiece(table.blackPieces.get(0));
                }
                else{
                    table.board.board[7][0].setOccupied(false);
                    table.board.board[7][0].setPiece(null);
                    table.blackPieces.get(0).position.setLocation(2,7);
                    table.blackPieces.get(0).image.setLocation(3 * Utils.TILE_WIDTH, 8*Utils.TILE_WIDTH);
                    table.board.board[7][2].setOccupied(true);
                    table.board.board[7][2].setPiece(table.blackPieces.get(0));
                }

            }
            else {
                if((int)position.getX() + 1 - x < -1){
                    table.board.board[0][7].setOccupied(false);
                    table.board.board[0][7].setPiece(null);
                    table.whitePieces.get(7).position.setLocation(4,0);
                    table.whitePieces.get(7).image.setLocation(5* Utils.TILE_WIDTH, 1*Utils.TILE_WIDTH);
                    table.board.board[0][4].setOccupied(true);
                    table.board.board[0][4].setPiece(table.whitePieces.get(7));
                }
                else{
                    table.board.board[0][0].setOccupied(false);
                    table.board.board[0][0].setPiece(null);
                    table.whitePieces.get(0).position.setLocation(2,0);
                    table.whitePieces.get(0).image.setLocation(3 * Utils.TILE_WIDTH, 1*Utils.TILE_WIDTH);
                    table.board.board[0][2].setOccupied(true);
                    table.board.board[0][2].setPiece(table.whitePieces.get(0));
                }
            }
        }

    }

    public void checkMove(int x, int y){

        try{
            System.out.println(x  + " " + y);
            if( x > 8 || x < 1 || y > 8 || y < 1){
                throw new OutOfBoardException();
            }
            if(x - 1 == (int)position.getX() && y - 1 == (int)position.getY()){
                throw new SamePlaceException();
            }
            if(Utils.timeIsOver){
                throw new MoveAfterTimeException();
            }
            Tile t = table.board.board[y - 1][x - 1];
            if(t.isOccupied() && t.getPiece().playerColor == playerColor ){
                throw new StepOnPieceException();
            }
        } catch (MoveAfterTimeException e) {
        } catch (StepOnPieceException e) {
        } catch (OutOfBoardException e) {
        } catch (SamePlaceException e) {
        }


    }

    public PlayerColor getPlayerColor() {
        return playerColor;
    }

    public void setPlayerColor(PlayerColor playerColor) {
        this.playerColor = playerColor;
    }

    public JLabel getImage() {
        return image;
    }

    public Point getPosition() {
        return position;
    }

    public void addMoveToString(int x , int y, PieceType pieceType){

        switch(y){
            case 0:
                Utils.moves += Utils.moveCounter++ + ". " + pieceType.getAbr() + "a" + (x+1);
                break;
            case 1:
                Utils.moves += Utils.moveCounter++ + ". " + pieceType.getAbr() + "b" + (x+1);
                break;
            case 2:
                Utils.moves += Utils.moveCounter++ + ". " + pieceType.getAbr() + "c" + (x+1);
                break;
            case 3:
                Utils.moves += Utils.moveCounter++ + ". " + pieceType.getAbr() + "d" + (x+1);
                break;
            case 4:
                Utils.moves += Utils.moveCounter++ + ". " + pieceType.getAbr() + "e" + (x+1);
                break;
            case 5:
                Utils.moves += Utils.moveCounter++ + ". " + pieceType.getAbr() + "f" + (x+1);
                break;
            case 6:
                Utils.moves += Utils.moveCounter++ + ". " + pieceType.getAbr() + "g" + (x+1);
                break;
            case 7:
                Utils.moves += Utils.moveCounter++ + ". " + pieceType.getAbr() + "h" + (x+1);
                break;
            default:
                System.out.println("Invalid PGN");
        }

    }

    public void setImage(JLabel image) {
        this.image = image;
    }

    private void updateThreats(boolean whiteFirst){
        table.board.clearIfAttackedTiles();
        if(whiteFirst){
            for(Piece p : table.whitePieces){
                if(!p.taken)
                p.calculateLegalMoves();
            }
            for(Piece p : table.blackPieces){
                if(!p.taken)
                p.calculateLegalMoves();
            }
            return;
        }
        for(Piece p : table.blackPieces){
            if(!p.taken)
            p.calculateLegalMoves();
        }
        for(Piece p : table.whitePieces){
            if(!p.taken)
            p.calculateLegalMoves();
        }
    }

    public void changeTurn() {
        if (Utils.playerColor == PlayerColor.Black){
            Utils.playerColor = PlayerColor.White;
        }
        else{
            Utils.playerColor = PlayerColor.Black;
        }
    }

    private int checkIfCheck(){

        // 1 - whites are in check
        // 2 - blacks are in check
        // 0 - there is no check
        if(thisPiece.playerColor == PlayerColor.Black){
            Point pos = this.table.whitePieces.get(3).getPosition();
            if(table.board.tilesAttackedByBlack[(int) pos.getY()][(int) pos.getX()]){
                System.out.println("CHECK ! " + pos.getX() + " " + pos.getY());
                Utils.isCheck = true;
                return 1;
            }
        }
        else{
            Point pos = this.table.blackPieces.get(3).getPosition();
            if(table.board.tilesAttackedByEnemy[(int) pos.getY()][(int) pos.getX()]){
                System.out.println("CHECK ! " + pos.getX() + " " + pos.getY());
                Utils.isCheck = true;
                return 2;
            }
        }
        return 0;
    }

    private boolean checkIfGameOver(){
        if(checkIfCheck() > 0){
            return !checkIfCanDefend();
        }
        return false;
    }

    private boolean checkIfCanDefend(){
        int px, py;
        int kingX, kingY;
        Piece takenPiece;
        boolean canDefend = false;
        if(playerColor == PlayerColor.Black){
            kingX = table.whitePieces.get(3).position.x;
            kingY = table.whitePieces.get(3).position.y;
            for( Piece p:table.whitePieces){
                if(canDefend) break;
                for( int i = 0 ; i < p.LEGAL_MOVES.size(); i++){
                    Point x = p.LEGAL_MOVES.get(i);
                    if(canDefend) break;
                    py = p.position.y;
                    px = p.position.x;
                    p.position.x += (int)x.getX();
                    p.position.y += (int)x.getY();

                    takenPiece = processPieceMove(px, py,p.position.x,p.position.y,p,null);
                    updateThreats(true);

                    if(p.pieceType == PieceType.King){
                        if(!table.board.tilesAttackedByBlack[p.position.y][p.position.x]){
                            System.out.println("You can escape: " + p.pieceType.getAbr() + " -> " + p.position.x + " " + p.position.y);
                            canDefend = true;
                        }
                    }
                    else{
                        if(!table.board.tilesAttackedByBlack[kingY][kingX]){
                            System.out.println("You can escape: " + p.pieceType.getAbr() + " -> " + p.position.x + " " + p.position.y);
                            canDefend = true;
                        }
                    }
                    processPieceMove(p.position.x,p.position.y,px, py,p,takenPiece);
                    p.position.x = px;
                    p.position.y = py;
                    updateThreats(false);
                }
            }
        }
        else{
            kingX = table.blackPieces.get(3).position.x;
            kingY = table.blackPieces.get(3).position.y;
            for( Piece p:table.blackPieces){
                if(canDefend) break;
                for( int i = 0 ; i < p.LEGAL_MOVES.size(); i++){
                    Point x = p.LEGAL_MOVES.get(i);
                    if(canDefend) break;
                    py = p.position.y;
                    px = p.position.x;
                    p.position.x += (int)x.getX();
                    p.position.y += (int)x.getY();

                    takenPiece = processPieceMove(px, py,p.position.x,p.position.y,p,null);

                    updateThreats(false);

                    if(p.pieceType == PieceType.King){
                        if(!table.board.tilesAttackedByEnemy[p.position.y][p.position.x]){
                            System.out.println("You can escape: " + p.pieceType.getAbr() + " -> " + p.position.x + " " + p.position.y);
                            canDefend = true;
                        }
                    }
                    else{
                        if(!table.board.tilesAttackedByEnemy[kingY][kingX]){
                            System.out.println("You can escape: " + p.pieceType.getAbr() + " -> " + p.position.x + " " + p.position.y);
                            canDefend = true;
                        }
                    }

                    processPieceMove(p.position.x,p.position.y,px, py,p,takenPiece);
                    p.position.x = px;
                    p.position.y = py;

                    updateThreats(true);

                }
            }
        }
        return canDefend;
    }

    private boolean checkIfTakeMove(int x, int y){

        Tile t = table.board.board[y-1][x-1];

        if(playerColor == PlayerColor.Black){
            if(t.isOccupied() && t.getPiece().playerColor == PlayerColor.White ){
                t.getPiece().taken = true;
                t.getPiece().image.setVisible(false);
                return true;
            }
        }
        else{
            if(t.isOccupied() && t.getPiece().playerColor == PlayerColor.Black ){
                t.getPiece().taken = true;
                t.getPiece().image.setVisible(false);
                return true;
            }

        }

        return false;
    }

    public void clearThreatenTiles(){
        for( int i = 0 ; i < 8; i++){
            for( int j = 0 ; j < 8 ; j++){
                threatTiles[i][j] = false;
            }
        }
    }

    private Piece processPieceMove(int px, int py, int dx, int dy, Piece pieceToMove, Piece takenPiece){
        Piece piece = null;

        if(takenPiece != null){
            table.board.board[py][px].setOccupied(true);
            table.board.board[py][px].setPiece(takenPiece);
        }
        else{
            table.board.board[py][px].setOccupied(false);
            table.board.board[py][px].setPiece(null);
        }

        if(table.board.board[dy][dx].isOccupied()){
            piece = table.board.board[dy][dx].getPiece();
        }
        table.board.board[dy][dx].setOccupied(true);
        table.board.board[dy][dx].setPiece(pieceToMove);

        return piece;
    }

    private boolean checkIfNotForked(int x , int y){

        boolean canMove = true;
        int kingY,kingX;
        int py, px;
        Piece takenPiece;

        py = thisPiece.position.y;
        px = thisPiece.position.x;
        thisPiece.position.x = x - 1;
        thisPiece.position.y = y - 1;

        if(playerColor == PlayerColor.Black){
            kingX = table.blackPieces.get(3).position.x;
            kingY = table.blackPieces.get(3).position.y;

            takenPiece = processPieceMove(px, py,thisPiece.position.x,thisPiece.position.y,thisPiece,null);
            updateThreats(false);

            if(table.board.tilesAttackedByEnemy[kingY][kingX]){
                canMove = false;
            }
            else{
                canMove = true;
            }

            processPieceMove(thisPiece.position.x,thisPiece.position.y,px, py,thisPiece,takenPiece);
            thisPiece.position.x = px;
            thisPiece.position.y = py;

            updateThreats(true);
        }
        else{

            kingX = table.whitePieces.get(3).position.x;
            kingY = table.whitePieces.get(3).position.y;

            takenPiece = processPieceMove(px, py,thisPiece.position.x,thisPiece.position.y,thisPiece,null);
            updateThreats(true);

            if(table.board.tilesAttackedByBlack[kingY][kingX]){
                canMove = false;
            }
            else{
                canMove = true;
            }



            processPieceMove(thisPiece.position.x,thisPiece.position.y,px, py,thisPiece,takenPiece);
            thisPiece.position.x = px;
            thisPiece.position.y = py;
            updateThreats(false);
        }



        return canMove;

    }

    private boolean checkPromotion(){
        if(thisPiece.pieceType == PieceType.Pawn){
            if( thisPiece.playerColor == PlayerColor.Black){
                if(thisPiece.position.y == 0) return true;
            }
            else{
                if(thisPiece.position.y == 7) return true;
            }


        }
        return false;
    }

    public void promotePawn(){
        if( thisPiece.playerColor == PlayerColor.Black){
            for( int i = 8; i < 16; i++){
                if( table.blackPieces.get(i).position.x == thisPiece.position.x &&
                        table.blackPieces.get(i).position.y == thisPiece.position.y){
                    Piece newPiece = createPiece(Utils.promotedPieceType);
                    //newPiece.image.setSize(Utils.TILE_WIDTH,Utils.TILE_WIDTH);
                    //newPiece.image.setLocation((int)position.x*Utils.TILE_WIDTH,(int)position.x*Utils.TILE_WIDTH);

                    //table.blackPieces.get(i).image.setIcon(newPiece.image.getIcon());

                    table.blackPieces.get(i).image.setVisible(false);
                    table.blackPieces.get(i).taken = true;
                    table.blackPieces.remove(i);
                    //table.blackPieces.add(newPiece);

                    table.addPiece(newPiece);
                    newPiece.image.setVisible(true);
                    //table.gameTable.add(table.blackPieces.get(table.blackPieces.size() - 1));
                    //table.addPiece(newPiece, i);
                    //table.blackPieces.get(table.blackPieces.size() - 1).image.setIcon(newPiece.image.getIcon());
                    //table.blackPieces.get(table.blackPieces.size() - 1).image.setVisible(true);

                    table.gameTable.repaint();
                }
            }
        }
        else{
            for( int i = 8; i < 16; i++){
                if( table.whitePieces.get(i).position.x == thisPiece.position.x &&
                        table.whitePieces.get(i).position.y == thisPiece.position.y){
                    Piece newPiece = createPiece(Utils.promotedPieceType);
                    newPiece.image.setSize(Utils.TILE_WIDTH,Utils.TILE_WIDTH);
                    newPiece.image.setLocation((int)position.x*Utils.TILE_WIDTH,(int)position.x*Utils.TILE_WIDTH);
                    newPiece.image.setVisible(true);

                    //table.blackPieces.get(i).image.setIcon(newPiece.image.getIcon());
                    table.addPiece(newPiece);
                    table.whitePieces.get(i).image.setVisible(false);
                    table.whitePieces.get(i).taken = true;
                    table.whitePieces.add(newPiece);
                    table.gameTable.add(table.whitePieces.get(table.whitePieces.size() - 1));
                }
            }
        }
    }

    private Piece createPiece(PieceType pieceType){

        switch(pieceType){
            case Bishop:
                if(playerColor == PlayerColor.Black)
                return new Bishop(pieceType,new JLabel(new ImageIcon(getClass().getClassLoader().getResource("BB.png"))),position,playerColor,table);

                return new Bishop(pieceType,new JLabel(new ImageIcon(getClass().getClassLoader().getResource("BW.png"))),position,playerColor,table);

            case Queen:
                if(playerColor == PlayerColor.Black)
                return new Queen(pieceType,new JLabel(new ImageIcon(getClass().getClassLoader().getResource("QB1.png"))),position,playerColor,table);

                return new Queen(pieceType,new JLabel(new ImageIcon(getClass().getClassLoader().getResource("QW.png"))),position,playerColor,table);

            case Knight:
                if(playerColor == PlayerColor.Black)
                return new Knight(pieceType,new JLabel(new ImageIcon(getClass().getClassLoader().getResource("NB.png"))),position,playerColor,table);

                return new Knight(pieceType,new JLabel(new ImageIcon(getClass().getClassLoader().getResource("NW.png"))),position,playerColor,table);


            case Rook:
                if(playerColor == PlayerColor.Black)
                return new Rook(pieceType,new JLabel(new ImageIcon(getClass().getClassLoader().getResource("RB.png"))),position,playerColor,table);

                return new Rook(pieceType,new JLabel(new ImageIcon(getClass().getClassLoader().getResource("RW.png"))),position,playerColor,table);

            default:
                Utils.logger.info("Ivalid promotion type!");
                if(playerColor == PlayerColor.Black)
                return new Queen(pieceType,new JLabel(new ImageIcon(getClass().getClassLoader().getResource("QB.png"))),position,playerColor,table);

                return new Queen(pieceType,new JLabel(new ImageIcon(getClass().getClassLoader().getResource("QW.png"))),position,playerColor,table);


        }
    }
}
