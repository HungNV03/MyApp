package fpoly.edu.du_an_1_pd06861.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import fpoly.edu.du_an_1_pd06861.R;
import fpoly.edu.du_an_1_pd06861.dao.NhanVienDAO;
import fpoly.edu.du_an_1_pd06861.helper.DBhelper;
import fpoly.edu.du_an_1_pd06861.modol.KhachHang;
import fpoly.edu.du_an_1_pd06861.modol.NhanVien;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.ViewHolder> {
    private ArrayList<NhanVien>list;
    private Context context;
    private NhanVienDAO nhanVienDAO;

    public NhanVienAdapter(ArrayList<NhanVien> list, Context context,NhanVienDAO nhanVienDAO) {
        this.list = list;
        this.context = context;
        this.nhanVienDAO=nhanVienDAO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.item_recyler_nhanvien,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvidnv.setText("ID nhân viên: "+list.get(position).getIdnv());
        holder.tvtennv.setText("Tên nhân viên: "+list.get(position).getTennv());
        holder.tvchucvu.setText("Chức vụ: "+list.get(position).getChuvu());

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                capnhat(list.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvidnv,tvtennv,tvchucvu;
        ImageView edit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvidnv=itemView.findViewById(R.id.tvIdnv);
            tvtennv=itemView.findViewById(R.id.tvTennv);
            tvchucvu=itemView.findViewById(R.id.tvChucVu);
            edit=itemView.findViewById(R.id.ivEdit);
        }
    }
    private void capnhat(NhanVien nhanVien){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        LayoutInflater layoutInflater=((Activity)context).getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.dialog_chinhsua_nhanvien,null);
        builder.setView(view);

        TextView txtIdnv=view.findViewById(R.id.txtIdnv);
        EditText edTennv=view.findViewById(R.id.edTennv);
        EditText edChucvu=view.findViewById(R.id.edChucvu);


        txtIdnv.setText("ID NV: "+nhanVien.getIdnv());
        edTennv.setText(nhanVien.getTennv());
        edChucvu.setText(nhanVien.getChuvu());


        builder.setNegativeButton("cập nhật", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tennv=edTennv.getText().toString();
                String chucvu=edChucvu.getText().toString();


                int idnv=nhanVien.getIdnv();
                boolean check=nhanVienDAO.CapnhatthongtinNV(idnv,tennv,chucvu);
                if (check){
                    Toast.makeText(context, "cập nhật thông tin thành công", Toast.LENGTH_SHORT).show();

                    loadData();
                }else {
                    Toast.makeText(context, "cập nhật thông tin không thành công", Toast.LENGTH_SHORT).show();
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
        list.clear();
        list=nhanVienDAO.getNhanvien();
        notifyDataSetChanged();
    }
}
