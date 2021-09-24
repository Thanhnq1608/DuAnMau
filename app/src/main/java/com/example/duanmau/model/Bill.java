package com.example.duanmau.model;

import java.util.Date;

public class Bill {
    private int maHoaDon;
    private Date ngayMua;

    public Bill() {
    }

    public Bill(int maHoaDon, Date  ngayMua) {
        this.maHoaDon = maHoaDon;
        this.ngayMua = ngayMua;
    }
    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public Date getNgayMua() {
        return ngayMua;
    }

    public void setNgayMua(Date ngayMua) {
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
