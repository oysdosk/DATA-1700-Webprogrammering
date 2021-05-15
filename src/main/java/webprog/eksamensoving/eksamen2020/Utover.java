package webprog.eksamensoving.eksamen2020;

public class Utover {
    private String id;
    private String Fornavn;
    private String Etternavn;
    private String klubb;
    private String epost;
    private String passord;

    public Utover(String id, String fornavn, String etternavn, String klubb, String epost, String passord) {
        this.id = id;
        Fornavn = fornavn;
        Etternavn = etternavn;
        this.klubb = klubb;
        this.epost = epost;
        this.passord = passord;
    }

    public Utover (){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFornavn() {
        return Fornavn;
    }

    public void setFornavn(String fornavn) {
        Fornavn = fornavn;
    }

    public String getEtternavn() {
        return Etternavn;
    }

    public void setEtternavn(String etternavn) {
        Etternavn = etternavn;
    }

    public String getKlubb() {
        return klubb;
    }

    public void setKlubb(String klubb) {
        this.klubb = klubb;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public String getPassord() {
        return passord;
    }

    public void setPassord(String passord) {
        this.passord = passord;
    }
}
