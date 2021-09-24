package com.example.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau.database.DatabaseHelper;
import com.example.duanmau.model.Bill;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class BillDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String TABLE_NAME = "HoaDon";
    public static final String SQL_HOA_DON = "CREATE TABLE HoaDon (mahoadon int primary key, ngaymua date);";
    public static final String TAG = "HoaDonDAO";
    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
    String date;

    public BillDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //insert
    public int inserHoaDon(Bill hd) {
        date = dateFormat.format(hd.getNgayMua());
        ContentValues values = new ContentValues();
        values.put("mahoadon", hd.getMaHoaDon());
        values.put("ngaymua", date);
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
    public ArrayList<Bill> getAllHoaDon(){
        ArrayList<Bill> dsBill = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        c.moveToFirst();
        while (c.isAfterLast() == false) {
            Bill ee = new Bill();
            ee.setMaHoaDon(c.getInt(0));
            try {
                ee.setNgayMua(new SimpleDateFormat("yyyy-mm-dd").parse(c.getString(1)));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            dsBill.add(ee);
            c.moveToNext();
        }
        c.close();
        return dsBill;
    }

    //update
    public int updateHoaDon(Bill hd) {
        date = dateFormat.format(hd.getNgayMua());
        ContentValues values = new ContentValues();
        values.put("mahoadon", hd.getMaHoaDon());
        values.put("ngaymua", date);
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
