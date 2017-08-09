package joc;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import sah.*;

public class GameOverPopup extends PopupWindow implements Serializable
{
	private static final long serialVersionUID = 1400666790706351389L;
	private JLabel text;
	private JButton no, yes;
	
	public GameOverPopup(final GUI parent, String title,final GUI g)
	{
		super(parent, title);
		
		text = new JLabel(title + "\n Doresti sa primiti mutarile jocului prin e-mail?");
                no = new JButton("Nu");
                yes = new JButton("Da");
                no.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        g.setVisible(false);
                    }
                });
                yes.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        //preiau mutarile 
                        ArrayList<JLabel> pgn = parent.getPgnPanel().getPgn();
                        for(int i = 0; i < pgn.size(); i++)
                        {
                                String temp = pgn.get(i).getText().substring(pgn.get(i).getText().indexOf(".")+1).trim();
                                if(temp.length()==0)
                                {
                                        pgn.remove(i);
                                        i--;
                                }
                        }
                        for(int i = 0; i < pgn.size(); i++)
                            System.out.print(pgn.get(i).getText().substring(3) + "\n" );
                        
                        //salvez mutarile intr-un fisier
                        try{
                            File mutari = new File("mutari.txt");
                            if (!mutari.exists()) {
                                    mutari.createNewFile();
                            }                           
                            FileWriter fw = new FileWriter(mutari.getAbsoluteFile());
                            BufferedWriter bw = new BufferedWriter(fw);
                            for( int i = 0; i < pgn.size(); i++ ){
                                bw.write(pgn.get(i).getText().substring(3));
                                bw.newLine();
                            }
                            bw.close();
                        } catch(IOException f) {}
                        try {
                            g.getGR().dos.writeUTF("trimail");
                        } catch (IOException ex) {
                            Logger.getLogger(GameOverPopup.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        g.setVisible(false);
                    }
                });

		JPanel top = new JPanel();
		top.setBackground(new Color(0,0,0,0));
		top.add(text);
		JPanel bottom = new JPanel();
		bottom.setBackground(new Color(0,0,0,0));
                bottom.add(yes);
                bottom.add(no);
		add(top);
		add(bottom);
		
		pack();
		resetLocation();
	}
}
