package Windows;

import GameTable.Table;
import Misc.Language;
import Misc.Utils;
import Web.DownloadPage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static Appearance.Gui.setSkin;

public class StartWindow extends JFrame{
    private JButton start;
    private JButton exit;
    private JLabel time1;
    private boolean windowClosed;
     public StartWindow(){
         setSkin();
         this.setTitle("CHESS");
         this.setLayout(new GridBagLayout());
         this.setSize(500,300);
         this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         this.setLocationRelativeTo(null);

         GridBagConstraints gc = new GridBagConstraints();
         gc.weightx = 0.5;
         gc.weighty = 0.5;

         setMainButtons(gc);
         setTimeLabel(gc);
         checkLanguage();

         this.setVisible(true);
     }

     public void closeWindow(){
         windowClosed = true;
         this.dispose();
     }
     public void setMainButtons(GridBagConstraints gc){
         start = new JButton("PLAY");
         exit = new JButton("EXIT");
         start.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {

                 DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                 Date date = new Date();
                 Utils.match.setStart(dateFormat.format(date));
                 closeWindow();
                 new Table();
             }
         });
         exit.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                 new LogInWindow();
                 closeWindow();
             }
         });

         gc.gridx = 3;
         gc.gridy = 0;

         add(start,gc);

         gc.gridx = 3;
         gc.gridy = 1;

         add(exit,gc);
     }
     public void checkLanguage(){

        switch (Utils.language){
            case English:
                start.setText("PLAY");
                exit.setText("EXIT");
                this.setTitle("CHESS");

                break;

            case Polski:

                start.setText("GRAJ");
                exit.setText("WYJDŹ");
                this.setTitle("SZACHY");
                break;
        }
     }
     public void setTimeLabel(GridBagConstraints gc){
         String loading = "Loading...";
         if(Utils.language == Language.English){
             time1 = new JLabel("Loading...", SwingConstants.CENTER);
         }
         else{
             time1 = new JLabel("Ładowanie...", SwingConstants.CENTER);
         }

         time1.setFont(new Font("TimeFont",1, 14));
         gc.gridx = 1;
         gc.gridy = 4;
         add(time1,gc);
         runTime();


     }
     public void runTime(){
         SwingWorker<Void, Void> timeFromPage = new SwingWorker<Void, Void>() {
             @Override
             protected Void doInBackground() throws Exception {

                 DownloadPage downloadPage = null;
                 if(Utils.language == Language.English){
                     downloadPage = new DownloadPage(time1,"Warsaw");
                 }
                 else{
                     downloadPage = new DownloadPage(time1,"Warszawa");
                 }
                 while(!windowClosed){
                     time1.setText(downloadPage.takeTime());
                 }
                 return null;
             }
         };
         timeFromPage.execute();
     }
}
