package com.example.demo2611;

import java.io.Serializable;

public class SanPham{
    String maSP, tenSP;
    int soLuong;

    public SanPham() {
    }

    public SanPham(String maSP, String tenSP, int soLuong) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
}
