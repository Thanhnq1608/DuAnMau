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

import com.example.duanmau.adapter.BillAdapter;
import com.example.duanmau.model.HoaDon;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class BillActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    BillAdapter billAdapter;
    ArrayList<HoaDon> hoaDonArrayList;
    RecyclerView recBill;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        Menu();
        AnhXa();

        hoaDonArrayList = new ArrayList<>();
        addBill();
        billAdapter=new BillAdapter(this,hoaDonArrayList);
        recBill.setLayoutManager(new GridLayoutManager(this,2));
        recBill.setAdapter(billAdapter);


    }

    private void addBill() {
        HoaDon hoaDon1 = new HoaDon("1", "21/10/2010");
        HoaDon hoaDon2 = new HoaDon("2", "21/10/2010");
        HoaDon hoaDon3 = new HoaDon("3", "21/10/2010");
        HoaDon hoaDon4 = new HoaDon("4", "21/10/2010");
        HoaDon hoaDon5 = new HoaDon("5", "21/10/2010");
        HoaDon hoaDon6 = new HoaDon("6", "21/10/2010");
        HoaDon hoaDon7 = new HoaDon("7", "21/10/2010");
        HoaDon hoaDon8 = new HoaDon("8", "21/10/2010");
        HoaDon hoaDon9 = new HoaDon("9", "21/10/2010");
        HoaDon hoaDon10 = new HoaDon("10", "21/10/2010");
        HoaDon hoaDon11 = new HoaDon("11", "21/10/2010");
        HoaDon hoaDon12 = new HoaDon("12", "21/10/2010");
        HoaDon hoaDon13 = new HoaDon("13", "21/10/2010");
        HoaDon hoaDon14 = new HoaDon("14", "21/10/2010");
        HoaDon hoaDon15 = new HoaDon("15", "21/10/2010");
        HoaDon hoaDon16 = new HoaDon("16", "21/10/2010");
        HoaDon hoaDon17 = new HoaDon("17", "21/10/2010");
        hoaDonArrayList.add(hoaDon1);
        hoaDonArrayList.add(hoaDon2);
        hoaDonArrayList.add(hoaDon3);
        hoaDonArrayList.add(hoaDon4);
        hoaDonArrayList.add(hoaDon5);
        hoaDonArrayList.add(hoaDon6);
        hoaDonArrayList.add(hoaDon7);
        hoaDonArrayList.add(hoaDon8);
        hoaDonArrayList.add(hoaDon9);
        hoaDonArrayList.add(hoaDon10);
        hoaDonArrayList.add(hoaDon11);
        hoaDonArrayList.add(hoaDon12);
        hoaDonArrayList.add(hoaDon13);
        hoaDonArrayList.add(hoaDon14);
        hoaDonArrayList.add(hoaDon15);
        hoaDonArrayList.add(hoaDon16);
        hoaDonArrayList.add(hoaDon17);




    }

    private void AnhXa() {
        recBill = findViewById(R.id.rec_bill);
    }

    private void Menu() {
        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("Thể Loại Sách");
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_bill);
        navigationView = findViewById(R.id.navigation_view_bill);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.user_menu) {
            startActivity(new Intent(BillActivity.this, UserActivity.class));
        } else if (item.getItemId() == R.id.type_menu) {
            startActivity(new Intent(BillActivity.this, TheLoaiActivity.class));
        } else if (item.getItemId() == R.id.book_menu) {
            startActivity(new Intent(BillActivity.this, SachActivity.class));
        } else if (item.getItemId() == R.id.bill_menu) {
            startActivity(new Intent(BillActivity.this, BillActivity.class));
        } else if (item.getItemId() == R.id.statistical_menu) {
            startActivity(new Intent(BillActivity.this, ThongKeActivity.class));
        } else if (item.getItemId() == R.id.settings_menu) {
            startActivity(new Intent(BillActivity.this, SettingActivity.class));
        } else if (item.getItemId() == R.id.exit_menu) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(BillActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
            builder.setTitle(R.string.dialog_exit_title);
            builder.setIcon(R.drawable.icon_exit);
            builder.setPositiveButton(getString(R.string.dialog_exit_yes), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    System.exit(0);
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