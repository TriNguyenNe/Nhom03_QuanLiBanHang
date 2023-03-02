package com.example.nhom03_quanlibanhang.getMa_DoiTuong;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nhom03_quanlibanhang.SQLite.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class getMa_DoiTuong {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private Context context;

    public getMa_DoiTuong(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public List<String> getManv(){
        List<String> manv= new ArrayList<String>();
        String s= "SELECT * FROM NhanVien";
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            String sp ="";
            sp = c.getString(0);
            manv.add(sp);
            c.moveToNext();
        }
        c.close();
        return manv;
    }
    public List<String> getMakh(){
        List<String> manv= new ArrayList<String>();
        String s= "SELECT * FROM KhachHang";
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            String sp ="";
            sp = c.getString(0);
            manv.add(sp);
            c.moveToNext();
        }
        c.close();
        return manv;
    }
    public List<String> getMaDH(){
        List<String> manv= new ArrayList<String>();
        String s= "SELECT * FROM DonHang";
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            String sp ="";
            sp = c.getString(0);
            manv.add(sp);
            c.moveToNext();
        }
        c.close();
        return manv;
    }
    public List<String> getMaMH(){
        List<String> manv= new ArrayList<String>();
        String s= "SELECT * FROM MatHang";
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            String sp ="";
            sp = c.getString(0);
            manv.add(sp);
            c.moveToNext();
        }
        c.close();
        return manv;
    }
    public List<String> getMaNCC(){
        List<String> manv= new ArrayList<String>();
        String s= "SELECT * FROM NhaCungCap";
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            String sp ="";
            sp = c.getString(0);
            manv.add(sp);
            c.moveToNext();
        }
        c.close();
        return manv;
    }
    public List<String> getMaPN(){
        List<String> manv= new ArrayList<String>();
        String s= "SELECT * FROM PhieuNhap";
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            String sp ="";
            sp = c.getString(0);
            manv.add(sp);
            c.moveToNext();
        }
        c.close();
        return manv;
    }
    public List<String> getMaLH(){
        List<String> manv= new ArrayList<String>();
        String s= "SELECT * FROM LoaiHang";
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            String sp ="";
            sp = c.getString(0);
            manv.add(sp);
            c.moveToNext();
        }
        c.close();
        return manv;
    }
}
