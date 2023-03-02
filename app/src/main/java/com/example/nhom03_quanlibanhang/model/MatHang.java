package com.example.nhom03_quanlibanhang.model;

import java.io.Serializable;

public class MatHang implements Serializable {
    private String MaMH;
    private String TenMH;
    private String DonViTinh;
    private float DonGia;
    private int SoLuongTon;
    private String MaLH;
    private String MaNCC;
    private int ID_a;
    public MatHang()
    {}
    public MatHang(String maMH, String tenMH, String donViTinh, float donGia, int soLuongTon, String maLH, String maNCC, int ID_a) {
        MaMH = maMH;
        TenMH = tenMH;
        DonViTinh = donViTinh;
        DonGia = donGia;
        SoLuongTon = soLuongTon;
        MaLH = maLH;
        MaNCC = maNCC;
        this.ID_a = ID_a;
    }

    public String getMaMH() {
        return MaMH;
    }

    public void setMaMH(String maMH) {
        MaMH = maMH;
    }

    public String getTenMH() {
        return TenMH;
    }

    public void setTenMH(String tenMH) {
        TenMH = tenMH;
    }

    public String getDonViTinh() {
        return DonViTinh;
    }

    public void setDonViTinh(String donViTinh) {
        DonViTinh = donViTinh;
    }

    public float getDonGia() {
        return DonGia;
    }

    public void setDonGia(float donGia) {
        DonGia = donGia;
    }

    public int getSoLuongTon() {
        return SoLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        SoLuongTon = soLuongTon;
    }

    public String getMaLH() {
        return MaLH;
    }

    public void setMaLH(String maLH) {
        MaLH = maLH;
    }

    public String getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(String maNCC) {
        MaNCC = maNCC;
    }

    public int getID_a() {
        return ID_a;
    }

    public void setID_a(int ID_a) {
        this.ID_a = ID_a;
    }
}
