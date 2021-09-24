package com.example.duanmau.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.duanmau.DAO.BillDetailDAO;
import com.example.duanmau.R;
import com.example.duanmau.model.BillDetail;

import java.util.ArrayList;

public class UnpaidBillsAdapter extends BaseAdapter {
    ArrayList<BillDetail> arrBillDetail;
    public Activity context;
    public LayoutInflater inflater;
    BillDetailDAO billDetailDAO;
    public UnpaidBillsAdapter(Activity context, ArrayList<BillDetail> arrayBillDetail) {
        super();
        this.context = context;
        this.arrBillDetail = arrayBillDetail;
        this.inflater =
                (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        billDetailDAO = new BillDetailDAO(context);
    }
    @Override
    public int getCount() {
        return arrBillDetail.size();
    }
    @Override
    public Object getItem(int position) {
        return arrBillDetail.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    public static class ViewHolder {
        TextView txtMaSach;
        TextView txtSoLuong;
        TextView txtGiaBia;
        TextView txtThanhTien,tvBill,tvDateBill;
        ImageView imgDelete;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null)
        {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.item_cart, null);
            holder.txtMaSach = (TextView) convertView.findViewById(R.id.tvMaSach);
            holder.txtSoLuong = (TextView) convertView.findViewById(R.id.tvSoLuong);
            holder.txtGiaBia = (TextView) convertView.findViewById(R.id.tvGiaBia);
            holder.txtThanhTien = (TextView) convertView.findViewById(R.id.tvThanhTien);
            holder.tvBill=convertView.findViewById(R.id.tvMaBill);
            holder.tvDateBill=convertView.findViewById(R.id.tvDateBill);
            holder.imgDelete = (ImageView)convertView.findViewById(R.id.ivDelete);
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    billDetailDAO.deleteHoaDonChiTietByID(String.valueOf(arrBillDetail.get(position).getMaHDCT()));
                    arrBillDetail.remove(position);
                    notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);
        }
        else
            holder=(ViewHolder)convertView.getTag();
        BillDetail billDetail = arrBillDetail.get(position);
        holder.tvBill.setText("Mã Hóa đơn: "+ billDetail.getMahoaDon());
        holder.txtMaSach.setText("Mã sách: "+ billDetail.getSach().getMaSach());
        holder.txtSoLuong.setText("Số lượng: "+ billDetail.getSoLuongMua());
        holder.txtGiaBia.setText("Giá bìa: "+ billDetail.getSach().getGiaBia() +" vnd");
        holder.txtThanhTien.setText("Thành tiền:"+ billDetail.getSoLuongMua()* billDetail.getSach().getGiaBia()+" vnd");
        return convertView;
    }
    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }
    public void changeDataset(ArrayList<BillDetail> items){
        this.arrBillDetail = items;
        notifyDataSetChanged();
    }
}
