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

public class Queen extends ChessPiece implements Serializable
{
	private static final long serialVersionUID = 716804568379608862L;
	
	public Queen(boolean isWhite, Location loc)
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
                            Image bb = ImageIO.read(getClass().getResource("../img/wq.png"));
                            g.drawImage(new ImageIcon(bb).getImage(), x, y, null);
                        } catch (IOException ex) {}
		}
		else
		{
			g.setColor(Color.BLACK);
			g.setFont(new Font("Courier", g.getFont().getStyle(), width));
                         try {
                            Image bb = ImageIO.read(getClass().getResource("../img/bq.png"));
                            g.drawImage(new ImageIcon(bb).getImage(), x, y, null);
                        } catch (IOException ex) {}
		}
	}
	
	public ArrayList<Location> getMoves(BoardState board)
	{
		ArrayList<Location> possibleMoves = new ArrayList<>();
		int y = getLocation().getRow();
		int x = getLocation().getCol();
		
		boolean[] isRowBlocked = new boolean[8];
		
		Location[] locs = new Location[64];
		
		for(int z=1; z<=64; z++)
		{
			if(z<=8)
				locs[z-1] = new Location(y-z%8, x-z%8);
			else if(z<=16)
				locs[z-1] = new Location(y-z%8, x);
			else if(z<=24)
				locs[z-1] = new Location(y-z%8, x+z%8);
			else if(z<=32)
				locs[z-1] = new Location(y, x+z%8);
			else if(z<=40)
				locs[z-1] = new Location(y+z%8, x+z%8);
			else if(z<=48)
				locs[z-1] = new Location(y+z%8, x);
			else if(z<=56)
				locs[z-1] = new Location(y+z%8, x-z%8);
			else
				locs[z-1] = new Location(y, x-z%8);
		}

		if(getColor())
		{
			for(int i=0; i<locs.length; i++)
				if(board.isValid(locs[i]) && !isRowBlocked[i/8] && (board.isEmpty(locs[i]) || !board.isPieceWhite(locs[i])))
				{
					possibleMoves.add(locs[i]);
					if(!board.isEmpty(locs[i]) && !board.isPieceWhite(locs[i]))
						isRowBlocked[i/8]=true;
				}
				else
					isRowBlocked[i/8]=true;
		}
		else
		{
			for(int i=0; i<locs.length; i++)
				if(board.isValid(locs[i]) && !isRowBlocked[i/8] && (board.isEmpty(locs[i]) || board.isPieceWhite(locs[i])))
				{
					possibleMoves.add(locs[i]);
					if(!board.isEmpty(locs[i]) && board.isPieceWhite(locs[i]))
						isRowBlocked[i/8]=true;
				}
				else
					isRowBlocked[i/8]=true;
		}
		return possibleMoves;
	}

	public void moveTo(Location moveLoc)
	{
		setLocation(moveLoc);
	}
	
	public String toString()
	{
		return super.toString()+" Queen";
	}
}