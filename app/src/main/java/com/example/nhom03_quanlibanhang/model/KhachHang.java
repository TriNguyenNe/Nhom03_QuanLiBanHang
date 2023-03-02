package com.example.nhom03_quanlibanhang.model;

public class KhachHang {
    private String MaKH;
    private String HoTenKH;
    private String NgaySinh;
    private String DiaChi;
    private String SdtKH;
    private String pass;
    public KhachHang()
    {}
    public KhachHang(String maKH, String hoTenKH, String ngaySinh, String diaChi, String sdtKH, String pass) {
        MaKH = maKH;
        HoTenKH = hoTenKH;
        NgaySinh = ngaySinh;
        DiaChi = diaChi;
        SdtKH = sdtKH;
        this.pass = pass;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String maKH) {
        MaKH = maKH;
    }

    public String getHoTenKH() {
        return HoTenKH;
    }

    public void setHoTenKH(String hoTenKH) {
        HoTenKH = hoTenKH;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getSdtKH() {
        return SdtKH;
    }

    public void setSdtKH(String sdtKH) {
        SdtKH = sdtKH;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
