package fpoly.edu.du_an_1_pd06861.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    public DBhelper(Context context ) {
        super(context, "BATDONGSAN",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String dbnguoidung="CREATE TABLE NGUOIDUNG(id integer primary key , username text, password text, name text)";
        sqLiteDatabase.execSQL(dbnguoidung);
        String insnguoidung= "INSERT INTO NGUOIDUNG VALUES(1,'nguyenvanhung','123456','Nguyễn Hùng')";
        sqLiteDatabase.execSQL(insnguoidung);
        String dbnhanvien="CREATE TABLE NHANVIEN(idnv integer primary key, tennv text, chucvu text)";
        sqLiteDatabase.execSQL(dbnhanvien);
        String insnhanvien="INSERT INTO NHANVIEN VALUES(1,'nguyễn văn hùng', 'quản lý'), (2,'lê văn tùng','nhân viên'), (3,'đoàn thị thùy trâm','nhân viên'), (4,'lý ngọc sang','nhân viên'), (5,'đỗ thị thừa','nhân viên')";
        sqLiteDatabase.execSQL(insnhanvien);
        String dbkhachhang="CREATE TABLE KHACHHANG(idkh integer primary key, tenkh text,cccd text, phone text)";
        sqLiteDatabase.execSQL(dbkhachhang);
        String inskhachhang="INSERT INTO KHACHHANG VALUES(1,'nguyễn văn kiên',0365874,0365874555),(2,'lê văn vĩnh',0365874,036254578855),(3,'nguyễn văn tài',0365874,036587368)";
        sqLiteDatabase.execSQL(inskhachhang);
        String dbduan="CREATE TABLE DUAN(idda integer primary key, tenduan text)";
        sqLiteDatabase.execSQL(dbduan);
        String insduan="INSERT INTO DUAN VALUES(1,'chung cư phía đông'),(2,'công ty nhựa đường'),(3,'khu vui chơi')";
        sqLiteDatabase.execSQL(insduan);
        String dbhopdong="CREATE TABLE HOPDONG(mahd integer primary key autoincrement, idnv integer references NHANVIEN(idnv), idkh integer references KHACHHANG(idkh),idda integer references DUAN(idda),sotien integer, ngaymua text, trangthai integer)";
        sqLiteDatabase.execSQL(dbhopdong);
        String inshopdong="INSERT INTO HOPDONG VALUES(1,1,2,1,30,'22/12/2022',0),(2,2,1,2,100,'22/02/2022',1), (3,1,3,3,2000,'12/12/2022',0)";
        sqLiteDatabase.execSQL(inshopdong);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        if (i!=i1){
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS NGUOIDUNG");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS NHANVIEN");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS KHACHHANG");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS DUAN");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS HOPDONG");

            onCreate(sqLiteDatabase);
        }
    }
}
