package joc;
import java.awt.*;
import java.io.Serializable;
import javax.swing.*;
import sah.*;

public class PromotionPopup extends PopupWindow implements Serializable
{
	private static final long serialVersionUID = 5337411370661157823L;

	public PromotionPopup(GUI listener)
	{
		super(listener, "Promoveaza pionul la:");
		
		setLayout(new GridLayout(2,2));
		
		JButton[] pieces = new JButton[4];
		pieces[0] = new JButton("Regina");
		pieces[1] = new JButton("Tura");
		pieces[2] = new JButton("Nebun");
		pieces[3] = new JButton("Cal");
		
		for(int x=0; x<pieces.length; x++)
		{
			pieces[x].addActionListener(listener);
			pieces[x].setActionCommand("-"+(x+1));
			add(pieces[x]);
		}
		
		pack();
		resetLocation();
	}
}