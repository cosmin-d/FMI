package joc;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Knight extends ChessPiece implements Serializable
{
	private static final long serialVersionUID = 851797694201220077L;
	
	public Knight(boolean isWhite, Location loc)
	{
		super(isWhite, loc);
	}
	
	public void draw(Graphics g)
	{
		final int x = 6;
		final int y = 6;
		final int width = 40;
		if(getColor())
		{
			g.setColor(Color.WHITE);
			g.setFont(new Font("Courier", g.getFont().getStyle(), width));
                         try {
                            Image bb = ImageIO.read(getClass().getResource("../img/wn.png"));
                            g.drawImage(new ImageIcon(bb).getImage(), x, y, null);
                        } catch (IOException ex) {}
		}
		else
		{
			g.setColor(Color.BLACK);
			g.setFont(new Font("Courier", g.getFont().getStyle(), width));
                         try {
                            Image bb = ImageIO.read(getClass().getResource("../img/bn.png"));
                            g.drawImage(new ImageIcon(bb).getImage(), x, y, null);
                        } catch (IOException ex) {}
		}
	}
	
	public ArrayList<Location> getMoves(BoardState board)
	{
		ArrayList<Location> possibleMoves = new ArrayList<Location>();
		int y = getLocation().getRow();
		int x = getLocation().getCol();
		
		Location[] locs = new Location[8];
		locs[0] = new Location(y-1,x-2);
		locs[1] = new Location(y-2,x-1);
		locs[2] = new Location(y-2,x+1);
		locs[3] = new Location(y-1,x+2);
		locs[4] = new Location(y+1,x+2);
		locs[5] = new Location(y+2,x+1);
		locs[6] = new Location(y+2,x-1);
		locs[7] = new Location(y+1,x-2);
		
		if(getColor())
		{
			for(Location z: locs)
				if(board.isValid(z) && (board.isEmpty(z) || !board.isPieceWhite(z)))
					possibleMoves.add(z);
		}
		else
		{
			for(Location z: locs)
				if(board.isValid(z) && (board.isEmpty(z) || board.isPieceWhite(z)))
					possibleMoves.add(z);
		}
		return possibleMoves;
	}
	
	public void moveTo(Location moveLoc)
	{
		setLocation(moveLoc);
	}
	
	public String toString()
	{
		return super.toString()+" Knight";
	}
}