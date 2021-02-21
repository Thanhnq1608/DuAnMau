package com.example.duanmau.LOGIN;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.DAO.NguoiDungDAO;
import com.example.duanmau.R;
import com.example.duanmau.TheLoaiActivity;
import com.example.duanmau.model.NguoiDung;
import com.google.android.material.textfield.TextInputLayout;

public class LogInActivity extends AppCompatActivity {
    EditText user;
    Button login, cancel;
    TextInputLayout pass;
    TextView createAccount, fogetPassword;
    NguoiDung nguoiDung=new NguoiDung();
    NguoiDungDAO nguoiDungDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        anhXa();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getText().equals("admin") && pass.getEditText().equals("admin")) {
                    Intent intent = new Intent(LogInActivity.this, TheLoaiActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LogInActivity.this, "Tài khoán hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(LogInActivity.this, pass.getEditText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this,SignInActivity.class);
                startActivity(intent);
            }
        });

    }

    private void anhXa() {
        user = findViewById(R.id.ed_user_login);
        pass = findViewById(R.id.ed_pass_login);
        login = findViewById(R.id.btn_login_login);
        cancel = findViewById(R.id.btn_signin_login);
        createAccount = findViewById(R.id.tv_login_signin);
        fogetPassword = findViewById(R.id.tv_login_foget_pass);
    }
}