package com.example.demo2611;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class SanPham_DAO {
    SQLiteDatabase db;
    SQLiteHelper dbHelper;

    Context context;

    public SanPham_DAO( Context context) {
        this.dbHelper = new SQLiteHelper(context);
        this.db = dbHelper.getWritableDatabase();
        this.context = context;
    }

    public boolean insert(SanPham s){
        ContentValues values = new ContentValues(); // tao doi tuong chua du lieu
        // Dua du lieu vao doi tuong chua
        values.put("maSP", s.getMaSP());
        values.put("tenSP", s.getTenSP());
        values.put("soLuong", s.getSoLuong());

        // thuc thi insert
        long kq = db.insert("sanpham", null, values);

        // kiem tra kq
        if (kq <= 0) {
            return false;
            // insert that bai
        }
        return true; // insert thanh cong
    }

    public int DeleteSanPham(String masp){
        // thuc thi xoa
        int kq = db.delete("sanpham", "maSP = ?" , new String[]{masp});
        // kiem tra kq
        if (kq <= 0) {
            return -1;
            // delete that bai
        }
        return 1; // delete thanh cong
    }

    // UPDATE
    public int UpdateSanPham(SanPham s){
        ContentValues values = new ContentValues(); // tao doi tuong chua du lieu

        values.put("maSP", s.getMaSP());
        values.put("tenSP", s.getTenSP());
        values.put("soLuong", s.getSoLuong());

        // thuc thi update
        long kq = db.update("sanpham", values, "maSP = ?",new String[]{s.getMaSP()});
        // kiem tra kq
        if (kq <= 0) {
            return -1;
            // update that bai
        }
        return 1; // update thanh cong
    }

    public List<SanPham> getAllSanPham(){
        List<SanPham> ls = new ArrayList<>(); // tao mot danh sach rong
        // Tao con tro doc bang du lieu san pham
        Cursor c = db.query("sanpham", null, null, null, null, null, null);
        c.moveToFirst(); // di chuyen con tro ve ban ghi dau tien

        // doc
        while (c.isAfterLast() == false) // trong khi khong phai dong cuoi cung
        {
            SanPham s = new SanPham(); // tao doi tuong moi de chua du lieu
            s.setMaSP(c.getString(0)); // doc du lieu truong ma san pham va dua vao doi tuong
            s.setTenSP(c.getString(1)); // doc du lieu truong ten san pham
            s.setSoLuong(c.getInt(2)); // doc du lieu truong so luong

            // dua chuoi vao líst
            ls.add(s);
            c.moveToNext();
        }
        c.close(); // dong con tro
        return ls;
    }

    public List<SanPham> getFilteredSanPhamToString(String keyword) {
        List<SanPham> filteredList = new ArrayList<>();

        // Lấy tất cả sản phẩm từ CSDL (giả định là đã có phương thức getAllSanPhamToString)
        List<SanPham> allProducts = getAllSanPham();

        // Lọc các sản phẩm dựa trên từ khóa
        for (SanPham sp : allProducts) {
            if (sp.getTenSP().toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(sp);
            }
        }

        return filteredList;
    }

}
