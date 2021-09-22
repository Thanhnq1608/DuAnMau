package com.example.duanmau.model;

public class HoaDonChiTiet {
    private int maHDCT;
    private int mahoaDon;
    private String purchaseDate;
    private Sach sach;
    private int soLuongMua;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int maHDCT, int mahoaDon,String purchaseDate,Sach sach, int soLuongMua) {
        this.maHDCT = maHDCT;
        this.mahoaDon = mahoaDon;
        this.purchaseDate=purchaseDate;
        this.sach = sach;
        this.soLuongMua = soLuongMua;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getMahoaDon() {
        return mahoaDon;
    }

    public void setMahoaDon(int mahoaDon) {
        this.mahoaDon = mahoaDon;
    }

    public int getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(int maHDCT) {
        this.maHDCT = maHDCT;
    }


    public Sach getSach() {
        return sach;
    }

    public void setSach(Sach sach) {
        this.sach = sach;
    }

    public int getSoLuongMua() {
        return soLuongMua;
    }

    public void setSoLuongMua(int soLuongMua) {
        this.soLuongMua = soLuongMua;
    }

    @Override
    public String toString() {
        return "HoaDonChiTiet{" +
                "maHDCT=" + maHDCT +
                ", mahoaDon=" + mahoaDon +
                ", book=" + sach +
                ", soLuongMua=" + soLuongMua +
                '}';
    }
}
