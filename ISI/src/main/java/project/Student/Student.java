package project.Student;

public class Student {
    private int codst;
    private String nume;
    private String prenume;
    private String cetatenie;
    private String datan;

    public Student(int codst, String nume, String prenume, String cetatenie, String datan) {
        super();
        this.codst = codst;
        this.nume = nume;
        this.prenume = prenume;
        this.cetatenie = cetatenie;
        this.datan = datan;

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

    public void setCetatenie(String cetatenie) {
        this.cetatenie = cetatenie;
    }

    public void setDatan(String datan) {
        this.datan = datan;
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

    public String getCetatenie() {
        return cetatenie;
    }

    public String getDatan() {
        return datan;
    }
}
