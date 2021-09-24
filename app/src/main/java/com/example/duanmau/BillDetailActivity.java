package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.DAO.BillDetailDAO;
import com.example.duanmau.DAO.BillDAO;
import com.example.duanmau.DAO.BookDAO;
import com.example.duanmau.DAO.BookCategoryDAO;
import com.example.duanmau.adapter.UnpaidBillsAdapter;
import com.example.duanmau.model.Bill;
import com.example.duanmau.model.BillDetail;
import com.example.duanmau.model.Book;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BillDetailActivity extends AppCompatActivity {
    EditText edSoLuong;
    TextView edMaHoaDon, tvThanhTien;
    Spinner spnMaSach;
    BillDetailDAO billDetailDAO;
    BookDAO bookDAO;
    ArrayList<BillDetail> listUnpaidBill = new ArrayList<>();
    BookCategoryDAO bookCategoryDAO;
    BillDAO billDAO;
    ListView lvCart;
    boolean temp = true;
    Bill bill;
    List<Book> listBooks = new ArrayList<>();
    UnpaidBillsAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_detail);
        bookCategoryDAO = new BookCategoryDAO(BillDetailActivity.this);
        bookDAO = new BookDAO(BillDetailActivity.this);
        billDetailDAO = new BillDetailDAO(BillDetailActivity.this);
        bookDAO = new BookDAO(BillDetailActivity.this);


        spnMaSach = findViewById(R.id.edMaSach);
        edMaHoaDon = findViewById(R.id.edMaHoaDon);
        edSoLuong = findViewById(R.id.edSoLuongMua);
        lvCart = findViewById(R.id.lvCart);
        tvThanhTien = findViewById(R.id.tv_ThanhTien);
        adapter = new UnpaidBillsAdapter(BillDetailActivity.this, listUnpaidBill);
        lvCart.setAdapter(adapter);

        //LẤY mã hóa đơn
        Intent in = getIntent();
        String b = in.getStringExtra("MAHOADON");
        if (b != null) {
            edMaHoaDon.setText(b);
        }


        //Spiner sách cần mua
        ArrayList<String> listSP = new ArrayList<>();
        listSP.add("Chọn Sách cần mua.");
        for (int i = 0; i < bookCategoryDAO.getAllTheLoai().size(); i++) {
            String idType = bookCategoryDAO.getAllTheLoai().get(i).getMaTheLoai();
            for (int j = 0; j < bookDAO.getSachByType(idType).size(); j++) {
                String sach = idType + "_" + bookDAO.getSachByType(idType).get(j).getMaSach() + "_" + bookDAO.getSachByType(idType).get(j).getTenSach() + "_" + bookDAO.getSachByType(idType).get(j).getGiaBia() + "đ";
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
        listBooks.add(bookDAO.getSachByID(temp[1]));
        billDAO = new BillDAO(BillDetailActivity.this);
        if (!spnMaSach.getSelectedItem().toString().equalsIgnoreCase("Chọn Sách cần mua.")) {
            for (int i = 0; i < billDAO.getAllHoaDon().size(); i++) {
                if (billDAO.getAllHoaDon().get(i).getMaHoaDon() == Integer.parseInt(edMaHoaDon.getText().toString())) {
                    if (Integer.parseInt(edSoLuong.getText().toString()) <= listBooks.get(0).getSoLuong()) {
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                        String strDate = dateFormat.format(billDAO.getAllHoaDon().get(i).getNgayMua());
                        BillDetail billDetail = new
                                BillDetail(billDetailDAO.getAllHoaDonChiTiet().size() + 1,
                                Integer.parseInt(edMaHoaDon.getText().toString()),
                                strDate, listBooks.get(0),
                                Integer.parseInt(edSoLuong.getText().toString()));
                        listUnpaidBill.add(billDetail);
                        adapter.changeDataset(listUnpaidBill);
                        Toast.makeText(this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }

                    break;
                }
            }

        } else {
            Toast.makeText(getApplicationContext(), "Mã sách không tồn tại", Toast.LENGTH_SHORT).show();
        }
    }

    public void thanhToanHoaDon(View view) {
        billDetailDAO = new BillDetailDAO(BillDetailActivity.this);
        int thanhTien = 0;

        if (!listUnpaidBill.isEmpty()) {
            tvThanhTien.setText("Thành tiền: " + thanhTien);
            Toast.makeText(this, "Bạn đã thanh toán hóa đơn thành công", Toast.LENGTH_SHORT).show();
            for (BillDetail hd : listUnpaidBill) {
                thanhTien += hd.getSoLuongMua() * hd.getSach().getGiaBia();
            }
            int temp = listUnpaidBill.size();
            for (int i = 0; i < temp; i++) {
                billDetailDAO.inserHoaDonChiTiet(listUnpaidBill.get(i));
                listUnpaidBill.remove(i);
            }
            adapter.notifyDataSetChanged();
        } else {
            Toast.makeText(this, "Bạn không có hóa đơn nào cần thanh toán!", Toast.LENGTH_SHORT).show();
        }
    }

    public int checkMaSach(ArrayList<BillDetail> lsHD, String maSach) {
        int pos = -1;
        for (int i = 0; i < lsHD.size(); i++) {
            BillDetail hd = lsHD.get(i);
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