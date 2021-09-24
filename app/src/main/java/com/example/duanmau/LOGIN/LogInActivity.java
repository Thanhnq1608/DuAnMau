package com.example.duanmau.LOGIN;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.duanmau.DAO.UserDAO;
import com.example.duanmau.R;
import com.example.duanmau.BookCategoryActivity;
import com.example.duanmau.model.User;
import com.google.android.material.textfield.TextInputLayout;

public class LogInActivity extends AppCompatActivity {
    EditText username, pass;
    CheckBox chk_save_pass;
    String sTrUser, sTrPass;
    Button login, cancel;
    TextInputLayout passLayout;
    TextView createAccount, fogetPassword;
    User user = new User();
    UserDAO userDAO;

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
                userDAO =new UserDAO(LogInActivity.this);
                sTrUser = username.getText().toString();
                sTrPass = pass.getText().toString();
                if (sTrUser.equals("admin") && sTrPass.equals("admin")) {
                    Toast.makeText(getApplicationContext(), "Bạn đang đăng nhập với tư cách Admin!", Toast.LENGTH_SHORT).show();
                    rememberUser(sTrUser,sTrPass,chk_save_pass.isChecked());
                    Intent intent = new Intent(LogInActivity.this, BookCategoryActivity.class);
                    startActivity(intent);
                } else if (userDAO.checkLogin(sTrUser, sTrPass) > 0) {
                    Toast.makeText(getApplicationContext(), "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    rememberUser(sTrUser,sTrPass,chk_save_pass.isChecked());
                    Intent  intent =new Intent(LogInActivity.this, BookCategoryActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LogInActivity.this, "Tài khoán hoặc mật khẩu không chính xác!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(LogInActivity.this, pass.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, CreateAccountActivity.class);
                startActivity(intent);
            }
        });

        fogetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LogInActivity.this,FogetPassActivity.class);
                startActivity(intent);
            }
        });

    }
    public void rememberUser(String u ,String p,boolean stutas){
        SharedPreferences sharedPreferences  = getSharedPreferences("USER_FILE",MODE_PRIVATE);
        SharedPreferences.Editor editor =sharedPreferences.edit();
        if (!stutas){
            editor.clear();

        }else {
            editor.putString("USERNAME",u);
            editor.putString("PASSWORD",p);
            editor.putBoolean("REMEMBER",stutas);
        }
        editor.commit();
    }

    private void anhXa() {
        username = findViewById(R.id.ed_user_login);
        passLayout = findViewById(R.id.edPassLayout);
        pass = findViewById(R.id.ed_pass_login);
        login = findViewById(R.id.btn_login_login);
        cancel = findViewById(R.id.btn_signin_login);
        createAccount = findViewById(R.id.tv_login_signin);
        fogetPassword = findViewById(R.id.tv_login_foget_pass);
        chk_save_pass = findViewById(R.id.chk_remember_pass);
    }
}