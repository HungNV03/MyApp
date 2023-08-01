package fpoly.edu.du_an_1_pd06861;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;

import androidx.annotation.Nullable;

import fpoly.edu.du_an_1_pd06861.dao.NguoidungDao;

public class KTlogin extends Service {
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

            Bundle bundle = intent.getExtras();
            String user = bundle.getString("user");
            String pass = bundle.getString("pass");
            NguoidungDao nguoiDungDao = new NguoidungDao(this);
            boolean check = nguoiDungDao.kiemtradangnhap(user, pass);
//gui date
            Intent intentBR = new Intent();
            Bundle bundleBR = new Bundle();
            bundleBR.putBoolean("check", check);
            intentBR.putExtras(bundleBR);
            intentBR.setAction("kiemtradangnhap");
            sendBroadcast(intentBR);
            return super.onStartCommand(intent, flags, startId);
        }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
