package com.example.nhom03_quanlibanhang.model;

public class HoaDon {
    private String MaHD;
    private String MaNV;
    private String MaDH;
    private String NgayLapHD ;
    private String NgayGiaoThucte;
    private Float TongTien;
    public HoaDon()
    {}

    public String getMaDH() {
        return MaDH;
    }

    public void setMaDH(String maDH) {
        MaDH = maDH;
    }

    public String getNgayLapHD() {
        return NgayLapHD;
    }

    public void setNgayLapHD(String ngayLapHD) {
        NgayLapHD = ngayLapHD;
    }

    public String getNgayGiaoThucte() {
        return NgayGiaoThucte;
    }

    public void setNgayGiaoThucte(String ngayGiaoThucte) {
        NgayGiaoThucte = ngayGiaoThucte;
    }

    public Float getTongTien() {
        return TongTien;
    }

    public void setTongTien(Float tongTien) {
        TongTien = tongTien;
    }

    public HoaDon(String maHD, String maNV, String maDH, String ngayLapHD, String ngayGiaoThucte, Float tongTien) {
        MaHD = maHD;
        MaNV = maNV;
        MaDH = maDH;
        NgayLapHD = ngayLapHD;
        NgayGiaoThucte = ngayGiaoThucte;
        TongTien = tongTien;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String maHD) {
        MaHD = maHD;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String maNV) {
        MaNV = maNV;
    }

    public String getMaGH() {
        return MaDH;
    }

    public void setMaGH(String maGH) {
        MaDH = maGH;
    }
}
