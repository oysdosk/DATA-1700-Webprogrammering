package webprog.eksamensoving.konte2020;

public class StudentKarakter {
    private int sId;
    private String fId;
    private String karakter;
    private int prosent;
    private int aar;

    public StudentKarakter(int sId, String fId, String karakter, int prosent, int aar) {
        this.sId = sId;
        this.fId = fId;
        this.karakter = karakter;
        this.prosent = prosent;
        this.aar = aar;
    }

    public StudentKarakter() {
    }

    public int getsId() {
        return sId;
    }

    public void setsId(int sId) {
        this.sId = sId;
    }

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    public String getKarakter() {
        return karakter;
    }

    public void setKarakter(String karakter) {
        this.karakter = karakter;
    }

    public int getProsent() {
        return prosent;
    }

    public void setProsent(int prosent) {
        this.prosent = prosent;
    }

    public int getAar() {
        return aar;
    }

    public void setAar(int aar) {
        this.aar = aar;
    }
}
