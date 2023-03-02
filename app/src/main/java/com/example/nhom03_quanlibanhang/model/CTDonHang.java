package com.example.nhom03_quanlibanhang.model;

public class CTDonHang {
    private String MaDH;
    private String MaMH;
    private int SoLuong;
    private float DgBan;
    public CTDonHang(){}

    public CTDonHang(String maDH, String maMH, int soLuong, float dgBan) {
        MaDH = maDH;
        MaMH = maMH;
        SoLuong = soLuong;
        DgBan = dgBan;
    }

    public String getMaDH() {
        return MaDH;
    }

    public void setMaGH(String maDH) {
        MaDH = maDH;
    }

    public String getMaMH() {
        return MaMH;
    }

    public void setMaMH(String maMH) {
        MaMH = maMH;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int soLuong) {
        SoLuong = soLuong;
    }

    public float getDgBan() {
        return DgBan;
    }

    public void setDgBan(float dgBan) {
        DgBan = dgBan;
    }
}
