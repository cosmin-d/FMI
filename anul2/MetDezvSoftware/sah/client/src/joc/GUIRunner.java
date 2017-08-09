package joc;

import sah.*;
import java.util.*;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GUIRunner implements Serializable
{
	private static final long serialVersionUID = -5502586734068739286L;
	private Location selectedPiece, promotedPiece;
	private BoardState board;
	private GUI gui;
	private boolean isWhiteTurn;
        private boolean playerMoved;
	private boolean highlight;
        private boolean color;
	public ArrayList<Integer> undoMoves;
	public ArrayList<Integer> wasPieceTaken;
	private ArrayList<Location> moves;
        String oponent;
        boolean vizitator, replay,vaz;
	Socket cs;
        DataInputStream dis = null;
        DataOutputStream dos = null;
        
	public GUIRunner(Socket client, String op, boolean c,int priv, boolean viz)
	{
                color = c;
                oponent = op;
                cs = client;
                replay = false;
                vizitator = viz;
            try {
                dis = new DataInputStream(cs.getInputStream());
                dos = new DataOutputStream(cs.getOutputStream());
                if(c){
                    dos.writeUTF("partida");
                    dos.writeUTF(op);
                    dos.writeInt(priv);
                }
            } catch (IOException ex) {
                Logger.getLogger(GUIRunner.class.getName()).log(Level.SEVERE, null, ex);
            }
                System.out.print("Jocul a inceput!");
		board = new BoardState();
		gui = new GUI(board, this);
                gui.getToolBar().whiteChronometer.launchStopWatch();
		highlight = true;
		moves = new ArrayList<>();
	}
	
        public GUIRunner()
	{
                color = true;
                vizitator = true;
                replay = true;
                System.out.print("Jocul a inceput!");
		board = new BoardState();
		gui = new GUI(board, this);
                gui.getToolBar().whiteChronometer.launchStopWatch();
		highlight = true;
		moves = new ArrayList<>();
	}
                
        public void setSelectedPiece(Location loc){
            selectedPiece = loc;
        }
        
        public Location getSelectedPiece(){
            return selectedPiece;
        }
        
        public boolean getPlayerMoved(){
            return playerMoved;
        }
        
	public void setVisible()
	{
                
		gui.setVisible(true);
		newGame();
	}
	
	public void newGame()
	{
		board.resetBoardState();
		selectedPiece = null;
		isWhiteTurn = true;
                playerMoved = false;
		board.setTurn(isWhiteTurn);
		gui.updateBoard(board);
		gui.enableSide(isWhiteTurn);
		undoMoves = new ArrayList<>();
		wasPieceTaken = new ArrayList<>();
		gui.newGame();
	}
	
        public BoardState getBoard(){
            return board;
        }
        
        public Location getPromotedPiece(){
            return promotedPiece;
        }
        
        public void setPlayerMoved(boolean b){
            playerMoved = b;
        }
        
	public boolean getTurn()
	{
		return isWhiteTurn;
	}
	
        public void setIsWhiteTurn(boolean b){
            isWhiteTurn = b;
        }
        
	public ArrayList<Location> getMoves(Location piece)
	{
		ArrayList<Location> allMoves = board.getState()[piece.getRow()][piece.getCol()].getMoves(board);
		for(int a=0; a<allMoves.size(); a++)
			if(!isLegal(piece, allMoves.get(a), 0))
			{
				allMoves.remove(a);
				a--;
			}
		return allMoves;
	}
        
        public GUI getGui()
        {
            return gui;
        }
	
	public void checkGameOver()
	{       
		ArrayList<Location> moves = new ArrayList<>();
		ArrayList<Location> temp = new ArrayList<>();
		for(int y=0; y<board.getState().length; y++)
			for(int x=0; x<board.getState().length; x++)
			{
				if(board.getState()[y][x]!=null && board.getState()[y][x].getColor()==isWhiteTurn)
				{       
					temp = getMoves(new Location(y,x));
					for(Location z : temp)
						moves.add(z);
					if(moves.size()>0)
						break;
				}
				if(moves.size()>0)
					break;
			}
                
		if(moves.isEmpty())
		{       
			boolean isCheck = true;
			for(int y=0; y<board.getState().length; y++)
				for(int x=0; x<board.getState().length; x++)
					if(board.getState()[y][x]!=null && board.getState()[y][x] instanceof King && board.getState()[y][x].getColor()==isWhiteTurn)
					{       
						isCheck = ((King)board.getState()[y][x]).isChecked();
						break;
					}
                        
			if(isCheck != true)
			{      
                            try{
				gui.updateStatusBar("Sah mat!", true);
                                if(replay == false){
                                    if(isWhiteTurn){
                                        if(color == isWhiteTurn){
                                            dos.writeUTF("castig");
                                            dos.writeUTF(oponent);
                                        }
                                        gui.gameOver(-1);
                                    }
                                    else{
                                        gui.gameOver(1);
                                    }
                                }
                            }
                            catch (IOException ex) {
                                Logger.getLogger(GUIRunner.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                        else
                        {
                            gui.updateStatusBar("Remiza fortata!", true);
                            gui.gameOver(0);
                        }
                }
                
		boolean isWhiteKnight = false;
		boolean isWhiteBishop = false;
		boolean isBlackKnight = false;
		boolean isBlackBishop = false;
		boolean isDraw = true;
		for(int y=0; y<board.getState().length; y++)
			for(int x=0; x<board.getState().length; x++)
			{
				if(board.getState()[y][x]!=null && (board.getState()[y][x] instanceof Queen || board.getState()[y][x] instanceof Rook || board.getState()[y][x] instanceof Pawn))
				{
					isDraw=false;
					break;
				}
				else if(board.getState()[y][x]!=null && board.getState()[y][x] instanceof Bishop && board.getState()[y][x].getColor()==isWhiteTurn)
					isWhiteBishop=true;
				else if(board.getState()[y][x]!=null && board.getState()[y][x] instanceof Knight && board.getState()[y][x].getColor()==isWhiteTurn)
					isWhiteKnight = true;
				else if(board.getState()[y][x]!=null && board.getState()[y][x] instanceof Bishop && board.getState()[y][x].getColor()!=isWhiteTurn)
					isBlackBishop=true;
				else if(board.getState()[y][x]!=null && board.getState()[y][x] instanceof Knight && board.getState()[y][x].getColor()!=isWhiteTurn)
					isBlackKnight = true;
			}
		if(isDraw)
			if(isWhiteBishop && isWhiteKnight || isBlackBishop && isBlackKnight || isBlackBishop && isWhiteBishop || isBlackKnight && isWhiteKnight)
				isDraw = false;
		if(isDraw)
		{
			gui.updateStatusBar("Draw!", true);
			gui.gameOver(0);
		}
	}
	
        public void setPromotedPiece(Location loc){
            promotedPiece = loc;
        }
        
	public void checkPromotion(Location loc)
	{
		if(board.getState()[loc.getRow()][loc.getCol()] instanceof Pawn && (loc.getRow()==0 || loc.getRow()==7))
		{
			gui.promotion();
			promotedPiece = loc;
		}
	}
	
	public boolean isLegal(Location start, Location end, int restraint)
	{
		boolean isLegal = true;
		boolean complete = true;
		int queue;
		
		if(restraint!=1 && board.getState()[start.getRow()][start.getCol()] instanceof King)
		{
			if(start.getRow()==end.getRow() && Math.abs(start.getCol()-end.getCol())==2)
			{
				((King)board.getState()[start.getRow()][start.getCol()]).updateIsChecked(board);
				if(((King)board.getState()[start.getRow()][start.getCol()]).getHasMoved() || ((King)board.getState()[start.getRow()][start.getCol()]).isChecked())
				{
					isLegal = false;
					complete = false;
				}
				else if(end.getCol()==2 && board.isEmpty(new Location(start.getRow(),start.getCol()-1)) && board.isEmpty(new Location(start.getRow(),start.getCol()-2)) && board.isEmpty(new Location(start.getRow(),start.getCol()-3)) && !board.isEmpty(new Location(start.getRow(),start.getCol()-4)) && board.getState()[start.getRow()][start.getCol()-4] instanceof Rook && !((Rook)board.getState()[start.getRow()][start.getCol()-4]).getHasMoved())
				{
					if(isLegal(start, new Location(start.getRow(), start.getCol()-1), 1) && isLegal(start, end, 1))
					{
						isLegal = true;
						complete = false;
					}
					else
					{
						isLegal = false;
						complete = false;
					}
				}
				else if(end.getCol()==6 && board.isEmpty(new Location(start.getRow(),start.getCol()+1)) && board.isEmpty(new Location(start.getRow(),start.getCol()+2)) && !board.isEmpty(new Location(start.getRow(),start.getCol()+3)) && board.getState()[start.getRow()][start.getCol()+3] instanceof Rook && !((Rook)board.getState()[start.getRow()][start.getCol()+3]).getHasMoved())
				{
					if(isLegal(start, new Location(start.getRow(), start.getCol()+1), 1) && isLegal(start, end, 1))
					{
						isLegal = true;
						complete = false;
					}
					else
					{
						isLegal = false;
						complete = false;
					}
				}
				else
				{
					isLegal = false;
					complete = false;
				}
			}
		}
		
		if(complete)
		{
			queue = board.saveState();
			board.moveFrom_To(start, end);
			for(int y=0; y<board.getState().length; y++)
				for(int x=0; x<board.getState().length; x++)
					if(board.getState()[y][x]!=null && board.getState()[y][x] instanceof King && board.getState()[y][x].getColor()==isWhiteTurn)
					{
						((King)board.getState()[y][x]).updateIsChecked(board);
						isLegal = !((King)board.getState()[y][x]).isChecked();
						break;
					}
			board.undoMove(queue);
		}
		
		return isLegal;
	}
	
	public void movePiece(Location loc)
	{
		undoMoves.add(board.saveState());
		if(board.getState()[loc.getRow()][loc.getCol()]!=null)
		{
			wasPieceTaken.add(1);
		}
		else
		{
			wasPieceTaken.add(0);
		}
                if(selectedPiece == null) System.out.println("null from");
		board.moveFrom_To(selectedPiece,loc);
		gui.updatePGN(selectedPiece, loc);
                playerMoved = true;
		board.setTurn(isWhiteTurn);
		selectedPiece = null;
		gui.updateBoard(board);
		gui.enableSide(isWhiteTurn);
		for(int y=0; y<board.getState().length; y++)
			for(int x=0; x<board.getState().length; x++)
				if(board.getState()[y][x]!=null && board.getState()[y][x] instanceof King && board.getState()[y][x].getColor()==isWhiteTurn)
				{
                                    ((King)board.getState()[y][x]).updateIsChecked(board);
                                    if(((King)board.getState()[y][x]).isChecked())
                                        break;
                                }

	}
	
	public void processMoves()
	{
		boolean isCheck = true;
		moves = getMoves(selectedPiece);
		if(moves.size()==0)
		{
			for(int y=0; y<board.getState().length; y++)
				for(int x=0; x<board.getState().length; x++)
					if(board.getState()[y][x]!=null && board.getState()[y][x] instanceof King && board.getState()[y][x].getColor()==isWhiteTurn)
					{
						isCheck = ((King)board.getState()[y][x]).isChecked();
						break;
					}
			if(isCheck)
				gui.updateStatusBar("Esti in sah!", true);
			else
				gui.updateStatusBar("Nu poti muta piesa!", true);
		}
		else
			for(Location z : moves)
				gui.enable(z, highlight);
	}
	
        int x1 = -321;
        int y1 = -321;
        int x2 = -321;
        int y2 = -321;
        public int prom = 0;
        
        //actiunile de pe tabla de sah
	public void processOne(Location loc)
	{       
            if(board.getState()[loc.getRow()][loc.getCol()]!=null)
            {
                //am apasat pe o piesa pe care vreau sa o mut
                if((board.getState()[loc.getRow()][loc.getCol()].getColor() && isWhiteTurn) || (!board.getState()[loc.getRow()][loc.getCol()].getColor() && !isWhiteTurn))
                {       
                    if(playerMoved == true)
                        gui.updateStatusBar("Ai mutat deja", true);
                    else if((color == isWhiteTurn && vizitator == false)  ||  (replay == true)){
                        gui.enableSide(isWhiteTurn);
                        selectedPiece = loc;  
                        gui.resetBackground();
                        gui.resetBorders();                                
                        if(replay == false){
                            gui.selected(selectedPiece);
                            processMoves();
                        }
                        x1 = loc.Row;
                        y1 = loc.Column;
                    }
                    else{
                        gui.updateStatusBar("Nu poti muta pieasa aceasta", true);
                    }
                }
                //cand mananc o piesa 
                else
                {          
                    
                    x2 = loc.getRow();
                    y2 = loc.getCol();
                    movePiece(loc);
                    board.resetOtherPawns(loc);
                    checkPromotion(loc);
                    checkGameOver();
                    playerMoved = true;
                    gui.getToolBar().undo.setEnabled(true);
                }
            }
            
            //mut o piesa intr-o locatie valabila
            else
            {                             
                x2 = loc.getRow();
                y2 = loc.getCol();
                movePiece(loc);
                board.resetOtherPawns(loc);
                checkPromotion(loc);
                checkGameOver();
                playerMoved = true;
                gui.getToolBar().undo.setEnabled(true); 
            }
            
        }
        
        //actiunile butoanelor
        public void processTwo(int command)
        {
            // promovez pionul
            if(command>=-4)
            {
                prom = command;                  
                if(command==-1)
                    board.addQueen(promotedPiece);
                else if(command==-2)
                    board.addRook(promotedPiece);
                else if(command==-3)
                    board.addBishop(promotedPiece);
                else if(command==-4)
                    board.addKnight(promotedPiece);
                for(int y=0; y<board.getState().length; y++)
                    for(int x=0; x<board.getState().length; x++)
                        if(board.getState()[y][x]!=null && board.getState()[y][x] instanceof King && board.getState()[y][x].getColor()==isWhiteTurn)
                        {
                            ((King)board.getState()[y][x]).updateIsChecked(board);
                            if(((King)board.getState()[y][x]).isChecked())
                                break;
                        }
                gui.endPromotion();
                gui.updateBoard(board);
            }
            
            //termina tura
            else if(command==-20)
            {
                if(playerMoved == false)
                    gui.updateStatusBar("Nu ai facut nicio mutare", true);
                else{                        
                    isWhiteTurn = !isWhiteTurn;
                    playerMoved = false;
                    board.setTurn(isWhiteTurn);
                    selectedPiece = null;
                    gui.updateBoard(board);
                    gui.enableSide(isWhiteTurn);
                    gui.getToolBar().undo.setEnabled(false);
                    if(isWhiteTurn == true)
                    {
                        gui.getToolBar().blackChronometer.stopStopWatch();
                        gui.getToolBar().whiteChronometer.launchStopWatch();
                    }
                    else
                    {
                        gui.getToolBar().whiteChronometer.stopStopWatch();
                        gui.getToolBar().blackChronometer.launchStopWatch();
                    }
                    checkGameOver();
                    if(replay == false)
                        try {
                            dos.writeUTF("mutare");
                            dos.writeUTF(oponent);
                            dos.writeInt(prom);
                            dos.writeInt(x1);
                            dos.writeInt(y1);
                            dos.writeInt(x2);
                            dos.writeInt(y2);   
                            x1 = -321;
                            y1 = -321;
                            x2 = -321;
                            y2 = -321;
                        } 
                        catch (IOException ex) {
                            Logger.getLogger(GUIRunner.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
            }
            
            //anuleaza mutare
            else if(command==-21)
            {
                if(undoMoves.size() > 0)
                {
                    board.undoMove(undoMoves.get(undoMoves.size()-1));
                    undoMoves.remove(undoMoves.size()-1);
                    board.setTurn(isWhiteTurn);
                    selectedPiece=null;
                    gui.updateBoard(board);
                    gui.enableSide(isWhiteTurn);
                    wasPieceTaken.remove(wasPieceTaken.size()-1);
                    gui.backPGN();
                    playerMoved = false;
                    gui.getToolBar().undo.setEnabled(false);
                    playerMoved = false;
                }
                else
                    gui.updateStatusBar("Nu mai poti anula mutari!", true);
            }
            
            //declara remiza
            else if(command==-22)
            {
                try {
                    dos.writeUTF("remiza");
                    dos.writeUTF(oponent);
                } catch (IOException ex) {
                    Logger.getLogger(GUIRunner.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
            //chat
            else if(command==-23)
            {
                try {
                    dos.writeUTF("Deseneaza");
                    dos.writeUTF(oponent);
                }
                catch (IOException ex) {
                    Logger.getLogger(GUIRunner.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        
        
        public void processOne(Location loc, int ceva)
	{
		if(board.getState()[loc.getRow()][loc.getCol()]!=null)
		{
                    //am apasat pe o piesa pe care vreau sa o mut
			if((board.getState()[loc.getRow()][loc.getCol()].getColor() && isWhiteTurn) || (!board.getState()[loc.getRow()][loc.getCol()].getColor() && !isWhiteTurn))
			{       
                            if(playerMoved == true)
                                gui.updateStatusBar("Ai mutat deja", true);
                            else{
				gui.enableSide(isWhiteTurn);
				selectedPiece = loc;
				gui.resetBackground();
				gui.resetBorders();
				gui.selected(selectedPiece);
				processMoves();
                            }
			}
                        //cand mananc o piesa 
			else
			{       
				movePiece(loc);
				board.resetOtherPawns(loc);
				checkPromotion(loc);
				checkGameOver();
                                playerMoved = true;
                                gui.getToolBar().undo.setEnabled(true);
			}
		}
                
                //mut o piesa intr-o locatie valabila
		else
		{       
			movePiece(loc);
			board.resetOtherPawns(loc);
			checkPromotion(loc);
			checkGameOver();
                        playerMoved = true;
                        gui.getToolBar().undo.setEnabled(true);
                        
		}
	}
	
        //actiunile butoanelor
	public void processTwo(int command, int ceva)
	{
                // promovez pionul
		if(command>=-4)
		{
			if(command==-1)
				board.addQueen(promotedPiece);
			else if(command==-2)
				board.addRook(promotedPiece);
			else if(command==-3)
				board.addBishop(promotedPiece);
			else if(command==-4)
				board.addKnight(promotedPiece);
			for(int y=0; y<board.getState().length; y++)
				for(int x=0; x<board.getState().length; x++)
					if(board.getState()[y][x]!=null && board.getState()[y][x] instanceof King && board.getState()[y][x].getColor()==isWhiteTurn)
					{
						((King)board.getState()[y][x]).updateIsChecked(board);
						if(((King)board.getState()[y][x]).isChecked())
						break;
					}
			gui.endPromotion();
			gui.updateBoard(board);
		}
                
                //termina tura
		else if(command==-20)
		{
                    if(playerMoved == false)
                        gui.updateStatusBar("Nu ai facut nicio mutare", true);
                    else{
                        isWhiteTurn = !isWhiteTurn;
                        playerMoved = false;
                        board.setTurn(isWhiteTurn);
                        selectedPiece=null;
                        gui.updateBoard(board);
                        gui.enableSide(isWhiteTurn);
                        gui.getToolBar().undo.setEnabled(false);
                        if(isWhiteTurn == true)
                        {
                            gui.getToolBar().blackChronometer.stopStopWatch();
                            gui.getToolBar().whiteChronometer.launchStopWatch();
                        }
                        else
                        {
                            gui.getToolBar().whiteChronometer.stopStopWatch();
                            gui.getToolBar().blackChronometer.launchStopWatch();
                        }
                        checkGameOver();
                    }
		}
                
                //anuleaza mutare
		else if(command==-21)
		{
			if(undoMoves.size() > 0)
			{
				board.undoMove(undoMoves.get(undoMoves.size()-1));
				undoMoves.remove(undoMoves.size()-1);
				board.setTurn(isWhiteTurn);
				selectedPiece=null;
				gui.updateBoard(board);
				gui.enableSide(isWhiteTurn);
				wasPieceTaken.remove(wasPieceTaken.size()-1);
				gui.backPGN();
                                playerMoved = false;
				gui.getToolBar().undo.setEnabled(false);
                                playerMoved = false;
			}
			else
				gui.updateStatusBar("Nu mai poti anula mutari!", true);
		}
                
                //declara remiza
		else if(command==-22)
		{
                    try {
                        dos.writeUTF("remiza");
                        dos.writeUTF(oponent);
                    } catch (IOException ex) {
                        Logger.getLogger(GUIRunner.class.getName()).log(Level.SEVERE, null, ex);
                    }
		}
	}
}