package com.example.duanmau;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.accounts.AbstractAccountAuthenticator;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.duanmau.DAO.SachDAO;
import com.example.duanmau.DAO.TheLoaiDAO;
import com.example.duanmau.LOGIN.LogInActivity;
import com.example.duanmau.adapter.SachAdapter;
import com.example.duanmau.model.Sach;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class SachActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    RecyclerView recSach;
    ArrayList<Sach> sachWithTheLoai=new ArrayList<>();
    SachAdapter sachAdapter;
    SachDAO sachDAO;

    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sach);
        anhXa();
        Menu();
        sachDAO = new SachDAO(SachActivity.this);

        Intent intent = this.getIntent();
        String maTL=intent.getStringExtra("maTheLoai");
        if (maTL==null){
            sachAdapter = new SachAdapter(this, sachDAO.getAllSach());
            recSach.setLayoutManager(new GridLayoutManager(this, 2));
            recSach.setAdapter(sachAdapter);
            sachAdapter.notifyDataSetChanged();
        }else {
            for (int i = 0;i<sachDAO.getAllSach().size();i++){
                if (sachDAO.getAllSach().get(i).getMaTheLoai().equalsIgnoreCase(maTL)){
                    sachWithTheLoai.add(sachDAO.getAllSach().get(i));
                    sachAdapter = new SachAdapter(this, sachWithTheLoai);
                    recSach.setLayoutManager(new GridLayoutManager(this, 2));
                    recSach.setAdapter(sachAdapter);
                    sachAdapter.notifyDataSetChanged();
                }
            }
        }
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
        navigationView.setNavigationItemSelectedListener(SachActivity.this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.user_menu) {
            startActivity(new Intent(SachActivity.this, UserActivity.class));
        } else if (item.getItemId() == R.id.type_menu) {
            startActivity(new Intent(SachActivity.this, TheLoaiActivity.class));
        } else if (item.getItemId() == R.id.bill_menu) {
            startActivity(new Intent(SachActivity.this, BillActivity.class));
        } else if (item.getItemId() == R.id.book_menu) {
            startActivity(new Intent(SachActivity.this, SachActivity.class));
        } else if (item.getItemId() == R.id.statistical_menu) {
            startActivity(new Intent(SachActivity.this, ThongKeActivity.class));
        } else if (item.getItemId() == R.id.settings_menu) {
            startActivity(new Intent(SachActivity.this, SettingActivity.class));
        } else if (item.getItemId() == R.id.exit_menu) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(SachActivity.this, android.R.style.Theme_DeviceDefault_Light_Dialog);
            builder.setTitle(R.string.dialog_exit_title);
            builder.setIcon(R.drawable.icon_exit);
            builder.setPositiveButton(getString(R.string.dialog_exit_yes), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(SachActivity.this, LogInActivity.class);
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
        MenuItem searchItem = menu.findItem(R.id.action_menu);
        androidx.appcompat.widget.SearchView searchView = (androidx.appcompat.widget.SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                sachAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        dialog = new Dialog(SachActivity.this);
        View view =LayoutInflater.from(this).inflate(R.layout.item_add_sach,null);
        dialog.setCancelable(false);
        dialog.setContentView(view);
        final Spinner spn_type = view.findViewById(R.id.spn_ten_type_itemAđSach);
        final EditText idSach, nameSach, nXBSach, tacGia, soLuong, price;
        idSach = view.findViewById(R.id.edt_id_addBook);
        nameSach = view.findViewById(R.id.edt_name_addBook);
        nXBSach = view.findViewById(R.id.edt_nxb_addBook);
        tacGia = view.findViewById(R.id.edt_tacGia_addBook);
        soLuong = view.findViewById(R.id.edt_soLuong_addBook);
        price = view.findViewById(R.id.edt_price_addBook);
        final Button btn_save, btn_cancel;
        btn_save = view.findViewById(R.id.btn_save_itemAddSach);
        btn_cancel = view.findViewById(R.id.btn_cancel_itemAddSach);

        TheLoaiDAO theLoaiDAO = new TheLoaiDAO(SachActivity.this);
        ArrayList<String> list = new ArrayList<String>();
        list.add("Chọn tên thể loại!");
        for (int i = 0; i < theLoaiDAO.getAllTheLoai().size(); i++) {
            list.add(theLoaiDAO.getAllTheLoai().get(i).getMaTheLoai());
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
                            Toast.makeText(SachActivity.this, "Bạn phải chọn thể loại sách!", Toast.LENGTH_SHORT).show();
                        } else if (idSach.getText().toString().equals("") || nameSach.getText().toString().equals("") || nXBSach.getText().toString().equals("") || tacGia.getText().toString().equals("") || soLuong.getText().toString().equals("") || price.getText().toString().equals("")) {
                            Toast.makeText(SachActivity.this, "Bạn không được bỏ trống dữ liệu!", Toast.LENGTH_SHORT).show();
                        } else if (checkid(idSach.getText().toString())) {
                            Toast.makeText(SachActivity.this, "ID đã tồn tại!", Toast.LENGTH_SHORT).show();
                        } else {
                            Sach sach = new Sach();
                            sach.setMaSach(idSach.getText().toString());
                            sach.setMaTheLoai(spn_type.getSelectedItem().toString());
                            sach.setGiaBia(Integer.parseInt(price.getText().toString()));
                            sach.setNXB(nXBSach.getText().toString());
                            sach.setSoLuong(Integer.parseInt(soLuong.getText().toString()));
                            sach.setTacGia(tacGia.getText().toString());
                            sach.setTenSach(nameSach.getText().toString());
                            sachDAO.inserSach(sach);
                            Toast.makeText(SachActivity.this, "Thêm sách thành công!", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                            sachAdapter.notifyDataSetChanged();
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
        for (int i = 0; i < sachDAO.getAllSach().size(); i++) {
            if (id.equalsIgnoreCase(sachDAO.getAllSach().get(i).getMaSach())) {
                return true;
            }
        }
        return false;
    }
}