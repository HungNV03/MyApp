package fpoly.edu.du_an_1_pd06861.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import fpoly.edu.du_an_1_pd06861.helper.DBhelper;
import fpoly.edu.du_an_1_pd06861.modol.HopDong;

public class HopDongDAO {
    DBhelper dBhelper;

    public HopDongDAO(Context context) {
        dBhelper=new DBhelper(context);
    }
    public ArrayList<HopDong> getHopDong(){
        ArrayList<HopDong> list=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase =dBhelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT hd.mahd, hd.idnv,nv.tennv, hd.idkh,kh.tenkh, hd.idda,da.tenduan, hd.sotien,hd.ngaymua,hd.trangthai FROM HOPDONG hd,NHANVIEN nv, KHACHHANG kh, DUAN da WHERE hd.idnv=nv.idnv AND hd.idkh=kh.idkh AND hd.idda=da.idda",null);
        if (cursor.getCount()!=0){
            cursor.moveToFirst();
            do {
                list.add(new HopDong(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4),cursor.getInt(5),cursor.getString(6),cursor.getInt(7),cursor.getString(8),cursor.getInt(9)));
            }while (cursor.moveToNext());
        }

        return list;
    }
    public boolean Thaydoitrangthai(int mahd){
        SQLiteDatabase sqLiteDatabase=dBhelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("trangthai",1);
        long check=sqLiteDatabase.update("HOPDONG",contentValues,"mahd=?",new String[]{String.valueOf(mahd)});
        if (check==-1)
            return false;
        return true;
    }
    public  boolean ThemHopdong(HopDong hopDong){
        SQLiteDatabase sqLiteDatabase=dBhelper.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put("idnv",hopDong.getIdnv());
        contentValues.put("idkh",hopDong.getIdkh());
        contentValues.put("idda",hopDong.getIdda());
        contentValues.put("sotien",hopDong.getSotien());
        contentValues.put("ngaymua",hopDong.getNgaymua());
        contentValues.put("trangthai",hopDong.getTrangthai());

        long check=sqLiteDatabase.insert("HOPDONG",null,contentValues);
        if (check==-1)
            return false;
        return true;


    }
}
