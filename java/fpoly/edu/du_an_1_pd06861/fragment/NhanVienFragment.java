package fpoly.edu.du_an_1_pd06861.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import fpoly.edu.du_an_1_pd06861.R;
import fpoly.edu.du_an_1_pd06861.adapter.NhanVienAdapter;
import fpoly.edu.du_an_1_pd06861.dao.NhanVienDAO;
import fpoly.edu.du_an_1_pd06861.modol.NhanVien;


public class NhanVienFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_nhan_vien, container, false);
        RecyclerView recyclerView=view.findViewById(R.id.rcvNhanvien);


        NhanVienDAO nhanVienDAO=new NhanVienDAO(getContext());
        ArrayList<NhanVien> list=nhanVienDAO.getNhanvien();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        NhanVienAdapter nhanVienAdapter=new NhanVienAdapter(list,requireContext(),nhanVienDAO);
        recyclerView.setAdapter(nhanVienAdapter);
        return view;
    }
}