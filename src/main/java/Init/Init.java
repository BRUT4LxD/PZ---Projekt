package Init;

import Appearance.SkinTypes;
import Misc.Language;
import Misc.Logger;
import Misc.Utils;
import Pieces.PieceType;
import Pieces.PlayerColor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.persistence.Persistence;
import java.io.*;
import java.util.Properties;

public class Init {

    public Init(){
        getPropeties();
        setLogger();
        setDatabase();
        initXML();
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
        setLanguage(p.getProperty("Language"));
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
    private void setLanguage(String language){
        if(language.equals("english")){
            Utils.language = Language.English;
        }
        if(language.equals("polish")){
            Utils.language = Language.Polski;
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

    private void initXML(){

        try {

            File fXmlFile = new File("pieces.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            //optional, but recommended
            //read this - http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            doc.getDocumentElement().normalize();

            NodeList nList = doc.getElementsByTagName("Piece");


            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);

                System.out.println("\nCurrent Element :" + nNode.getNodeName());

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    PieceType.King.setLongName(eElement.getElementsByTagName("King").item(0).getTextContent());
                    PieceType.Queen.setLongName(eElement.getElementsByTagName("Queen").item(0).getTextContent());
                    PieceType.Knight.setLongName(eElement.getElementsByTagName("Knight").item(0).getTextContent());
                    PieceType.Rook.setLongName(eElement.getElementsByTagName("Rook").item(0).getTextContent());
                    PieceType.Pawn.setLongName(eElement.getElementsByTagName("Pawn").item(0).getTextContent());
                    PieceType.Bishop.setLongName(eElement.getElementsByTagName("Bishop").item(0).getTextContent());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
