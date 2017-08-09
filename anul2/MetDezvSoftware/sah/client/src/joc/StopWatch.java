package joc;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;
import javax.swing.*;
import sah.*;

public class StopWatch extends JPanel implements Serializable
{

	private Timer myTimer1;
	public static final int ONE_SEC = 1000;   
	public static final int TENTH_SEC = 100;

	private Font myClockFont;
        public JTextArea timeLbl;
        private GUI gui;

	private int clockTick;  	// timpul in milisecunde
	private int clockTime;  	// timpul in secunde
	private String clockTimeString;
        private int min1;
        private int min2;


	public StopWatch(GUI gui){
                
                this.gui = gui;
		myClockFont = new Font("Verdana", Font.PLAIN, 18);
                timeLbl = new JTextArea();
                timeLbl.setSize(100,50);
                timeLbl.setOpaque(false);
                timeLbl.setEditable(false);
		timeLbl.setFont(myClockFont);
                timeLbl.setForeground(Color.WHITE);
		timeLbl.setText("30 : 00");
                add(timeLbl);
                
                // pornesc cronometrul de la 29 : 59
                clockTick = 600; // setez timpul unui minut
                min1 = 2; // setez prima cifra a minutului cu 2 
                min2 = 9; // setez a doua cifra a minutlui cu 9
		clockTime = clockTick/10;                
		clockTimeString = new Double(clockTime).toString();
                
		myTimer1 = new Timer(TENTH_SEC, new ActionListener() {
		public void actionPerformed(ActionEvent evt) {
                    
                        clockTick--;
			clockTime = clockTick/10;
                        
                         if( min1 == 0 && min2 == 0 && clockTick == 0){
                             // a expirat timpului unui jucator, deci acesta a pierdut
                             lost();
                         }
  
                        if (clockTick == 0){
                            min2--;
                            if( min2 == -1 ){
                                min1--;
                                min2 = 9;
                            }
                            clockTick += 599;
                            clockTime = clockTick/10;
                        }
                            
                        if (clockTime < 10) 
                                 clockTimeString = Integer.toString(min1) + Integer.toString(min2) + " : 0" + Integer.toString(clockTime);
                        else 
                            clockTimeString = Integer.toString(min1) + Integer.toString(min2) + " : " + Integer.toString(clockTime);                   
                            
                        timeLbl.setText(clockTimeString);
                        
		    }
		});

	}

	public void launchStopWatch(){

            myTimer1.start();

	}
        
        public void stopStopWatch(){
            
            myTimer1.stop();
        }
        
        public void lost(){
            
            gui.updateStatusBar("Timpul a expirat", true);
            gui.gameOver(3);
        }

}

