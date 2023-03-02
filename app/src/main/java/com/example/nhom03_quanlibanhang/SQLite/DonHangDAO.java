package com.example.nhom03_quanlibanhang.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nhom03_quanlibanhang.model.DonHang;

import java.security.DomainCombiner;
import java.util.ArrayList;
import java.util.List;

public class DonHangDAO {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private Context context;
    public DonHangDAO(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public int themsp(DonHang donhang){
        ContentValues values = new ContentValues();
        //values.put("MaDH",donhang.getMaDH());
        values.put("MaKH",donhang.getMaKH());
        values.put("NgayDat",donhang.getNgayDat());
        values.put("NgayGiaoDuKien",donhang.getNgayGiaoDuKien());
        long kq = db.insert("DonHang",null,values);
        if(kq<=0)
        {return -1;}
        return 1;
    }
    public int xoasp(String MaGH){
        int kq = db.delete("DonHang","MaDH=?",new String[]{MaGH});
        if(kq<=0)
        {return -1;}
        return 1;
    }
    public int update( String MaGH,DonHang donhang){
        ContentValues values = new ContentValues();
        //values.put("MaDH",donhang.getMaDH());
        values.put("MaKH",donhang.getMaKH());
        values.put("NgayDat",donhang.getNgayDat());
        values.put("NgayGiaoDuKien",donhang.getNgayGiaoDuKien());
        int kq = db.update("DonHang",values,"MaDH =?",new String[]{MaGH});
        if(kq<=0)
        {return -1;}
        return 1;
    }
    public List<DonHang> getDSSP(){
        List<DonHang> sanp= new ArrayList<>();
        String s= "SELECT * FROM DonHang";
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            DonHang hd= new DonHang();
            hd.setMaDH(c.getString(0));
            hd.setMaKH(c.getString(1));
            hd.setNgayDat(c.getString(2));
            hd.setNgayGiaoDuKien(c.getString(3));
            sanp.add(hd);
            c.moveToNext();
        }
        c.close();
        return sanp;
    }
    public ArrayList<DonHang> getDSSP1(){
        ArrayList<DonHang> sanp= new ArrayList<>();
        String s= "SELECT * FROM DonHang";
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            DonHang hd= new DonHang();
            hd.setMaDH(c.getString(0));
            hd.setMaKH(c.getString(1));
            hd.setNgayDat(c.getString(2));
            hd.setNgayGiaoDuKien(c.getString(3));
            sanp.add(hd);
            c.moveToNext();
        }
        c.close();
        return sanp;
    }
}
