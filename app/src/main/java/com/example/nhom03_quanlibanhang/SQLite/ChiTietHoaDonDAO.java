package com.example.nhom03_quanlibanhang.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nhom03_quanlibanhang.model.ChiTietHoaDon;

import java.util.ArrayList;
import java.util.List;

public class ChiTietHoaDonDAO {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private Context context;
    public ChiTietHoaDonDAO(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public int themsp(ChiTietHoaDon hd){
        ContentValues values = new ContentValues();
        values.put("MaHD",hd.getMaHD());
        values.put("MaMH",hd.getMaMH());
        values.put("SoLuong",hd.getSoLuong());
        values.put("DonGia",hd.getDonGia());

        long kq = db.insert("ChiTietHD",null,values);
        if(kq<=0)
        {return -1;}
        return 1;
    }
    public int xoasp(String MaHD){
        int kq = db.delete("ChiTietHD","MaHD =?",new String[]{MaHD});
        if(kq<=0)
        {return -1;}
        return 1;
    }
    public int xoasp_hd(String MaHD,String MaMH){
        int kq = db.delete("ChiTietHD","MaHD =? and MaMH =?",new String[]{MaHD,MaMH});
        if(kq<=0)
        {return -1;}
        return 1;
    }
    public int update( String MaHD,String MaMH,ChiTietHoaDon hd){

        ContentValues values = new ContentValues();
        values.put("MaHD",hd.getMaHD());
        values.put("MaMH",hd.getMaMH());
        values.put("SoLuong",hd.getSoLuong());
        values.put("DonGia",hd.getDonGia());

        int kq = db.update("ChiTietHD",values,"MaHD =? and MaMH",new String[]{MaHD,MaMH});
        if(kq<=0)
        {return -1;}
        return 1;
    }
    public List<ChiTietHoaDon> getDSSP(String MaKH){
        List<ChiTietHoaDon> sanp= new ArrayList<>();
        String s= "SELECT c.MaHD,MaMH,SoLuong,DonGia FROM ChiTietHD c join HoaDon h on h.MaHD = c.MaHD " +
                "join DonHang d on d.MaDH = h.MaDH where MaKH ='"+MaKH+"'";
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            ChiTietHoaDon hd= new ChiTietHoaDon();
            hd.setMaHD(c.getString(0));
            hd.setMaMH(c.getString(1));
            hd.setSoLuong(c.getInt(2));
            hd.setDonGia(c.getFloat(3));
            sanp.add(hd);
            c.moveToNext();
        }
        c.close();
        return sanp;
    }
    public ArrayList<ChiTietHoaDon> getDSSP1(String MaHD){
        ArrayList<ChiTietHoaDon> sanp= new ArrayList<>();
        String s= "SELECT* FROM ChiTietHD  where MaHD ='"+MaHD+"'";
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            ChiTietHoaDon hd= new ChiTietHoaDon();
            hd.setMaHD(c.getString(0));
            hd.setMaMH(c.getString(1));
            hd.setSoLuong(c.getInt(2));
            hd.setDonGia(c.getFloat(3));
            sanp.add(hd);
            c.moveToNext();
        }
        c.close();
        return sanp;
    }
}
