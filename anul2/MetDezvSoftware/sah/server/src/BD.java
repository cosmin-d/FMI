import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class BD {
    Statement stmt;

    BD(Statement s) {
        stmt = s;
    }

    public void adauga(persoana p) throws Exception {
        String my_stmt = "INSERT INTO JUCATORI (NUME,PAROLA, POZA, EMAIL, PUNCTAJ)"
                + "VALUES('" + p.getNume() + "','"
                + p.getParola() + "','"
                + p.getPoza() + "','"
                + p.getEmail() + "','"
                + p.getPunctaj() + "');";

        int i = stmt.executeUpdate(my_stmt);

    }

    public persoana cauta(String camp, String valoare, String insertedPassword) throws Exception {
        String my_query = "SELECT * FROM JUCATORI WHERE( " + camp + " = " + "'" + valoare + "');";

        ResultSet rs = stmt.executeQuery(my_query);
        int ok = 0;

        persoana p = null;
        while (rs.next()) {
            String nume = rs.getString("nume");
            String parola = rs.getString("parola");
            String email = rs.getString("email");
            String punctaj = rs.getString("punctaj");
            ok = 1;

            if (insertedPassword.equals(parola)) {
                p = new persoana(nume, parola, "poza", email, punctaj);
            } else
                p = new persoana("-", "-", "-", "-", "-");
        }
        if (ok == 0)
            p = new persoana("*", "-", "-", "-", "-");
        System.out.println(p.getNume() + p.getEmail() + p.getPunctaj());
        return p;
    }

    //Adaugata-> returenaza persoana cu numele dat ca argument
    public persoana cautaNume(String valoarea) throws Exception {
        String my_query = "SELECT * FROM JUCATORI WHERE NUME = '" + valoarea + "'";

        ResultSet rs = stmt.executeQuery(my_query);
        int ok = 0;

        persoana p = null;
        while (rs.next()) {
            String nume = rs.getString("nume");
            String parola = rs.getString("parola");
            String email = rs.getString("email");
            String punctaj = rs.getString("punctaj");
            ok = 1;
            p = new persoana(nume, parola, "poza", email, punctaj);
        }

        if (ok == 0)
            p = new persoana("-", "-", "-", "-", "-");

        return p;
    }

    //Adaugata-> retureneaza 1 daca numele dat ca argument se afla deja in baza de date, 0 altfel
    public int cautaNume2(String valoarea) throws Exception {
        String my_query = "SELECT * FROM JUCATORI WHERE NUME = '" + valoarea + "'";

        ResultSet rs = stmt.executeQuery(my_query);
        int ok = 0;
        System.out.println(my_query);
        persoana p = null;
        while (rs.next()) {
            String nume = rs.getString("nume");
            String parola = rs.getString("parola");
            String email = rs.getString("email");
            String punctaj = rs.getString("punctaj");
            ok = 1;
        }

        if (ok == 0)
            return 0;

        return 1;
    }

    public void modificaPunctaj(String camp, String valoare, double pct) throws SQLException {
        String my_query = "SELECT * FROM JUCATORI WHERE( " + camp + " = " + "'" + valoare + "');";

        ResultSet rs = stmt.executeQuery(my_query);

        double punctaj = 0;
        String nume = "";
        while (rs.next()) {
            punctaj = Double.parseDouble(rs.getString("punctaj"));
            nume = rs.getString("nume");
        }

        punctaj += pct;

        String my_stmt = "UPDATE JUCATORI SET PUNCTAJ = '" + Double.toString(punctaj) + "' WHERE( NUME" + " = " + "'" + nume + "');";

        int i = stmt.executeUpdate(my_stmt);
    }

    public void trimiteMail(String camp, String valoare) throws Exception {
        String my_query = "SELECT EMAIL FROM JUCATORI WHERE (" + camp + " = '" + valoare + "');";
        ResultSet rs = stmt.executeQuery(my_query);

        String to = "";
        while (rs.next())
            to = rs.getString(1);

        String host = "smtp.gmail.com";
        String username = "dragomirmadalina93@gmail.com";
        String password = "mz1pq0gnx";
        String subject = "Hello";
        String body = "Nati, moboginie. Sa dea cel de sus sa mearga!";

        Properties props = new Properties();
        props.put("mail.smtps.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage msg = new MimeMessage(session);
        BodyPart msgBodyPart = new MimeBodyPart();
        Multipart multipart = new MimeMultipart();

        msg.setFrom(new InternetAddress(username));
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
        msg.setSubject(subject);
        msgBodyPart.setText("Moboginie");
        multipart.addBodyPart(msgBodyPart);

        msgBodyPart = new MimeBodyPart();
        String filename = "C:\\Users\\Madalina\\Desktop\\mds\\sah\\client\\mutari.txt";
        DataSource source = new FileDataSource(filename);
        msgBodyPart.setDataHandler(new DataHandler(source));
        msgBodyPart.setFileName(filename);
        multipart.addBodyPart(msgBodyPart);
        msg.setContent(multipart);

        Transport t = session.getTransport("smtps");
        t.connect(host, username, password);
        t.sendMessage(msg, msg.getAllRecipients());
        t.close();
    }
}