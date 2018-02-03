package Windows;

import Database.Queries;
import Exceptions.PlayerAlreadyExistsException;
import Misc.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Appearance.Gui.setSkin;

public class RegistrationWindow extends JFrame {

    private JButton registerButton;
    private JButton exitButton;
    private JLabel nickLabel;
    private JLabel passwordLabel;
    private JTextField nickText;
    private JPasswordField passwordText;

    public RegistrationWindow(){
        setSkin();
        this.setTitle("REGISTRATION");
        this.setLayout(new GridBagLayout());
        this.setSize(500,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        GridBagConstraints gc = new GridBagConstraints();

        gc.weighty=0.2;
        gc.weightx=0.2;

        setRegistration(gc);
        checkLanguage();
        this.setVisible(true);
    }
    public void setRegistration(GridBagConstraints gc) {
        registerButton = new JButton("Register");
        exitButton = new JButton("Exit");
        nickLabel = new JLabel("Nick");
        passwordLabel = new JLabel("Password");
        nickText = new JTextField(12);
        passwordText = new JPasswordField(12);
        passwordText.setEchoChar('*');

        gc.gridx = 0;
        gc.gridy = 0;

        add(nickLabel, gc);

        gc.gridx = 0;
        gc.gridy = 1;

        add(nickText, gc);

        gc.gridx = 0;
        gc.gridy = 2;

        add(passwordLabel, gc);

        gc.gridx = 0;
        gc.gridy = 3;

        add(passwordText, gc);

        gc.gridx = 0;
        gc.gridy = 4;

        add(registerButton, gc);
        gc.gridx = 0;
        gc.gridy = 5;

        add(exitButton, gc);

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try{
                    if(Queries.checkRegistration(nickText.getText())){
                        Queries.addPlayer(nickText.getText(), passwordText.getText());
                        new LogInWindow();
                        closeWindow();
                    }
                } catch (PlayerAlreadyExistsException playerAlreadyExists) {
                }
            }
        });
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new LogInWindow();
                dispose();
            }
        });
    }
    public void closeWindow(){
        this.dispose();
    }
    public void checkLanguage(){

        switch (Utils.language){
            case English:

                registerButton.setText("Register");
                nickLabel.setText("Nick");
                passwordLabel.setText("Password");
                exitButton.setText("Exit");

                this.setTitle("CHESS");

                break;

            case Polski:


                registerButton.setText("Zarejestruj");
                nickLabel.setText("Nazwa");
                passwordLabel.setText("Haslo");
                exitButton.setText("Wyjdz");

                this.setTitle("SZACHY");
                break;

        }

    }

}
