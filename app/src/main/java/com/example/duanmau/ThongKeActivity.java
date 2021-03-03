package com.example.duanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.duanmau.DAO.HoaDonChiTietDAO;
import com.example.duanmau.LOGIN.LogInActivity;
import com.google.android.material.navigation.NavigationView;

public class ThongKeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    TextView tvNgay, tvThang, tvNam;
    HoaDonChiTietDAO hoaDonChiTietDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_ke);
        Menu();
        tvNgay = (TextView) findViewById(R.id.tvThongKeNgay);
        tvThang = (TextView) findViewById(R.id.tv_money_month);
        tvNam = (TextView) findViewById(R.id.tv_money_year);
        hoaDonChiTietDAO = new HoaDonChiTietDAO(this);
        tvNgay.setText("Hôm nay: "+hoaDonChiTietDAO.getDoanhThuTheoNgay());
        tvThang.setText("Tháng này: "+hoaDonChiTietDAO.getDoanhThuTheoThang());
        tvNam.setText("Năm này: "+hoaDonChiTietDAO.getDoanhThuTheoNam());
    }

    private void Menu() {
        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("Statistical");
        setSupportActionBar(toolbar);
//        getSupportActionBar().hide();
        drawerLayout = findViewById(R.id.drawer_thongke);
        navigationView = findViewById(R.id.navigation_view_thongke);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.user_menu) {
            startActivity(new Intent(ThongKeActivity.this, UserActivity.class));
        } else if (item.getItemId() == R.id.type_menu) {
            startActivity(new Intent(ThongKeActivity.this, TheLoaiActivity.class));
        } else if (item.getItemId() == R.id.book_menu) {
            startActivity(new Intent(ThongKeActivity.this, SachActivity.class));
        } else if (item.getItemId() == R.id.bill_menu) {
            startActivity(new Intent(ThongKeActivity.this, BillActivity.class));
        } else if (item.getItemId() == R.id.statistical_menu) {
            startActivity(new Intent(ThongKeActivity.this, ThongKeActivity.class));
        } else if (item.getItemId() == R.id.settings_menu) {
            startActivity(new Intent(ThongKeActivity.this, SettingActivity.class));
        } else if (item.getItemId() == R.id.exit_menu) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(ThongKeActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
            builder.setTitle(R.string.dialog_exit_title);
            builder.setIcon(R.drawable.icon_exit);
            builder.setPositiveButton(getString(R.string.dialog_exit_yes), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent=new Intent(ThongKeActivity.this, LogInActivity.class);
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