package com.example.duanmau.model;

public class BillDetail {
    private int maHDCT;
    private int mahoaDon;
    private String purchaseDate;
    private Book book;
    private int soLuongMua;

    public BillDetail() {
    }

    public BillDetail(int maHDCT, int mahoaDon, String purchaseDate, Book book, int soLuongMua) {
        this.maHDCT = maHDCT;
        this.mahoaDon = mahoaDon;
        this.purchaseDate=purchaseDate;
        this.book = book;
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


    public Book getSach() {
        return book;
    }

    public void setSach(Book book) {
        this.book = book;
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
                ", book=" + book +
                ", soLuongMua=" + soLuongMua +
                '}';
    }
}
