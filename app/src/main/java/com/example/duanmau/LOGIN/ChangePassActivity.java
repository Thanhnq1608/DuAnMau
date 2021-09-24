package com.example.duanmau.LOGIN;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.duanmau.DAO.UserDAO;
import com.example.duanmau.R;
import com.example.duanmau.model.User;

public class ChangePassActivity extends AppCompatActivity {
    EditText user,pass,re_pass;
    Button btn_cofirm,btn_cancel;
    UserDAO userDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pass);
        user=findViewById(R.id.ed_user_changepass);
        pass=findViewById(R.id.ed_pass_changepass);
        re_pass=findViewById(R.id.ed_repass_changepass);
        btn_cofirm=findViewById(R.id.btn_confirm_changepass);
        btn_cancel=findViewById(R.id.btn_cancel_changepass);

        btn_cofirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences pref = getSharedPreferences("USER_FILE",MODE_PRIVATE);
                String strUserName = pref.getString("USERNAME",user.getText().toString());
                userDAO = new UserDAO(ChangePassActivity.this);
                User username = new User(strUserName, pass.getText().toString(), "", "","");
                try {
                    if (validateForm()>0){
                        if (userDAO.changePasswordNguoiDung(username) > 0) {
                            Toast.makeText(getApplicationContext(), "Lưu thành công",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Lưu thất bại",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                    finish();
                } catch (Exception ex) {
                    Log.e("Error", ex.toString());
                }
            }
        });
    }

    public int validateForm() {
        int check = 1;
        if (pass.getText().length() == 0 || re_pass.getText().length() == 0) {
            Toast.makeText(getApplicationContext(), "Bạn phải nhập đầy đủ thông ",
                    Toast.LENGTH_SHORT).show();
            check = -1;
        } else {
            String password = pass.getText().toString();
            String rePass = re_pass.getText().toString();
            if (!password.equals(rePass)) {
                Toast.makeText(getApplicationContext(), "Mật khẩu không trùng khớp",
                        Toast.LENGTH_SHORT).show();
                check = -1;
            }
        }
        return check;
    }

}