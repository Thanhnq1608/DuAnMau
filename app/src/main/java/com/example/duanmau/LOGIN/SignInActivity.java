package com.example.duanmau.LOGIN;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau.DAO.NguoiDungDAO;
import com.example.duanmau.R;
import com.example.duanmau.model.NguoiDung;

public class SignInActivity extends AppCompatActivity {
    NguoiDung nguoiDung;
    NguoiDungDAO nguoiDungDAO;
    EditText userName, passWord, rePass, phone, name;
    Button btnConfirm, btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        userName = findViewById(R.id.ed_user_signin);
        passWord = findViewById(R.id.ed_pass_signin);
        rePass = findViewById(R.id.ed_repass_signin);
        phone = findViewById(R.id.ed_phone_signin);
        name = findViewById(R.id.ed_name_signin);
        btnConfirm = findViewById(R.id.btn_confirm_signin);
        btnCancel = findViewById(R.id.btn_cancel_signin);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userName.getText().equals("") || passWord.getText().equals("") || rePass.getText().equals("") || phone.getText().equals("") || name.getText().equals("")) {
                    Toast.makeText(SignInActivity.this, "Bạn không được bỏ trống!", Toast.LENGTH_SHORT).show();
                    if (userName.getText().equals("")) {
                        userName.setBackgroundResource(R.color.colorRed);
                    } else {
                        userName.setBackgroundColor(0);
                    }
                    if (passWord.getText().equals("")) {
                        passWord.setBackgroundResource(R.color.colorRed);
                    } else {
                        passWord.setBackgroundColor(0);
                    }
                    if (rePass.getText().equals("")) {
                        rePass.setBackgroundResource(R.color.colorRed);
                    } else {
                        rePass.setBackgroundColor(0);
                    }
                    if (phone.getText().equals("")) {
                        phone.setBackgroundResource(R.color.colorRed);
                    } else {
                        phone.setBackgroundColor(0);
                    }
                    if (name.getText().equals("")) {
                        name.setBackgroundResource(R.color.colorRed);
                    } else {
                        name.setBackgroundColor(0);
                    }
                } else {
                    if (nguoiDung.getUserName().equalsIgnoreCase(userName.getText().toString())) {
                        Toast.makeText(SignInActivity.this, "Tên tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show();
                    } else {
                        if (passWord.getText().toString().equals(rePass.getText())) {
                            NguoiDung nguoiDung1 = new NguoiDung();
                            nguoiDung1.setUserName(userName.getText().toString());
                            nguoiDung1.setPassword(passWord.getText().toString());
                            nguoiDung1.setPhone(phone.getText().toString());
                            nguoiDung1.setHoTen(name.getText().toString());
                            nguoiDungDAO.inserNguoiDung(nguoiDung1);
                            Toast.makeText(SignInActivity.this, "Thêm Người Dùng Thành Công!", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });
    }
}