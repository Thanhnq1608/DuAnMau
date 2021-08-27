package com.example.duanmau.model;

public class NguoiDung {
    private String userName;
    private String password;
    private String gioiTinh;
    private String phone;
    private String hoTen;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public NguoiDung(String userName, String password, String gioiTinh, String phone, String hoTen) {
        this.userName = userName;
        this.password = password;
        this.gioiTinh = gioiTinh;
        this.phone = phone;
        this.hoTen = hoTen;
    }

    public NguoiDung() {
    }

    @Override
    public String toString() {
        return "NguoiDung{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", gioiTinh='" + gioiTinh + '\'' +
                ", phone='" + phone + '\'' +
                ", hoTen='" + hoTen + '\'' +
                '}';
    }
}
