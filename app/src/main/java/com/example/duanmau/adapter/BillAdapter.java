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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.DAO.BillDAO;
import com.example.duanmau.BillDetailActivity;
import com.example.duanmau.R;
import com.example.duanmau.model.Bill;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder> {
    private Context context;
    ArrayList<Bill> billList;
    Dialog dialog;

    public BillAdapter(Context context, ArrayList<Bill> billList) {
        this.context = context;
        this.billList = billList;
    }

    @NonNull
    @Override
    public BillAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_bill, parent, false);
        BillAdapter.ViewHolder viewHolder = new BillAdapter.ViewHolder(view);
        viewHolder.imgBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return viewHolder;
    }
    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull BillAdapter.ViewHolder holder, final int position) {
        final BillDAO billDAO =new BillDAO(context);
        final Bill bill = billList.get(position);
        if (billList == null) {
            return;
        }
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
        holder.imgBill.setBackgroundColor(R.drawable.icon_bill);
        holder.tvNgayMua.setText(dateFormat.format(bill.getNgayMua()));
        holder.tvId.setText(String.valueOf(bill.getMaHoaDon()));

        holder.imgBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new  Intent(context, BillDetailActivity.class);
                intent.putExtra("MAHOADON", String.valueOf(bill.getMaHoaDon()));
                context.startActivity(intent);
            }
        });
        holder.imgBill.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final AlertDialog.Builder builder =new AlertDialog.Builder(context,R.style.Theme_AppCompat_DayNight_Dialog_Alert);
                builder.setTitle("bạn chắc chắn muốn xóa!");
                builder.setPositiveButton(String.valueOf(R.string.dialog_exit_yes), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            billDAO.deleteHoaDonByID(String.valueOf(billDAO.getAllHoaDon().get(position).getMaHoaDon()));
                        Toast.makeText(context, "Xóa Thành công!", Toast.LENGTH_SHORT).show();
                        notifyDataSetChanged();
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
        if (billList!=null){
            return billList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgBill;
        TextView tvNgayMua,tvId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBill = itemView.findViewById(R.id.img_bill);
            tvNgayMua = itemView.findViewById(R.id.tv_ngay_mua_itemBill);
            tvId=itemView.findViewById(R.id.tv_id_bill_itemBill);
        }
    }

}
