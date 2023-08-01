package fpoly.edu.du_an_1_pd06861.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import fpoly.edu.du_an_1_pd06861.R;
import fpoly.edu.du_an_1_pd06861.dao.DuAnDAO;
import fpoly.edu.du_an_1_pd06861.modol.DuAn;
import fpoly.edu.du_an_1_pd06861.modol.ItemClick;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DuAnAdapter extends RecyclerView.Adapter<DuAnAdapter.ViewHolder> {
    private ArrayList<DuAn> list;
    private Context context;
    private ItemClick itemClick;

    public DuAnAdapter(ArrayList<DuAn> list, Context context,ItemClick itemClick) {
        this.list = list;
        this.context = context;
        this.itemClick=itemClick;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=((Activity)context).getLayoutInflater();
        View view=inflater.inflate(R.layout.item_recyler_duan,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvidda.setText("ID Dự Án: "+list.get(position).getIdda());
        holder.tvtenduan.setText("Tên Dự Án: "+list.get(position).getTenduan());
        holder.ivxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DuAnDAO duAnDAO=new DuAnDAO(context);
                int check=duAnDAO.xoaduan(list.get(holder.getAdapterPosition()).getIdda());
                switch (check){
                    case 1:
                        list.clear();
                        list=duAnDAO.getDuAn();
                        notifyDataSetChanged();
                        break;
                    case 0:
                        Toast.makeText(context, "xóa thành công", Toast.LENGTH_SHORT).show();
                    default:
                        break;
                }
            }
        });
        holder.ivedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DuAn duAn=list.get(holder.getAdapterPosition());
                itemClick.onClick(duAn);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvidda,tvtenduan;
        ImageView ivxoa,ivedit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvidda=itemView.findViewById(R.id.tvIdda);
            tvtenduan=itemView.findViewById(R.id.tvTenduan);
            ivxoa=itemView.findViewById(R.id.ivXoa);
            ivedit=itemView.findViewById(R.id.ivEdit);
        }
    }

}
