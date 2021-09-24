package com.example.duanmau.LOGIN;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.duanmau.DAO.UserDAO;
import com.example.duanmau.R;
import com.example.duanmau.adapter.UserAdapter;
import com.example.duanmau.model.User;

public class CreateAccountActivity extends AppCompatActivity {
    User user;
    UserDAO userDAO;
    EditText userName, passWord, rePass, phone, name;
    Button btnConfirm, btnCancel;
    RadioButton rdo_nam,rdo_nu;
    UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        userDAO = new UserDAO(CreateAccountActivity.this);

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
                Intent intent = new Intent(CreateAccountActivity.this, LogInActivity.class);
                startActivity(intent);
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userName.getText().toString().equals("") || passWord.getText().toString().equals("") || rePass.getText().toString().equals("") || phone.getText().toString().equals("") || name.getText().toString().equals("")) {
                    Toast.makeText(CreateAccountActivity.this, "Bạn không được bỏ trống!", Toast.LENGTH_SHORT).show();
                } else {
                    if (checkUser(userName.getText().toString())) {
                        Toast.makeText(CreateAccountActivity.this, "Tên tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show();
                    } else if (!passWord.getText().toString().equals(rePass.getText().toString())) {
                        Toast.makeText(CreateAccountActivity.this, "Bạn phải nhập lại đúng mật khẩu!", Toast.LENGTH_SHORT).show();
                    }
                    else if (rdo_nam.isChecked()==false&&rdo_nu.isChecked()==false){
                        Toast.makeText(CreateAccountActivity.this, "Bạn phải chọn giới tính!", Toast.LENGTH_SHORT).show();
                    }else {
                        User user1 = new User();
                        user1.setUserName(userName.getText().toString());
                        user1.setPassword(passWord.getText().toString());
                        user1.setPhone(phone.getText().toString());
                        user1.setHoTen(name.getText().toString());
                        if (rdo_nu.isChecked()==true){
                            user1.setGioiTinh("Nữ");
                        }else if (rdo_nam.isChecked()==true){
                            user1.setGioiTinh("Nam");
                        }
                        userDAO.inserNguoiDung(user1);
                        userAdapter.notifyDataSetChanged();
                        Toast.makeText(CreateAccountActivity.this, "Thêm Người Dùng Thành Công!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean checkUser(String u) {

        for (int i = 0; i < userDAO.getAllNguoiDung().size(); i++) {
            if (userDAO.getAllNguoiDung().get(i).getUserName().equalsIgnoreCase(u)) {
                return true;
            }
        }
        return false;
    }
}