package com.example.duanmau.LOGIN;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau.DAO.UserDAO;
import com.example.duanmau.R;
import com.example.duanmau.model.User;

public class FogetPassActivity extends AppCompatActivity {
    EditText user, phone, pass, rePass;
    Button confirm, cancel;
    UserDAO userDAO;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foget_pass);
        user = findViewById(R.id.ed_user_foget);
        phone = findViewById(R.id.ed_phone_foget);
        pass = findViewById(R.id.ed_pass_foget);
        rePass = findViewById(R.id.ed_repass_foget);
        confirm = findViewById(R.id.btn_confirm_foget);
        cancel = findViewById(R.id.btn_cancel_foget);

        userDAO =new UserDAO(FogetPassActivity.this);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FogetPassActivity.this, LogInActivity.class);
                startActivity(intent);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user.getText().toString().equals("") || phone.getText().toString().equals("") || pass.getText().toString().equals("") || rePass.getText().toString().equals("")) {
                    Toast.makeText(FogetPassActivity.this, "Bạn không được bỏ trống dữ liệu!", Toast.LENGTH_SHORT).show();
                } else if (checkUser(user.getText().toString())) {
                    Toast.makeText(FogetPassActivity.this, "Tài Khoản hoặc số điện thoại không chính xác1!", Toast.LENGTH_SHORT).show();
                } else if (checkPhoneNumber(user.getText().toString(), phone.getText().toString())) {
                    Toast.makeText(FogetPassActivity.this, "Tài Khoản hoặc số điện thoại không chính xác2!", Toast.LENGTH_SHORT).show();
                } else if (pass.getText().toString().length() < 6) {
                    Toast.makeText(FogetPassActivity.this, "Mật khẩu phải có ít nhất 6 kí tự!", Toast.LENGTH_SHORT).show();
                } else if (!pass.getText().toString().equals(rePass.getText().toString())) {
                    Toast.makeText(FogetPassActivity.this, "Bạn phải nhập lại đúng mật khẩu đã thiết lập!", Toast.LENGTH_SHORT).show();

                } else {
                    SharedPreferences pref = getSharedPreferences("USER_FILE", MODE_PRIVATE);
                    String strUserName = pref.getString("USERNAME", user.getText().toString());
                    User username = new User(strUserName, pass.getText().toString(), "","",
                            "");
                    if (userDAO.changePasswordNguoiDung(username) > 0) {
                        Toast.makeText(getApplicationContext(), "Lưu thành công",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Lưu thất bại",
                                Toast.LENGTH_SHORT).show();
                    }
                    Intent intent = new Intent(FogetPassActivity.this, LogInActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean checkUser(String u) {
        for (int i = 0; i < userDAO.getAllNguoiDung().size(); i++) {
            if (userDAO.getAllNguoiDung().get(i).getUserName().equalsIgnoreCase(u)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkPhoneNumber(String u, String phone) {
        for (int i = 0; i < userDAO.getAllNguoiDung().size(); i++) {
            if (userDAO.getAllNguoiDung().get(i).getUserName().equalsIgnoreCase(u)) {
                if (userDAO.getAllNguoiDung().get(i).getPhone().equalsIgnoreCase(phone)) {
                    return false;
                }
            }
        }
        return true;
    }
}