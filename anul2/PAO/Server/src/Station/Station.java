/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Station;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author cosmi
 */
public class Station {
    BufferedReader in;
    PrintWriter out;
    
    
    private void run() throws IOException {
        System.out.println("The station is running.");
        Socket socket = new Socket("localhost",4440);
        in = new BufferedReader(new InputStreamReader(System.in));
        out = new PrintWriter(socket.getOutputStream(), true);
        while (true) {
            String line = in.readLine();
            //do something with the message received from console
           // System.out.println(line.toUpperCase());
           out.println(line);
        }
        
    }
    
    
    public static void main(String[] args) throws IOException  {
        Station station =new Station();
        station.run();
         
    }
   
}
