package com.example.nhom03_quanlibanhang.GioHang;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GioHang implements Serializable {
    private String MaKH;
    private String MaMH;
    private String TenMH;
    private int Soluong;
    private double DonGia;
    private int id_a;

    public GioHang() {
    }
    public GioHang(String maKH,String maMH, String tenMH, int soluong, double gia, int id_a) {
        MaKH = maKH;
        MaMH = maMH;
        TenMH = tenMH;
        Soluong = soluong;
        DonGia = gia;
        this.id_a = id_a;
    }

    public int getId_a() {
        return id_a;
    }

    public void setId_a(int id_a) {
        this.id_a = id_a;
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

    public int getSoluong() {
        return Soluong;
    }

    public void setSoluong(int soluong) {
        Soluong = soluong;
    }

    public String getMaKH() {
        return MaKH;
    }

    public void setMaKH(String maKH) {
        MaKH = maKH;
    }

    public double getDonGia() {
        return DonGia;
    }

    public void setDonGia(double donGia) {
        DonGia = donGia;
    }
}
