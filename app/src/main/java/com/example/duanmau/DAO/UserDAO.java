package com.example.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau.database.DatabaseHelper;
import com.example.duanmau.model.User;

import java.util.ArrayList;

public class UserDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String TABLE_NAME = "NguoiDung";
    public static final String SQL_NGUOI_DUNG = "CREATE TABLE NguoiDung (username text primary key, password text, phone text, hoten text,gioitinh text);";
    public static final String TAG = "NguoiDungDAO";

    public UserDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //insert
    public int inserNguoiDung(User nd) {
        ContentValues values = new ContentValues();
        values.put("username", nd.getUserName());
        values.put("password", nd.getPassword());
        values.put("phone", nd.getPhone());
        values.put("hoten", nd.getHoTen());
        values.put("gioitinh",nd.getGioiTinh());
        try {
            if (db.insert(TABLE_NAME, null, values) == -1) {
                return -1;
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.toString());
        }
        return 1;
    }

    //getAll
    public ArrayList<User> getAllNguoiDung() {
        ArrayList<User> dsUser = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            User ee = new User();
            ee.setUserName(c.getString(0));
            ee.setPassword(c.getString(1));
            ee.setPhone(c.getString(2));
            ee.setHoTen(c.getString(3));
            ee.setGioiTinh(c.getString(4));
            dsUser.add(ee);
            Log.d("//=====", ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsUser;
    }

    //update
    public int updateNguoiDung(User nd) {
        ContentValues values = new ContentValues();
        values.put("username", nd.getUserName());
        values.put("password", nd.getPassword());
        values.put("phone", nd.getPhone());
        values.put("hoten", nd.getHoTen());
        int result = db.update(TABLE_NAME, values, "username=?", new
                String[]{nd.getUserName()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public int changePasswordNguoiDung(User nd) {
        ContentValues values = new ContentValues();
        values.put("username", nd.getUserName());
        values.put("password", nd.getPassword());
        int result = db.update(TABLE_NAME, values, "username=?", new
                String[]{nd.getUserName()});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    public int updateInfoNguoiDung(String username, String phone, String name) {
        ContentValues values = new ContentValues();
        values.put("phone", phone);
        values.put("hoten", name);
        int result = db.update(TABLE_NAME, values, "username=?", new
                String[]{username});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    //delete
    public int deleteNguoiDungByID(String username) {
        int result = db.delete(TABLE_NAME, "username=?", new String[]{username});
        if (result == 0)
            return -1;
        return 1;
    }

    //check login
    public int checkLogin(String username, String password) {
        int result = db.delete(TABLE_NAME, "username=? AND password=?", new
                String[]{username, password
        });
        if (result == 0)
            return -1;
        return 1;
    }
}
