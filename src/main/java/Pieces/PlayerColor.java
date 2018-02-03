package Pieces;

public enum PlayerColor {
    Black("BLACK"),
    White("WHITE"),
    Unknown("UNKNOWN");

    private String name;

    public String getName() {
        return name;
    }

    PlayerColor(String name) {
        this.name = name;
    }
}