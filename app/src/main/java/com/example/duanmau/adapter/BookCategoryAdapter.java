package com.example.duanmau.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.DAO.BookCategoryDAO;
import com.example.duanmau.R;
import com.example.duanmau.BookActivity;
import com.example.duanmau.BookCategoryActivity;
import com.example.duanmau.model.BookCategory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class BookCategoryAdapter extends RecyclerView.Adapter<BookCategoryAdapter.ViewHolder> {
    private Context context;
    Bitmap b;
    BitmapDrawable drawable;
    ArrayList<BookCategory> loaiSachList;
    Dialog dialog;

    public BookCategoryAdapter(Context context, ArrayList<BookCategory> loaiSachList) {
        this.context = context;
        this.loaiSachList = loaiSachList;
    }

    @NonNull
    @Override
    public BookCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_category, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull BookCategoryAdapter.ViewHolder holder, final int position) {
        final BookCategoryDAO bookCategoryDAO = new BookCategoryDAO(context);
        BookCategory bookCategory = loaiSachList.get(position);

        if (loaiSachList == null) {
            return;
        }
        final Thread t1 =  new Thread(new Runnable() {
            @Override
            public void run() {
                // lấy dứ liệu về
                final Drawable b = ImageLoading(loaiSachList.get(position).getMauNen());
                //đẩy lên giao diện
                holder.colorBack.post(new Runnable() {
                    @Override
                    public void run() {
                        holder.colorBack.setBackground(b);
                    }
                });
            }
        });
        t1.start();//bắt đầu thực hiện tiến trình

        holder.colorBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, BookActivity.class);
                intent.putExtra("maTheLoai",loaiSachList.get(position).getMaTheLoai());
                context.startActivity(intent);

            }
        });
        holder.tvTenLoaiSach.setText(bookCategory.getTenTheLoai());

        holder.colorBack.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                builder.setTitle("bạn chắc chắn muốn xóa!");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        bookCategoryDAO.deleteTheLoaiByID(bookCategoryDAO.getAllTheLoai().get(position).getMaTheLoai());
                        Toast.makeText(context, "Xóa Thành công!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, BookCategoryActivity.class);
                        context.startActivity(intent);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
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
                dialog = new Dialog(context);
                dialog.setContentView(R.layout.item_info_type);
                dialog.setCancelable(false);
                dialog.setTitle("Thông tin Thể Loại");
                final TextView tv_id, tv_Name, tv_moTa, tv_location;
                tv_id = dialog.findViewById(R.id.tv_id_infoType);
                tv_Name = dialog.findViewById(R.id.tv_name_infoType);
                tv_moTa = dialog.findViewById(R.id.tv_moTa_infoType);
                tv_location = dialog.findViewById(R.id.tv_location_infoType);
                final ImageView btn_exit = dialog.findViewById(R.id.btn_exit);
                tv_id.setText(bookCategoryDAO.getAllTheLoai().get(position).getMaTheLoai());
                tv_Name.setText(bookCategoryDAO.getAllTheLoai().get(position).getTenTheLoai());
                tv_moTa.setText(bookCategoryDAO.getAllTheLoai().get(position).getMoTa());
                tv_location.setText(String.valueOf(bookCategoryDAO.getAllTheLoai().get(position).getViTri()));
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

    public Drawable ImageLoading(String str){

        b =null;
        URL url;
        try {
            url=new URL(str);
            b = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            drawable = new BitmapDrawable(b);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return drawable;
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
