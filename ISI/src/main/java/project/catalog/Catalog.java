package project.catalog;

public class Catalog {
    private int id;
    private int codst;
    private String nume;
    private String prenume;
    private String sesiune;
    private String ip;
    private String bd;
    private String paw;
    private String plf;
    private String poo;
    private String am;
    private String lft;

    public Catalog(int id, int codst, String nume, String prenume, String sesiune, String ip, String bd, String paw, String plf, String poo, String am, String lft) {
        this.id = id;
        this.codst = codst;
        this.nume = nume;
        this.prenume = prenume;
        this.sesiune = sesiune;
        this.ip = ip;
        this.bd = bd;
        this.paw = paw;
        this.plf = plf;
        this.poo = poo;
        this.am = am;
        this.lft = lft;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCodst(int codst) {
        this.codst = codst;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public void setSesiune(String sesiune) {
        this.sesiune = sesiune;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setBd(String bd) {
        this.bd = bd;
    }

    public void setPaw(String paw) {
        this.paw = paw;
    }

    public void setPlf(String plf) {
        this.plf = plf;
    }

    public void setPoo(String poo) {
        this.poo = poo;
    }

    public void setAm(String am) {
        this.am = am;
    }

    public void setLft(String lft) {
        this.lft = lft;
    }

    public int getId() {
        return id;
    }

    public int getCodst() {
        return codst;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public String getSesiune() {
        return sesiune;
    }

    public String getIp() {
        return ip;
    }

    public String getBd() {
        return bd;
    }

    public String getPaw() {
        return paw;
    }

    public String getPlf() {
        return plf;
    }

    public String getPoo() {
        return poo;
    }

    public String getAm() {
        return am;
    }

    public String getLft() {
        return lft;
    }
}
