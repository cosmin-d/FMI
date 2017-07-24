/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.net.Socket;

/**
 *
 * @author cosmi
 */
public class Connection {
    Socket socket;
    
    Connection(Socket s)
    {
        this.socket=s;
    }
    public Socket getSocket()
    {return this.socket;}
}
