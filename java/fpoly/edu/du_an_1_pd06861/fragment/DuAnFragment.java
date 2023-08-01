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
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

import fpoly.edu.du_an_1_pd06861.R;
import fpoly.edu.du_an_1_pd06861.adapter.DuAnAdapter;
import fpoly.edu.du_an_1_pd06861.adapter.HopdongAdapter;
import fpoly.edu.du_an_1_pd06861.dao.DuAnDAO;
import fpoly.edu.du_an_1_pd06861.modol.DuAn;
import fpoly.edu.du_an_1_pd06861.modol.ItemClick;

public class DuAnFragment extends Fragment {
    RecyclerView recyclerView;
    DuAnDAO dao;
    EditText edThemduan;
    int maduan;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_du_an, container, false);
        recyclerView=view.findViewById(R.id.rcvDuan);
        edThemduan=view.findViewById(R.id.edthemda);
        Button btnThem=view.findViewById(R.id.btnThem);
        Button btnsua=view.findViewById(R.id.btnSua);

         dao=new DuAnDAO(getContext());
         loadData();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenduan=edThemduan.getText().toString();
                if (dao.ThemDuAn(tenduan)){
                    loadData();
                }else {
                    Toast.makeText(getContext(), "thêm dự án thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnsua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenduan=edThemduan.getText().toString();
                DuAn duAn=new DuAn(maduan,tenduan);
                if (dao.Thaydoiduan(duAn)){
                    loadData();
                    edThemduan.setText("");
                }else {
                    Toast.makeText(getContext(),"sửa không thành công",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
    private void loadData(){
        ArrayList<DuAn> list=dao.getDuAn();
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        DuAnAdapter adapter=new DuAnAdapter(list, getContext(), new ItemClick() {
            @Override
            public void onClick(DuAn duAn) {
                edThemduan.setText(duAn.getTenduan());
                maduan=duAn.getIdda();

            }
        });
        recyclerView.setAdapter(adapter);
    }

}