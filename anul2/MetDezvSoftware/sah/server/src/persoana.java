public class persoana {
    private String nume, parola, poza, email, punctaj;

    public persoana(String n, String p, String po, String e, String pu) {
        nume = n;
        parola = p;
        poza = po;
        email = e;
        punctaj = pu;
    }

    public String getNume() {
        return nume;
    }

    public String getParola() {
        return parola;
    }

    public String getPoza() {
        return poza;
    }

    public String getEmail() {
        return email;
    }

    public String getPunctaj() {
        return punctaj;
    }

}

