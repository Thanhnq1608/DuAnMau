package com.example.duanmau.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;
import com.example.duanmau.model.Sach;

import java.util.ArrayList;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolder> {
    private Context context;
    ArrayList<Sach> sachList;
    Dialog dialog;

    public SachAdapter(Context context, ArrayList<Sach> loaiSachList) {
        this.context = context;
        this.sachList = loaiSachList;
    }

    @NonNull
    @Override
    public SachAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sach, parent, false);
        SachAdapter.ViewHolder viewHolder = new SachAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SachAdapter.ViewHolder holder, int position) {
        final Sach sach = sachList.get(position);
        if (sachList == null) {
            return;
        }
//        holder.colorBack.setBackgroundColor(sach.getMauNen());
        holder.tvTenSach.setText(sach.getTenSach());
        holder.imgSach.setImageResource(R.drawable.icon_book);
        holder.tvTenLoaiSach.setText(sach.getMaTheLoai());
    }

    @Override
    public int getItemCount() {
        if (sachList!=null){
            return sachList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgSach;
        TextView tvTenSach,tvTenLoaiSach;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSach = itemView.findViewById(R.id.img_sach);
            tvTenSach = itemView.findViewById(R.id.tv_ten_sach);
            tvTenLoaiSach=itemView.findViewById(R.id.tv_type_book);
        }
    }
}
