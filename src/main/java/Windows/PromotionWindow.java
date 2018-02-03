package Windows;

import Misc.Utils;
import Pieces.Piece;
import Pieces.PieceType;
import Pieces.PlayerColor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import static Appearance.Gui.setSkin;

public class PromotionWindow extends JFrame {

    private JLabel bishop;
    private JLabel knight;
    private JLabel queen;
    private JLabel rook;
    private Piece pawn;
    private JFrame parent;


    public PromotionWindow(Piece piece, JFrame parent){
        this.parent = parent;
        this.pawn = piece;
        setSkin();
        this.setTitle("CHESS");
        this.setLayout(new GridBagLayout());
        this.setSize(300,200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initImages();
        checkLanguage();
        setVisible(true);
    }

    private void initImages(){
        ImageIcon R;
        ImageIcon N;
        ImageIcon B;
        ImageIcon Q;
        if( pawn.getPlayerColor() == PlayerColor.Black){
            R = new ImageIcon(getClass().getClassLoader().getResource("RB.png"));
            N = new ImageIcon(getClass().getClassLoader().getResource("NB.png"));
            B = new ImageIcon(getClass().getClassLoader().getResource("BB.png"));
            Q = new ImageIcon(getClass().getClassLoader().getResource("QB.png"));
        }
        else{
            R = new ImageIcon(getClass().getClassLoader().getResource("RW.png"));
            N = new ImageIcon(getClass().getClassLoader().getResource("NW.png"));
            B = new ImageIcon(getClass().getClassLoader().getResource("BW.png"));
            Q = new ImageIcon(getClass().getClassLoader().getResource("QW.png"));
        }

        bishop = new JLabel(B);
        knight = new JLabel(N);
        queen = new JLabel(Q);
        rook = new JLabel(R);


        bishop.setSize(Utils.TILE_WIDTH*2, Utils.TILE_WIDTH*2);
        knight.setSize(Utils.TILE_WIDTH*2, Utils.TILE_WIDTH*2);
        queen.setSize(Utils.TILE_WIDTH*2, Utils.TILE_WIDTH*2);
        rook.setSize(Utils.TILE_WIDTH*2, Utils.TILE_WIDTH*2);

        GridBagConstraints gc = new GridBagConstraints();

        gc.weighty = 0.5;
        gc.weightx = 0.5;

        gc.gridx = 0;
        gc.gridy = 0;

        add(queen,gc);

        gc.gridx = 1;
        add(rook,gc);

        gc.gridx = 2;
        add(bishop,gc);

        gc.gridx = 3;
        add(knight,gc);
        bishop.addMouseListener(new MouseListener() {
             @Override
             public void mouseClicked(MouseEvent e) {
                 Utils.promotedPieceType = PieceType.Bishop;
                 pawn.promotePawn();
                 parent.show();
                 dispose();
             }
             @Override
             public void mousePressed(MouseEvent e) { }
             @Override
             public void mouseReleased(MouseEvent e) { }
             @Override
             public void mouseEntered(MouseEvent e) { }
             @Override
             public void mouseExited(MouseEvent e) { }
         });

        knight.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Utils.promotedPieceType = PieceType.Knight;
                pawn.promotePawn();
                parent.show();
                dispose();
            }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        });

        rook.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Utils.promotedPieceType = PieceType.Rook;
                pawn.promotePawn();
                parent.show();
                dispose();
            }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        });

        queen.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Utils.promotedPieceType = PieceType.Queen;
                pawn.promotePawn();
                parent.show();
                dispose();
            }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }
        });

    }

    public void checkLanguage(){

        switch (Utils.language){
            case English:
                this.setTitle("CHESS");
                break;

            case Polski:

                this.setTitle("SZACHY");
                break;

        }

    }
}
