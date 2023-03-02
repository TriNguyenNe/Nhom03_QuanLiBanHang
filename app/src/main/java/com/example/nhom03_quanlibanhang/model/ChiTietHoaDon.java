package com.example.nhom03_quanlibanhang.model;

public class ChiTietHoaDon {
    private String MaHD;
    private String MaMH;
    private int SoLuong;
    private float DonGia;
    public ChiTietHoaDon(){

    }

    public ChiTietHoaDon(String maHD, String maMH, int soLuong, float donGia) {
        MaHD = maHD;
        MaMH = maMH;
        SoLuong = soLuong;
        DonGia = donGia;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String maHD) {
        MaHD = maHD;
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

    public float getDonGia() {
        return DonGia;
    }

    public void setDonGia(float donGia) {
        DonGia = donGia;
    }
}
