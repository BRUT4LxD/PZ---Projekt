package Database;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "Match")
public class Match {

    public Match(){

    }
    public Match(String start, String end, String pgn, Player player) {
        this.start = start;
        this.end = end;
        this.pgn = pgn;
        this.player = player;
    }
    @Id
    @Column (name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@GeneratedValue(generator = "incrementator")
   // @GenericGenerator(name = "incrementator", strategy = "increment")
    private int id;

    @Column (name = "Start")
    private String start;

    @Column (name = "End")
    private String end;

    @Column (name = "PGN")
    private String pgn;

    @ManyToOne(fetch = FetchType.EAGER)
    private Player player;


    public String toString(){
        return "ID: " + id +
                "\nStart: " + start +
                "\nEnd: " + end +
                "\nPlayerName: " + player.getNick();
    }
    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getPgn() {
        return pgn;
    }

    public void setPgn(String pgn) {
        this.pgn = pgn;
    }

}
