package com.example.demo2611;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {


    // lệnh tạo bảng sản phẩm
    public static final String SQL_SANPHAM = "CREATE TABLE sanpham (maSP TEXT PRIMARY KEY, tenSP TEXT, soLuong INTEGER)";

    // Hàm tạo CSDL
    public SQLiteHelper(Context context) {
        super(context, "QLBH.db", null, 1);
    }

    // Tạo bảng dữ liệu

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_SANPHAM);
    }

    // xóa bảng dữ liệu cũ, tạo bảng dữ liệu mới

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS sanpham"); // xóa bảng cũ, tạo bảng ới
    }
}
