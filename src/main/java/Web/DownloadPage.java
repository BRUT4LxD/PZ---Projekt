package Web;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.TimeUnit;

public class DownloadPage implements Runnable {

    private JLabel time;
    private String city;
    private String previous = "";
    public DownloadPage(JLabel time, String city) {
        this.time = time;
        this.city = city;
    }
    public String takeTime(){
        URL url = null;
        try {
            TimeUnit.MILLISECONDS.sleep(10);
            url = new URL("https://time.is");
            // Get the input stream through URL Connection
            URLConnection con = url.openConnection();
            InputStream is =con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            int counter = 1;
            // read each line and write to System.out
            while ((line = br.readLine()) != null) {
                if(counter == 271){
                    line = line.substring(98,106);
                    return city + ": " + line;
                }

                counter++;
            }
        } catch (MalformedURLException e) {
            System.out.println("URL does not exist!!");
        } catch (IOException e) {
            System.out.println("Cannot read file!");
        } catch (InterruptedException e) {
            System.out.println("Time unit error!");
        }
        return null;
    }

    public void run() {

        while(true){
            URL url = null;
            try {
                TimeUnit.MILLISECONDS.sleep(10);
                url = new URL("https://time.is");
                // Get the input stream through URL Connection
                URLConnection con = url.openConnection();
                InputStream is =con.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line = null;
                int counter = 1;
                // read each line and write to System.out
                while ((line = br.readLine()) != null) {
                    if(counter == 271){
                        line = line.substring(98,106);
                        if(line != previous){
                            this.time.setText(city + ": " +line);
                            previous = line;
                        }
                        break;
                    }

                    counter++;
                }
            } catch (MalformedURLException e) {
                System.out.println("URL does not exist!!");
            } catch (IOException e) {
                System.out.println("Cannot read file!");
            } catch (InterruptedException e) {
                System.out.println("Time unit error!");
            }
        }
    }
}