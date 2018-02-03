package Misc;

import Database.Match;
import Database.Player;
import Pieces.PieceType;
import Pieces.PlayerColor;
import org.slf4j.Logger;

import javax.persistence.EntityManagerFactory;
import java.awt.*;

public class Utils {
    public static Dimension WINDOW_SIZE = new Dimension(700,700);
    public static int BOARD_X = 50;
    public static int BOARD_Y = 50;
    public static int NUM_OF_TILES = 64;
    public static int TILE_WIDTH = 64;
    public static int HINT_RADIUS = 20;
    public static Color HINT_COLOR = Color.GREEN;
    public static Dimension BOARD_SIZE = new Dimension(TILE_WIDTH*9,TILE_WIDTH*9);
    public static boolean timeIsOver = false;
    public static String moves = "";
    public static int moveCounter = 1;
    public static Language language = Language.English;
    public static String skin = null;
    public static PlayerColor playerColor = PlayerColor.Black;
    public static Logger logger;
    public static Match match = new Match();
    public static Player player = new Player();
    public static EntityManagerFactory entityManagerFactory = null;
    public static boolean isCheck = false;
    public static boolean gameOver = false;
    public static PieceType promotedPieceType = PieceType.Queen;
}