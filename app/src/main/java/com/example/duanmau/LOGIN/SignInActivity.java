package com.example.duanmau.LOGIN;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.duanmau.DAO.NguoiDungDAO;
import com.example.duanmau.R;
import com.example.duanmau.adapter.UserAdapter;
import com.example.duanmau.model.NguoiDung;

public class SignInActivity extends AppCompatActivity {
    NguoiDung nguoiDung;
    NguoiDungDAO nguoiDungDAO;
    EditText userName, passWord, rePass, phone, name;
    Button btnConfirm, btnCancel;
    RadioButton rdo_nam,rdo_nu;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        nguoiDungDAO = new NguoiDungDAO(SignInActivity.this);

        userName = findViewById(R.id.ed_user_signin);
        passWord = findViewById(R.id.ed_pass_signin);
        rePass = findViewById(R.id.ed_repass_signin);
        phone = findViewById(R.id.ed_phone_signin);
        name = findViewById(R.id.ed_name_signin);
        btnConfirm = findViewById(R.id.btn_confirm_signin);
        btnCancel = findViewById(R.id.btn_cancel_signin);
        rdo_nam=findViewById(R.id.rdo_nam);
        rdo_nu=findViewById(R.id.rdo_nu);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignInActivity.this, LogInActivity.class);
                startActivity(intent);
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userName.getText().toString().equals("") || passWord.getText().toString().equals("") || rePass.getText().toString().equals("") || phone.getText().toString().equals("") || name.getText().toString().equals("")) {
                    Toast.makeText(SignInActivity.this, "Bạn không được bỏ trống!", Toast.LENGTH_SHORT).show();
                } else {
                    if (checkUser(userName.getText().toString())) {
                        Toast.makeText(SignInActivity.this, "Tên tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show();
                    } else if (!passWord.getText().toString().equals(rePass.getText().toString())) {
                        Toast.makeText(SignInActivity.this, "Bạn phải nhập lại đúng mật khẩu!", Toast.LENGTH_SHORT).show();
                    }
                    else if (rdo_nam.isChecked()==false&&rdo_nu.isChecked()==false){
                        Toast.makeText(SignInActivity.this, "Bạn phải chọn giới tính!", Toast.LENGTH_SHORT).show();
                    }else {
                        NguoiDung nguoiDung1 = new NguoiDung();
                        nguoiDung1.setUserName(userName.getText().toString());
                        nguoiDung1.setPassword(passWord.getText().toString());
                        nguoiDung1.setPhone(phone.getText().toString());
                        nguoiDung1.setHoTen(name.getText().toString());
                        if (rdo_nu.isChecked()==true){
                            nguoiDung1.setGioiTinh("Nữ");
                        }else if (rdo_nam.isChecked()==true){
                            nguoiDung1.setGioiTinh("Nam");
                        }
                        nguoiDungDAO.inserNguoiDung(nguoiDung1);
                        userAdapter.notifyDataSetChanged();
                        Toast.makeText(SignInActivity.this, "Thêm Người Dùng Thành Công!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean checkUser(String u) {

        for (int i = 0; i < nguoiDungDAO.getAllNguoiDung().size(); i++) {
            if (nguoiDungDAO.getAllNguoiDung().get(i).getUserName().equalsIgnoreCase(u)) {
                return true;
            }
        }
        return false;
    }
}