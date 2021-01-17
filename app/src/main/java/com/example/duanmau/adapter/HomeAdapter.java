package com.example.duanmau.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;
import com.example.duanmau.model.TheLoaiSach;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private Context context;
    ArrayList<TheLoaiSach> loaiSachList;
    Dialog dialog;

    public HomeAdapter(Context context, ArrayList<TheLoaiSach> loaiSachList) {
        this.context = context;
        this.loaiSachList = loaiSachList;
    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sach, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        viewHolder.colorBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {
        final TheLoaiSach theLoaiSach = loaiSachList.get(position);
        if (loaiSachList == null) {
            return;
        }
        holder.colorBack.setBackgroundColor(theLoaiSach.getMauNen());
        holder.tvTenLoaiSach.setText(theLoaiSach.getTenTheLoai());
    }

    @Override
    public int getItemCount() {
        if (loaiSachList!=null){
return loaiSachList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
            LinearLayout colorBack;
        TextView tvTenLoaiSach;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            colorBack = itemView.findViewById(R.id.background_item_theloai);
            tvTenLoaiSach = itemView.findViewById(R.id.tvTenLoaiSach);
        }
    }
}
