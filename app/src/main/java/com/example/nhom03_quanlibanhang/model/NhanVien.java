package com.example.nhom03_quanlibanhang.model;

public class NhanVien {
    private String MaNV;
    private String HoTenNV;
    private String NgaySinh;
    private String DiaChi;
    private String GioiTinh;
    private String SdtNV;
    private String EmailNV;
    private String MatKhau;
    private String ChucVu;
    public NhanVien()
    {
    }

    public NhanVien(String maNV, String hoTenNV, String ngaySinh, String diaChi, String gioiTinh,String sdtNV, String emailNV, String matKhau, String chucVu) {
        MaNV = maNV;
        HoTenNV = hoTenNV;
        NgaySinh = ngaySinh;
        DiaChi = diaChi;
        GioiTinh = gioiTinh;
        SdtNV = sdtNV;
        EmailNV = emailNV;
        MatKhau = matKhau;
        ChucVu = chucVu;
    }

    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String maNV) {
        MaNV = maNV;
    }

    public String getHoTenNV() {
        return HoTenNV;
    }

    public void setHoTenNV(String hoTenNV) {
        HoTenNV = hoTenNV;
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

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getSdtNV() {
        return SdtNV;
    }

    public void setSdtNV(String sdtNV) {
        SdtNV = sdtNV;
    }

    public String getEmailNV() {
        return EmailNV;
    }

    public void setEmailNV(String emailNV) {
        EmailNV = emailNV;
    }

    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String chucVu) {
        ChucVu = chucVu;
    }
}
