package GameTable;

import GameTable.Field;
import Pieces.Piece;

import javax.swing.*;

public class Tile extends Field {

    private JPanel tile;
    private boolean isOccupied;
    private Piece piece;

    public Tile(JPanel tile, boolean isOccupied) {
        this.tile = tile;
        this.isOccupied = isOccupied;
    }

    public JPanel getTile() {
        return tile;
    }

    public void setTile(JPanel tile) {
        this.tile = tile;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }
}
