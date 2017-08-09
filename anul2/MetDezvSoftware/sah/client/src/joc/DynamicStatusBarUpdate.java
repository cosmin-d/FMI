package joc;
import java.awt.event.*;
import java.io.Serializable;
import javax.swing.*;
import sah.*;

public class DynamicStatusBarUpdate extends MouseAdapter implements Serializable
{
	private GUI gui;
	
	public DynamicStatusBarUpdate(GUI gui)
	{
		this.gui = gui;
	}
	
	public void mouseEntered(MouseEvent e)
	{
		JButton temp = (JButton)e.getComponent();
		gui.dynamicUpdateStatusBar(temp);
	}
	public void mouseExited(MouseEvent e)
	{
		gui.updateStatusBar(" ", false);
	}
}
