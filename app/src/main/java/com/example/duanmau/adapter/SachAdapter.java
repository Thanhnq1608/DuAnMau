package com.example.duanmau.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.DAO.SachDAO;
import com.example.duanmau.R;
import com.example.duanmau.SachActivity;
import com.example.duanmau.model.Book;

import java.util.ArrayList;
import java.util.List;

public class SachAdapter extends RecyclerView.Adapter<SachAdapter.ViewHolder>{
    private Context context;
    ArrayList<Book> bookList;
    Dialog dialog;

    public SachAdapter(Context context, ArrayList<Book> bookList) {
        this.context = context;
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public SachAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sach, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull SachAdapter.ViewHolder holder, final int position) {
        final SachDAO sachDAO = new SachDAO(context);
        Book book = bookList.get(position);
        if (bookList == null) {
            return;
        }
//        holder.colorBack.setBackgroundColor(sach.getMauNen());
        holder.tvTenSach.setText(book.getTenSach());
        holder.imgSach.setImageResource(R.drawable.icon_book);
        holder.tvLoaiSach.setText(book.getMaTheLoai());
        holder.tvMaSach.setText(String.valueOf(book.getMaSach()));


        holder.imgSach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new Dialog(context);
                dialog.setContentView(R.layout.item_info_sach);

                final ImageView img_x = dialog.findViewById(R.id.img_x_info_book);
                final TextView idTypeBook, idSach, nameSach, nXBSach, tacGia, soLuong, price;

                idSach = dialog.findViewById(R.id.tv_id_infoBook);
                nameSach = dialog.findViewById(R.id.tv_name_infoBook);
                nXBSach = dialog.findViewById(R.id.tv_nxb_infoBook);
                tacGia = dialog.findViewById(R.id.tv_tacGia_infoBook);
                soLuong = dialog.findViewById(R.id.tv_soLuong_infoBook);
                price = dialog.findViewById(R.id.tv_price_infoBook);
                idTypeBook = dialog.findViewById(R.id.tv_id_type_infoBook);

                idSach.setText("Mã sách: "+book.getMaSach());
                idTypeBook.setText("Mã thể loại: "+book.getMaTheLoai());
                nameSach.setText("Tên sách: "+book.getTenSach());
                nXBSach.setText("Nhà XB: "+book.getNXB());
                tacGia.setText("Tác giả: "+book.getTacGia());
                soLuong.setText("Số lượng: "+book.getSoLuong());
                price.setText("Giá bán: "+book.getGiaBia());

                img_x.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
            }
        });


        holder.imgSach.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                builder.setTitle("bạn chắc chắn muốn xóa!");
                builder.setPositiveButton(String.valueOf(R.string.dialog_exit_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        sachDAO.deleteSachByID(sachDAO.getAllSach().get(position).getMaSach());
                        Intent intent = new Intent(context, SachActivity.class);
                        context.startActivity(intent);
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
        if (bookList != null) {
            return bookList.size();
        }
        return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgSach;
        TextView tvTenSach, tvLoaiSach, tvMaSach;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgSach = itemView.findViewById(R.id.img_sach);
            tvTenSach = itemView.findViewById(R.id.tv_ten_sach);
            tvLoaiSach = itemView.findViewById(R.id.tv_typeBook);
            tvMaSach = itemView.findViewById(R.id.tv_Idbook);
        }
    }
}
