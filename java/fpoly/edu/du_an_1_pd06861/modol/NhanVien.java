package fpoly.edu.du_an_1_pd06861.modol;

public class NhanVien {
    private int idnv;
    private String tennv;
    private String chuvu;

    public NhanVien(int idnv, String tennv, String chuvu) {
        this.idnv = idnv;
        this.tennv = tennv;
        this.chuvu = chuvu;
    }

    public int getIdnv() {
        return idnv;
    }

    public void setIdnv(int idnv) {
        this.idnv = idnv;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public String getChuvu() {
        return chuvu;
    }

    public void setChuvu(String chuvu) {
        this.chuvu = chuvu;
    }
}
