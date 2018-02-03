import Appearance.SkinTypes;
import Database.Queries;
import Misc.Logger;
import Misc.Utils;
import Pieces.PlayerColor;

import javax.persistence.Persistence;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Init {

    Init(){
        getPropeties();
        setLogger();
        //setDatabase();
    }
    private void getPropeties() {
        try{

            Properties p = new Properties();
            InputStream is = new FileInputStream("config.properties");
            p.load(is);
            setUtilsFromProperties(p);
        } catch (FileNotFoundException e) {
            System.out.println("Properties file not found!");
        } catch (IOException e) {
            System.out.println("Properites file could not load");
        }


    }
    private void setUtilsFromProperties(Properties p ){
        setTheme(p.getProperty("Theme"));
        //setPlayerColor(p.getProperty("PlayerColor"));
    }
    private void setTheme(String theme){
        if( theme.equals("nimbus")){
            Utils.skin = SkinTypes.Nimbus;
        }
        else if( theme.equals("metal")){
            Utils.skin = SkinTypes.Metal;
        }
        else if( theme.equals("metal1")){
            Utils.skin = SkinTypes.Metal1;
        }
        else if( theme.equals("normal")){
            Utils.skin = SkinTypes.Normal;
        }
        else if( theme.equals("windows")){
            Utils.skin = SkinTypes.windows;
        }

    }
    private void setPlayerColor(String playerColor1){
        if(playerColor1.equals("black")) Utils.playerColor = PlayerColor.Black;
        else if(playerColor1.equals("white")) Utils.playerColor = PlayerColor.White;
        else Utils.playerColor = PlayerColor.Black;
    }
    private void setDatabase(){
        Utils.entityManagerFactory = Persistence.createEntityManagerFactory("PZ");
    }
    private void setLogger(){
        Logger logger = Logger.getInstance();
        logger.setLogger();
    }
}
