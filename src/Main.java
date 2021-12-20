import Begin.Login;
import Music.AudioPlayDemo;

import javax.swing.*;

import Music.CanClickMusic;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Login login = new Login();
            try {
                login.init();
            } catch (IOException e) {
                e.printStackTrace();
            }

            new CanClickMusic().start();
            new AudioPlayDemo();



        });
    }
}
