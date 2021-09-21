package com.example.duanmau.model;

import java.util.Date;

public class HoaDon {
    private int maHoaDon;
    private String ngayMua;

    public HoaDon() {
    }

    public HoaDon(int maHoaDon, String  ngayMua) {
        this.maHoaDon = maHoaDon;
        this.ngayMua = ngayMua;
    }
    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(String ngayMua) {
        this.ngayMua = ngayMua;
    }

    @Override
    public String toString() {
        return "HoaDon{" +
                "maHoaDon='" + maHoaDon + '\'' +
                ", ngayMua=" + ngayMua +
                '}';
    }
}
