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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.duanmau.DAO.BillDAO;
import com.example.duanmau.LOGIN.LogInActivity;
import com.example.duanmau.adapter.BillAdapter;
import com.example.duanmau.model.Bill;
import com.google.android.material.navigation.NavigationView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BillActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    BillAdapter billAdapter;
    ArrayList<Bill> billArrayList;
    RecyclerView recBill;
    BillDAO billDAO;
    Dialog dialog;
    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill);
        Menu();
        AnhXa();
        billDAO = new BillDAO(BillActivity.this);

            billAdapter = new BillAdapter(this, billDAO.getAllHoaDon());

        recBill.setLayoutManager(new GridLayoutManager(this, 2));
        recBill.setAdapter(billAdapter);



    }

    @Override
    protected void onRestart() {
        super.onRestart();
        billAdapter.notifyDataSetChanged();
    }

    private void AnhXa() {
        recBill = findViewById(R.id.rec_bill);
    }

    private void Menu() {
        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle(getString(R.string.bill_title));
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
            startActivity(new Intent(BillActivity.this, BookCategoryActivity.class));
        } else if (item.getItemId() == R.id.book_menu) {
            startActivity(new Intent(BillActivity.this, BookActivity.class));
        } else if (item.getItemId() == R.id.bill_menu) {
            startActivity(new Intent(BillActivity.this, BillActivity.class));
        } else if (item.getItemId() == R.id.statistical_menu) {
            startActivity(new Intent(BillActivity.this, StatisticalActivity.class));
        } else if (item.getItemId() == R.id.settings_menu) {
            startActivity(new Intent(BillActivity.this, SettingActivity.class));
        } else if (item.getItemId() == R.id.exit_menu) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(BillActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
            builder.setTitle(R.string.dialog_exit_title);
            builder.setIcon(R.drawable.icon_exit);
            builder.setPositiveButton(getString(R.string.dialog_exit_yes), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(BillActivity.this, LogInActivity.class);
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
        getMenuInflater().inflate(R.menu.add_bill, menu);
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        billAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        dialog = new Dialog(BillActivity.this);
        dialog.setContentView(R.layout.item_add_bill);
        dialog.setCancelable(false);
        final EditText idBill;
        DatePicker date;
        idBill = dialog.findViewById(R.id.edt_id_bill_addBill);
        date = dialog.findViewById(R.id.date_picker_bill);
        final Button btnDate, btnSave;
//        btnDate = dialog.findViewById(R.id.btn_date_addBill);
        btnSave = dialog.findViewById(R.id.btn_save_addBill);
        final ImageView img_x = dialog.findViewById(R.id.img_x_addBill);
        img_x.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         dialog.dismiss();
                                     }
                                 });
        dialog.show();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (String.valueOf(date.getDayOfMonth()).equals("") || idBill.getText().toString().equals("")) {
                    Toast.makeText(BillActivity.this, "Bạn phải nhập đủ thông tin!", Toast.LENGTH_SHORT).show();

                } else {
                    try {
                        if (checkIdBill(Integer.parseInt(idBill.getText().toString()))) {
                            Toast.makeText(BillActivity.this, "Mã Hóa Đơn đã tồn tại!", Toast.LENGTH_SHORT).show();
                        } else {
                            Bill bill = new Bill();
                            bill.setMaHoaDon(Integer.parseInt(idBill.getText().toString()));
                            Date date1=new SimpleDateFormat("yyyy-mm-dd").parse(date.getYear()+"-"+date.getMonth()+"-"+date.getDayOfMonth());
                            bill.setNgayMua(date1);
                            billDAO.inserHoaDon(bill);
                            billAdapter.notifyDataSetChanged();
                            Intent intent = new  Intent(BillActivity.this, BillDetailActivity.class);
                            intent.putExtra("MAHOADON", idBill.getText().toString());
                            startActivity(intent);
                            Toast.makeText(BillActivity.this, "Thêm hóa đơn thành công!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        return super.onOptionsItemSelected(item);
    }

    private boolean checkIdBill(int id) throws ParseException {
        for (int i = 0; i < billDAO.getAllHoaDon().size(); i++) {
            if (id== billDAO.getAllHoaDon().get(i).getMaHoaDon()) {
                return true;
            }
        }
        return false;
    }

}