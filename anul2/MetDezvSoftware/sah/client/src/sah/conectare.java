/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sah;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class conectare implements Runnable {

    private static Socket cs = null;
    private static DataInputStream dis = null;
    private static PrintStream os = null;
    private static BufferedReader inputLine = null;
    private static boolean closed = false;
    private boolean eroare = false;

    public void conectareServer() {
        Scanner sc = new Scanner(System.in);
        String adresa = "127.0.0.1";
        int port = 50000;
        try {
            cs = new Socket(adresa, port);
            inputLine = new BufferedReader(new InputStreamReader(System.in));

        } catch (Exception e) {
            eroare = true;

        }
        Thread t = new Thread(new conectare());
        t.start();
    }

    public DataInputStream input() {
        DataInputStream dis = null;
        try {
            dis = new DataInputStream(cs.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(conectare.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dis;
    }

    public DataOutputStream output() {
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(cs.getOutputStream());
        } catch (IOException ex) {
            Logger.getLogger(conectare.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dos;
    }

    public int error() {
        if (eroare == true) return 1;
        else return 0;
    }

    public void run() {

        String raspuns;
        try {
            while ((raspuns = dis.readLine()) != null) {
                System.out.println(raspuns);
                if (raspuns.startsWith("Bye, bye")) {
                    System.out.println("Logged out...");
                    System.exit(1);
                    break;

                }
            }
            closed = true;
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
    }
}