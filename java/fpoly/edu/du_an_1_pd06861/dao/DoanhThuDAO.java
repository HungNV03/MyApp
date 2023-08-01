package fpoly.edu.du_an_1_pd06861.dao;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import fpoly.edu.du_an_1_pd06861.helper.DBhelper;

public class DoanhThuDAO {
    DBhelper dBhelper;
    public DoanhThuDAO(Context context){
        dBhelper=new DBhelper(context);
    }

    public int getDoanhThu(String tungay, String denngay){
        tungay=tungay.replace("/","");
        denngay=denngay.replace("/","");
        SQLiteDatabase sqLiteDatabase=dBhelper.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT SUM(sotien) FROM HOPDONG WHERE  ? AND ?", new String[]{tungay,denngay});
        if (cursor.getCount() != 0){
            cursor.moveToFirst();
            return cursor.getInt(0);
        }
        return 0;
    }
}
