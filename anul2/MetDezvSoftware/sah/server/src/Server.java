import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.*;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Server {

    public static Vector<String> logname;
    public static Conexiune[] threads;
    Scanner sc = new Scanner(System.in);
    public Vector<partida> partide;

    Server() throws Exception {
        /*System.out.println("Introduceti portul:");*/
        ServerSocket ss = new ServerSocket(50000);
        System.out.println("Serverul a pornit!");
        threads = new Conexiune[100];
        logname = new Vector();
        partide = new Vector<>();
        while (true) {
            Socket cs = ss.accept();
            System.out.println("S-a conectat un client nou!");

            int i;
            for (i = 0; i < 100; i++) {
                if (threads[i] == null) {
                    (threads[i] = new Conexiune(cs, threads)).start();
                    break;
                }
            }
            if (i == 100) {
                cs.close();
            }
        }
    }

    public static void main(String[] sir) throws Exception {
        Server s = new Server();
    }


    public class mutar {
        public int x1, x2, y1, y2, prom;

        public mutar(int x, int y, int xx, int yy, int pr) {
            x1 = x;
            y1 = y;
            x2 = xx;
            y2 = yy;
            prom = pr;
        }
    }

    class partida {
        public String unu;
        public String doi;
        public Vector<String> part = new Vector<>();
        public Vector<mutar> mutari = new Vector<>();

        public partida(String u, String d) {
            unu = u;
            doi = d;
        }
    }

    class Conexiune extends Thread {

        String myname = null;
        Socket cs = null;
        DataInputStream dis = null;
        DataOutputStream dos = null;
        public Conexiune[] threads;
        Scanner sc = new Scanner(System.in);

        public Conexiune(Socket client, Conexiune[] th)
                throws IOException {
            cs = client;
            dis = new DataInputStream(cs.getInputStream());
            dos = new DataOutputStream(cs.getOutputStream());
            threads = th;
        }

        public void run() {
            BD bd = null;
            persoana p = null;
            byte[] imageBytes;

            Statement stmt = null;
            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/sah", "root", "password");
                stmt = con.createStatement();
                bd = new BD(stmt);

            } catch (ClassNotFoundException ex) {
            } catch (SQLException ex) {
            }


            String email = null, parola = null, poza = null, numele = null;
            String input = null;

            try {
                input = dis.readUTF();
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }

            System.out.println(input);
            if (input.startsWith("Creare cont.")) {
                try {
                    email = dis.readUTF();
                    parola = dis.readUTF();
                    numele = dis.readUTF();
                    poza = dis.readUTF();
                    int verifica = 0;
                    //am apelat functia de verificare
                    try {
                        verifica = bd.cautaNume2(numele);
                    } catch (Exception ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (numele.equals("") || poza.equals("") || parola.equals("")) {
                        dos.writeUTF("Eroare campuri.");
                    } else if (verifica == 1) {
                        dos.writeUTF("Eroare nume.");
                    } else {
                        Pattern emailreg = Pattern.compile("[a-zA-Z0-9[!#$%&'()*+,/\\-_\\.\\\"]]+@[a-zA-Z0-9[!#$%&'()*+,/\\-_\\\"]]+\\.[a-zA-Z0-9[!#$%&'()*+,/\\-_\\\"\\.]]+");
                        Matcher m = emailreg.matcher(email);
                        Boolean b = m.matches();

                        if (b == false) {
                            dos.writeUTF("Eroare email.");
                        } else {

                            dos.writeUTF("Fara eroare.");
                            persoana pers_nou = new persoana(numele, parola, poza, email, "0");

                            try {
                                bd.adauga(pers_nou);
                            } catch (Exception ex) {
                                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            try {
                                pers_nou = bd.cauta("email", email, parola);
                            } catch (Exception ex) {
                                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            String my_query = "SELECT POZA FROM JUCATORI WHERE( EMAIL " + " = " + "'" + pers_nou.getEmail() + "');";
                            ResultSet rs = stmt.executeQuery(my_query);
                            int n = 0;
                            while (rs.next()) {
                                imageBytes = rs.getBytes(1);
                                n++;
                            }
                            try {
                                dos.writeUTF(Integer.toString(n));
                            } catch (IOException ex) {
                                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            rs = stmt.executeQuery(my_query);
                            byte[] imageBytes2 = null;
                            while (rs.next()) {
                                imageBytes2 = rs.getBytes(1);

                                try {
                                    dos.writeInt(imageBytes2.length);
                                    dos.write(imageBytes2);

                                } catch (IOException ex) {
                                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                                }

                            }

                            try {
                                dos.writeUTF(pers_nou.getNume());
                                dos.writeUTF(pers_nou.getNume());
                                dos.writeUTF(pers_nou.getEmail());
                                dos.writeUTF(pers_nou.getPunctaj());
                            } catch (IOException ex) {
                                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            logname.add(pers_nou.getNume());
                            for (int i = 0; i < 100; i++) {
                                if (threads[i] == this) {
                                    myname = pers_nou.getNume();
                                    break;
                                }
                            }
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
            String emaillog = null, parolalog = null;

            if (input.startsWith("Logare cont.")) {
                try {

                    emaillog = dis.readUTF();
                    parolalog = dis.readUTF();
                    System.out.println(emaillog + "         " + parolalog);

                    try {
                        p = bd.cauta("email", emaillog, parolalog);

                    } catch (Exception ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String my_query = "SELECT POZA FROM JUCATORI WHERE( EMAIL " + " = " + "'" + p.getEmail() + "');";
                    ResultSet rs = stmt.executeQuery(my_query);
                    int n = 0;
                    while (rs.next()) {
                        imageBytes = rs.getBytes(1);
                        n++;
                    }
                    try {
                        dos.writeUTF(Integer.toString(n));
                    } catch (IOException ex) {
                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    rs = stmt.executeQuery(my_query);
                    byte[] imageBytes2 = null;
                    while (rs.next()) {
                        imageBytes2 = rs.getBytes(1);

                        try {
                            dos.writeInt(imageBytes2.length);
                            dos.write(imageBytes2);

                        } catch (IOException ex) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }

                    if (p.getNume().equals("-")) {
                        try {
                            dos.writeUTF("Eroare parola!");
                        } catch (IOException ex) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else if (p.getNume().equals("*")) {
                        try {
                            dos.writeUTF("Eroare cont!");
                        } catch (IOException ex) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        try {
                            dos.writeUTF("Fara eroare!");
                        } catch (IOException ex) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        try {
                            dos.writeUTF(p.getNume());
                            // dos.writeUTF(p.getEmail());
                            dos.writeUTF(p.getNume());
                            dos.writeUTF(p.getEmail());
                            dos.writeUTF(p.getPunctaj());
                            logname.add(p.getNume());
                            for (int i = 0; i < 100; i++) {
                                if (threads[i] == this) {
                                    myname = p.getNume();
                                    break;
                                }
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            try {
                dos.writeUTF(this.myname);
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                synchronized (this) {
                    for (int j = 0; j < 100; j++) {
                        if (threads[j] != null && threads[j].myname != null) {
                            threads[j].dos.writeUTF("default");
                        }
                    }
                }


                //dos.writeUTF("default" );
            } catch (IOException ex) {
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
            while (true) {
                try {

                    String cerere = dis.readUTF();
                    System.out.println(cerere);

                    if (cerere.startsWith("Lista jucatori!")) {

                        dos.writeUTF("lista");
                        int nr_logati = logname.size();

                        try {
                            dos.writeInt(nr_logati - 1);

                            for (int i = 0; i < logname.size(); i++) {
                                if (!logname.get(i).equals(myname)) {
                                    dos.writeUTF(logname.get(i));
                                }

                            }

                        } catch (IOException ex) {
                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    if (cerere.equals("Sign out!")) {
                        String numejuc = dis.readUTF();
                        System.out.println("***" + numejuc);
                        synchronized (this) {
                            for (int j = 0; j < 100; j++) {
                                if (threads[j] != null && threads[j] != this && threads[j].myname != null && threads[j].myname.equals(numejuc)) {
                                    threads[j].dos.writeUTF("Sign out/" + this.myname + " a parasit conversatia.");
                                    break;
                                }
                            }
                        }
                    }

                    if (cerere.equals("Iesire!")) {
                        System.out.println("iese" + myname);
                        synchronized (this) {
                            for (int j = 0; j < 100; j++) {
                                if (threads[j] != null && threads[j] == this) {
                                    threads[j] = null;
                                    dos.writeUTF("Iesi");
                                    break;
                                }
                            }
                        }
                        for (int i = 0; i < logname.size(); i++) {
                            if (logname.get(i).equals(myname)) {
                                logname.remove(i);
                            }
                        }
                        synchronized (this) {
                            for (int j = 0; j < 100; j++) {
                                if (threads[j] != null && threads[j] != this && threads[j].myname != null) {
                                    threads[j].dos.writeUTF("listout");
                                    int nr_logati = logname.size();
                                    try {
                                        dos.writeInt(nr_logati - 1);
                                        for (int i = 0; i < logname.size(); i++) {
                                            if (!logname.get(i).equals(myname)) {
                                                threads[j].dos.writeUTF(logname.get(i));
                                            }
                                        }
                                    } catch (IOException ex) {
                                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }
                            }
                        }
                    }

                    if (cerere.equals("remiza")) {
                        String num = dis.readUTF();
                        int res = -32;
                        synchronized (this) {
                            for (int j = 0; j < 100; j++) {
                                if (threads[j] != null && threads[j] != this && threads[j].myname != null && threads[j].myname.equals(num)) {
                                    threads[j].dos.writeUTF("rem");
                                    threads[j].dos.writeUTF(myname);
                                    break;
                                }
                            }
                        }
                    }

                    if (cerere.equals("rasprem")) {
                        String num = dis.readUTF();
                        int res = dis.readInt();
                        synchronized (this) {
                            for (int j = 0; j < 100; j++) {
                                if (threads[j] != null && threads[j] != this && threads[j].myname != null && threads[j].myname.equals(num)) {
                                    threads[j].dos.writeUTF("remiz");
                                    threads[j].dos.writeInt(res);
                                    threads[j].dos.writeUTF(num);
                                    break;
                                }
                            }
                        }
                    }

                    if (cerere.equals("castig")) {
                        String num = dis.readUTF();
                        persoana pers = bd.cautaNume(myname);
                        bd.modificaPunctaj("email", pers.getEmail(), +1);
                        pers = bd.cautaNume(num);
                        bd.modificaPunctaj("email", pers.getEmail(), -1);
                    }

                    if (cerere.startsWith("eroaremsg")) {
                        String nume_log = dis.readUTF();
                        synchronized (this) {
                            for (int j = 0; j < 100; j++) {
                                if (threads[j] != null && threads[j] != this && threads[j].myname != null && threads[j].myname.equals(nume_log)) {
                                    threads[j].dos.writeUTF("eroaremsg");
                                    //System.out.println(this.myname + "eroare");
                                    break;
                                }
                            }
                        }

                    }

                    if (cerere.equals("tabla")) {
                        String num = dis.readUTF();
                        persoana pers = bd.cautaNume(myname);
                        bd.modificaPunctaj("email", pers.getEmail(), -1);
                        synchronized (this) {
                            for (int j = 0; j < 100; j++) {
                                if (threads[j] != null && threads[j] != this && threads[j].myname != null && threads[j].myname.equals(num)) {
                                    pers = bd.cautaNume(threads[j].myname);
                                    bd.modificaPunctaj("email", pers.getEmail(), +1);
                                    threads[j].dos.writeUTF("termin");
                                    threads[j].dos.writeUTF(myname);
                                    break;
                                }
                            }
                        }
                    }

                    if (cerere.equals("trimail")) {
                        persoana pers = bd.cautaNume(myname);
                        bd.trimiteMail("email", pers.getEmail());
                    }

                    if (cerere.startsWith("Deseneaza")) {

                        String nume_juc = dis.readUTF();
                        dos.writeUTF("deseneaza");
                        persoana pers;
                        pers = bd.cautaNume(nume_juc);
                        synchronized (this) {
                            for (int j = 0; j < 100; j++) {
                                if (threads[j] != null && threads[j] != this && threads[j].myname != null && threads[j].myname.equals(pers.getNume())) {
                                    String my_query = "SELECT POZA FROM JUCATORI WHERE( EMAIL " + " = " + "'" + pers.getEmail() + "');";
                                    ResultSet rs = stmt.executeQuery(my_query);
                                    int n = 0;
                                    while (rs.next()) {
                                        imageBytes = rs.getBytes(1);
                                        n++;
                                    }
                                    try {
                                        dos.writeInt(n);
                                    } catch (IOException ex) {
                                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    rs = stmt.executeQuery(my_query);
                                    byte[] imageBytes2 = null;
                                    while (rs.next()) {
                                        imageBytes2 = rs.getBytes(1);

                                        try {
                                            dos.writeInt(imageBytes2.length);
                                            dos.write(imageBytes2);

                                        } catch (IOException ex) {
                                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                    }
                                    dos.writeUTF(nume_juc);
                                    break;
                                }
                            }
                        }

                        pers = bd.cautaNume(myname);
                        synchronized (this) {
                            for (int j = 0; j < 100; j++) {
                                if (threads[j] != null && threads[j] != this && threads[j].myname != null && threads[j].myname.equals(nume_juc)) {
                                    threads[j].dos.writeUTF("deseneaza");
                                    String my_query = "SELECT POZA FROM JUCATORI WHERE( EMAIL " + " = " + "'" + pers.getEmail() + "');";
                                    ResultSet rs = stmt.executeQuery(my_query);
                                    int n = 0;
                                    while (rs.next()) {
                                        imageBytes = rs.getBytes(1);
                                        n++;
                                    }
                                    try {
                                        threads[j].dos.writeInt(n);
                                    } catch (IOException ex) {
                                        Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                                    }

                                    rs = stmt.executeQuery(my_query);
                                    byte[] imageBytes2 = null;
                                    while (rs.next()) {
                                        imageBytes2 = rs.getBytes(1);

                                        try {
                                            threads[j].dos.writeInt(imageBytes2.length);
                                            threads[j].dos.write(imageBytes2);

                                        } catch (IOException ex) {
                                            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                                        }

                                    }
                                    threads[j].dos.writeUTF(myname);
                                    break;
                                }
                            }
                        }
                    }

                    if (cerere.startsWith("msg")) {
                        //String msgPrimit = dis.readUTF();
                        String[] msg = cerere.split("/");
                        if (!msg[2].trim().equals("")) {
                            synchronized (this) {
                                for (int j = 0; j < 100; j++) {
                                    if (threads[j] != null && threads[j] != this && threads[j].myname != null && threads[j].myname.equals(msg[1])) {
                                        System.out.println("mesaj pt" + threads[j].myname);
                                        threads[j].dos.writeUTF("msg/" + this.myname + "/" + msg[2]);
                                        break;
                                    }
                                }
                            }

                        }

                    }

                    if (cerere.equals("vezi")) {
                        String unul = dis.readUTF();
                        String doil = dis.readUTF();
                        dos.writeUTF("viz");
                        dos.writeUTF("nimic");
                        dos.writeBoolean(false);
                        dos.writeInt(1);
                        for (int i = 0; i < partide.size(); i++) {
                            if (partide.get(i).unu.equals(unul) && partide.get(i).doi.equals(doil)) {
                                for (int j = 0; j < partide.get(i).mutari.size(); j++) {
                                    int xu = partide.get(i).mutari.get(j).x1;
                                    int yu = partide.get(i).mutari.get(j).y1;
                                    int xd = partide.get(i).mutari.get(j).x2;
                                    int yd = partide.get(i).mutari.get(j).y2;
                                    int prom = partide.get(i).mutari.get(j).prom;
                                    System.out.println("ceva");
                                    dos.writeUTF("muta");
                                    System.out.println("ceva");
                                    dos.writeInt(prom);
                                    dos.writeInt(xu);
                                    dos.writeInt(yu);
                                    dos.writeInt(xd);
                                    dos.writeInt(yd);
                                }
                                partide.get(i).part.add(myname);
                            }
                        }
                    }

                    if (cerere.equals("chat")) {
                        String num = dis.readUTF();
                        synchronized (this) {
                            for (int j = 0; j < 100; j++) {
                                if (threads[j] != null && threads[j] != this && threads[j].myname != null && threads[j].myname.equals(num)) {
                                    threads[j].dos.writeUTF("desc");
                                    threads[j].dos.writeUTF(myname);
                                    break;
                                }
                            }
                        }
                    }

                    if (cerere.equals("joaca")) {
                        String num = dis.readUTF();
                        int priv = dis.readInt();
                        persoana pers;
                        pers = bd.cautaNume(num);
                        synchronized (this) {
                            for (int j = 0; j < 100; j++) {
                                if (threads[j] != null && threads[j] != this && threads[j].myname != null && threads[j].myname.equals(pers.getNume())) {
                                    threads[j].dos.writeUTF("invitatie");
                                    threads[j].dos.writeUTF(myname);
                                    threads[j].dos.writeInt(priv);
                                }
                            }
                        }
                    }

                    if (cerere.equals("rasp")) {
                        String num = dis.readUTF();
                        int rsp = dis.readInt();
                        int priv = dis.readInt();

                        synchronized (this) {
                            for (int j = 0; j < 100; j++) {
                                if (threads[j] != null && threads[j] != this && threads[j].myname != null && threads[j].myname.equals(num)) {
                                    threads[j].dos.writeUTF("raspuns");
                                    threads[j].dos.writeUTF(myname);
                                    threads[j].dos.writeInt(rsp);
                                    threads[j].dos.writeInt(priv);
                                    break;
                                }
                            }
                        }
                    }

                    if (cerere.equals("partide")) {
                        dos.writeUTF("listuta");
                        dos.writeInt(partide.size());
                        for (int i = 0; i < partide.size(); i++) {
                            String pa = "joc intre " + partide.get(i).unu + " si " + partide.get(i).doi;
                            dos.writeUTF(pa);
                        }
                    }

                    if (cerere.equals("partida")) {
                        String op = dis.readUTF();
                        int priv = dis.readInt();
                        if (priv == 1) {
                            partida part = new partida(op, myname);
                            partide.add(part);
                            synchronized (this) {
                                for (int j = 0; j < 100; j++) {
                                    if (threads[j] != null && threads[j].myname != null) {
                                        threads[j].dos.writeUTF("listuta");
                                        threads[j].dos.writeInt(partide.size());
                                        for (int i = 0; i < partide.size(); i++) {
                                            String pa = "joc intre " + partide.get(i).unu + " si " + partide.get(i).doi;
                                            threads[j].dos.writeUTF(pa);
                                        }
                                    }
                                }
                            }
                            for (int i = 0; i < partide.size(); i++) {
                                System.out.println(partide.get(i).unu + " cu " + partide.get(i).doi);
                            }
                        }
                    }

                    if (cerere.equals("inc")) {
                        String op = dis.readUTF();
                        int priv = dis.readInt();
                        dos.writeUTF("incepe");
                        dos.writeUTF(op);
                        dos.writeBoolean(true);
                        dos.writeInt(priv);
                    }

                    if (cerere.equals("mat")) {
                        String num = dis.readUTF();
                        for (int i = 0; i < partide.size(); i++) {
                            System.out.print(partide.get(i).unu + " cu " + partide.get(i).doi);
                        }
                        for (int i = 0; i < partide.size(); i++) {
                            if ((partide.get(i).unu.equals(myname) && partide.get(i).doi.equals(num)) || (partide.get(i).unu.equals(num) && partide.get(i).doi.equals(myname))) {
                                partide.remove(i);
                                System.out.println("gasit!!!!");
                            }
                        }
                        for (int i = 0; i < partide.size(); i++) {
                            System.out.print(partide.get(i).unu + " cu " + partide.get(i).doi);
                        }
                        synchronized (this) {
                            for (int j = 0; j < 100; j++) {
                                if (threads[j] != null && threads[j] != this && threads[j].myname != null) {
                                    threads[j].dos.writeUTF("matout");
                                    int nr_part = partide.size();
                                }
                            }
                        }
                    }

                    if (cerere.equals("inc2")) {
                        String op = dis.readUTF();
                        int priv = dis.readInt();
                        dos.writeUTF("incepe");
                        dos.writeUTF(op);
                        dos.writeBoolean(false);
                        dos.writeInt(priv);
                    }

                    if (cerere.equals("mutare")) {
                        String num = dis.readUTF();
                        int prom = dis.readInt();
                        int x1 = dis.readInt();
                        int y1 = dis.readInt();
                        int x2 = dis.readInt();
                        int y2 = dis.readInt();
                        mutar m = new mutar(x1, y1, x2, y2, prom);
                        for (int i = 0; i < partide.size(); i++) {
                            if ((partide.get(i).unu.equals(myname) && partide.get(i).doi.equals(num)) || (partide.get(i).unu.equals(num) && partide.get(i).doi.equals(myname))) {
                                partide.get(i).mutari.add(m);
                                break;
                            }
                        }
                        synchronized (this) {
                            for (int j = 0; j < 100; j++) {
                                if (threads[j] != null && threads[j] != this && threads[j].myname != null && threads[j].myname.equals(num)) {
                                    threads[j].dos.writeUTF("muta");
                                    threads[j].dos.writeInt(prom);
                                    threads[j].dos.writeInt(x1);
                                    threads[j].dos.writeInt(y1);
                                    threads[j].dos.writeInt(x2);
                                    threads[j].dos.writeInt(y2);
                                }
                            }
                        }
                        for (int i = 0; i < partide.size(); i++) {
                            if ((partide.get(i).unu.equals(num) && partide.get(i).doi.equals(myname)) || (partide.get(i).unu.equals(myname) && partide.get(i).doi.equals(num))) {
                                for (int k = 0; k < partide.get(i).part.size(); k++) {
                                    synchronized (this) {
                                        for (int j = 0; j < 100; j++) {
                                            if (threads[j] != null && threads[j] != this && threads[j].myname != null && threads[j].myname.equals(partide.get(i).part.get(k))) {
                                                threads[j].dos.writeUTF("muta");
                                                threads[j].dos.writeInt(prom);
                                                threads[j].dos.writeInt(x1);
                                                threads[j].dos.writeInt(y1);
                                                threads[j].dos.writeInt(x2);
                                                threads[j].dos.writeInt(y2);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                } catch (IOException ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                } catch (Exception ex) {
                    Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
