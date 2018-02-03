package Pieces;

public enum PieceType {
    King(1000,"K",""),
    Queen(9,"Q",""),
    Rook(5,"R",""),
    Bishop(3,"B",""),
    Knight(3,"N",""),
    Pawn(1,"P","");

    private int value;
    private String abr;
    private String longName;
    PieceType(int value,String abr, String longName) {
        this.value = value;
        this.abr = abr;
        this.longName = longName;
    }

    public void setLongName(String longName) {
        this.longName = longName;
    }

    public String getAbr() {
        return abr;
    }

    public int getValue() {
        return value;
    }
}