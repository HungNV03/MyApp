package fpoly.edu.du_an_1_pd06861.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.edu.du_an_1_pd06861.helper.DBhelper;
import fpoly.edu.du_an_1_pd06861.modol.KhachHang;

public class KhachHangDAO {
    DBhelper dBhelper;
    public KhachHangDAO(Context context){
        dBhelper=new DBhelper(context);

    }
    public ArrayList<KhachHang> getKhachHang(){
        ArrayList<KhachHang>list=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=dBhelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT kh.idkh,kh.tenkh,kh.cccd,kh.phone FROM KHACHHANG kh",null);
        if (cursor.getCount()!=0){
            cursor.moveToFirst();
            do {
                list.add(new KhachHang(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public boolean capnhatthongtinkh(int idkh, String tenkh, String cccd, String phone){
        SQLiteDatabase sqLiteDatabase=dBhelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("tenkh", tenkh);
        contentValues.put("cccd", cccd);
        contentValues.put("phone", phone);
        long check=sqLiteDatabase.update("KHACHHANG",contentValues,"idkh=?",new String[]{String.valueOf(idkh)});
        if (check==-1)
            return false;
        return true;
    }
    public int xoathogtinkh(int idkh){
        SQLiteDatabase sqLiteDatabase=dBhelper.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM HOPDONG WHERE idkh=?",new String[]{String.valueOf(idkh)});
        if (cursor.getCount()!=0){
            return -1;
        }
        long check=sqLiteDatabase.delete("KHACHHANG","idkh=?",new String[]{String.valueOf(idkh)});
        if (check==-1)
            return 0;
        return 1;
    }
    public boolean ThemKhachHang(String tenkh,String cccd,String phone){
        SQLiteDatabase sqLiteDatabase=dBhelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("tenkh",tenkh);
        contentValues.put("cccd",cccd);
        contentValues.put("phone",phone);

        long check=sqLiteDatabase.insert("KHACHHANG",null,contentValues);
        if (check==-1)
            return false;
        return true;
    }
}
