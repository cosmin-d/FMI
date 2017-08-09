package joc;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import sah.*;

public class GUI extends JFrame implements ActionListener,Serializable
{
	private static final long serialVersionUID = -5825115473793035743L;

	private GUIRunner runner;
	private ChessBoard board;	
	private PromotionPopup promotion;
	private GameOverPopup gameOver;		
	private StatusBar statusBar;	
	private PGNDisplay pgnPanel;       
        private ToolBar toolBar;
        
	public GUI(BoardState boardState, GUIRunner runner)
	{
		super("G Chess");
                setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                ActionListennerTabla();
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension boardSize = new Dimension(482,482);
		Dimension pgnPanelSize = new Dimension(160,461);
		Dimension southSize = new Dimension(812, 41);
		Dimension statusBarSize = new Dimension(812, 27);
		Dimension toolBarSize = new Dimension(170,482);

		this.runner = runner;
	
		
		//"this" Main Frame***********************************************************
		setLayout(new BorderLayout());
		setResizable(false);
		//****************************************************************************
		
		//South Panel*****************************************************************
		JPanel south = new JPanel();
		south.setPreferredSize(southSize);
		south.setLayout(new BoxLayout(south, BoxLayout.PAGE_AXIS));
		JPanel southTop = new JPanel();
		southTop.setOpaque(true);
		southTop.setBackground(new Color(90, 24, 24));
		JPanel southBottom = new JPanel();
		southBottom.setOpaque(true);
		southBottom.setBackground(new Color(90, 24, 24));
		//****************************************************************************

		//Status Bar******************************************************************
		statusBar = new StatusBar(statusBarSize);
		southBottom.add(statusBar);
		//****************************************************************************
		
		south.add(southTop);
		south.add(southBottom);
		add(south, BorderLayout.SOUTH);

		//PGN Panel*******************************************************************		
		pgnPanel = new PGNDisplay(pgnPanelSize);
		add(pgnPanel,BorderLayout.WEST);
		//****************************************************************************

		//Button Panel****************************************************************
		toolBar = new ToolBar(this, toolBarSize);
		add(toolBar,BorderLayout.EAST);
		//****************************************************************************

		//Game Board******************************************************************
		board = new ChessBoard(boardState, this, boardSize);
		updateBoard(boardState);		
		add(board,BorderLayout.CENTER);
		//****************************************************************************


		pack();
		setLocation(screen.width/2-getSize().width/2, screen.height/2-getSize().height/2);
		repaint();
	}
        
        public GUIRunner getGR(){
            return runner;
        }
        
	public ChessBoard getChessBoard()
	{
		return board;
	}
        
	public StatusBar getStatusBar()
	{
		return statusBar;
	}
	public PGNDisplay getPgnPanel()
	{
		return pgnPanel;
	}
        
        public ToolBar getToolBar()
        {
            return toolBar;
        }
        
	public Location processLocation(Location loc)
	{
		return loc;
	}

	public void gameOver(int outcome)
	{
		if(outcome==1){
                    gameOver = new GameOverPopup(this, " Sah mat! Jucatorul alb a castigat",this);
                    try {
                        runner.dos.writeUTF("mat");
                        runner.dos.writeUTF(runner.oponent);
                    } catch (IOException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                else if(outcome==-1){
                    try {
                        runner.dos.writeUTF("mat");
                        runner.dos.writeUTF(runner.oponent);
                    } catch (IOException ex) {
                        Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    gameOver = new GameOverPopup(this, " Sah mat! Jucatorul negru a castigat",this);
                }
                else if(outcome==0)
                    gameOver = new GameOverPopup(this, " Remiza! Nu a castigat niciun jucator",this);                
                else if(outcome==2)
                        gameOver = new GameOverPopup(this, "Ai declarat remiza!",this);
                else
                        gameOver = new GameOverPopup(this, "Timpul a expirat!",this);
                setEnabled(false);
                if(runner.vizitator == false){
                    gameOver.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Jocul s-a terminat!");
                    setVisible(false);
                }
	}
	
	public void newGame()
	{
		setEnabled(true);
		if(gameOver!=null)
			gameOver.dispose();
		clearPGN();
		updateStatusBar("Joc nou!", true);
	}
        
	public void updatePGN(Location start, Location end)
	{
		pgnPanel.updatePGN(start, end, runner.getTurn());
	}
	
	public void backPGN()
	{
		pgnPanel.backPGN(runner.getTurn());
	}
	
	public void clearPGN()
	{
		pgnPanel.clearPGN();
	}
	
	public void promotion()
	{
		setEnabled(false);
		promotion = new PromotionPopup(this);
		promotion.setVisible(true);
	}
	        
	public void endPromotion()
	{
		setEnabled(true);
		promotion.dispose();
	}
	
	public void enableSide(boolean turn)
	{
		board.enableSide(turn);
	}

	public void resetBorders()
	{
		board.resetBorders();
	}
	
	public void resetBackground()
	{
		board.resetBackground();
	}
	
	public void enable(Location loc, boolean highlight)
	{
		board.enable(loc, highlight);
	}
	
	public void selected(Location loc)
	{
		board.selected(loc);
	}
	
	public void dynamicUpdateStatusBar(JButton button)
	{
		for(int y=0; y<board.getSide(); y++)
			for(int x=0; x<board.getSide(); x++)
				if(button==board.getButton(y,x))
					updateStatusBar(""+processLocation(new Location(y,x)), false);
	}
	
	public void updateStatusBar(String output, boolean isAlert)
	{
		statusBar.update(output, isAlert);
	}
	
	public void updateBoard(BoardState boardState)
	{
		board.updateBoard(boardState);
	}
	
        @Override
	public void actionPerformed(ActionEvent event)
	{
		int command = Integer.parseInt(event.getActionCommand());
		
		if(command >= 0)
		{
			int x = command%10;
			int y = command/10;
			Location loc = processLocation(new Location(y,x)); 
			runner.processOne(loc);
		}
		else
			runner.processTwo(command);
	}
        public void ActionListennerTabla(){
            addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    if(runner.replay == false && runner.vizitator == false){
                        String renunt = "Vrei sa renunti la joc? Vei pierde un punct.";
                        int result = JOptionPane.showConfirmDialog((Component) null, renunt,"Renunta", JOptionPane.YES_NO_OPTION);
                        if(result == 0){
                            runner.dos.writeUTF("tabla");
                            runner.dos.writeUTF(runner.oponent);
                            JOptionPane.showMessageDialog(null,"Jocul s-a incheiat. Ai pierdut!");
                            setVisible(false);
                        }
                    }
                    else{
                        setVisible(false);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(GUI1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
        }
}