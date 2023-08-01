package fpoly.edu.du_an_1_pd06861.dao;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import fpoly.edu.du_an_1_pd06861.helper.DBhelper;
import fpoly.edu.du_an_1_pd06861.modol.Nguoidung;

public class NguoidungDao {
    DBhelper dbHelper;
    SharedPreferences sharedPreferences;

    public NguoidungDao(Context context){
        dbHelper=new DBhelper(context);
        sharedPreferences=context.getSharedPreferences("THONGTIN",Context.MODE_PRIVATE);
    }
    public boolean kiemtradangnhap(String user , String pass){
        SQLiteDatabase sqLiteDatabase= dbHelper.getReadableDatabase();
        Cursor cursor= sqLiteDatabase.rawQuery("SELECT * FROM NGUOIDUNG WHERE username= ? AND password= ?" ,new String[]{user, pass});
        if (cursor.getCount()!=0){
            cursor.moveToFirst();
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putInt("id",cursor.getInt(0));
            editor.apply();
            return true;
        }
        return false;
    }


}
