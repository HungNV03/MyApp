package fpoly.edu.du_an_1_pd06861.modol;

public class KhachHang {
    private int idkh;
    private String tenkh;
    private String cccd;
    private String phone;

    public KhachHang(int idkh, String tenkh, String cccd, String phone) {
        this.idkh = idkh;
        this.tenkh = tenkh;
        this.cccd = cccd;
        this.phone = phone;
    }

    public int getIdkh() {
        return idkh;
    }

    public void setIdkh(int idkh) {
        this.idkh = idkh;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public String  getCccd() {

        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
