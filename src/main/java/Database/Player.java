package Database;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Player")

public class Player {

    public Player(){

    }
    public Player(String nick, String password) {
        this.nick = nick;
        this.password = password;
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "Nick")
    private String nick;

    @Column(name = "Password")
    private String password;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "player")
    @Fetch(FetchMode.SELECT)
    private List<Match> matches;



    public String toString(){
        return "ID: " + id + "Nick: " + nick + "\n Password: " + password + "\nGames: " + matches.size();
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
