package fpoly.edu.du_an_1_pd06861.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

import fpoly.edu.du_an_1_pd06861.R;
import fpoly.edu.du_an_1_pd06861.dao.DoanhThuDAO;

public class DoanhThuFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_doanhthu,container,false);
        EditText edtungay=view.findViewById(R.id.edTungay);
        EditText eddenngay=view.findViewById(R.id.edDenngay);
        Button btnThongke=view.findViewById(R.id.btnThongke);
        TextView txtketqua=view.findViewById(R.id.txtKetqua);
        Calendar calendar=Calendar.getInstance();
        edtungay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                edtungay.setText(i2+"/"+(i1+1)+"/"+i);
                            }
                        },
                        calendar.get(Calendar.DAY_OF_MONTH),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.YEAR)
                );
                datePickerDialog.show();
            }
        });
        eddenngay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog=new DatePickerDialog(
                        getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                eddenngay.setText(i2+"/"+(i1+1)+"/"+i);
                            }
                        },
                        calendar.get(Calendar.DAY_OF_MONTH),
                        calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.YEAR)
                );
                datePickerDialog.show();
            }
        });
        btnThongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DoanhThuDAO doanhThuDAO=new DoanhThuDAO(getContext());
                String tungay=edtungay.getText().toString();
                String denngay=eddenngay.getText().toString();
                int doanhthu=doanhThuDAO.getDoanhThu(tungay,denngay);
                txtketqua.setText(doanhthu + "VND");
            }
        });
        return view;
    }
}
