/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;
import javax.swing.JFrame;



public class Client  {
    
    BufferedReader in;
   static PrintWriter out;
    static cli_interface frame;

    public Client() {
      Client.frame = new cli_interface();
        
    }
    Thread refresh =new Thread(){
            public void run(){
                
                long startTime = System.currentTimeMillis();
            long elapsedTime = 0L;
                while(true){
                    elapsedTime = (new Date()).getTime() - startTime;
           if (elapsedTime > 10*1000) {
          Client.frame.setNumar("");
          Client.frame.setNume("");
          Client.frame.setNameNotEditable();
            Client.frame.setLabelInvisible();
           break;
}
            }
            }
        };
    public void run() throws IOException {

        // Make connection and initialize streams
        String serverAddress = "localhost";
        Socket socket = new Socket(serverAddress, 4444);
       
        in = new BufferedReader(new InputStreamReader(
            socket.getInputStream()));
        out = new PrintWriter(socket.getOutputStream(), true);

        // Process all messages from server
        while (true) {
            String line = in.readLine();
            String [] f=line.split("[,]");
            Client.frame.setNumar(f[0]);
            Client.frame.setNume(f[1]);
            Client.frame.setNameEditable();
            Client.frame.setLabelVisible();
            refresh.run();
            //do something with the message received from server
            System.out.println(line);
        }
    }
    
    public static void main(String[] args) throws Exception {
        Client client = new Client();
        Client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Client.frame.setLabelInvisible();
        Client.frame.setVisible(true);
        Client.frame.setNameNotEditable();
        Client.frame.jButton1.addActionListener((ActionEvent ae) -> {
            System.out.println("buton apasat");
            //out.println("buton apasat");
            out.println(Client.frame.getNumar());
            out.println(Client.frame.getNume());
        });
        client.run();
        
       
    }

}

