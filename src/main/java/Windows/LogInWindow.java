package Windows;

import Appearance.Skin;
import Appearance.SkinTypes;
import Database.Queries;
import Exceptions.PlayerNotFound;
import GameTable.Table;
import Misc.Language;
import Misc.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static Appearance.Gui.setSkin;
import static Misc.Language.*;

public class LogInWindow extends JFrame {

    private JButton logInButton;
    private JLabel nickLabel;
    private JLabel passwordLabel;
    private JTextField nickText;
    private JPasswordField passwordText;
    private JButton registration;

    private JLabel skinsLabel;
    private JButton skin1Button;
    private JButton skin2Button;
    private JButton skin3Button;
    private JButton skin4Button;
    private JButton skin5Button;



    private JLabel languageLabel;
    private JButton engButton;
    private JButton polButton;

    public LogInWindow(){

        setSkin();
        this.setTitle("CHESS");
        this.setLayout(new GridBagLayout());
        this.setSize(600,500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        GridBagConstraints gc = new GridBagConstraints();

        gc.weighty=0.1;
        gc.weightx=0.1;

        setLogIn(gc);
        setSkinsSettings(gc);
        setLanguageSettings(gc);

        this.setVisible(true);

    }

    public void setLogIn(GridBagConstraints gc){
        setSkin();
        logInButton = new JButton("Log in");
        nickLabel = new JLabel("NICK");
        passwordLabel = new JLabel("Password");
        nickText = new JTextField(12);
        passwordText = new JPasswordField(12);
        registration = new JButton("Register");
        passwordText.setEchoChar('*');
        gc.gridx = 3;
        gc.gridy = 0;

        add(nickLabel, gc);

        gc.gridx = 3;
        gc.gridy = 1;

        add(nickText, gc);

        gc.gridx = 3;
        gc.gridy = 2;

        add(passwordLabel, gc);

        gc.gridx = 3;
        gc.gridy = 3;

        add(passwordText, gc);

        gc.gridx = 3;
        gc.gridy = 4;

        add(logInButton, gc);

        gc.gridx = 3;
        gc.gridy = 5;

        add(registration, gc);



        logInButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                /*try {
                    Utils.player = Queries.checkLogIn(nickText.getText(), passwordText.getText());
                    closeWindow();
                    new StartWindow();
                } catch (PlayerNotFound playerNotFound) {
                }*/
                closeWindow();
                new Table();

            }
        });
        registration.addActionListener((new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                closeWindow();
                new RegistrationWindow();
            }
        }));

    }
    public void setLanguageSettings(GridBagConstraints gc){

        polButton = new JButton("Polski");
        engButton = new JButton("English");
        languageLabel = new JLabel("Language");
        languageLabel.setFont(new Font("title", 1 , 15));

        polButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Utils.language = Polski;
                setLanguage();
                repaint();
            }
        });
        engButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Utils.language = English;
                setLanguage();
                repaint();
            }
        });
        gc.gridx = 3;
        gc.gridy = 8;

        add(languageLabel,gc);

        gc.gridx = 2;
        gc.gridy = 9;

        add(polButton,gc);

        gc.gridx = 4;
        gc.gridy = 9;

        add(engButton,gc);


    }
    public void setLanguage(){

        switch (Utils.language){
            case English:

                logInButton.setText("Log in");
                nickLabel.setText("NICK");
                passwordLabel.setText("Password");
                registration.setText("Register");
                skin1Button.setText("Skin 1");
                skin2Button.setText("Skin 2");
                skin3Button.setText("Skin 3");
                skin4Button.setText("Skin 4");
                skin5Button.setText("Skin 5");
                languageLabel.setText("Language");
                skinsLabel.setText("Skin");

                this.setTitle("CHESS");

                break;

            case Polski:

                logInButton.setText("Zaloguj sie");
                nickLabel.setText("Nazwa");
                passwordLabel.setText("Haslo");
                registration.setText("Zarejestruj sie");
                skin1Button.setText("Skórka 1");
                skin2Button.setText("Skórka 2");
                skin3Button.setText("Skórka 3");
                skin4Button.setText("Skórka 4");
                skin5Button.setText("Skórka 5");
                languageLabel.setText("Język");
                skinsLabel.setText("Skórka");

                this.setTitle("SZACHY");
                break;

        }

    }

    public void setSkinsSettings(GridBagConstraints gc){

        skin1Button = new JButton("Skin 1");
        skin2Button = new JButton("Skin 2");
        skin3Button = new JButton("Skin 3");
        skin4Button = new JButton("Skin 4");
        skin5Button = new JButton("Skin 5");
        skinsLabel = new JLabel("Skin");
        skinsLabel.setFont(new Font("label", 1 , 15));

        skin1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<UIManager.LookAndFeelInfo> looks = new ArrayList<UIManager.LookAndFeelInfo>();
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    looks.add(info);
                }
                Skin.background = Color.white;
                Skin.black = Color.BLUE;
                Skin.white = Color.ORANGE;
                Utils.skin = SkinTypes.Metal;
                new LogInWindow();
                dispose();
            }
        });
        skin2Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Skin.background = Color.GRAY;
                Skin.black = Color.BLUE;
                Skin.white = Color.WHITE;
                Utils.skin = SkinTypes.Nimbus;
                new LogInWindow();
                dispose();
            }
        });
        skin3Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Skin.background = Color.pink;
                Skin.black = Color.red;
                Skin.white = Color.BLUE;
                Utils.skin = SkinTypes.windows;
                new LogInWindow();
                dispose();
            }
        });
        skin4Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Skin.background = Color.pink;
                Skin.black = Color.red;
                Skin.white = Color.BLUE;
                Utils.skin = SkinTypes.Normal;
                new LogInWindow();
                dispose();
            }
        });
        skin5Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Skin.background = Color.pink;
                Skin.black = Color.red;
                Skin.white = Color.BLUE;
                Utils.skin = SkinTypes.Metal1;
                new LogInWindow();
                dispose();
            }
        });

        gc.gridx = 3;
        gc.gridy = 6;

        add(skinsLabel,gc);

        gc.gridx = 1;
        gc.gridy = 7;

        add(skin1Button,gc);
        gc.gridx = 2;
        gc.gridy = 7;

        add(skin2Button,gc);
        gc.gridx = 3;
        gc.gridy = 7;

        add(skin3Button,gc);
        gc.gridx = 4;
        gc.gridy = 7;

        add(skin4Button,gc);
        gc.gridx = 5;
        gc.gridy = 7;

        add(skin5Button,gc);


    }
    public void closeWindow(){
        this.dispose();
    }


}
