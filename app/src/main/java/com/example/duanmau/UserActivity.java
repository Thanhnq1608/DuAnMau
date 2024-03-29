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
import android.view.Menu;
import android.view.MenuItem;

import com.example.duanmau.DAO.UserDAO;
import com.example.duanmau.LOGIN.ChangePassActivity;
import com.example.duanmau.LOGIN.LogInActivity;
import com.example.duanmau.LOGIN.CreateAccountActivity;
import com.example.duanmau.adapter.UserAdapter;
import com.example.duanmau.model.User;
import com.google.android.material.navigation.NavigationView;

public class UserActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recUser;
    UserAdapter userAdapter;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    UserDAO userDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        anhXa();
        Menu();
        userDAO =new UserDAO(UserActivity.this);

        addUser();
        userAdapter = new UserAdapter(this, userDAO.getAllNguoiDung() );
        recUser.setLayoutManager(new GridLayoutManager(this, 1));
        recUser.setAdapter(userAdapter);
        userAdapter.notifyDataSetChanged();
    }

    private void anhXa() {
        recUser = findViewById(R.id.rec_user);
    }

    private void addUser(){
        User user =new User("Thanh","thanh123","Nam","12344","thanh567");
        User user1 =new User("Thanh12","thanh123","Nữ","1245344","thanh567");
        User user2 =new User("Thanh323","thanh123","Nam","12365444","thanh567");
        User user3 =new User("Thanh443","thanh123","Nữ","12364544","thanh567");
        userDAO.inserNguoiDung(user);
        userDAO.inserNguoiDung(user1);
        userDAO.inserNguoiDung(user2);
        userDAO.inserNguoiDung(user3);
    }

    private void Menu() {
        toolbar = findViewById(R.id.tool_bar);
        toolbar.setTitle("User");
        setSupportActionBar(toolbar);
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
            startActivity(new Intent(UserActivity.this, BookCategoryActivity.class));
        } else if (item.getItemId() == R.id.book_menu) {
            startActivity(new Intent(UserActivity.this, BookActivity.class));
        } else if (item.getItemId() == R.id.bill_menu) {
            startActivity(new Intent(UserActivity.this, BillActivity.class));
        } else if (item.getItemId() == R.id.statistical_menu) {
            startActivity(new Intent(UserActivity.this, StatisticalActivity.class));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.user_service_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_add_user:
                Intent intent=new Intent(UserActivity.this, CreateAccountActivity.class);
                startActivity(intent);
                return true;
            case R.id.menu_change_pass_user:
                Intent intent1=new Intent(UserActivity.this, ChangePassActivity.class);
                startActivity(intent1);
                return true;
            case R.id.menu_logout_user:
                Intent intent2=new Intent(UserActivity.this, LogInActivity.class);
                startActivity(intent2);
        }
        return super.onOptionsItemSelected(item);
    }
}