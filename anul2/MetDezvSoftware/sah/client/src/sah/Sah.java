package sah;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Sah {

    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new GUI1().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Sah.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

}