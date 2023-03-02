package com.example.nhom03_quanlibanhang.GioHang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nhom03_quanlibanhang.SQLite.DBHelper;
import com.example.nhom03_quanlibanhang.model.MatHang;

import java.util.ArrayList;
import java.util.List;

public class GioHangDAO {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private Context context;

    public GioHangDAO(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public int themsp(GioHang sp){
        ContentValues values = new ContentValues();
        values.put("MaKH", sp.getMaKH());
        values.put("MaMH",sp.getMaMH());
        values.put("TenMH",sp.getTenMH());
        values.put("SoLuong",sp.getSoluong());
        values.put("DonGia",sp.getDonGia());
        values.put("id_a",sp.getId_a());
        long kq = db.insert("GioHang",null,values);
        if(kq<=0)
        {return -1;}
        return 1;
    }
    public int xoasp(String MaMH){
        int kq = db.delete("GioHang","MaMH =?",new String[]{MaMH});
        if(kq<=0)
        {return -1;}
        return 1;
    }
    public int update(String MaMH,String MaKH, GioHang sp){

        ContentValues values = new ContentValues();
        //values.put("MaMH",sp.getMaMH());
        values.put("TenMH",sp.getTenMH());
        values.put("SoLuong",sp.getSoluong());
        values.put("DonGia",sp.getDonGia());
        values.put("id_a",sp.getId_a());
        int kq = db.update("GioHang",values,"MaMH =? and MaKH =?",new String[]{MaMH,MaKH});
        if(kq<=0)
        {return -1;}
        return 1;
    }
    public ArrayList<GioHang> getDSSP(String makh){
        ArrayList<GioHang> sanp= new ArrayList<GioHang>();
        String s= "SELECT * FROM GioHang where MaKH='"+makh+"'";
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            GioHang sp= new GioHang();
            sp.setMaMH(c.getString(1));
            sp.setTenMH(c.getString(2));
            sp.setSoluong(c.getInt(3));
            sp.setDonGia(c.getDouble(4));
            sp.setId_a(c.getInt(5));
            sanp.add(sp);
            c.moveToNext();
        }
        c.close();
        return sanp;
    }
}
