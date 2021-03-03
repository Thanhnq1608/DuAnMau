package com.example.duanmau.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.DAO.NguoiDungDAO;
import com.example.duanmau.R;
import com.example.duanmau.model.NguoiDung;
import com.google.android.gms.maps.model.TileOverlay;

import java.io.FileInputStream;
import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private Context context;
    ArrayList<NguoiDung> userList;
    Dialog dialog;

    public UserAdapter(Context context, ArrayList<NguoiDung> userList) {
        this.context = context;
        this.userList = userList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, final int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        UserAdapter.ViewHolder viewHolder = new UserAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final NguoiDungDAO nguoiDungDAO = new NguoiDungDAO(context);
        NguoiDung nguoiDung = userList.get(position);

        if (nguoiDung == null) {
            return;
        }
        holder.tvTenNguoiDung.setText(nguoiDung.getHoTen());
        holder.tvUser.setText(nguoiDung.getUserName());
        holder.imgUser.setImageResource(R.drawable.icon_user);
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nguoiDungDAO.deleteNguoiDungByID(userList.get(position).getUserName());
                userList.remove(position);
                Toast.makeText(context, "Xóa Thành Công!", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });

//Hiện Dialog xem thông tin người Dùng
        dialog = new Dialog(context);
        LayoutInflater  inflater= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view =inflater.inflate(R.layout.item_info_user,null);
        dialog.setCancelable(false);
        dialog.setTitle("Thông Tin Người Dùng");
        final TextView tv_user, tv_name, tv_phone, tv_pass;
        final ImageView img_ava,img_x;
        tv_user = view.findViewById(R.id.tv_user_info_user);
        tv_pass = view.findViewById(R.id.tv_pass_info_user);
        tv_name = view.findViewById(R.id.tv_name_info_user);
        tv_phone = view.findViewById(R.id.tv_phone_info_user);
        img_ava = view.findViewById(R.id.imgAva_info_user);
        img_x=view.findViewById(R.id.img_btn_x_infoUser);
        holder.imgUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                img_ava.setImageResource(R.drawable.icon_user);
                tv_user.setText(nguoiDungDAO.getAllNguoiDung().get(position).getUserName());
                tv_name.setText(nguoiDungDAO.getAllNguoiDung().get(position).getHoTen());
                tv_phone.setText(nguoiDungDAO.getAllNguoiDung().get(position).getPhone());
                tv_pass.setText(nguoiDungDAO.getAllNguoiDung().get(position).getPassword());
                dialog.setContentView(view);
                dialog.show();
                img_x.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
            }
        });


    }

    @Override
    public int getItemCount() {
        if (userList != null) {
            return userList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgUser;
        TextView tvTenNguoiDung, tvUser;
        Button btn_delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgUser = itemView.findViewById(R.id.img_ava_user);
            tvTenNguoiDung = itemView.findViewById(R.id.tv_ten_itemUser);
            tvUser = itemView.findViewById(R.id.tv_user_itemUser);
            btn_delete = itemView.findViewById(R.id.btn_delete_user);
        }
    }
}
