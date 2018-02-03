package GameTable;

import Misc.Utils;

import javax.swing.*;
import java.awt.*;

public class HintCircle extends JPanel {

    private int radius;
    private Color color;
    private Point position;
    public HintCircle(Point position){
        this.radius = Utils.HINT_RADIUS;
        this.color = Utils.HINT_COLOR;
        this.position = position;
        this.setSize(radius,radius);
        this.setVisible(true);
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillOval((int)position.getX(),(int)position.getY(),radius, radius);
    }

    public Dimension getPreferredSize() {
        return new Dimension(radius, radius); // appropriate constants
    }

}
