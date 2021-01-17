package com.example.duanmau;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.duanmau.adapter.UserAdapter;
import com.example.duanmau.model.NguoiDung;
import com.example.duanmau.model.Sach;

import java.util.ArrayList;

public class UserActivity extends AppCompatActivity {
    RecyclerView recUser;
    ArrayList<NguoiDung> nguoiDungList;
    UserAdapter userAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        anhXa();

        nguoiDungList=new ArrayList<>();
        addUser();
        userAdapter = new UserAdapter(this,nguoiDungList);
        recUser.setLayoutManager(new GridLayoutManager(this,1));
        recUser.setAdapter(userAdapter);
    }

    private void anhXa() {
        recUser = findViewById(R.id.rec_user);
    }

    private void addUser() {
        NguoiDung sach0 = new NguoiDung("thanhnq","thanh123","0332751701","Quang Thanh");
        NguoiDung sach1 = new NguoiDung("thanhnq","thanh123","0332751701","Quang Thanh");
        NguoiDung sach2 = new NguoiDung("thanhnq","thanh123","0332751701","Quang Thanh");
        NguoiDung sach3 = new NguoiDung("thanhnq","thanh123","0332751701","Quang Thanh");
        NguoiDung sach4 = new NguoiDung("thanhnq","thanh123","0332751701","Quang Thanh");
        NguoiDung sach5 = new NguoiDung("thanhnq","thanh123","0332751701","Quang Thanh");
        NguoiDung sach6 = new NguoiDung("thanhnq","thanh123","0332751701","Quang Thanh");
        nguoiDungList.add(sach0);
        nguoiDungList.add(sach1);
        nguoiDungList.add(sach2);
        nguoiDungList.add(sach3);
        nguoiDungList.add(sach4);
        nguoiDungList.add(sach5);
        nguoiDungList.add(sach6);
    }
}