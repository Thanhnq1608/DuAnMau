package com.example.duanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duanmau.DAO.BookDAO;
import com.example.duanmau.DAO.BookCategoryDAO;
import com.example.duanmau.LOGIN.LogInActivity;
import com.example.duanmau.adapter.BookAdapter;
import com.example.duanmau.model.Book;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class BookActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    RecyclerView recSach;
    ArrayList<Book> bookWithTheLoai =new ArrayList<>();
    BookAdapter bookAdapter;
    BookDAO bookDAO;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        anhXa();
        Menu();
        bookDAO = new BookDAO(BookActivity.this);

        Intent intent = this.getIntent();
        String maTL=intent.getStringExtra("maTheLoai");
        if (maTL==null){
            bookAdapter = new BookAdapter(this, bookDAO.getAllSach());
            recSach.setLayoutManager(new GridLayoutManager(this, 2));
            recSach.setAdapter(bookAdapter);
            bookAdapter.notifyDataSetChanged();
        }else {
            for (int i = 0; i< bookDAO.getAllSach().size(); i++){
                if (bookDAO.getAllSach().get(i).getMaTheLoai().equalsIgnoreCase(maTL)){
                    bookWithTheLoai.add(bookDAO.getAllSach().get(i));
                    bookAdapter = new BookAdapter(this, bookWithTheLoai);
                    recSach.setLayoutManager(new GridLayoutManager(this, 2));
                    recSach.setAdapter(bookAdapter);
                    bookAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    private void addSach(){
//        Book book =new Book("it1","1","Yêu Công Nghệ","Quang Thanh","Kim Đồng",20000,20);
//        Book book1 =new Book("it1","2","Công Nghệ và Hiện Đại","Thanh","Kim Đồng",120000,20);
//        Book book2 =new Book("py1","3","Vật Lý Học","Hoàng Anh","Kim Đồng",210000,20);
//        Book book3 =new Book("py1","4","Những nhà Bác Học","Mạnh Hùng","Kim Đồng",130000,20);
//        Book book4 =new Book("hs1","5","Lịch Sử Đảng","Nghiêm Đạt","Kim Đồng",250000,20);
//        Book book5 =new Book("hs1","6","Triết học gia","Xuân Hoàng","Kim Đồng",220000,20);
//        Book book6 =new Book("ma1","7","Những Con số","Bá Nam","Kim Đồng",150000,20);
//        Book book7 =new Book("ma1","8","Nghệ thuật logic","Thị Bích","Kim Đồng",120000,20);
        bookDAO.inserSach(new Book("it1","1","Yêu Công Nghệ","Quang Thanh","Kim Đồng",20000,20));
//        sachDAO.inserSach(book1);
//        sachDAO.inserSach(book2);
//        sachDAO.inserSach(book3);
//        sachDAO.inserSach(book4);
//        sachDAO.inserSach(book5);
//        sachDAO.inserSach(book6);
//        sachDAO.inserSach(book7);


    }

    private void anhXa() {
        recSach = findViewById(R.id.recSach);
    }

    private void Menu() {
        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle(getString(R.string.book_title));
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_book);
        navigationView = findViewById(R.id.navigation_view_book);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(BookActivity.this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.user_menu) {
            startActivity(new Intent(BookActivity.this, UserActivity.class));
        } else if (item.getItemId() == R.id.type_menu) {
            startActivity(new Intent(BookActivity.this, BookCategoryActivity.class));
        } else if (item.getItemId() == R.id.bill_menu) {
            startActivity(new Intent(BookActivity.this, BillActivity.class));
        } else if (item.getItemId() == R.id.book_menu) {
            startActivity(new Intent(BookActivity.this, BookActivity.class));
        } else if (item.getItemId() == R.id.statistical_menu) {
            startActivity(new Intent(BookActivity.this, StatisticalActivity.class));
        } else if (item.getItemId() == R.id.settings_menu) {
            startActivity(new Intent(BookActivity.this, SettingActivity.class));
        } else if (item.getItemId() == R.id.exit_menu) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(BookActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
            builder.setTitle(R.string.dialog_exit_title);
            builder.setIcon(R.drawable.icon_exit);
            builder.setPositiveButton(getString(R.string.dialog_exit_yes), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(BookActivity.this, LogInActivity.class);
                    startActivity(intent);
                }
            });
            builder.setNegativeButton(getString(R.string.dialog_exit_no), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            builder.show();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_book, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        dialog = new Dialog(BookActivity.this);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.item_add_book);
        final Spinner spn_type = dialog.findViewById(R.id.spn_ten_type_itemAđSach);
        final EditText idSach, nameSach, nXBSach, tacGia, soLuong, price;
        idSach = dialog.findViewById(R.id.edt_id_addBook);
        nameSach = dialog.findViewById(R.id.edt_name_addBook);
        nXBSach = dialog.findViewById(R.id.edt_nxb_addBook);
        tacGia = dialog.findViewById(R.id.edt_tacGia_addBook);
        soLuong = dialog.findViewById(R.id.edt_soLuong_addBook);
        price = dialog.findViewById(R.id.edt_price_addBook);
        final Button btn_save, btn_cancel;
        btn_save = dialog.findViewById(R.id.btn_save_itemAddSach);
        btn_cancel = dialog.findViewById(R.id.btn_cancel_itemAddSach);

        BookCategoryDAO bookCategoryDAO = new BookCategoryDAO(BookActivity.this);
        ArrayList<String> list = new ArrayList<String>();
        list.add("Chọn tên thể loại!");
        for (int i = 0; i < bookCategoryDAO.getAllTheLoai().size(); i++) {
            list.add(bookCategoryDAO.getAllTheLoai().get(i).getMaTheLoai());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spn_type.setAdapter(adapter);

        switch (item.getItemId()) {
            case R.id.menu_add_book:
                dialog.show();
                btn_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (spn_type.getSelectedItem().toString().equals("Chọn tên thể loại!")) {
                            Toast.makeText(BookActivity.this, "Bạn phải chọn thể loại sách!", Toast.LENGTH_SHORT).show();
                        } else if (idSach.getText().toString().equals("") || nameSach.getText().toString().equals("") || nXBSach.getText().toString().equals("") || tacGia.getText().toString().equals("") || soLuong.getText().toString().equals("") || price.getText().toString().equals("")) {
                            Toast.makeText(BookActivity.this, "Bạn không được bỏ trống dữ liệu!", Toast.LENGTH_SHORT).show();
                        } else if (checkid(idSach.getText().toString())) {
                            Toast.makeText(BookActivity.this, "ID đã tồn tại!", Toast.LENGTH_SHORT).show();
                        } else {
                            Book book = new Book();
                            book.setMaSach(idSach.getText().toString());
                            book.setMaTheLoai(spn_type.getSelectedItem().toString());
                            book.setGiaBia(Integer.parseInt(price.getText().toString()));
                            book.setNXB(nXBSach.getText().toString());
                            book.setSoLuong(Integer.parseInt(soLuong.getText().toString()));
                            book.setTacGia(tacGia.getText().toString());
                            book.setTenSach(nameSach.getText().toString());
                            bookDAO.inserSach(book);
                            recreate();
                            Toast.makeText(BookActivity.this, "Thêm sách thành công!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    }
                });
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean checkid(String id) {
        for (int i = 0; i < bookDAO.getAllSach().size(); i++) {
            if (id.equalsIgnoreCase(bookDAO.getAllSach().get(i).getMaSach())) {
                return true;
            }
        }
        return false;
    }
}