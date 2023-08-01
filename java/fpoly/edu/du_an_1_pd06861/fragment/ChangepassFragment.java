package fpoly.edu.du_an_1_pd06861.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import fpoly.edu.du_an_1_pd06861.R;
import fpoly.edu.du_an_1_pd06861.dao.NguoidungDao;

public class ChangepassFragment extends Fragment {


        TextInputEditText edPassOld, edPass, edRePass;
        Button btnSave,btnCancel;
        NguoidungDao dao;


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

            View v=inflater.inflate(R.layout.change_pass, container, false);
            dao=new NguoidungDao(getActivity());
            edPassOld=v.findViewById(R.id.edPassOld);
            edPass=v.findViewById(R.id.edPassChange);
            edRePass=v.findViewById(R.id.edRePassChange);
            btnSave=v.findViewById(R.id.btnSaveUserChange);
            btnCancel=v.findViewById(R.id.btnCancelUserChange);
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    edPassOld.setText("");
                    edPass.setText("");
                    edRePass.setText("");

                }
            });

//            btnSave.setOnClickListener(new View.OnClickListener() {
//                SharedPreferences pref=getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
//                @Override
//                public void onClick(View view) {
//                     String user=pref.getString("USERNAME","");
//                    if (validate()>0){
//                        NguoidungDao nguoidungDao=dao.getClass(user);
//                        thuThu.setMatKhau(edPass.getText().toString());
//                        if (dao.updatePass(thuThu)>0){
//                            Toast.makeText(getActivity(),"thay doi mat khau thanh cong",Toast.LENGTH_SHORT).show();
//                            edPassOld.setText("");
//                            edPass.setText("");
//                            edRePass.setText("");
//                        }else {
//                            Toast.makeText(getActivity(),"thay doi mat khau that bai",Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//            });
            return v;
        }
        public int validate(){
            int check= 1;
            if (edPassOld.getText().length()==0 || edPass.getText().length()==0 || edRePass.getText().length()==0){
                Toast.makeText(getContext(),"ban phai nhap du thong tin",Toast.LENGTH_SHORT).show();
                check=-1;
            }else {
                SharedPreferences pref=getActivity().getSharedPreferences("USER_FILE", Context.MODE_PRIVATE);
                String passOld=pref.getString("PASSWORD","");
                String pass=edPass.getText().toString();
                String repass=edRePass.getText().toString();
                if (!passOld.equals(edPassOld.getText().toString())){
                    Toast.makeText(getContext(),"mat khau cu khong dung",Toast.LENGTH_SHORT).show();
                    check=-1;
                }
            }
            return check;
        }

}