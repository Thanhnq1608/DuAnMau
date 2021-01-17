package com.example.duanmau;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.duanmau.adapter.SachAdapter;
import com.example.duanmau.model.Sach;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class SachActivity extends AppCompatActivity {
//    DrawerLayout drawerLayout;
//    ActionBarDrawerToggle actionBarDrawerToggle;
//    Toolbar toolbar;
//    NavigationView navigationView;
    RecyclerView recSach;
    ArrayList<Sach> sachList;
    SachAdapter sachAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach);
        anhXa();

        sachList = new ArrayList<>();
        addSach();
        sachAdapter = new SachAdapter(this, sachList);
        recSach.setLayoutManager(new GridLayoutManager(this, 2));
        recSach.setAdapter(sachAdapter);
    }

    private void anhXa() {
        recSach = findViewById(R.id.recSach);
    }

    private void addSach() {
        Sach sach0 = new Sach("it1", "it1", "yêu công nghệ", "thanh", "kim đồng", 20000, 100);
        Sach sach1 = new Sach("it1", "it1", "yêu công nghệ", "thanh", "kim đồng", 20000, 100);
        Sach sach2 = new Sach("it1", "it1", "yêu công nghệ", "thanh", "kim đồng", 20000, 100);
        Sach sach3 = new Sach("it1", "it1", "yêu công nghệ", "thanh", "kim đồng", 20000, 100);
        Sach sach4 = new Sach("it1", "it1", "yêu công nghệ", "thanh", "kim đồng", 20000, 100);
        Sach sach5 = new Sach("it1", "it1", "yêu công nghệ", "thanh", "kim đồng", 20000, 100);
        Sach sach6 = new Sach("it1", "it1", "yêu công nghệ", "thanh", "kim đồng", 20000, 100);
        Sach sach7 = new Sach("it1", "it1", "yêu công nghệ", "thanh", "kim đồng", 20000, 100);
        Sach sach8 = new Sach("it1", "it1", "yêu công nghệ", "thanh", "kim đồng", 20000, 100);
        Sach sach9 = new Sach("it1", "it1", "yêu công nghệ", "thanh", "kim đồng", 20000, 100);
        Sach sach10 = new Sach("it1", "it1", "yêu công nghệ", "thanh", "kim đồng", 20000, 100);
        sachList.add(sach0);
        sachList.add(sach1);
        sachList.add(sach2);
        sachList.add(sach3);
        sachList.add(sach4);
        sachList.add(sach5);
        sachList.add(sach6);
        sachList.add(sach7);
        sachList.add(sach8);
        sachList.add(sach9);
        sachList.add(sach10);

    }

//    private void Menu() {
//        toolbar = findViewById(R.id.tool_bar);
//        toolbar.setTitle("Thể Loại Sách");
//        setSupportActionBar(toolbar);
//        drawerLayout = findViewById(R.id.drawer);
//        navigationView = findViewById(R.id.navigation_view);
//        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
//        drawerLayout.addDrawerListener(actionBarDrawerToggle);
//        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
//        actionBarDrawerToggle.syncState();
//        navigationView.setNavigationItemSelectedListener(SachActivity.this);
////    }


}