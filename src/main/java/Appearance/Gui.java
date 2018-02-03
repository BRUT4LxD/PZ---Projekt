package Appearance;

import Misc.Utils;

import javax.swing.*;

public class Gui {



    public static void createInfoMessage(String infoMessage, String titleBar){
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    public static void setSkin(){
        try {
            if(Utils.skin == null) Utils.skin = SkinTypes.Nimbus;

            UIManager.setLookAndFeel(Utils.skin);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
    }

}
