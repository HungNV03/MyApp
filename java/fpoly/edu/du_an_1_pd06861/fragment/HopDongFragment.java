package fpoly.edu.du_an_1_pd06861.fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import fpoly.edu.du_an_1_pd06861.R;
import fpoly.edu.du_an_1_pd06861.adapter.HopdongAdapter;
import fpoly.edu.du_an_1_pd06861.dao.DuAnDAO;
import fpoly.edu.du_an_1_pd06861.dao.HopDongDAO;
import fpoly.edu.du_an_1_pd06861.dao.KhachHangDAO;
import fpoly.edu.du_an_1_pd06861.dao.NhanVienDAO;
import fpoly.edu.du_an_1_pd06861.modol.DuAn;
import fpoly.edu.du_an_1_pd06861.modol.HopDong;
import fpoly.edu.du_an_1_pd06861.modol.KhachHang;
import fpoly.edu.du_an_1_pd06861.modol.NhanVien;


public class HopDongFragment extends Fragment {
    HopDongDAO hopDongDAO;
    RecyclerView rcvhopdong;
    ArrayList<HopDong> list;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_hop_dong, container, false);
        rcvhopdong=view.findViewById(R.id.rcvHopDong);
        FloatingActionButton floa=view.findViewById(R.id.fab);

       Loaddata();


        floa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Showdialog();
            }
        });
        return view;
    }
    private void Loaddata(){
        hopDongDAO=new HopDongDAO(getContext());
        list=hopDongDAO.getHopDong();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        rcvhopdong.setLayoutManager(linearLayoutManager);
        HopdongAdapter adapter=new HopdongAdapter(list,getContext());
        rcvhopdong.setAdapter(adapter);
    }
    private void Showdialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        LayoutInflater inflater= getLayoutInflater();
        View view=inflater.inflate(R.layout.dialog_them_hopdong,null);
//        TextView txtID=view.findViewById(R.id.txtIDhd);
        Spinner spnNhanvien=view.findViewById(R.id.spnNhanvien);
        Spinner spnKhachhang=view.findViewById(R.id.spnKhachhang);
        Spinner spnDuan=view.findViewById(R.id.spnDuan);
        EditText edTien=view.findViewById(R.id.edTien);
        getDataNhanVien(spnNhanvien);
        getDataKhachHang(spnKhachhang);
        getDataDuAn(spnDuan);
        builder.setView(view);

        builder.setPositiveButton("thêm", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                HashMap<String,Object> hsNV= (HashMap<String, Object>) spnNhanvien.getSelectedItem();
                int idnv=(int) hsNV.get("idnv");
                HashMap<String,Object> hsKH=(HashMap<String,Object>) spnKhachhang.getSelectedItem();
                int idkh=(int) hsKH.get("idkh");
                HashMap<String,Object> hsDA=(HashMap<String,Object>) spnDuan.getSelectedItem();
                int idda=(int) hsDA.get("idda");

                int sotien=Integer.parseInt(edTien.getText().toString());
                ThemHopDong(idnv,idkh,idda,sotien);

            }
        });
        builder.setNegativeButton("hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });


        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
    private void getDataNhanVien(Spinner spnNhanvien){
        NhanVienDAO nhanVienDAO=new NhanVienDAO(getContext());
        ArrayList<NhanVien> list=nhanVienDAO.getNhanvien();
        ArrayList<HashMap<String,Object>> listHM=new ArrayList<>();
        for (NhanVien nv: list){
            HashMap<String,Object> hashMap=new HashMap<>();
            hashMap.put("idnv",nv.getIdnv());
            hashMap.put("tennv",nv.getTennv());
            hashMap.put("chucvu",nv.getChuvu());
            listHM.add(hashMap);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(
                getContext(),listHM, android.R.layout.simple_list_item_1,
                new String[]{"tennv"},
        new int[]{android.R.id.text1}
        );
        spnNhanvien.setAdapter(simpleAdapter);
    }
    private void getDataKhachHang(Spinner spnKhachHang){
        KhachHangDAO khachHangDAO=new KhachHangDAO(getContext());
        ArrayList<KhachHang> list=khachHangDAO.getKhachHang();
        ArrayList<HashMap<String,Object>> listHM=new ArrayList<>();
        for (KhachHang kh:list){
            HashMap<String,Object> hashMap=new HashMap<>();
            hashMap.put("idkh",kh.getIdkh());
            hashMap.put("tenkh",kh.getTenkh());
            hashMap.put("cccd",kh.getCccd());
            hashMap.put("phone",kh.getPhone());
            listHM.add(hashMap);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(
                getContext(),listHM, android.R.layout.simple_list_item_1,
                new String[]{"tenkh"},
                new int[]{android.R.id.text1}
        );
        spnKhachHang.setAdapter(simpleAdapter);
    }
    private void getDataDuAn(Spinner spnDuAn){
        DuAnDAO duAnDAO=new DuAnDAO(getContext());
        ArrayList<DuAn> list=duAnDAO.getDuAn();
        ArrayList<HashMap<String,Object>> listHM=new ArrayList<>();
        for (DuAn da:list){
            HashMap<String,Object> hashMap=new HashMap<>();
            hashMap.put("idda",da.getIdda());
            hashMap.put("tenduan",da.getTenduan());


            listHM.add(hashMap);
        }
        SimpleAdapter simpleAdapter=new SimpleAdapter(
                getContext(),listHM, android.R.layout.simple_list_item_1,
                new String[]{"tenduan"},
                new int[]{android.R.id.text1}
        );
        spnDuAn.setAdapter(simpleAdapter);
    }
    private void ThemHopDong(int idnv, int idkh, int idda, int sotien){
        Date currentTime= Calendar.getInstance().getTime();
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
        String ngaymua=simpleDateFormat.format(currentTime);

        HopDong hopDong=new HopDong(idnv,idkh,idda,sotien,ngaymua,0);

        boolean kiemtra=hopDongDAO.ThemHopdong(hopDong);
        if (kiemtra){
            Toast.makeText(getContext(),"them thanh cong",Toast.LENGTH_SHORT).show();
            Loaddata();
        }else {
            Toast.makeText(getContext(),"them that bai",Toast.LENGTH_SHORT).show();
        }
    }
}