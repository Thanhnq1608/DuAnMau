package com.example.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau.database.DatabaseHelper;
import com.example.duanmau.model.HoaDon;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String TABLE_NAME = "HoaDon";
    public static final String SQL_HOA_DON = "CREATE TABLE HoaDon (mahoadon int primary key, ngaymua text);";
    public static final String TAG = "HoaDonDAO";

    public HoaDonDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //insert
    public int inserHoaDon(HoaDon hd) {
        ContentValues values = new ContentValues();
        values.put("mahoadon", hd.getMaHoaDon());
        values.put("ngaymua", hd.getNgayMua());
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
    public ArrayList<HoaDon> getAllHoaDon(){
        ArrayList<HoaDon> dsHoaDon = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            HoaDon ee = new HoaDon();
            ee.setMaHoaDon(c.getInt(0));
            ee.setNgayMua(c.getString(1));
            dsHoaDon.add(ee);
            c.moveToNext();
        }
        c.close();
        return dsHoaDon;
    }

    //update
    public int updateHoaDon(HoaDon hd) {
        ContentValues values = new ContentValues();
        values.put("mahoadon", hd.getMaHoaDon());
        values.put("ngaymua", hd.getNgayMua());
        int result = db.update(TABLE_NAME, values, "mahoadon=?", new
                String[]{String.valueOf(hd.getMaHoaDon())});
        if (result == 0) {
            return -1;
        }
        return 1;
    }

    //delete
    public int deleteHoaDonByID(String mahoadon) {
        int result = db.delete(TABLE_NAME, "mahoadon=?", new String[]{mahoadon});
        if (result == 0)
            return -1;
        return 1;
    }
}
