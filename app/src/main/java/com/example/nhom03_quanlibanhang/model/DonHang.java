package com.example.nhom03_quanlibanhang.model;

public class DonHang {
    private String  MaDH;
    private String MaKH;
    private String NgayDat;
    private String NgayGiaoDuKien ;

    public DonHang() {
    }

    public DonHang(String maDH, String maKH, String ngayDat, String ngayGiaoDuKien) {
        MaDH = maDH;
        MaKH = maKH;
        NgayDat = ngayDat;
        NgayGiaoDuKien = ngayGiaoDuKien;
    }

    public String getMaDH() {
        return MaDH;
    }

    public void setMaDH(String maGH) {
        MaDH = maGH;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String maKH) {
        MaKH = maKH;
    }

    public String getNgayDat() {
        return NgayDat;
    }

    public void setNgayDat(String ngayDat) {
        NgayDat = ngayDat;
    }

    public String getNgayGiaoDuKien() {
        return NgayGiaoDuKien;
    }

    public void setNgayGiaoDuKien(String ngayGiaoDuKien) {
        NgayGiaoDuKien = ngayGiaoDuKien;
    }
}
