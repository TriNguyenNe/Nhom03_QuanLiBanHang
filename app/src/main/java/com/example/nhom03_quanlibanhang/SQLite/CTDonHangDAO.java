package com.example.nhom03_quanlibanhang.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nhom03_quanlibanhang.model.CTDonHang;

import java.util.ArrayList;
import java.util.List;

public class CTDonHangDAO {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private Context context;
    public CTDonHangDAO(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public int themsp(CTDonHang hd){
        ContentValues values = new ContentValues();
        values.put("MaDH",hd.getMaDH());
        values.put("MaMH",hd.getMaMH());
        values.put("SoLuong",hd.getSoLuong());
        values.put("DgBan",hd.getDgBan());

        long kq = db.insert("CTDonHang",null,values);
        if(kq<=0)
        {return -1;}
        return 1;
    }
    public int xoasp(String MaGH,String MaMH){
        int kq = db.delete("CTDonHang","MaDH=? and MaMH=?",new String[]{MaGH,MaMH});
        if(kq<=0)
        {return -1;}
        return 1;
    }
    public int update( String MaGH,String MaMH,CTDonHang hd){
        ContentValues values = new ContentValues();
        values.put("SoLuong",hd.getSoLuong());
        values.put("DgBan",hd.getDgBan());
        int kq = db.update("CTDonHang",values,"MaDH=? and MaMH=?",new String[]{MaGH,MaMH});
        if(kq<=0)
        {return -1;}
        return 1;
    }
    public List<CTDonHang> getDSSP(String MaKH){
        List<CTDonHang> sanp= new ArrayList<>();
        String s= "SELECT d.MaDH,MaMH,SoLuong,DgBan FROM CTDonHang c join DonHang d on c.MaDH= d.MaDH where MaKH ='"+MaKH+"'";
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            CTDonHang hd= new CTDonHang();
            hd.setMaGH(c.getString(0));
            hd.setMaMH(c.getString(1));
            hd.setSoLuong(c.getInt(2));
            hd.setDgBan(c.getFloat(3));
            sanp.add(hd);
            c.moveToNext();
        }
        c.close();
        return sanp;
    }
    public ArrayList<CTDonHang> getDSSP1(String MaDH){
        ArrayList<CTDonHang> sanp= new ArrayList<>();
        String s= "SELECT * FROM CTDonHang where MaDH='"+MaDH+"'";
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            CTDonHang hd= new CTDonHang();
            hd.setMaGH(c.getString(0));
            hd.setMaMH(c.getString(1));
            hd.setSoLuong(c.getInt(2));
            hd.setDgBan(c.getFloat(3));
            sanp.add(hd);
            c.moveToNext();
        }
        c.close();
        return sanp;
    }
}
