package com.example.nhom03_quanlibanhang.model;

public class PhieuNhap {
    private String MaPN;
    private String MaNV;
    private String MaNCC;
    private String NgayLap;
    public PhieuNhap()
    {}

    public PhieuNhap(String maPN, String maNV, String maNCC, String ngayLap) {
        MaPN = maPN;
        MaNV = maNV;
        MaNCC = maNCC;
        NgayLap = ngayLap;
    }

    public String getMaPN() {
        return MaPN;
    }

    public void setMaPN(String maPN) {
        MaPN = maPN;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String maNV) {
        MaNV = maNV;
    }

    public String getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(String maNCC) {
        MaNCC = maNCC;
    }

    public String getNgayLap() {
        return NgayLap;
    }

    public void setNgayLap(String ngayLap) {
        NgayLap = ngayLap;
    }
}
