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
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.duanmau.DAO.NguoiDungDAO;
import com.example.duanmau.LOGIN.LogInActivity;
import com.example.duanmau.adapter.UserAdapter;
import com.example.duanmau.model.NguoiDung;
import com.example.duanmau.model.Sach;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recUser;
    ArrayList<NguoiDung> nguoiDungList;
    UserAdapter userAdapter;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        anhXa();
        Menu();
        nguoiDungDAO=new NguoiDungDAO(UserActivity.this);

        nguoiDungList = new ArrayList<>();
//        addUser();
        nguoiDungList = nguoiDungDAO.getAllNguoiDung();
//        nguoiDungList.addAll(nguoiDungDAO.getAllNguoiDung());
        userAdapter = new UserAdapter(this, nguoiDungList);
        recUser.setLayoutManager(new GridLayoutManager(this, 1));
        recUser.setAdapter(userAdapter);
    }

    private void anhXa() {
        recUser = findViewById(R.id.rec_user);
    }

    private void addUser() {
        NguoiDung sach0 = new NguoiDung("thanhnq", "thanh123", "0332751701", "Quang Thanh");
        NguoiDung sach1 = new NguoiDung("thanhnq1", "thanh123", "0332751701", "Quang Thanh");
        NguoiDung sach2 = new NguoiDung("thanhnq2", "thanh123", "0332751701", "Quang Thanh");
        NguoiDung sach3 = new NguoiDung("thanhnq3", "thanh123", "0332751701", "Quang Thanh");
        NguoiDung sach4 = new NguoiDung("thanhnq4", "thanh123", "0332751701", "Quang Thanh");
        NguoiDung sach5 = new NguoiDung("thanhnq5", "thanh123", "0332751701", "Quang Thanh");
        NguoiDung sach6 = new NguoiDung("thanhnq6", "thanh123", "0332751701", "Quang Thanh");
//        nguoiDungList.add(sach0);
//        nguoiDungList.add(sach1);
//        nguoiDungList.add(sach2);
//        nguoiDungList.add(sach3);
//        nguoiDungList.add(sach4);
//        nguoiDungList.add(sach5);
//        nguoiDungList.add(sach6);
        nguoiDungDAO.inserNguoiDung(sach0);
        nguoiDungDAO.inserNguoiDung(sach1);
        nguoiDungDAO.inserNguoiDung(sach2);
        nguoiDungDAO.inserNguoiDung(sach3);
        nguoiDungDAO.inserNguoiDung(sach4);
        nguoiDungDAO.inserNguoiDung(sach5);
        nguoiDungDAO.inserNguoiDung(sach6);

    }

    private void Menu() {
        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("Thể Loại Sách");
        setSupportActionBar(toolbar);
//        getSupportActionBar().hide();
        drawerLayout = findViewById(R.id.drawer_user);
        navigationView = findViewById(R.id.navigation_view_user);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.user_menu) {
            startActivity(new Intent(UserActivity.this, UserActivity.class));
        } else if (item.getItemId() == R.id.type_menu) {
            startActivity(new Intent(UserActivity.this, TheLoaiActivity.class));
        } else if (item.getItemId() == R.id.book_menu) {
            startActivity(new Intent(UserActivity.this, SachActivity.class));
        } else if (item.getItemId() == R.id.bill_menu) {
            startActivity(new Intent(UserActivity.this, BillActivity.class));
        } else if (item.getItemId() == R.id.statistical_menu) {
            startActivity(new Intent(UserActivity.this, ThongKeActivity.class));
        } else if (item.getItemId() == R.id.settings_menu) {
            startActivity(new Intent(UserActivity.this, SettingActivity.class));
        } else if (item.getItemId() == R.id.exit_menu) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
            builder.setTitle(R.string.dialog_exit_title);
            builder.setIcon(R.drawable.icon_exit);
            builder.setPositiveButton(getString(R.string.dialog_exit_yes), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent=new Intent(UserActivity.this, LogInActivity.class);
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
}