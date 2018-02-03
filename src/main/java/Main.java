import Database.Queries;
import GameTable.Table;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(final String args[]) throws InterruptedException {
        new Init();
        //Queries.printDatabase();
        //new Windows.LogInWindow();
        new Table();

    }

}
