package fpoly.edu.du_an_1_pd06861;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import fpoly.edu.du_an_1_pd06861.fragment.ChangepassFragment;
import fpoly.edu.du_an_1_pd06861.fragment.DoanhThuFragment;
import fpoly.edu.du_an_1_pd06861.fragment.DuAnFragment;
import fpoly.edu.du_an_1_pd06861.fragment.HopDongFragment;
import fpoly.edu.du_an_1_pd06861.fragment.KhachHangFragment;
import fpoly.edu.du_an_1_pd06861.fragment.NhanVienFragment;
import fpoly.edu.du_an_1_pd06861.fragment.TrangchuFragment;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    Toolbar toolbar;
    View mHeaderView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer=findViewById(R.id.drawer_layout);
        toolbar=findViewById(R.id.toolbar1);

        setSupportActionBar(toolbar);
        ActionBar ab=getSupportActionBar();
        FragmentManager manager=getSupportFragmentManager();
        ab.setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24);
        ab.setDisplayHomeAsUpEnabled(true);

        HopDongFragment hopDongFragment=new HopDongFragment();
        manager.beginTransaction().replace(R.id.flContent,hopDongFragment).commit();

        NavigationView nv=findViewById(R.id.nvView);
        mHeaderView=nv.getHeaderView(0);

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentManager manager=getSupportFragmentManager();
                switch (item.getItemId()){
//                    case R.id.nav_Home:
//                        setTitle("Trang Chủ");
//                        TrangchuFragment trangchuFragment=new TrangchuFragment();
//                        manager.beginTransaction().replace(R.id.flContent,trangchuFragment).commit();
//                        break;
                    case R.id.nav_KhachHang:
                        setTitle("Khách Hàng");
                        KhachHangFragment khachHangFragment=new KhachHangFragment();
                        manager.beginTransaction().replace(R.id.flContent,khachHangFragment).commit();
                        break;
                    case R.id.nav_DuAn:
                        setTitle("Dự Án");
                        DuAnFragment duAnFragment=new DuAnFragment();
                        manager.beginTransaction().replace(R.id.flContent,duAnFragment).commit();
                        break;
                    case R.id.nav_HopDong:
                        setTitle("Hợp Đồng");
                        HopDongFragment hopDongFragment=new HopDongFragment();
                        manager.beginTransaction().replace(R.id.flContent,hopDongFragment).commit();
                        break;
                    case R.id.nav_NhanSu:
                        setTitle("Nhân Sự");
                        NhanVienFragment nhanVienFragment=new NhanVienFragment();
                        manager.beginTransaction().replace(R.id.flContent,nhanVienFragment).commit();
                        break;
                    case R.id.sub_DoanhThu:
                        setTitle("Doanh Thu");
                        DoanhThuFragment doanhThuFragment=new DoanhThuFragment();
                        manager.beginTransaction().replace(R.id.flContent,doanhThuFragment).commit();
                        break;


                    case R.id.sub_Logout:
                        setTitle("Dang Xuat");
                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        finish();
                        break;
                }
                drawer.closeDrawer(GravityCompat.START);
                return false;
            }

        });
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if (id==android.R.id.home)
            drawer.openDrawer(GravityCompat.START);
        return super.onOptionsItemSelected(item);
    }

}