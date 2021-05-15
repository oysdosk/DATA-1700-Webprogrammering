package webprog.eksamensoving.eksamen2020;

public class Person {
    private String epost;
    private String passord;

    public Person(String epost, String passord) {
        this.epost = epost;
        this.passord = passord;
    }

    public Person(){}

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
