package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.DAO.HoaDonChiTietDAO;
import com.example.duanmau.DAO.HoaDonDAO;
import com.example.duanmau.DAO.SachDAO;
import com.example.duanmau.DAO.TheLoaiDAO;
import com.example.duanmau.adapter.CartAdapter;
import com.example.duanmau.model.HoaDon;
import com.example.duanmau.model.HoaDonChiTiet;
import com.example.duanmau.model.Book;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class HoaDonChiTietActivity extends AppCompatActivity {
    EditText edSoLuong;
    TextView edMaHoaDon, tvThanhTien;
    Spinner spnMaSach;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    SachDAO sachDAO;
    TheLoaiDAO theLoaiDAO;
    HoaDonDAO hoaDonDAO;
    public ArrayList<HoaDonChiTiet> dsHDCT = new ArrayList<>();
    ListView lvCart;
    boolean temp = true;
    HoaDon hoaDon;
    List<Book> listBook = new ArrayList<>();
    CartAdapter adapter = null;
    double thanhTien = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hoa_don_chi_tiet);
        theLoaiDAO = new TheLoaiDAO(this);
        sachDAO = new SachDAO(this);
        hoaDonDAO = new HoaDonDAO(this);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(this);


        spnMaSach = findViewById(R.id.edMaSach);
        edMaHoaDon = findViewById(R.id.edMaHoaDon);
        edSoLuong = findViewById(R.id.edSoLuongMua);
        lvCart = findViewById(R.id.lvCart);
        tvThanhTien = findViewById(R.id.tvThanhTien);
        adapter = new CartAdapter(this, dsHDCT);
        lvCart.setAdapter(adapter);

        //LẤY mã hóa đơn
        Intent in = getIntent();
        String b = in.getStringExtra("MAHOADON");
        if (b != null) {
            edMaHoaDon.setText("Mã Hóa Đơn: " + b);
        }


        //Spiner sách cần mua
        ArrayList<String> listSP = new ArrayList<>();
        listSP.add("Chọn Sách cần mua.");
        for (int i = 0; i < theLoaiDAO.getAllTheLoai().size(); i++) {
            String idType = theLoaiDAO.getAllTheLoai().get(i).getMaTheLoai();
            for (int j = 0; j < sachDAO.getSachByType(idType).size(); j++) {
                String sach = idType + "_" + sachDAO.getSachByType(idType).get(j).getMaSach() + "_" + sachDAO.getSachByType(idType).get(j).getTenSach() + "_" + sachDAO.getSachByType(idType).get(j).getGiaBia() + "đ";
                listSP.add(sach);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listSP);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spnMaSach.setAdapter(adapter);


    }

    public void ADDHoaDonCHITIET(View view) {
        //các loại sách đã mua
        String[] temp = spnMaSach.getSelectedItem().toString().split("_", 3);
        listBook.add(sachDAO.getSachByID(temp[1]));
        hoaDonChiTietDAO = new HoaDonChiTietDAO(HoaDonChiTietActivity.this);
        sachDAO = new SachDAO(HoaDonChiTietActivity.this);
        try {
            if (!spnMaSach.getSelectedItem().toString().equalsIgnoreCase("Chọn Sách cần mua.")) {
                for (int i = 0; i < hoaDonDAO.getAllHoaDon().size(); i++) {
                    if (hoaDonDAO.getAllHoaDon().get(i).getMaHoaDon().equalsIgnoreCase(edMaHoaDon.getText().toString())) {
                        hoaDon = new HoaDon(edMaHoaDon.getText().toString(), hoaDonDAO.getAllHoaDon().get(i).getNgayMua());
//                        temp = false;
                        break;
                    }
                }
                if (Integer.parseInt(edSoLuong.getText().toString()) <= listBook.get(0).getSoLuong()) {

                    HoaDonChiTiet hoaDonChiTiet = new
                            HoaDonChiTiet(hoaDonChiTietDAO.getAllHoaDonChiTiet().size() + 1, hoaDon, listBook.get(0), Integer.parseInt(edSoLuong.getText().toString()));
//                    if (pos>=0){
//                        int soluong = dsHDCT.get(pos).getSoLuongMua();
//                        hoaDonChiTiet.setSoLuongMua(soluong + Integer.parseInt(edSoLuong.getText().toString()));
//                        dsHDCT.set(pos,hoaDonChiTiet);
//                    }else {
                    dsHDCT.add(hoaDonChiTiet);
//                    }
                    adapter.changeDataset(dsHDCT);
                    Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Mã sách không tồn tại", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public void thanhToanHoaDon(View view) {
        hoaDonChiTietDAO = new HoaDonChiTietDAO(HoaDonChiTietActivity.this);
        //tinh tien
        thanhTien = 0;
        try {
            for (HoaDonChiTiet hd : dsHDCT) {
                hoaDonChiTietDAO.inserHoaDonChiTiet(hd);
                thanhTien += hd.getSoLuongMua() * hd.getSach().getGiaBia();
            }
            tvThanhTien.setText("Tổng tiền: " + thanhTien);
        } catch (Exception ex) {
            Log.e("Error", ex.toString());
        }
    }

    public int checkMaSach(ArrayList<HoaDonChiTiet> lsHD, String maSach) {
        int pos = -1;
        for (int i = 0; i < lsHD.size(); i++) {
            HoaDonChiTiet hd = lsHD.get(i);
            if (hd.getSach().getMaSach().equalsIgnoreCase(maSach)) {
                pos = i;
                break;
            }
        }
        return pos;
    }
//    public int validation(){
//        if
//        (edMaSach.getText().toString().isEmpty()||edSoLuong.getText().toString().isEmpty()||
//                edMaHoaDon.getText().toString().isEmpty()){
//            return -1;
//        }
//        return 1;
//    }
}