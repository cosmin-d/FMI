package joc;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import sah.*;


public class ReplayGame implements Runnable, Serializable {
    
    GUIRunner run;
    String fis;
    
    public ReplayGame(String str)
    {
        run = new GUIRunner();
        run.setVisible();
        run.vizitator = true;
        fis = str;
        Runnable r = this;
        Thread t = new Thread(r);
        t.start();
    }
    /*
    public static void main(String args[]) throws IOException
    {
        
    }*/

    
    @Override
    public void run()
    {
        try
        {
            System.out.println(fis);
            FileReader moves = new FileReader(fis);
            BufferedReader br = new BufferedReader(moves);
            String line;
            while( (line = br.readLine()) != null )
            {
                System.out.println(line);
                String[] lineSplitted = line.split(":");
                String[] whiteMoves = lineSplitted[0].split(" ");
                String[] blackMoves = lineSplitted[1].split(" ");
                //alb
                String whiteInitialPosition = whiteMoves[1];
                String whiteFinalPosition = whiteMoves[2];
                char whiteInitialRow = whiteInitialPosition.charAt(1);
                char whiteInitialColumn = whiteInitialPosition.charAt(0);
                Integer wInitRow = 8 - Character.getNumericValue(whiteInitialRow);
                Integer wInitCol = decode(whiteInitialColumn);
                char whiteFinalRow = whiteFinalPosition.charAt(1);
                char whiteFinalColumn = whiteFinalPosition.charAt(0);
                Integer wFinRow = 8 - Character.getNumericValue(whiteFinalRow);
                Integer wFinCol = decode(whiteFinalColumn);
                          
                int ceva = 0;
                System.out.println("de la " + wInitRow + " " + wInitCol + "  la  " + wFinRow + " " + wFinCol);
                run.processOne(new Location(wInitRow, wInitCol),ceva);
                run.processOne(new Location(wFinRow, wFinCol),ceva);
                run.processTwo(-20,ceva);
                System.out.println("ceva");
                Thread.sleep(3000);
                System.out.println("ceva");
                        
                //negru
                String blackInitialPosition = blackMoves[2];
                String blackFinalPosition = blackMoves[3];
                
                char blackInitialRow = blackInitialPosition.charAt(1);
                char blackInitialColumn = blackInitialPosition.charAt(0);
                Integer bInitRow = 8 - Character.getNumericValue(blackInitialRow);
                Integer bInitCol = decode(blackInitialColumn);
                
                char blackFinalRow = blackFinalPosition.charAt(1);
                char blackFinalColumn = blackFinalPosition.charAt(0);
                Integer bFinRow = 8 - Character.getNumericValue(blackFinalRow);
                Integer bFinCol = decode(blackFinalColumn);

                System.out.println("de la " + bInitRow + " " + bInitCol + "  la  " + bFinRow + " " + bFinCol);
                run.processOne(new Location(bInitRow, bInitCol));
                run.processOne(new Location(bFinRow, bFinCol));
                run.processTwo(-20);
                Thread.sleep(3000);
                
            }
            
        }
        catch (FileNotFoundException e) {}
        catch (IOException ex) {}
        catch (InterruptedException ex) {}
    }
    
    private Integer decode(Character a){
        
        int col = -1;
        
        switch(a)
        {
            case 'a' : col = 0; break;
            case 'b' : col = 1; break;
            case 'c' : col = 2; break;
            case 'd' : col = 3; break;
            case 'e' : col = 4; break;
            case 'f' : col = 5; break;
            case 'g' : col = 6; break;
            case 'h' : col = 7; break;
            
        }
        return col;
    }
    
}
