package com.example.duanmau.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.duanmau.database.DatabaseHelper;
import com.example.duanmau.model.BookCategory;

import java.util.ArrayList;

public class BookCategoryDAO {
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    public static final String TABLE_NAME = "TheLoai";
    public static final String SQL_THE_LOAI ="CREATE TABLE TheLoai (matheloai text primary key, tentheloai text, mota text, vitri int,mauNen text);";
    public static final String TAG = "TheLoaiDAO";
    public BookCategoryDAO(Context context) {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    //insert
    public int inserTheLoai(BookCategory theLoai){
        ContentValues values = new ContentValues();
        values.put("matheloai",theLoai.getMaTheLoai());
        values.put("tentheloai",theLoai.getTenTheLoai());
        values.put("mota",theLoai.getMoTa());
        values.put("vitri",theLoai.getViTri());
        values.put("mauNen",theLoai.getMauNen());
        try {
            if(db.insert(TABLE_NAME,null,values)== -1){
                return -1;
            }
        }catch (Exception ex){
            Log.e(TAG,ex.toString());
        }
        return 1;
    }
    //getAllTheLoai
    public ArrayList<BookCategory> getAllTheLoai(){
        ArrayList<BookCategory> dsTheLoai = new ArrayList<>();
        Cursor c = db.query(TABLE_NAME,null,null,null,null,null,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            BookCategory ee = new BookCategory();
            ee.setMaTheLoai(c.getString(0));
            ee.setTenTheLoai(c.getString(1));
            ee.setMoTa(c.getString(2));
            ee.setViTri(c.getInt(3));
            ee.setMauNen(c.getString(4));
            dsTheLoai.add(ee);
            Log.d("//=====",ee.toString());
            c.moveToNext();
        }
        c.close();
        return dsTheLoai;
    }
    //update
    public int updateTheLoai(BookCategory theLoai){
        ContentValues values = new ContentValues();
        values.put("matheloai",theLoai.getMaTheLoai());
        values.put("tentheloai",theLoai.getTenTheLoai());
        values.put("mota",theLoai.getMoTa());
        values.put("vitri",theLoai.getViTri());
        values.put("mauNen",theLoai.getMauNen());
        int result = db.update(TABLE_NAME,values,"matheloai=?", new
                String[]{theLoai.getMaTheLoai()});
        if (result == 0){
            return -1;
        }
        return 1;
    }
    //delete
    public int deleteTheLoaiByID(String matheloai){
        int result = db.delete(TABLE_NAME,"matheloai=?",new String[]{matheloai});
        if (result == 0)
            return -1;
        return 1;
    }
}
