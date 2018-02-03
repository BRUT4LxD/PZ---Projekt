package GameTable;

import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

public class Time implements Runnable{

    public boolean timeToMove;
    public Semaphore isRunning;
    private Timer timer;


    public Time(boolean timeToMove) {
        this.timeToMove = timeToMove;
        if(timeToMove){
            isRunning = new Semaphore(1);
        }
    }

    @Override
    public void run() {

    }

    public void processMove(){

    }
    private void setTimer(){
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
             //do somethig
            }
        },10);
        timer.toString();
    }
}
