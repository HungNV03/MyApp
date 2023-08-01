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
import fpoly.edu.du_an_1_pd06861.dao.KhachHangDAO;
import fpoly.edu.du_an_1_pd06861.modol.KhachHang;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class KhachHangAdapter extends RecyclerView.Adapter<KhachHangAdapter.ViewHolder>{
    private ArrayList<KhachHang>list;
    private Context context;
    private KhachHangDAO khachHangDAO;

    public KhachHangAdapter(ArrayList<KhachHang> list, Context context,KhachHangDAO khachHangDAO) {
        this.list = list;
        this.context = context;
        this.khachHangDAO=khachHangDAO;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.item_recyler_khachhang,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvidkh.setText("ID khách hàng: "+list.get(position).getIdkh());
        holder.tvtenkh.setText("Tên khách hàng: "+list.get(position).getTenkh());
        holder.tvcccd.setText("CCCD: "+list.get(position).getCccd());
        holder.tvphone.setText("Phone: "+list.get(position).getPhone());

        holder.ivEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                capnhat(list.get(holder.getAdapterPosition()));
            }
        });
        holder.ivXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int check=khachHangDAO.xoathogtinkh(list.get(holder.getAdapterPosition()).getIdkh());
                switch (check){
                    case 1:
                        Toast.makeText(context, "xóa khách hàng thành công", Toast.LENGTH_SHORT).show();
                        loadData();
                        break;
                    case 0:
                        Toast.makeText(context, "xóa không thành công", Toast.LENGTH_SHORT).show();
                        break;
                    case -1:
                        Toast.makeText(context, "khách hàng có trong dự án, không thể xóa", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvidkh,tvtenkh,tvcccd,tvphone;
        ImageView ivEdit,ivXoa;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvidkh=itemView.findViewById(R.id.tnIdkh);
            tvtenkh=itemView.findViewById(R.id.tnTenkh);
            tvcccd=itemView.findViewById(R.id.tnCccd);
            tvphone=itemView.findViewById(R.id.tnPhone);
            ivEdit=itemView.findViewById(R.id.ivEdit);
            ivXoa=itemView.findViewById(R.id.ivXoa);
        }
    }
    private void capnhat(KhachHang khachHang){
        AlertDialog.Builder builder=new AlertDialog.Builder(context);
        LayoutInflater layoutInflater=((Activity)context).getLayoutInflater();
        View view=layoutInflater.inflate(R.layout.dialog_chinhsua_khachhang,null);
        builder.setView(view);

        TextView txtIdkh=view.findViewById(R.id.txtIdkh);
        EditText edTenkh=view.findViewById(R.id.edTenkh);
        EditText edCccd=view.findViewById(R.id.edCccd);
        EditText edPhone=view.findViewById(R.id.edphone);

        txtIdkh.setText("ID KH: "+khachHang.getIdkh());
        edTenkh.setText(khachHang.getTenkh());
        edCccd.setText(khachHang.getCccd());
        edPhone.setText(khachHang.getPhone());

        builder.setNegativeButton("cập nhật", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String tenkh=edTenkh.getText().toString();
                String cccd=edCccd.getText().toString();
                String phone=edPhone.getText().toString();

                int idkh=khachHang.getIdkh();
                boolean check=khachHangDAO.capnhatthongtinkh(idkh,tenkh,cccd,phone);
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
        list=khachHangDAO.getKhachHang();
        notifyDataSetChanged();
    }
}
