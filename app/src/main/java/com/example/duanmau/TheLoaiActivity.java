package com.example.duanmau;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.duanmau.adapter.HomeAdapter;
import com.example.duanmau.model.TheLoaiSach;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class TheLoaiActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<TheLoaiSach> theloaiList;
    HomeAdapter homeAdapter;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_loai_sach);

        anhXa();

        theloaiList = new ArrayList<>();
        addTheLoai();
        homeAdapter = new HomeAdapter(this, theloaiList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(homeAdapter);
    }

    private void anhXa() {
        recyclerView = findViewById(R.id.recTrangChu);
    }

    private void addTheLoai() {
        TheLoaiSach theLoaiSach = new TheLoaiSach("It1", "Công nghệ Thông tin", "Sách hướng dẫn lập trình", 30, R.color.color_it);
        TheLoaiSach theLoaiSach1 = new TheLoaiSach("Hs1", "Lịch Sử", "Sách hướng dẫn lịch sử", 10, R.color.color_his);
        TheLoaiSach theLoaiSach2 = new TheLoaiSach("Dl1", "Địa Lý", "Sách hướng dẫn Dịa lý", 50, R.color.color_phy);
        TheLoaiSach theLoaiSach3 = new TheLoaiSach("To1", "Toán", "Sách hướng dẫn toán", 31, R.color.color_math);
        TheLoaiSach theLoaiSach4 = new TheLoaiSach("TV1", "Tiếng Việt", "Sách hướng dẫn tiếng việt", 13, R.color.color_viet);
        TheLoaiSach theLoaiSach5 = new TheLoaiSach("Mk1", "Maketing", "Sách hướng dẫn Maketing", 7, R.color.color_mar);
        theloaiList.add(theLoaiSach);
        theloaiList.add(theLoaiSach1);
        theloaiList.add(theLoaiSach2);
        theloaiList.add(theLoaiSach3);
        theloaiList.add(theLoaiSach4);
        theloaiList.add(theLoaiSach5);

    }

//    private void Menu() {
//
//        toolbar = findViewById(R.id.tool_bar);
//        toolbar.setTitle("Thể Loại Sách");
//        setSupportActionBar(toolbar);
//        drawerLayout = findViewById(R.id.drawer);
//        navigationView = findViewById(R.id.navigation_view);
//        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
//        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
//        actionBarDrawerToggle.syncState();
//        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
//    }

}