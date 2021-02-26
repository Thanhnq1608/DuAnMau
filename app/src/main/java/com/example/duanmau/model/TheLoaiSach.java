package com.example.duanmau.model;

import android.graphics.Color;

public class TheLoaiSach {
    private String maTheLoai;
    private String tenTheLoai;
    private String moTa;
    private int viTri;
    private int mauNen;

    public int getMauNen() {
        return mauNen;
    }

    public void setMauNen(int mauNen) {
        this.mauNen = mauNen;
    }

    public TheLoaiSach(String maTheLoai, String tenTheLoai, String moTa, int viTri,int mauNen) {
        this.maTheLoai = maTheLoai;
        this.tenTheLoai = tenTheLoai;
        this.moTa = moTa;
        this.viTri = viTri;
        this.mauNen=mauNen;
    }

    public TheLoaiSach() {
    }

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getViTri() {
        return viTri;
    }

    public void setViTri(int viTri) {
        this.viTri = viTri;
    }

    @Override
    public String toString() {
        return "TheLoaiSach{" +
                "maTheLoai='" + maTheLoai + '\'' +
                ", tenTheLoai='" + tenTheLoai + '\'' +
                ", moTa='" + moTa + '\'' +
                ", viTri=" + viTri +
                ", mauNen=" + mauNen +
                '}';
    }
}
