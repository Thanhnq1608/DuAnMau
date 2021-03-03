package com.example.duanmau.adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.DAO.SachDAO;
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
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SachAdapter.ViewHolder holder, final int position) {
        final SachDAO sachDAO =new SachDAO(context);
        Sach sach = sachList.get(position);
        if (sachList == null) {
            return;
        }
//        holder.colorBack.setBackgroundColor(sach.getMauNen());
        holder.tvTenSach.setText(sach.getTenSach());
        holder.imgSach.setImageResource(R.drawable.icon_book);
        holder.tvTenLoaiSach.setText(sach.getMaTheLoai());

        dialog=new Dialog(context);
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View viewInfo= inflater.inflate(R.layout.item_info_add_sach,null);
        final TextView idTypeBook, idSach, nameSach, nXBSach, tacGia, soLuong, price;
        idSach = viewInfo.findViewById(R.id.tv_id_infoBook);
        nameSach = viewInfo.findViewById(R.id.tv_name_infoBook);
        nXBSach = viewInfo.findViewById(R.id.tv_nxb_infoBook);
        tacGia = viewInfo.findViewById(R.id.tv_tacGia_infoBook);
        soLuong = viewInfo.findViewById(R.id.tv_soLuong_infoBook);
        price = viewInfo.findViewById(R.id.tv_price_infoBook);
        idTypeBook=viewInfo.findViewById(R.id.tv_id_type_infoBook);
        final ImageView img_x =viewInfo.findViewById(R.id.img_x_info_book);


        holder.imgSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idSach.setText(sachDAO.getAllSach().get(position).getMaSach());
                idTypeBook.setText(sachDAO.getAllSach().get(position).getMaTheLoai());
                nameSach.setText(sachDAO.getAllSach().get(position).getTenSach());
                nXBSach.setText(sachDAO.getAllSach().get(position).getNXB());
                tacGia.setText(sachDAO.getAllSach().get(position).getTacGia());
                soLuong.setText(sachDAO.getAllSach().get(position).getSoLuong());
                price.setText(String.valueOf(sachDAO.getAllSach().get(position).getGiaBia()));
                dialog.setContentView(viewInfo);
                dialog.show();
                img_x.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

            }
        });
        holder.imgSach.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final AlertDialog.Builder builder =new AlertDialog.Builder(context,R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                builder.setTitle("bạn chắc chắn muốn xóa!");
                builder.setPositiveButton(String.valueOf(R.string.dialog_exit_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sachDAO.deleteSachByID(sachDAO.getAllSach().get(position).getMaSach());
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
