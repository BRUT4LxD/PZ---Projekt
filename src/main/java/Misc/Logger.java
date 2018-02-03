package Misc;

import org.slf4j.LoggerFactory;

public class Logger {
    private static Logger ourInstance = new Logger();

    public static Logger getInstance() {
        return ourInstance;
    }

    private Logger() {
    }
    public void setLogger(){
        org.slf4j.Logger logger= LoggerFactory.getLogger("ChessLogger");
        logger.info("LOGGER STARTED!!!");
        Utils.logger = logger;
    }
}
