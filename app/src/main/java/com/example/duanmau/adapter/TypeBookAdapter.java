package com.example.duanmau.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.DAO.NguoiDungDAO;
import com.example.duanmau.DAO.TheLoaiDAO;
import com.example.duanmau.R;
import com.example.duanmau.model.TheLoaiSach;

import java.util.ArrayList;

public class TypeBookAdapter extends RecyclerView.Adapter<TypeBookAdapter.ViewHolder> {
    private Context context;
    ArrayList<TheLoaiSach> loaiSachList;
    Dialog dialog;

    public TypeBookAdapter(Context context, ArrayList<TheLoaiSach> loaiSachList) {
        this.context = context;
        this.loaiSachList = loaiSachList;
    }

    @NonNull
    @Override
    public TypeBookAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_the_loai_sach, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TypeBookAdapter.ViewHolder holder, final int position) {
        final TheLoaiDAO theLoaiDAO = new TheLoaiDAO(context);
        TheLoaiSach theLoaiSach = loaiSachList.get(position);

        if (loaiSachList == null) {
            return;
        }
        holder.colorBack.setBackgroundResource(theLoaiDAO.getAllTheLoai().get(position).getMauNen());
        holder.tvTenLoaiSach.setText(theLoaiSach.getTenTheLoai());

        dialog = new Dialog(context);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View view = inflater.inflate(R.layout.item_info_type, null);
        dialog.setTitle("Thông tin Thể Loại");
        final TextView tv_id, tv_Name, tv_moTa, tv_location;
        tv_id = view.findViewById(R.id.tv_id_infoType);
        tv_Name = view.findViewById(R.id.tv_name_infoType);
        tv_moTa = view.findViewById(R.id.tv_moTa_infoType);
        tv_location = view.findViewById(R.id.tv_location_infoType);
        final ImageView btn_exit = view.findViewById(R.id.btn_exit);
        holder.colorBack.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                builder.setTitle("bạn chắc chắn muốn xóa!");
                builder.setPositiveButton(String.valueOf(R.string.dialog_exit_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        theLoaiDAO.deleteTheLoaiByID(theLoaiDAO.getAllTheLoai().get(position).getMaTheLoai());
                        Toast.makeText(context, "Xóa Thành công!", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton(String.valueOf(R.string.dialog_exit_no), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.show();
                return false;
            }
        });
        holder.tvDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_id.setText(theLoaiDAO.getAllTheLoai().get(position).getMaTheLoai());
                tv_Name.setText(theLoaiDAO.getAllTheLoai().get(position).getTenTheLoai());
                tv_moTa.setText(theLoaiDAO.getAllTheLoai().get(position).getMoTa());
                tv_location.setText(String.valueOf(theLoaiDAO.getAllTheLoai().get(position).getViTri()));
                dialog.setContentView(view);
                dialog.show();
                btn_exit.setOnClickListener(new View.OnClickListener() {
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
        if (loaiSachList != null) {
            return loaiSachList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout colorBack;
        TextView tvTenLoaiSach, tvDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            colorBack = itemView.findViewById(R.id.background_item_theloai);
            tvTenLoaiSach = itemView.findViewById(R.id.tvTenLoaiSach);
            tvDetail = itemView.findViewById(R.id.tv_detail_type);
        }
    }
}
