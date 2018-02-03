package GameTable;

import Appearance.Skin;
import Misc.Utils;
import Pieces.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Table {

    public static JFrame gameTable;
    public static JLabel j ;
    public static BoardTemplate boardTemplate;
    public static List<Piece> whitePieces;
    public static List<Piece> blackPieces;
    public static List<HintCircle> hints;
    public static Board board;
    private JButton resetButton;

    public Table(){
        this.gameTable = new JFrame("CHESS");
        this.gameTable.setLayout(null);
        this.gameTable.setSize(Utils.WINDOW_SIZE);
        this.gameTable.setVisible(true);
        this.gameTable.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.gameTable.setBackground(Skin.background);
        this.gameTable.setLocationRelativeTo(null);
        this.board = new Board();
        whitePieces = new ArrayList<Piece>();
        blackPieces = new ArrayList<Piece>();
        hints = new ArrayList<HintCircle>();

        boardTemplate = new BoardTemplate();
        boardTemplate.setLocation(0,0/*Misc.Utils.BOARD_X,Misc.Utils.BOARD_Y*/);
        boardTemplate.setSize(Utils.BOARD_SIZE);
        boardTemplate.setVisible(true);

        initPieces();
        this.gameTable.add(boardTemplate);
        //setButtons();
        this.gameTable.repaint();




    }
    private void setButtons(){
        resetButton = new JButton("RESET");

        resetButton.setSize(100,50);

        resetButton.setLocation(580,100);

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Table();
                gameTable.dispose();
            }
        });
        this.gameTable.add(resetButton);
    }
    private static class BoardTemplate extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            for( int i = 0; i < 8 ; i++){
                for( int j = 0 ; j < 8 ; j++){
                    if((i+j)%2 == 0)
                        g.setColor(Skin.white);
                    else
                        g.setColor(Skin.black);

                    g.fillRect((j%8 + 1)* Utils.TILE_WIDTH,(i+1)* Utils.TILE_WIDTH, Utils.TILE_WIDTH, Utils.TILE_WIDTH);
                }
            }
        }

        public Dimension getPreferredSize() {
            return Utils.BOARD_SIZE; // appropriate constants
        }
    }
    public void initPieces(){
        initWhitePieces();
        initBlackPieces();
    }
    public void initWhitePieces(){
        ImageIcon R = new ImageIcon(getClass().getClassLoader().getResource("RW.png"));
        ImageIcon N = new ImageIcon(getClass().getClassLoader().getResource("NW.png"));
        ImageIcon B = new ImageIcon(getClass().getClassLoader().getResource("BW.png"));
        ImageIcon Q = new ImageIcon(getClass().getClassLoader().getResource("QW.png"));
        ImageIcon K = new ImageIcon(getClass().getClassLoader().getResource("KW.png"));
        ImageIcon P = new ImageIcon(getClass().getClassLoader().getResource("PW.png"));

        JLabel j = new JLabel(R);
        j.setSize(Utils.TILE_WIDTH, Utils.TILE_WIDTH);
        Point position = new Point(0,0);
        this.board.board[(int)position.getY()][(int)position.getX()].setOccupied(true);
        j.setLocation((int)(position.getX()+1)* Utils.TILE_WIDTH,(int)(position.getY()+1)* Utils.TILE_WIDTH);
        j.setSize(Utils.TILE_WIDTH, Utils.TILE_WIDTH);
        whitePieces.add(new Rook(PieceType.Rook,j,position, PlayerColor.White, this));
        this.board.board[(int)position.getY()][(int)position.getX()].setPiece(whitePieces.get(whitePieces.size() - 1));
        j = new JLabel(N);
        position = new Point(1,0);
        this.board.board[(int)position.getY()][(int)position.getX()].setOccupied(true);
        j.setLocation((int)(position.getX()+1)* Utils.TILE_WIDTH,(int)(position.getY()+1)* Utils.TILE_WIDTH);
        j.setSize(Utils.TILE_WIDTH, Utils.TILE_WIDTH);
        whitePieces.add(new Knight(PieceType.Knight,j,position, PlayerColor.White, this));
        this.board.board[(int)position.getY()][(int)position.getX()].setPiece(whitePieces.get(whitePieces.size() - 1));
        j = new JLabel(B);
        position = new Point(2,0);
        this.board.board[(int)position.getY()][(int)position.getX()].setOccupied(true);
        j.setLocation((int)(position.getX()+1)* Utils.TILE_WIDTH,(int)(position.getY()+1)* Utils.TILE_WIDTH);
        j.setSize(Utils.TILE_WIDTH, Utils.TILE_WIDTH);
        whitePieces.add(new Bishop(PieceType.Bishop,j,position, PlayerColor.White, this));
        this.board.board[(int)position.getY()][(int)position.getX()].setPiece(whitePieces.get(whitePieces.size() - 1));
        j = new JLabel(K);
        position = new Point(3,0);
        this.board.board[(int)position.getY()][(int)position.getX()].setOccupied(true);
        j.setLocation((int)(position.getX()+1)* Utils.TILE_WIDTH,(int)(position.getY()+1)* Utils.TILE_WIDTH);
        j.setSize(Utils.TILE_WIDTH, Utils.TILE_WIDTH);
        whitePieces.add(new King(PieceType.King,j,position, PlayerColor.White, this));
        this.board.board[(int)position.getY()][(int)position.getX()].setPiece(whitePieces.get(whitePieces.size() - 1));
        j = new JLabel(Q);
        position = new Point(4,0);
        this.board.board[(int)position.getY()][(int)position.getX()].setOccupied(true);
        j.setLocation((int)(position.getX()+1)* Utils.TILE_WIDTH,(int)(position.getY()+1)* Utils.TILE_WIDTH);
        j.setSize(Utils.TILE_WIDTH, Utils.TILE_WIDTH);
        whitePieces.add(new Queen(PieceType.Queen,j,position, PlayerColor.White, this));
        this.board.board[(int)position.getY()][(int)position.getX()].setPiece(whitePieces.get(whitePieces.size() - 1));
        j = new JLabel(B);
        position = new Point(5,0);
        this.board.board[(int)position.getY()][(int)position.getX()].setOccupied(true);
        j.setLocation((int)(position.getX()+1)* Utils.TILE_WIDTH,(int)(position.getY()+1)* Utils.TILE_WIDTH);
        j.setSize(Utils.TILE_WIDTH, Utils.TILE_WIDTH);
        whitePieces.add(new Bishop(PieceType.Bishop,j,position, PlayerColor.White, this));
        this.board.board[(int)position.getY()][(int)position.getX()].setPiece(whitePieces.get(whitePieces.size() - 1));
        j = new JLabel(N);
        position = new Point(6,0);
        this.board.board[(int)position.getY()][(int)position.getX()].setOccupied(true);
        j.setLocation((int)(position.getX()+1)* Utils.TILE_WIDTH,(int)(position.getY()+1)* Utils.TILE_WIDTH);
        j.setSize(Utils.TILE_WIDTH, Utils.TILE_WIDTH);
        whitePieces.add(new Knight(PieceType.Knight,j,position, PlayerColor.White, this));
        this.board.board[(int)position.getY()][(int)position.getX()].setPiece(whitePieces.get(whitePieces.size() - 1));
        j = new JLabel(R);
        position = new Point(7,0);
        this.board.board[(int)position.getY()][(int)position.getX()].setOccupied(true);
        j.setLocation((int)(position.getX()+1)* Utils.TILE_WIDTH,(int)(position.getY()+1)* Utils.TILE_WIDTH);
        j.setSize(Utils.TILE_WIDTH, Utils.TILE_WIDTH);
        whitePieces.add(new Rook(PieceType.Rook,j,position, PlayerColor.White, this));
        this.board.board[(int)position.getY()][(int)position.getX()].setPiece(whitePieces.get(whitePieces.size() - 1));


        for( int i = 0 ; i < 8; i++){

            j = new JLabel(P);
            position = new Point(i,1);
            this.board.board[(int)position.getY()][(int)position.getX()].setOccupied(true);
            j.setLocation((int)(position.getX()+1)* Utils.TILE_WIDTH,(int)(position.getY()+1)* Utils.TILE_WIDTH);
            j.setSize(Utils.TILE_WIDTH, Utils.TILE_WIDTH);
            whitePieces.add(new Pawn(PieceType.Pawn,j,position, PlayerColor.White, this));
            this.board.board[(int)position.getY()][(int)position.getX()].setPiece(whitePieces.get(whitePieces.size() - 1));

        }

        for(Piece p :whitePieces){
            this.gameTable.add(p.getImage());
        }

    }
    public void initBlackPieces(){
        ImageIcon R = new ImageIcon(getClass().getClassLoader().getResource("RB.png"));
        ImageIcon N = new ImageIcon(getClass().getClassLoader().getResource("NB.png"));
        ImageIcon B = new ImageIcon(getClass().getClassLoader().getResource("BB.png"));
        ImageIcon Q = new ImageIcon(getClass().getClassLoader().getResource("QB.png"));
        ImageIcon K = new ImageIcon(getClass().getClassLoader().getResource("KB.png"));
        ImageIcon P = new ImageIcon(getClass().getClassLoader().getResource("PB.png"));

        JLabel j = new JLabel(R);
        Point position = new Point(0,7);
        this.board.board[(int)position.getY()][(int)position.getX()].setOccupied(true);
        j.setLocation((int)(position.getX()+1)* Utils.TILE_WIDTH,(int)(position.getY()+1)* Utils.TILE_WIDTH);
        j.setSize(Utils.TILE_WIDTH, Utils.TILE_WIDTH);
        blackPieces.add(new Rook(PieceType.Rook,j,position, PlayerColor.Black, this));
        this.board.board[(int)position.getY()][(int)position.getX()].setPiece(blackPieces.get(blackPieces.size() - 1));
        j = new JLabel(N);
        position = new Point(1,7);
        this.board.board[(int)position.getY()][(int)position.getX()].setOccupied(true);
        j.setLocation((int)(position.getX()+1)* Utils.TILE_WIDTH,(int)(position.getY()+1)* Utils.TILE_WIDTH);
        j.setSize(Utils.TILE_WIDTH, Utils.TILE_WIDTH);
        blackPieces.add(new Knight(PieceType.Knight,j,position, PlayerColor.Black, this));
        this.board.board[(int)position.getY()][(int)position.getX()].setPiece(blackPieces.get(blackPieces.size() - 1));
        j = new JLabel(B);
        position = new Point(2,7);
        this.board.board[(int)position.getY()][(int)position.getX()].setOccupied(true);
        j.setLocation((int)(position.getX()+1)* Utils.TILE_WIDTH,(int)(position.getY()+1)* Utils.TILE_WIDTH);
        j.setSize(Utils.TILE_WIDTH, Utils.TILE_WIDTH);
        blackPieces.add(new Bishop(PieceType.Bishop,j,position, PlayerColor.Black, this));
        this.board.board[(int)position.getY()][(int)position.getX()].setPiece(blackPieces.get(blackPieces.size() - 1));
        j = new JLabel(K);
        position = new Point(3,7);
        this.board.board[(int)position.getY()][(int)position.getX()].setOccupied(true);
        j.setLocation((int)(position.getX()+1)* Utils.TILE_WIDTH,(int)(position.getY()+1)* Utils.TILE_WIDTH);
        j.setSize(Utils.TILE_WIDTH, Utils.TILE_WIDTH);
        blackPieces.add(new King(PieceType.King,j,position, PlayerColor.Black, this));
        this.board.board[(int)position.getY()][(int)position.getX()].setPiece(blackPieces.get(blackPieces.size() - 1));
        j = new JLabel(Q);
        position = new Point(4,7);
        this.board.board[(int)position.getY()][(int)position.getX()].setOccupied(true);
        j.setLocation((int)(position.getX()+1)* Utils.TILE_WIDTH,(int)(position.getY()+1)* Utils.TILE_WIDTH);
        j.setSize(Utils.TILE_WIDTH, Utils.TILE_WIDTH);
        blackPieces.add(new Queen(PieceType.Queen,j,position, PlayerColor.Black, this));
        this.board.board[(int)position.getY()][(int)position.getX()].setPiece(blackPieces.get(blackPieces.size() - 1));
        j = new JLabel(B);
        position = new Point(5,7);
        this.board.board[(int)position.getY()][(int)position.getX()].setOccupied(true);
        j.setLocation((int)(position.getX()+1)* Utils.TILE_WIDTH,(int)(position.getY()+1)* Utils.TILE_WIDTH);
        j.setSize(Utils.TILE_WIDTH, Utils.TILE_WIDTH);
        blackPieces.add(new Bishop(PieceType.Bishop,j,position, PlayerColor.Black, this));
        this.board.board[(int)position.getY()][(int)position.getX()].setPiece(blackPieces.get(blackPieces.size() - 1));
        j = new JLabel(N);
        position = new Point(6,7);
        this.board.board[(int)position.getY()][(int)position.getX()].setOccupied(true);
        j.setLocation((int)(position.getX()+1)* Utils.TILE_WIDTH,(int)(position.getY()+1)* Utils.TILE_WIDTH);
        j.setSize(Utils.TILE_WIDTH, Utils.TILE_WIDTH);
        blackPieces.add(new Knight(PieceType.Knight,j,position, PlayerColor.Black, this));
        this.board.board[(int)position.getY()][(int)position.getX()].setPiece(blackPieces.get(blackPieces.size() - 1));
        j = new JLabel(R);
        position = new Point(7,7);
        this.board.board[(int)position.getY()][(int)position.getX()].setOccupied(true);
        j.setLocation((int)(position.getX()+1)* Utils.TILE_WIDTH,(int)(position.getY()+1)* Utils.TILE_WIDTH);
        j.setSize(Utils.TILE_WIDTH, Utils.TILE_WIDTH);
        blackPieces.add(new Rook(PieceType.Rook,j,position, PlayerColor.Black, this));
        this.board.board[(int)position.getY()][(int)position.getX()].setPiece(blackPieces.get(blackPieces.size() - 1));


        for( int i = 0 ; i < 8; i++){

            j = new JLabel(P);
            position = new Point(i,6);
            this.board.board[(int)position.getY()][(int)position.getX()].setOccupied(true);
            j.setLocation((int)(position.getX()+1)* Utils.TILE_WIDTH,(int)(position.getY()+1)* Utils.TILE_WIDTH);
            j.setSize(Utils.TILE_WIDTH, Utils.TILE_WIDTH);
            blackPieces.add(new Pawn(PieceType.Pawn,j,position, PlayerColor.Black, this));
            this.board.board[(int)position.getY()][(int)position.getX()].setPiece(blackPieces.get(blackPieces.size() - 1));

        }


        for(Piece p :blackPieces){
            this.gameTable.add(p.getImage());
        }

    }
    public void addPiece(Piece piece){


        JLabel j = piece.getImage();
        j = piece.getImage();
        Point position = piece.getPosition();
        this.board.board[(int)position.getY()][(int)position.getX()].setOccupied(true);
        this.board.board[(int)position.getY()][(int)position.getX()].setPiece(piece);
        j.setLocation((int)(position.getX()+1)* Utils.TILE_WIDTH,(int)(position.getY()+1)* Utils.TILE_WIDTH);
        j.setSize(Utils.TILE_WIDTH, Utils.TILE_WIDTH);

        if(piece.getPlayerColor() == PlayerColor.Black){
            blackPieces.add(piece);
            this.board.board[(int)position.getY()][(int)position.getX()].setPiece(blackPieces.get(blackPieces.size() - 1));
        }
        else{
            whitePieces.add(piece);
            this.board.board[(int)position.getY()][(int)position.getX()].setPiece(whitePieces.get(whitePieces.size() - 1));
        }



        this.gameTable.repaint();


    }
    public void createQueen(Piece piece){
        ImageIcon QW = new ImageIcon(getClass().getClassLoader().getResource("QW.png"));
        ImageIcon QB = new ImageIcon(getClass().getClassLoader().getResource("QB.png"));
        j = new JLabel(QB);
        Point position = piece.getPosition();
        this.board.board[(int)position.getY()][(int)position.getX()].setOccupied(true);
        j.setLocation((int)(position.getX()+1)* Utils.TILE_WIDTH,(int)(position.getY()+1)* Utils.TILE_WIDTH);
        j.setSize(Utils.TILE_WIDTH, Utils.TILE_WIDTH);
        whitePieces.add(new Queen(PieceType.Queen,j,position, PlayerColor.White, this));
        this.board.board[(int)position.getY()][(int)position.getX()].setPiece(whitePieces.get(whitePieces.size() - 1));
    }
    void setPiecePlace(ImageIcon P, Point position){
        JLabel j = new JLabel(P);
        this.board.board[(int)position.getY()][(int)position.getX()].setOccupied(true);
        j.setLocation((int)(position.getX()+1)* Utils.TILE_WIDTH,(int)(position.getY()+1)* Utils.TILE_WIDTH);
        j.setSize(Utils.TILE_WIDTH, Utils.TILE_WIDTH);
    }

}
