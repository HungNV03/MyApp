package fpoly.edu.du_an_1_pd06861.modol;

public class Nguoidung {
    int id;
    String username;
    String password;
    String ten;

    public Nguoidung(int id, String username, String password, String ten) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.ten = ten;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
}
