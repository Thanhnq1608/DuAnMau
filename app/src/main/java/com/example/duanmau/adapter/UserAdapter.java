package com.example.duanmau.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;
import com.example.duanmau.model.NguoiDung;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context context;
    ArrayList<NguoiDung> userList;

    public UserAdapter(Context context, ArrayList<NguoiDung> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        UserAdapter.ViewHolder viewHolder = new UserAdapter.ViewHolder(view);
        viewHolder.tvTenNguoiDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       final NguoiDung nguoiDung = new NguoiDung();
        if (nguoiDung!=null){
            return;
        }
        holder.tvTenNguoiDung.setText(nguoiDung.getHoTen());
    }

    @Override
    public int getItemCount() {
        if (userList!=null){
            return userList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgUser;
        TextView tvTenNguoiDung;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUser=itemView.findViewById(R.id.img_ava_user);
            tvTenNguoiDung=itemView.findViewById(R.id.tv_ten_sach);
        }
    }
}
