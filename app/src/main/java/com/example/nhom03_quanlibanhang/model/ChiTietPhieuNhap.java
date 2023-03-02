package com.example.nhom03_quanlibanhang.model;

public class ChiTietPhieuNhap {
    private String MaPN;
    private String MaMH;
    private int SoLuongNhap;
    private float DgNhap;
    public ChiTietPhieuNhap()
    {}

    public ChiTietPhieuNhap(String maPN, String maMH, int soLuongNhap, float dgNhap) {
        MaPN = maPN;
        MaMH = maMH;
        SoLuongNhap = soLuongNhap;
        DgNhap = dgNhap;
    }

    public String getMaPN() {
        return MaPN;
    }

    public void setMaPN(String maPN) {
        MaPN = maPN;
    }

    public String getMaMH() {
        return MaMH;
    }

    public void setMaMH(String maMH) {
        MaMH = maMH;
    }

    public int getSoLuongNhap() {
        return SoLuongNhap;
    }

    public void setSoLuongNhap(int soLuongNhap) {
        SoLuongNhap = soLuongNhap;
    }

    public float getDgNhap() {
        return DgNhap;
    }

    public void setDgNhap(float dgNhap) {
        DgNhap = dgNhap;
    }
}
