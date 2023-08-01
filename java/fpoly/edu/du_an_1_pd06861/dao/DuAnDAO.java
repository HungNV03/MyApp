package fpoly.edu.du_an_1_pd06861.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import fpoly.edu.du_an_1_pd06861.helper.DBhelper;
import fpoly.edu.du_an_1_pd06861.modol.DuAn;

public class DuAnDAO {
    DBhelper dBhelper;

    public DuAnDAO(Context context){
        dBhelper=new DBhelper(context);
    }
    public ArrayList<DuAn> getDuAn(){
        ArrayList<DuAn> list=new ArrayList<>();
        SQLiteDatabase sqLiteDatabase=dBhelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT da.idda, da.tenduan FROM DUAN da",null);
        if (cursor.getCount()!=0){
            cursor.moveToFirst();
            do {
                list.add(new DuAn(cursor.getInt(0),cursor.getString(1)));
            }while (cursor.moveToNext());
        }
        return list;
    }
    public boolean ThemDuAn(String tenduan) {
        SQLiteDatabase sqLiteDatabase = dBhelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("tenduan", tenduan);

        long check = sqLiteDatabase.insert("DUAN", null, contentValues);
        if (check == -1)
            return false;
            return true;

        }
        public int xoaduan(int idda){
        SQLiteDatabase sqLiteDatabase=dBhelper.getWritableDatabase();
        long check=sqLiteDatabase.delete("DUAN","idda=?",new String[]{String.valueOf(idda)});
        if (check==-1)
            return 0;
        return 1;

        }
        public boolean Thaydoiduan(DuAn duAn) {
            SQLiteDatabase sqLiteDatabase = dBhelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("tenduan", duAn.getTenduan());
            long check = sqLiteDatabase.update("DUAN", contentValues, "idda=?", new String[]{String.valueOf(duAn.getIdda())});
            if (check == -1)
                return false;
            return true;


        }
    }
