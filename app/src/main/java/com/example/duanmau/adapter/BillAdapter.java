package com.example.duanmau.adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanmau.R;
import com.example.duanmau.model.HoaDon;
import com.example.duanmau.model.TheLoaiSach;

import java.util.ArrayList;

public class BillAdapter extends RecyclerView.Adapter<BillAdapter.ViewHolder> {
    private Context context;
    ArrayList<HoaDon> billList;
    Dialog dialog;

    public BillAdapter(Context context, ArrayList<HoaDon> billList) {
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

    @Override
    public void onBindViewHolder(@NonNull BillAdapter.ViewHolder holder, int position) {
        final HoaDon hoaDon = billList.get(position);
        if (billList == null) {
            return;
        }
        holder.imgBill.setBackgroundColor(R.drawable.icon_bill);
        holder.tvNgayMua.setText(String.valueOf(hoaDon.getNgayMua()));
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
        TextView tvNgayMua;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgBill = itemView.findViewById(R.id.img_bill);
            tvNgayMua = itemView.findViewById(R.id.tv_ngay_mua_itemBill);
        }
    }

}
