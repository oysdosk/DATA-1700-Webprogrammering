package webprog.eksamensoving.pojos;

public class Bil implements Comparable<Bil> {
    private String merke;
    private String type;

    public Bil(String merke, String type){
        this.merke = merke;
        this.type = type;
    }

    public Bil(){}

    public String getMerke(){
        return merke;
    }

    public void setMerke(String merke){
        this.merke = merke;
    }

    public String getType(){
        return type;
    }

    public void setType(String type){
        this.type = type;
    }

    public int compareTo(Bil b){
        return this.getMerke().compareTo(b.getMerke());
    }
}
