/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import database.DB;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Server {

    private static final int PORT = 4444;
    private static Socket station;
    private static BufferedReader stationIn;
    private static DB db;
   
    private static HashSet<PrintWriter> writers = new HashSet<PrintWriter>();
    
    
    public static void main(String[] args) throws Exception {
        db =new DB();
        db.DropDB();
        try{
            db.CreateDB();
        }
        catch (Exception e)
        {
            System.out.println("Baza de date a fost deja creata!");
        }
        System.out.println("The server is running.");
        ServerSocket listener = new ServerSocket(PORT);
        
        

        Thread stationListen =new Thread(){
            public void run(){
           
        while(true){
            
        try{
        ServerSocket stationListener = new ServerSocket(4440);
        Socket station = stationListener.accept();
       //
       BufferedReader stationIn = new BufferedReader(new InputStreamReader(
            station.getInputStream()));
        while (true) {
            String line = stationIn.readLine();
            if (line == null) {
                        return;
                    }
            String [] s=line.split("[,]");
            System.out.println(s.length);
            if(s.length >2)
            {
               db.Insert(s[0],s[1],s[2]);
            }
            else
            {
                String rez= db.Search(s[1]);
                //notific ca s[1] apeleaza
                String notif=s[1]+","+rez;
                System.out.println(notif);
                for (PrintWriter writer : writers) {
                    writer.println(notif);
                
            }
            }
            
            //do something with the message received from station
            System.out.println(line.toUpperCase());
        }
    }
       catch(IOException e) {
                System.out.println(e);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                // this client is disconnecting. remove its print writer from the set and close its socket.
                
                if (stationIn != null) {
                try {
                    station.close();
                    stationIn.close();
                } catch (IOException e) {
                }
    }
    }
    }
    }
        };
        stationListen.start();
        //
        try {
            while (true) {
                
                new Handler(listener.accept()).start();
            }
        } finally {
            listener.close();
        }
    }

   
    private static class Handler extends Thread {
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;

        
        public Handler(Socket socket) {
            this.socket = socket;
        }
        
        
        public void run() {
            try {

                // create character streams for the socket
                in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

               
                writers.add(out);System.out.println("adaugat client");

               
                while (true) {
                    String numar = in.readLine();
                    String nume=in.readLine();
                    db.Update(numar, nume);
                    System.out.println(numar+" "+nume);
                  
                }
            } catch (IOException e) {
                System.out.println(e);
            } finally {
                
                if (out != null) {
                    writers.remove(out);
                }
                try {
                    socket.close();
                } catch (IOException e) {
                }
            }
        }

}
}
 
    

