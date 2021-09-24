
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau.DAO.BookCategoryDAO;
import com.example.duanmau.LOGIN.LogInActivity;
import com.example.duanmau.adapter.BookCategoryAdapter;
import com.example.duanmau.model.BookCategory;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class BookCategoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recyclerView;
    ArrayList<BookCategory> theloaiList;
    BookCategoryAdapter bookCategoryAdapter;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    BookCategoryDAO bookCategoryDAO;
    Dialog dialog;
    BookCategory bookCategory = new BookCategory();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_category);
        anhXa();
        Menu();
        bookCategoryDAO = new BookCategoryDAO(BookCategoryActivity.this);

        bookCategoryAdapter = new BookCategoryAdapter(this, bookCategoryDAO.getAllTheLoai());
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(bookCategoryAdapter);
        bookCategoryAdapter.notifyDataSetChanged();
    }


    private void anhXa() {
        recyclerView = findViewById(R.id.recTrangChu);
    }

    private void addType(){
        BookCategory bookCategory = new BookCategory("it1","công nghệ máy tính","sách nói về lập trình",23,"https://i.pinimg.com/564x/fb/67/3b/fb673b887235fbe2ec62a26c138d1a04.jpg");
        BookCategory bookCategory1 = new BookCategory("hs1","Lịch sử cận đại","sách nói về lịch sử nhà nước",253,"https://i.pinimg.com/564x/23/15/d0/2315d04db1530eadb88a76e2351ae4b0.jpg");
        BookCategory bookCategory2 = new BookCategory("ma1","Đại số","sách nói về toán học",93,"https://i.pinimg.com/564x/a7/e6/bb/a7e6bb24e57f1f9b534e4f0ae4d41c92.jpg");
        BookCategory bookCategory3 = new BookCategory("py1","vật lý kĩ thuật","sách nói về vật lý",23,"https://wallpapercave.com/wp/wp2175403.jpg");
        bookCategoryDAO.inserTheLoai(bookCategory);
        bookCategoryDAO.inserTheLoai(bookCategory1);
        bookCategoryDAO.inserTheLoai(bookCategory2);
        bookCategoryDAO.inserTheLoai(bookCategory3);
//        theLoaiDAO.inserTheLoai(theLoaiSach3);
    }


    private void Menu() {
        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("Type Book");
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_type);
        navigationView = findViewById(R.id.navigation_view_type);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.user_menu) {
            startActivity(new Intent(BookCategoryActivity.this, UserActivity.class));
        } else if (item.getItemId() == R.id.type_menu) {
            startActivity(new Intent(BookCategoryActivity.this, BookCategoryActivity.class));
        } else if (item.getItemId() == R.id.bill_menu) {
            startActivity(new Intent(BookCategoryActivity.this, BillActivity.class));
        } else if (item.getItemId() == R.id.book_menu) {
            startActivity(new Intent(BookCategoryActivity.this, BookActivity.class));
        } else if (item.getItemId() == R.id.statistical_menu) {
            startActivity(new Intent(BookCategoryActivity.this, StatisticalActivity.class));
        } else if (item.getItemId() == R.id.settings_menu) {
            startActivity(new Intent(BookCategoryActivity.this, SettingActivity.class));
        } else if (item.getItemId() == R.id.exit_menu) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(BookCategoryActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
            builder.setTitle(R.string.dialog_exit_title);
            builder.setIcon(R.drawable.icon_exit);
            builder.setPositiveButton(getString(R.string.dialog_exit_yes), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(BookCategoryActivity.this, LogInActivity.class);
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
        getMenuInflater().inflate(R.menu.add_type, menu);
        MenuItem addType = menu.findItem(R.id.add_type);
        addType.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                dialog = new Dialog(BookCategoryActivity.this);
                dialog.setContentView(R.layout.item_add_book_category);
                dialog.setCancelable(false);
                dialog.setTitle("Add Type Book");
                final EditText id, name, describe, location,bg;
                id = dialog.findViewById(R.id.edt_id_type_itemType);
                name = dialog.findViewById(R.id.edt_name_type_itemType);
                describe = dialog.findViewById(R.id.edt_description_type_itemType);
                location = dialog.findViewById(R.id.edt_location_type_itemType);
                bg=dialog.findViewById(R.id.edt_background_type_itemType);

                final Button btnSave, btnCancel;
                btnCancel = dialog.findViewById(R.id.btn_cancel_itemType);
                btnSave = dialog.findViewById(R.id.btn_save_itemType);
                dialog.show();

                btnSave.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (id.getText().toString()==null || name.getText().toString()==null || describe.getText().toString()==null || location.getText().toString()==null) {
                            Toast.makeText(BookCategoryActivity.this, "Bạn không được bỏ trống thông tin!", Toast.LENGTH_SHORT).show();
                        } else {
                            BookCategory bookCategory1 =new BookCategory();
                            bookCategory1.setMaTheLoai(id.getText().toString());
                            bookCategory1.setTenTheLoai(name.getText().toString());
                            bookCategory1.setMoTa(describe.getText().toString());
                            bookCategory1.setViTri(Integer.parseInt(location.getText().toString()));
                            bookCategory1.setMauNen(bg.getText().toString());
                            bookCategoryDAO.inserTheLoai(bookCategory1);
                            bookCategoryAdapter.notifyDataSetChanged();
                            Toast.makeText(BookCategoryActivity.this, "Thêm Thể Loại Thành Công!", Toast.LENGTH_SHORT).show();
                            recreate();
                            dialog.dismiss();
                        }
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                return false;
            }
        });
        return true;
    }
}