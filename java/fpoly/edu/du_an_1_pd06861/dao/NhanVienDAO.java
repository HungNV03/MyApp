package fpoly.edu.du_an_1_pd06861.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.edu.du_an_1_pd06861.helper.DBhelper;
import fpoly.edu.du_an_1_pd06861.modol.NhanVien;

public class NhanVienDAO {
    DBhelper dBhelper;

    public NhanVienDAO(Context context) {
        dBhelper = new DBhelper(context);
    }
    public ArrayList<NhanVien> getNhanvien(){
        ArrayList<NhanVien> list=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=dBhelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT nv.idnv, nv.tennv, nv.chucvu FROM NHANVIEN nv",null);
        if (cursor.getCount()!=0){
            cursor.moveToFirst();
            do {
                list.add(new NhanVien(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public boolean CapnhatthongtinNV(int idnv, String tennv,String chucvu){
        SQLiteDatabase sqLiteDatabase=dBhelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("tennv", tennv);
        contentValues.put("chucvu", chucvu);
        long check=sqLiteDatabase.update("NHANVIEN",contentValues,"idnv=?",new String[]{String.valueOf(idnv)});
        if (check==-1)
            return false;
        return true;

    }
}
