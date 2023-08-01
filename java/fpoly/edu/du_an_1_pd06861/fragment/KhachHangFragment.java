package fpoly.edu.du_an_1_pd06861.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

import fpoly.edu.du_an_1_pd06861.R;
import fpoly.edu.du_an_1_pd06861.adapter.KhachHangAdapter;
import fpoly.edu.du_an_1_pd06861.dao.KhachHangDAO;
import fpoly.edu.du_an_1_pd06861.modol.KhachHang;


public class KhachHangFragment extends Fragment {
    KhachHangDAO khachHangDAO;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_khach_hang, container, false);
        recyclerView=view.findViewById(R.id.rcvKhachHang);
        FloatingActionButton floatingActionButton=view.findViewById(R.id.fab);

         khachHangDAO=new KhachHangDAO(getContext());
         loadData();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showdialog();
            }
        });


        return view;
    }
    private void showdialog(){
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        LayoutInflater layoutInflater=getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.dialog_themkhachhang,null);
        builder.setView(view);

        TextInputEditText edtenkh=view.findViewById(R.id.edTenkh);
        TextInputEditText edcccd=view.findViewById(R.id.edCccd);
        TextInputEditText edphone=view.findViewById(R.id.edPhone);


       builder.setNegativeButton("thêm", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {
               String tenkh=edtenkh.getText().toString();
               String cccd=edcccd.getText().toString();
               String phone=edphone.getText().toString();

               boolean check=khachHangDAO.ThemKhachHang(tenkh,cccd,phone);
               if (check){
                   Toast.makeText(getContext(), "thêm thành công", Toast.LENGTH_SHORT).show();
                   loadData();
               }else {
                   Toast.makeText(getContext(), "thêm thất bại", Toast.LENGTH_SHORT).show();
               }
           }
       });
       builder.setPositiveButton("hủy", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {

           }
       });

        AlertDialog alertDialog=builder.create();
        alertDialog.show();
    }
    private void loadData(){
        ArrayList<KhachHang>list=khachHangDAO.getKhachHang();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        KhachHangAdapter khachHangAdapter=new KhachHangAdapter(list,getContext(),khachHangDAO);
        recyclerView.setAdapter(khachHangAdapter);
    }
}