package joc;
import java.awt.*;
import java.io.Serializable;
import sah.*;
import javax.swing.*;

public class ToolBar extends JPanel implements Serializable
{
	private static final long serialVersionUID = 956862821780246300L;

        public JPanel title;
        public JButton finish;
        public JButton undo;
        public JButton draw;
        public JButton chat;
        public StopWatch whiteChronometer;
        public StopWatch blackChronometer;
        
	public ToolBar(GUI listener, Dimension size)
	{
		setPreferredSize(size);
		setOpaque(true);
		setBackground(new Color(90, 24, 24));
                setLayout(null);

                JLabel time = new JLabel("TIMP RAMAS");
                time.setOpaque(false);
                time.setFont(new Font("Verdana", Font.PLAIN, 20));
                time.setForeground(Color.WHITE);
                time.setBounds(20, 50, 200, 100);
                add(time);
                
                JLabel white = new JLabel("ALB");
                white.setOpaque(false);
                white.setFont(new Font("Verdana", Font.PLAIN, 15));
                white.setForeground(Color.WHITE);
                white.setBounds(30, 130, 100, 20);
                add(white);
                
                whiteChronometer = new StopWatch(listener);
                whiteChronometer.setOpaque(false);
                whiteChronometer.setBounds(-8,150,100,50);
                
                JLabel black = new JLabel("NEGRU");
                black.setOpaque(false);
                black.setFont(new Font("Verdana", Font.PLAIN, 15));
                black.setForeground(Color.WHITE);
                black.setBounds(100, 130, 100, 20);
                add(black);
                
                blackChronometer = new StopWatch(listener);
                blackChronometer.setOpaque(false);
                blackChronometer.setBounds(75,150,100,50);
                

		finish = new JButton("TERMINA TURA");
		finish.setPreferredSize(new Dimension(size.width-20, 50));
                finish.setBounds(10, 220, 150, 50);
		finish.setBorder(BorderFactory.createRaisedBevelBorder());
		finish.setBackground(Color.WHITE);
		
		undo = new JButton("ANULEAZA MUTARE");
		undo.setPreferredSize(new Dimension(size.width-20, 50));
                undo.setBounds(10, 290, 150, 50);
		undo.setBorder(BorderFactory.createRaisedBevelBorder());
		undo.setBackground(Color.LIGHT_GRAY);
                undo.setEnabled(false);		
		
		draw = new JButton("DECLARA REMIZA");
		draw.setPreferredSize(new Dimension(size.width-20, 50));
                draw.setBounds(10, 360, 150, 50);
		draw.setBorder(BorderFactory.createRaisedBevelBorder());
		draw.setBackground(Color.WHITE);	
		
		chat = new JButton("INTRA IN CHAT");
		chat.setPreferredSize(new Dimension(size.width-20, 50));
                chat.setBounds(10, 430, 150, 50);
		chat.setBorder(BorderFactory.createRaisedBevelBorder());
		chat.setBackground(Color.LIGHT_GRAY);

		finish.setActionCommand("-20");
		undo.setActionCommand("-21");
		draw.setActionCommand("-22");
		chat.setActionCommand("-23");
		
		finish.addActionListener(listener);
		undo.addActionListener(listener);
		draw.addActionListener(listener);
		chat.addActionListener(listener);
		
		add(finish);
		add(undo);
		add(draw);
		add(chat);
                add(whiteChronometer);
                add(blackChronometer);
	}
}
