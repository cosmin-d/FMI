package sah;

import joc.GUIRunner;
import joc.King;
import joc.Location;
import joc.ReplayGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GUI1 extends JPanel {

    GUIRunner run;
    Socket cs = null;
    Image backgroundImage;
    JFrame Window;
    JLabel Logare, Creare, jocTitle;
    JTextField emailArea1, emailArea2, numeArea, pozaArea, jocArea;
    JLabel emailLabel1, emailLabel2, numeLabel, pozaLabel, eroareParola, eroareCont, eroareConectare, eroareCampuri, eroareEmail, eroarePoza, eroarePoza2, eroareNume, eroareJoc;
    JPasswordField parolaArea1, parolaArea2;
    JLabel parolaLabel1, parolaLabel2, label, jocLabel;
    JButton butonLogin, butonCreare, butonPoza, butonJoc, butonInc;
    JLabel showNume, showEmail, showPunctaj;

    JFrame Window2;
    JLabel Welcome, jucatori_online, mesaj_nou, partide_online;
    JButton Poza;
    JLabel nume, email, punctaj;
    JList jucatori, partide;
    JButton joaca, vezi, online_chat;

    Image image, image2;

    JFrame Window3;
    JLabel Chatul, eroareChat, eroareConectareChat, nume1, nume2;
    JButton Poza2;
    JTextArea all_mesaj;
    JScrollPane scrolltxt;
    JTextField mesaj;

    DataInputStream dis = null;
    DataOutputStream dos = null;

    String MyName = null;
    // String[] paint ;


    GUI1() throws IOException {

        // paint = new String[100];
        Window = new JFrame("Getting started!");
        label = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("bck.jpg"))));
        Window.setContentPane(label);
        Window.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        Window.setLocation(dim.width / 2 - Window.getSize().width / 2, dim.height / 2 - Window.getSize().height / 2);
        Window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Logare = new JLabel("Logare in cont");
        Logare.setFont(new Font("Century Gothic", Font.BOLD, 20));
        Logare.setBounds(190, 20, 200, 100);

        emailLabel1 = new JLabel("Email: ");
        emailLabel1.setFont(new Font("Century Gothic", Font.BOLD, 15));
        emailLabel1.setBounds(90, 150, 80, 25);
        emailArea1 = new JTextField() {
            protected void paintComponent(Graphics g) {
                g.setColor(getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        emailArea1.setOpaque(false);
        emailArea1.setFont(new Font("Century Gothic", Font.PLAIN, 15));
        emailArea1.setBackground(new Color(0, 0, 0, 0));
        emailArea1.setBounds(150, 150, 200, 30);

        parolaLabel1 = new JLabel("Parola: ");
        parolaLabel1.setFont(new Font("Century Gothic", Font.BOLD, 15));
        parolaLabel1.setBounds(90, 210, 80, 25);
        parolaArea1 = new JPasswordField() {
            protected void paintComponent(Graphics g) {
                g.setColor(getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        parolaArea1.setOpaque(false);
        parolaArea1.setBackground(new Color(0, 0, 0, 0));
        parolaArea1.setBounds(150, 210, 200, 30);

        butonLogin = new JButton("LOGARE");
        butonLogin.setBackground(Color.decode("#58ACFA"));
        butonLogin.setForeground(Color.WHITE);
        butonLogin.setFont(new Font("Century Gothic", Font.BOLD, 14));
        butonLogin.setBounds(190, 270, 90, 30);
        ActionListenerLogare();

        eroareParola = new JLabel("Introduceti parola corect!");
        eroareParola.setFont(new Font("Century Gothic", Font.BOLD, 15));
        eroareParola.setBounds(90, 330, 200, 20);
        eroareParola.setVisible(false);

        eroareCont = new JLabel("Va rugam creati-va cont!");
        eroareCont.setFont(new Font("Century Gothic", Font.BOLD, 15));
        eroareCont.setBounds(90, 330, 200, 20);
        eroareCont.setVisible(false);


        jocTitle = new JLabel("Vizualizeaza joc salvat");
        jocTitle.setFont(new Font("Century Gothic", Font.BOLD, 20));
        jocTitle.setBounds(180, 360, 300, 25);
        jocLabel = new JLabel("Incarca joc: ");
        jocLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
        jocLabel.setBounds(90, 400, 100, 25);
        jocArea = new JTextField();
        jocArea.setOpaque(false);
        jocArea.setBackground(new Color(0, 0, 0, 0));
        jocArea.setBounds(190, 400, 200, 30);
        jocArea.setFont(new Font("Century Gothic", Font.PLAIN, 15));
        jocArea.setEditable(false);

        butonJoc = new JButton("Cauta");
        butonJoc.setBackground(Color.decode("#58ACFA"));
        butonJoc.setForeground(Color.WHITE);
        butonJoc.setFont(new Font("Century Gothic", Font.BOLD, 12));
        butonJoc.setBounds(400, 400, 70, 30);
        ActionListenerJoc();
        butonInc = new JButton("Vezi!");
        butonInc.setBackground(Color.decode("#58ACFA"));
        butonInc.setForeground(Color.WHITE);
        butonInc.setFont(new Font("Century Gothic", Font.BOLD, 12));
        butonInc.setBounds(250, 450, 70, 30);
        ActionListenerInc();

        eroareJoc = new JLabel("Introduceti locatia partidei salvate.");
        eroareJoc.setFont(new Font("Century Gothic", Font.BOLD, 15));
        eroareJoc.setBounds(90, 500, 300, 20);
        eroareJoc.setVisible(false);

        Window.add(Logare);
        Window.add(emailLabel1);
        Window.add(emailArea1);
        Window.add(parolaLabel1);
        Window.add(parolaArea1);
        Window.add(butonLogin);
        Window.add(eroareParola);
        Window.add(eroareCont);
        Window.add(jocLabel);
        Window.add(jocArea);
        Window.add(butonJoc);
        Window.add(butonInc);
        Window.add(jocTitle);
        Window.add(eroareJoc);


        Creare = new JLabel("Creare cont");
        Creare.setFont(new Font("Century Gothic", Font.BOLD, 20));
        Creare.setBounds(750, 20, 200, 100);

        emailLabel2 = new JLabel("Email: ");
        emailLabel2.setFont(new Font("Century Gothic", Font.BOLD, 15));
        emailLabel2.setBounds(610, 150, 80, 25);
        emailArea2 = new JTextField() {
            protected void paintComponent(Graphics g) {
                g.setColor(getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        emailArea2.setOpaque(false);
        emailArea2.setFont(new Font("Century Gothic", Font.PLAIN, 15));
        emailArea2.setBackground(new Color(0, 0, 0, 0));
        emailArea2.setBounds(700, 150, 200, 30);

        parolaLabel2 = new JLabel("Parola: ");
        parolaLabel2.setFont(new Font("Century Gothic", Font.BOLD, 15));
        parolaLabel2.setBounds(610, 210, 80, 25);
        parolaArea2 = new JPasswordField() {
            protected void paintComponent(Graphics g) {
                g.setColor(getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        parolaArea2.setOpaque(false);
        parolaArea2.setBackground(new Color(0, 0, 0, 0));
        parolaArea2.setBounds(700, 210, 200, 30);

        numeLabel = new JLabel("Username: ");
        numeLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
        numeLabel.setBounds(610, 270, 200, 25);
        numeArea = new JTextField() {
            protected void paintComponent(Graphics g) {
                g.setColor(getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        numeArea.setOpaque(false);
        numeArea.setFont(new Font("Century Gothic", Font.PLAIN, 15));
        numeArea.setBackground(new Color(0, 0, 0, 0));

        numeArea.setBounds(700, 270, 200, 30);

        pozaLabel = new JLabel("Poza: ");
        pozaLabel.setFont(new Font("Century Gothic", Font.BOLD, 15));
        pozaLabel.setBounds(610, 330, 80, 25);
        pozaArea = new JTextField() {
            protected void paintComponent(Graphics g) {
                g.setColor(getBackground());
                g.fillRect(0, 0, getWidth(), getHeight());
                super.paintComponent(g);
            }
        };
        pozaArea.setOpaque(false);
        pozaArea.setBackground(new Color(0, 0, 0, 0));
        pozaArea.setBounds(700, 330, 200, 30);
        pozaArea.setFont(new Font("Century Gothic", Font.PLAIN, 15));
        pozaArea.setEditable(false);

        butonPoza = new JButton("Cauta");
        butonPoza.setBackground(Color.decode("#58ACFA"));
        butonPoza.setForeground(Color.WHITE);
        butonPoza.setFont(new Font("Century Gothic", Font.BOLD, 12));
        butonPoza.setBounds(910, 330, 70, 30);
        ActionListenerPoza();

        butonCreare = new JButton("CREAZA");
        butonCreare.setBackground(Color.decode("#58ACFA"));
        butonCreare.setForeground(Color.WHITE);
        butonCreare.setFont(new Font("Century Gothic", Font.BOLD, 14));
        butonCreare.setOpaque(true);
        butonCreare.setBounds(750, 390, 90, 30);
        ActionListenerCreare();

        eroareCampuri = new JLabel("Va rugam completati toate campurile!");
        eroareCampuri.setFont(new Font("Century Gothic", Font.BOLD, 15));
        eroareCampuri.setBounds(610, 430, 300, 20);
        eroareCampuri.setVisible(false);

        eroareEmail = new JLabel("Email invalid!");
        eroareEmail.setFont(new Font("Century Gothic", Font.BOLD, 15));
        eroareEmail.setBounds(610, 430, 200, 20);
        eroareEmail.setVisible(false);

        eroarePoza = new JLabel("Va rugam introduceti poze cu dimensiune");
        eroarePoza2 = new JLabel("mai mica de 1MB!");
        eroarePoza.setFont(new Font("Century Gothic", Font.BOLD, 15));
        eroarePoza2.setFont(new Font("Century Gothic", Font.BOLD, 15));
        eroarePoza.setBounds(610, 430, 600, 60);
        eroarePoza2.setBounds(610, 460, 600, 60);
        eroarePoza.setVisible(false);
        eroarePoza2.setVisible(false);

        eroareNume = new JLabel("Username utilizat!");
        eroareNume.setFont(new Font("Century Gothic", Font.BOLD, 15));
        eroareNume.setBounds(610, 430, 200, 20);
        eroareNume.setVisible(false);

        Window.add(Creare);
        Window.add(emailLabel2);
        Window.add(emailArea2);
        Window.add(parolaLabel2);
        Window.add(parolaArea2);
        Window.add(butonCreare);
        Window.add(numeLabel);
        Window.add(numeArea);
        Window.add(pozaLabel);
        Window.add(butonPoza);
        Window.add(pozaArea);
        Window.add(eroareCampuri);
        Window.add(eroareEmail);
        Window.add(eroarePoza);
        Window.add(eroarePoza2);
        Window.add(eroareNume);

        eroareConectare = new JLabel("Eroare conectare!");
        eroareConectare.setFont(new Font("Century Gothic", Font.BOLD, 15));
        eroareConectare.setBounds(610, 430, 200, 20);
        eroareConectare.setVisible(false);
        Window.add(eroareConectare);
        Window.setVisible(true);

    }


    void ActionListenerLogare() {
        butonLogin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    String email = emailArea1.getText();
                    String parola = parolaArea1.getText();

                    try {
                        cs = new Socket("127.0.0.1", 50000);
                        dis = new DataInputStream(cs.getInputStream());
                        dos = new DataOutputStream(cs.getOutputStream());
                    } catch (Exception e) {
                        eroareConectare.setVisible(true);
                        System.exit(1);
                    }

                    System.out.println("conectare reusita!");
                    byte[] imageBytes = null;

                    try {
                        dos.writeUTF("Logare cont.");
                        dos.writeUTF(email);
                        dos.writeUTF(parola);

                    } catch (IOException ex) {
                        Logger.getLogger(GUI1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String nn = dis.readUTF();
                    int n = Integer.parseInt(nn);

                    int i;
                    for (i = 0; i < n; i++) {
                        int nr_byte = dis.readInt();
                        imageBytes = new byte[nr_byte];
                        dis.readFully(imageBytes, 0, imageBytes.length);
                        image = getToolkit().createImage(imageBytes);
                    }
                    String eroare = null;
                    try {
                        eroare = dis.readUTF();

                    } catch (IOException ex) {
                        Logger.getLogger(GUI1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (eroare.startsWith("Eroare parola!")) {
                        eroareParola.setVisible(true);
                    } else if (eroare.startsWith("Eroare cont!")) {
                        eroareCont.setVisible(true);
                    } else if (eroare.startsWith("Fara eroare!")) {

                        cont lc = new cont();
                        lc.start();

                    }
                } catch (IOException ex) {
                    Logger.getLogger(GUI1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }


    void ActionListenerPoza() {
        butonPoza.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JPanel frame = new JPanel();
                frame.setSize(400, 400);
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                        "JPG & GIF & PNG Images", "jpg", "gif", "png");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(frame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    if (chooser.getSelectedFile().length() > 1048576) {
                        eroareCampuri.setVisible(false);
                        eroareEmail.setVisible(false);
                        eroarePoza.setVisible(true);
                        eroarePoza2.setVisible(true);
                        pozaArea.setText("");
                    } else {
                        pozaArea.setText(chooser.getSelectedFile().getAbsolutePath());
                    }

                    frame.setVisible(true);
                }

            }
        });
    }

    void ActionListenerInc() {
        butonInc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (jocArea.getText().equals("")) {
                    eroareJoc.setVisible(true);
                } else {
                    String str = jocArea.getText();
                    ReplayGame rg = new ReplayGame(str);
                    eroareJoc.setVisible(false);
                }
                jocArea.setText("");
            }
        });
    }

    void ActionListenerJoc() {
        butonJoc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                JPanel frame = new JPanel();
                frame.setSize(400, 400);
                JFileChooser chooser = new JFileChooser();
                int returnVal = chooser.showOpenDialog(frame);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    if (chooser.getSelectedFile().length() > 1048576) {
                        jocArea.setText("");
                    } else {
                        jocArea.setText(chooser.getSelectedFile().getAbsolutePath());
                    }

                    frame.setVisible(true);
                }

            }
        });
    }

    void ActionListenerCreare() {
        butonCreare.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                try {
                    String email = emailArea2.getText();
                    String parola = parolaArea2.getText();
                    String nume = numeArea.getText();
                    String poza = pozaArea.getText();
                    poza = poza.replaceAll("\\\\", "/");
                    try {
                        cs = new Socket("127.0.0.1", 50000);
                        dis = new DataInputStream(cs.getInputStream());
                        dos = new DataOutputStream(cs.getOutputStream());
                    } catch (Exception e) {
                        eroareConectare.setVisible(true);
                        System.exit(1);
                    }
                    System.out.println("conectare reusita!");
                    byte[] imageBytes = null;

                    String bytes = null;
                    try {
                        dos.writeUTF("Creare cont.");
                        dos.writeUTF(email);
                        dos.writeUTF(parola);
                        dos.writeUTF(nume);
                        dos.writeUTF(poza);

                    } catch (IOException ex) {
                        Logger.getLogger(GUI1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    String eroare;
                    eroare = dis.readUTF();
                    if (eroare.equals("Eroare campuri.")) {
                        eroareEmail.setVisible(false);
                        eroarePoza.setVisible(false);
                        eroarePoza2.setVisible(false);
                        eroareCampuri.setVisible(true);
                        eroareNume.setVisible(false);
                    } else if (eroare.equals("Eroare email.")) {
                        eroareCampuri.setVisible(false);
                        eroarePoza.setVisible(false);
                        eroarePoza2.setVisible(false);
                        eroareEmail.setVisible(true);
                        eroareNume.setVisible(false);
                    } else if (eroare.equals("Eroare nume.")) {
                        eroareEmail.setVisible(false);
                        eroarePoza.setVisible(false);
                        eroarePoza2.setVisible(false);
                        eroareCampuri.setVisible(false);
                        eroareNume.setVisible(true);

                    } else if (eroare.equals("Fara eroare.")) {
                        String nn = dis.readUTF();
                        int n = Integer.parseInt(nn);

                        int i;
                        for (i = 0; i < n; i++) {
                            int nr_byte = dis.readInt();
                            imageBytes = new byte[nr_byte];
                            dis.readFully(imageBytes, 0, imageBytes.length);
                            image = getToolkit().createImage(imageBytes);
                        }
                        cont cc = new cont();
                        cc.start();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(GUI1.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        });
    }

    public void paintWindow2() throws IOException {

        Window2 = new JFrame("Welcome!");
        JLabel label2 = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("bck.jpg"))));
        Window2.setContentPane(label2);
        Window2.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        Window2.setLocation(dim.width / 2 - Window.getSize().width / 2, dim.height / 2 - Window.getSize().height / 2);
        Window2.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        ActionListenerWindow2();

        Welcome = new JLabel("Bun venit in cont,");
        Welcome.setFont(new Font("Century Gothic", Font.BOLD, 15));
        Welcome.setBounds(80, 40, 300, 30);

        Poza = new JButton();
        Poza.setSize(100, 100);
        Poza.setBounds(80, 80, 100, 100);
        ImageIcon icon = new ImageIcon(image);
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon newicon = new ImageIcon(newimg);
        Poza.setIcon(newicon);

        nume = new JLabel("Nume: ");
        showNume = new JLabel(" ");
        nume.setFont(new Font("Century Gothic", Font.BOLD, 15));
        showNume.setFont(new Font("Century Gothic", Font.PLAIN, 15));
        nume.setBounds(80, 190, 70, 20);
        showNume.setBounds(150, 190, 140, 20);

        email = new JLabel("Email: ");
        showEmail = new JLabel(" ");
        email.setFont(new Font("Century Gothic", Font.BOLD, 15));
        showEmail.setFont(new Font("Century Gothic", Font.PLAIN, 15));
        email.setBounds(80, 220, 200, 20);
        showEmail.setBounds(150, 220, 250, 20);

        punctaj = new JLabel("Punctaj: ");
        showPunctaj = new JLabel(" ");
        punctaj.setFont(new Font("Century Gothic", Font.BOLD, 15));
        showPunctaj.setFont(new Font("Century Gothic", Font.PLAIN, 15));
        punctaj.setBounds(80, 250, 200, 20);
        showPunctaj.setBounds(150, 250, 200, 20);

        Window2.add(Welcome);
        Window2.add(Poza);
        Window2.add(nume);
        Window2.add(showNume);
        Window2.add(email);
        Window2.add(showEmail);
        Window2.add(punctaj);
        Window2.add(showPunctaj);

        jucatori_online = new JLabel("Jucatorii logati sunt:");
        jucatori_online.setFont(new Font("Century Gothic", Font.BOLD, 15));
        jucatori_online.setBounds(500, 40, 300, 30);

        String subject[] = {" "};
        jucatori = new JList(subject);
        jucatori.setFont(new Font("Century Gothic", Font.PLAIN, 15));
        jucatori.setBounds(500, 80, 200, 470);

        partide_online = new JLabel("Partidele publice jucate in acest moment:");
        partide_online.setFont(new Font("Century Gothic", Font.BOLD, 15));
        partide_online.setBounds(80, 310, 350, 30);
        partide = new JList(subject);
        partide.setFont(new Font("Century Gothic", Font.PLAIN, 15));
        partide.setBounds(80, 350, 300, 200);

        eroareChat = new JLabel("Va rugam sa va conectati la chat!");
        eroareChat.setFont(new Font("Century Gothic", Font.BOLD, 15));
        eroareChat.setBounds(400, 500, 200, 20);
        eroareChat.setVisible(false);

        eroareConectareChat = new JLabel("Jucatorul nu este online!");
        eroareConectareChat.setFont(new Font("Century Gothic", Font.BOLD, 15));
        eroareConectareChat.setBounds(400, 500, 200, 20);
        eroareConectareChat.setVisible(false);


        Window2.add(jucatori_online);
        Window2.add(partide_online);
        Window2.add(jucatori);
        Window2.add(eroareChat);
        Window2.add(partide);

        Window.setVisible(false);
        Window2.setVisible(true);


    }

    // public static String logare;
    public void paintButoane(int y, String nume_log) throws IOException {
        joaca = new JButton("Joaca");
        joaca.setBackground(Color.decode("#58ACFA"));
        joaca.setForeground(Color.WHITE);
        joaca.setFont(new Font("Century Gothic", Font.BOLD, 12));
        joaca.setBounds(720, y, 100, 20);
        ActionListenerJoaca(nume_log);
        Window2.add(joaca);
        joaca.setVisible(true);
    }

    public void paintBut(int y, String nume_log) throws IOException {
        vezi = new JButton("Vezi");
        vezi.setBackground(Color.decode("#58ACFA"));
        vezi.setForeground(Color.WHITE);
        vezi.setFont(new Font("Century Gothic", Font.BOLD, 12));
        vezi.setBounds(390, y, 70, 20);
        String[] a = nume_log.split(" ");
        System.out.println(a[2] + a[4]);
        ActionListenerVezi(a[2], a[4]);
        Window2.add(vezi);
        vezi.setVisible(true);
    }

    public void ActionListenerVezi(final String unu, final String doi) {
        vezi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    dos.writeUTF("vezi");
                    dos.writeUTF(unu);
                    dos.writeUTF(doi);
                } catch (IOException ex) {
                    Logger.getLogger(GUI1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void ActionListenerJoaca(final String op) {
        joaca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                try {
                    String priv = "Doriti ca partida sa fie privata?";
                    int result = JOptionPane.showConfirmDialog((Component) null, priv, "Invitatie", JOptionPane.YES_NO_OPTION);
                    dos.writeUTF("joaca");
                    dos.writeUTF(op);
                    dos.writeInt(result);
                } catch (IOException ex) {
                    Logger.getLogger(GUI1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void ActionListenerMesaj(final String s) throws IOException {
        mesaj.addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_ENTER) {
                    try {
                        Toolkit.getDefaultToolkit().beep();

                        dos.writeUTF("msg/" + s + "/" + mesaj.getText());
                        if (!mesaj.getText().trim().equals("")) {
                            all_mesaj.append(">" + mesaj.getText() + "\n");
                        }
                        mesaj.setText("");

                    } catch (IOException ex) {
                        Logger.getLogger(GUI1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    public void paintWindow3(String s) throws IOException {

        Window3 = new JFrame("Chat");
        JLabel label2 = new JLabel(new ImageIcon(ImageIO.read(getClass().getResource("bck.jpg"))));
        Window3.setContentPane(label2);
        Window3.pack();
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        Window3.setLocation(dim.width / 2 - Window.getSize().width / 2, dim.height / 2 - Window.getSize().height / 2);
        Window3.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        ActionListenerWindow3(s);

        Chatul = new JLabel("Chat");
        Chatul.setFont(new Font("Century Gothic", Font.BOLD, 15));
        Chatul.setBounds(80, 40, 300, 30);

        Poza = new JButton();
        Poza.setSize(100, 100);
        Poza.setBounds(60, 90, 100, 100);
        ImageIcon icon = new ImageIcon(image);
        Image img = icon.getImage();
        Image newimg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon newicon = new ImageIcon(newimg);
        Poza.setIcon(newicon);

        nume1 = new JLabel(MyName);
        nume1.setFont(new Font("Century Gothic", Font.BOLD, 13));
        nume1.setBounds(60, 200, 100, 30);

        Poza2 = new JButton();
        Poza2.setSize(100, 100);
        Poza2.setBounds(480, 90, 100, 100);
        ImageIcon icon2 = new ImageIcon(image2);
        Image img2 = icon2.getImage();
        Image newimg2 = img2.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon newicon2 = new ImageIcon(newimg2);
        Poza2.setIcon(newicon2);

        nume2 = new JLabel(s);
        nume2.setFont(new Font("Century Gothic", Font.BOLD, 13));
        nume2.setBounds(480, 200, 100, 30);

        mesaj = new JTextField("");
        mesaj.setBounds(170, 90, 300, 30);
        ActionListenerMesaj(s);

        all_mesaj = new JTextArea();
        scrolltxt = new JScrollPane(all_mesaj);
        scrolltxt.setBounds(170, 130, 300, 200);


        Window3.add(Chatul);
        Window3.add(Poza);
        Window3.add(mesaj);
        Window3.add(Poza2);
        Window3.add(scrolltxt);
        Window3.add(nume1);
        Window3.add(nume2);

        Window3.setVisible(true);
        Window2.setVisible(true);
        Window.setVisible(false);

    }

    public void ActionListenerWindow2() throws IOException {
        Window2.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    dos.writeUTF("Iesire!");
                } catch (IOException ex) {
                    Logger.getLogger(GUI1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
    }

    public void ActionListenerWindow3(final String s) throws IOException {
        Window3.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    dos.writeUTF("Sign out!");
                    dos.writeUTF(s);
                    Window3.setVisible(false);
                } catch (IOException ex) {
                    Logger.getLogger(GUI1.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
    }

    class cont extends Thread {

        public void run() {
            try {

                paintWindow2();

                Welcome.setText("Bun venit in cont, " + dis.readUTF() + " !");
                showNume.setText(dis.readUTF());
                showEmail.setText(dis.readUTF());
                showPunctaj.setText(dis.readUTF());
                // Lista jucatorilor
                //dos.writeUTF("Lista jucatori!");
                MyName = dis.readUTF();
                //System.out.println(MyName);
                String cerere;
                int y = 410;
                int k = 0;
                String[] paint = new String[100];
                Vector<String> partid = new Vector<>();
                while (true) {
                    cerere = dis.readUTF();
                    System.out.println(cerere);
                    if (cerere.equals("lista")) {
                        int n = dis.readInt();
                        int i;

                        String[] logati = new String[n];

                        int oy = 80;
                        for (i = 0; i < n; i++) {
                            {
                                logati[i] = dis.readUTF();
                                paintButoane(oy, logati[i]);
                                oy += 25;
                            }
                        }
                        jucatori.setListData(logati);
                    }


                    if (cerere.equals("raspuns")) {
                        String op = dis.readUTF();
                        int rsp = dis.readInt();
                        int priv = dis.readInt();
                        if (rsp == 1) {
                            JOptionPane.showMessageDialog(null, op + " nu a acceptat invitatia ta.");
                        } else {
                            dos.writeUTF("inc");
                            dos.writeUTF(op);
                            dos.writeInt(priv);
                        }
                    }

                    if (cerere.startsWith("Sign out")) {
                        String[] close = cerere.split("/");

                        all_mesaj.append(close[1]);
                    }

                    if (cerere.equals("invitatie")) {
                        String num = dis.readUTF();
                        int priv = dis.readInt();
                        String invit = num + " te-a invitat la o partida ";
                        if (priv == 0) {
                            invit = invit + "privata. ";
                        } else {
                            invit = invit + "publica. ";
                        }
                        invit = invit + "Acceptati?";
                        int result = JOptionPane.showConfirmDialog((Component) null, invit, "Invitatie", JOptionPane.YES_NO_OPTION);
                        dos.writeUTF("rasp");
                        dos.writeUTF(num);
                        dos.writeInt(result);
                        dos.writeInt(priv);
                        if (result == 0) {
                            dos.writeUTF("inc2");
                            dos.writeUTF(num);
                            dos.writeInt(priv);
                        }
                    }

                    if (cerere.equals("incepe")) {
                        String num = dis.readUTF();
                        boolean c = dis.readBoolean();
                        int priv = dis.readInt();
                        run = new GUIRunner(cs, num, c, priv, false);
                        run.setVisible();
                    }

                    if (cerere.equals("listuta")) {
                        int n = dis.readInt();
                        int oy = 350;
                        partid = new Vector<>();
                        for (int i = 0; i < n; i++) {
                            String part = dis.readUTF();
                            partid.add(part);
                            paintBut(oy, partid.get(i));
                            oy += 25;
                        }
                        partide.setListData(partid);
                    }

                    if (cerere.equals("termin")) {
                        String op = dis.readUTF();
                        JOptionPane.showMessageDialog(null, op + " a renuntat la joc. Ai castigat!");
                        run.getGui().setVisible(false);
                    }

                    if (cerere.equals("muta")) {
                        int prom = dis.readInt();
                        int x1 = dis.readInt();
                        int y1 = dis.readInt();
                        int x2 = dis.readInt();
                        int y2 = dis.readInt();
                        Location from = new Location(x1, y1);
                        Location to = new Location(x2, y2);
                        run.getGui().enableSide(run.getTurn());
                        run.setSelectedPiece(from);
                        run.getGui().resetBackground();
                        run.getGui().resetBorders();
                        run.getGui().selected(run.getSelectedPiece());

                        run.getBoard().moveFrom_To(from, to);
                        run.getGui().updatePGN(run.getSelectedPiece(), to);
                        run.setPlayerMoved(true);
                        run.getBoard().setTurn(run.getTurn());
                        run.setSelectedPiece(null);
                        run.getGui().updateBoard(run.getBoard());
                        run.getGui().enableSide(run.getTurn());
                        for (int yg = 0; yg < run.getBoard().getState().length; yg++)
                            for (int x = 0; x < run.getBoard().getState().length; x++)
                                if (run.getBoard().getState()[yg][x] != null && run.getBoard().getState()[yg][x] instanceof King && run.getBoard().getState()[yg][x].getColor() == run.getTurn()) {
                                    ((King) run.getBoard().getState()[yg][x]).updateIsChecked(run.getBoard());
                                    if (((King) run.getBoard().getState()[yg][x]).isChecked())
                                        break;
                                }
                        run.setPromotedPiece(to);
                        run.getBoard().resetOtherPawns(to);
                        if (prom != 0) {
                            if (prom == -1)
                                run.getBoard().addQueen(run.getPromotedPiece());
                            else if (prom == -2)
                                run.getBoard().addRook(run.getPromotedPiece());
                            else if (prom == -3)
                                run.getBoard().addBishop(run.getPromotedPiece());
                            else if (prom == -4)
                                run.getBoard().addKnight(run.getPromotedPiece());
                            for (int yg = 0; yg < run.getBoard().getState().length; yg++)
                                for (int x = 0; x < run.getBoard().getState().length; x++)
                                    if (run.getBoard().getState()[yg][x] != null && run.getBoard().getState()[yg][x] instanceof King && run.getBoard().getState()[yg][x].getColor() == run.getTurn()) {
                                        ((King) run.getBoard().getState()[yg][x]).updateIsChecked(run.getBoard());
                                        if (((King) run.getBoard().getState()[yg][x]).isChecked())
                                            break;
                                    }
                            run.getGui().updateBoard(run.getBoard());
                        }
                        run.prom = 0;
                        run.checkGameOver();
                        run.setPlayerMoved(true);
                        run.getGui().getToolBar().undo.setEnabled(true);


                        run.setIsWhiteTurn(!run.getTurn());
                        run.setPlayerMoved(false);
                        run.getBoard().setTurn(run.getTurn());
                        run.setSelectedPiece(null);
                        run.getGui().updateBoard(run.getBoard());
                        run.getGui().enableSide(run.getTurn());
                        run.getGui().getToolBar().undo.setEnabled(false);
                        if (run.getTurn() == true) {
                            run.getGui().getToolBar().blackChronometer.stopStopWatch();
                            run.getGui().getToolBar().whiteChronometer.launchStopWatch();
                        } else {
                            run.getGui().getToolBar().whiteChronometer.stopStopWatch();
                            run.getGui().getToolBar().blackChronometer.launchStopWatch();
                        }
                        run.checkGameOver();
                    }

                    if (cerere.equals("deseneaza")) {
                        eroareConectareChat.setVisible(false);
                        int n = dis.readInt();
                        byte[] imageBytes;
                        int i;
                        for (i = 0; i < n; i++) {
                            int nr_byte = dis.readInt();
                            imageBytes = new byte[nr_byte];
                            dis.readFully(imageBytes, 0, imageBytes.length);
                            image2 = getToolkit().createImage(imageBytes);
                        }
                        String p = dis.readUTF();
                        paintWindow3(p);
                        paint[k] = p;
                        k++;
                    }

                    if (cerere.startsWith("msg")) {
                        //String mesaj_de_primit = dis.readUTF();
                        String[] msg = cerere.split("/");
                        int ok = 0;
                        for (int i = 0; i < k; i++)
                            if (msg[1].equals(paint[i])) {
                                all_mesaj.append(msg[1] + "<" + msg[2] + "\n");
                                ok = 1;
                                break;
                            }
                    }

                    if (cerere.equals("rem")) {
                        String num = dis.readUTF();
                        String sr = "Jucatorul " + num + " propune remiza. Acceptati?";
                        int result = JOptionPane.showConfirmDialog((Component) null, sr, "Remiza", JOptionPane.YES_NO_OPTION);
                        if (result == 0) {
                            run.getGui().gameOver(2);
                        }
                        dos.writeUTF("rasprem");
                        dos.writeUTF(num);
                        dos.writeInt(result);
                    }

                    if (cerere.equals("eroaremsg")) {
                        all_mesaj.append("Utilizatorul nu a primit mesajul!");
                    }

                    if (cerere.equals("default")) {
                        dos.writeUTF("Lista jucatori!");
                        dos.writeUTF("partide");
                    }

                    if (cerere.equals("listout")) {
                        Vector<String> v = null;
                        jucatori.setListData(v);
                        joaca.setVisible(false);
                        dos.writeUTF("Lista jucatori!");
                    }

                    if (cerere.equals("matout")) {
                        Vector<String> v = null;
                        partide.setListData(v);
                        vezi.setVisible(false);
                        dos.writeUTF("partide");
                    }

                    if (cerere.equals("Iesi")) {
                        Window2.setVisible(false);
                        Window.setVisible(true);
                        emailArea1.setText("");
                        parolaArea1.setText("");
                    }

                    if (cerere.equals("remiz")) {
                        int res = dis.readInt();
                        String num = dis.readUTF();
                        if (res == 0) {
                            JOptionPane.showMessageDialog(null, "Jucatorul " + num + " a acceptat remiza.");
                            run.getGui().gameOver(2);
                        } else {
                            JOptionPane.showMessageDialog(null, "Jucatorul " + num + " nu a acceptat remiza.");
                        }
                    }

                    if (cerere.equals("viz")) {
                        String num = dis.readUTF();
                        boolean c = dis.readBoolean();
                        int priv = dis.readInt();
                        run = new GUIRunner(cs, num, c, priv, true);
                        run.setVisible();
                    }

                }
            } catch (Exception ex) {
                Logger.getLogger(GUI1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}