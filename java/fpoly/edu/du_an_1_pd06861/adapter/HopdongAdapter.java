package fpoly.edu.du_an_1_pd06861.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import fpoly.edu.du_an_1_pd06861.R;
import fpoly.edu.du_an_1_pd06861.dao.HopDongDAO;
import fpoly.edu.du_an_1_pd06861.modol.HopDong;

public class HopdongAdapter extends RecyclerView.Adapter<HopdongAdapter.ViewHolder> {
    private ArrayList<HopDong> list;
    private Context context;


    public HopdongAdapter(ArrayList<HopDong> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.item_recyler_hopdong,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvidhd.setText(   "Mã hợp đồng     : "+list.get(position).getMahd());
        holder.tvidnv.setText(   "ID nhân viên       : "+list.get(position).getIdnv());
        holder.tvtennv.setText(  "Tên nhân viên    : "+list.get(position).getTennv());
        holder.tvidkh.setText(   "ID khách hàng    : "+list.get(position).getIdkh());
        holder.tvtenkh.setText(  "Tên khách hàng : "+list.get(position).getTenkh());
        holder.tvidda.setText(   "ID dự án              : "+list.get(position).getIdda());
        holder.tvtenduan.setText("Tên dự án           : "+list.get(position).getTenduan());
        holder.tvsotien.setText( "Giá                       : "+list.get(position).getSotien());
        holder.tvngaymua.setText("Ngày                    : "+list.get(position).getNgaymua());
        String trangthai="";
        if (list.get(position).getTrangthai()==1){
            trangthai="đã triễn khai";
            holder.btnchuaban.setVisibility(View.GONE);

        }else {
            trangthai="đang triễn khai";
            holder.btnchuaban.setVisibility(View.VISIBLE);
        }
        holder.tvtrangthai.setText("Trạng thái           : "+trangthai);

        holder.btnchuaban.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HopDongDAO hopDongDAO=new HopDongDAO(context);
                boolean kiemtra=hopDongDAO.Thaydoitrangthai(list.get(holder.getAdapterPosition()).getMahd());
                if (kiemtra){
                    list.clear();
                    list=hopDongDAO.getHopDong();
                    notifyDataSetChanged();
                }else {
                    Toast.makeText(context,"không được",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvidhd,tvidnv,tvtennv,tvidkh,tvtenkh,tvidda,tvtenduan,tvsotien,tvngaymua,tvtrangthai;
        Button btnchuaban;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvidhd=itemView.findViewById(R.id.tvIdhd);
            tvidnv=itemView.findViewById(R.id.tvIdnv);
            tvtennv=itemView.findViewById(R.id.tvTennv);
            tvidkh=itemView.findViewById(R.id.tvIdkh);
            tvtenkh=itemView.findViewById(R.id.tvTenkh);
            tvidda=itemView.findViewById(R.id.tvIdda);
            tvtenduan=itemView.findViewById(R.id.tvTenduan);
            tvsotien=itemView.findViewById(R.id.tvSotien);
            tvngaymua=itemView.findViewById(R.id.tvNgaymua);
            tvtrangthai=itemView.findViewById(R.id.tvTrangthai);
            btnchuaban=itemView.findViewById(R.id.btnChuaban);
        }
    }
}
