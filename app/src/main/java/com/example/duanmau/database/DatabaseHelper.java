package com.example.duanmau.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.duanmau.DAO.BillDetailDAO;
import com.example.duanmau.DAO.BillDAO;
import com.example.duanmau.DAO.UserDAO;
import com.example.duanmau.DAO.BookDAO;
import com.example.duanmau.DAO.BookCategoryDAO;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "dbBookManager";
    public static final int VERSION = 1;
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UserDAO.SQL_NGUOI_DUNG);
        db.execSQL(BookCategoryDAO.SQL_THE_LOAI);
        db.execSQL(BookDAO.SQL_SACH);
        db.execSQL(BillDAO.SQL_HOA_DON);
        db.execSQL(BillDetailDAO.SQL_HOA_DON_CHI_TIET);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("Drop table if exists "+ UserDAO.TABLE_NAME);
        db.execSQL("Drop table if exists "+ BookCategoryDAO.TABLE_NAME);
        db.execSQL("Drop table if exists "+ BookDAO.TABLE_NAME);
        db.execSQL("Drop table if exists "+ BillDAO.TABLE_NAME);
        db.execSQL("Drop table if exists "+ BillDetailDAO.TABLE_NAME);
        onCreate(db);
    }
}
