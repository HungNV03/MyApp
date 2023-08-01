package fpoly.edu.du_an_1_pd06861.modol;

public class HopDong {
    private int mahd;
    private int idnv;
    private int idkh;
    private int idda;
    private int sotien;
    private String ngaymua;
    private String tennv;
    private String tenkh;
    private String tenduan;
    private int trangthai;

    public HopDong(int mahd, int idnv, String tennv, int idkh, String tenkh, int idda , String tenduan, int sotien, String ngaymua, int trangthai) {
        this.mahd = mahd;
        this.idnv = idnv;
        this.idkh = idkh;
        this.idda = idda;
        this.sotien = sotien;
        this.ngaymua = ngaymua;
        this.tennv = tennv;
        this.tenkh = tenkh;
        this.tenduan = tenduan;
        this.trangthai=trangthai;
    }

    public HopDong(int idnv, int idkh, int idda, int sotien, String ngaymua, int trangthai) {
        this.idnv = idnv;
        this.idkh = idkh;
        this.idda = idda;
        this.sotien = sotien;
        this.ngaymua = ngaymua;
        this.trangthai = trangthai;
    }

    public int getMahd() {
        return mahd;
    }

    public void setMahd(int mahd) {
        this.mahd = mahd;
    }

    public int getIdnv() {
        return idnv;
    }

    public void setIdnv(int idnv) {
        this.idnv = idnv;
    }

    public int getIdkh() {
        return idkh;
    }

    public void setIdkh(int idkh) {
        this.idkh = idkh;
    }

    public int getIdda() {
        return idda;
    }

    public void setIdda(int idda) {
        this.idda = idda;
    }

    public int getSotien() {
        return sotien;
    }

    public void setSotien(int sotien) {
        this.sotien = sotien;
    }

    public String getNgaymua() {
        return ngaymua;
    }

    public void setNgaymua(String ngaymua) {
        this.ngaymua = ngaymua;
    }

    public String getTennv() {
        return tennv;
    }

    public void setTennv(String tennv) {
        this.tennv = tennv;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public String getTenduan() {
        return tenduan;
    }

    public void setTenduan(String tenduan) {
        this.tenduan = tenduan;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }
}
