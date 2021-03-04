package project.Matricol;

public class Matricol {
    private int codm;
    private int an;
    private String grupa;
    private String medie;
    private int bursa;
    private String formainv;
    private int codst;
    private int cods;
    private String cetatenie;
    private String datan;
    private String nume;
    private String prenume;



    public Matricol(int codm, int an, String grupa, String medie, int bursa, String formainv, int codst, int cods, String nume, String prenume, String cetatenie, String datan) {
        super();
        this.codm = codm;
        this.an = an;
        this.grupa = grupa;
        this.medie = medie;
        this.bursa = bursa;
        this.formainv = formainv;
        this.codst = codst;
        this.cods= cods;
        this.nume = nume;
        this.prenume = prenume;
        this.cetatenie = cetatenie;
        this.datan = datan;

    }

    public void setCodm(int codm) {
        this.codm = codm;
    }

    public void setAn(int an) {
        this.an = an;
    }

    public void setGrupa(String grupa) {
        this.grupa = grupa;
    }

    public void setMedie(String medie) {
        this.medie = medie;
    }

    public void setBursa(int bursa) {
        this.bursa = bursa;
    }

    public void setFormainv(String formainv) {
        this.formainv = formainv;
    }

    public void setCodst(int codst) {
        this.codst = codst;
    }

    public void setCods(int cods) {
        this.cods = cods;
    }

    public void setCetatenie(String cetatenie) {
        this.cetatenie = cetatenie;
    }

    public void setDatan(String datan) {
        this.datan = datan;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public int getCodm() {
        return codm;
    }

    public int getAn() {
        return an;
    }

    public String getGrupa() {
        return grupa;
    }

    public String getMedie() {
        return medie;
    }

    public int getBursa() {
        return bursa;
    }

    public String getFormainv() {
        return formainv;
    }

    public int getCodst() {
        return codst;
    }

    public int getCods() {
        return cods;
    }

    public String getCetatenie() {
        return cetatenie;
    }

    public String getDatan() {
        return datan;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }
}
