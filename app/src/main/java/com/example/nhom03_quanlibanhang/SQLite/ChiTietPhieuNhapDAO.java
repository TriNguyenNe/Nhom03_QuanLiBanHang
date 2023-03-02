package com.example.nhom03_quanlibanhang.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nhom03_quanlibanhang.model.ChiTietPhieuNhap;

import java.util.ArrayList;
import java.util.List;

public class ChiTietPhieuNhapDAO {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private Context context;
    public ChiTietPhieuNhapDAO(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public int themsp(ChiTietPhieuNhap hd){
        ContentValues values = new ContentValues();
        values.put("MaPN",hd.getMaPN());
        values.put("MaMH",hd.getMaMH());
        values.put("SoLuongNhap",hd.getSoLuongNhap());
        values.put("DgNhap",hd.getDgNhap());

        long kq = db.insert("CTPhieuNhap",null,values);
        if(kq<=0)
        {return -1;}
        return 1;
    }
    public int xoasp(String MaPN){
        int kq = db.delete("CTPhieuNhap","MaPN =?",new String[]{MaPN});
        if(kq<=0)
        {return -1;}
        return 1;
    }
    public int xoasp_hd(String MaPN,String MaMH){
        int kq = db.delete("CTPhieuNhap","MaPN =? and MaMH=?",new String[]{MaPN,MaMH});
        if(kq<=0)
        {return -1;}
        return 1;
    }
    public int update( String MaPN,String MaMH,ChiTietPhieuNhap hd){

        ContentValues values = new ContentValues();
        values.put("MaPN",hd.getMaPN());
        values.put("MaMH",hd.getMaMH());
        values.put("SoLuongNhap",hd.getSoLuongNhap());
        values.put("DgNhap",hd.getDgNhap());

        int kq = db.update("CTPhieuNhap",values,"MaPN =? and MaMH =?",new String[]{MaPN,MaMH});
        if(kq<=0)
        {return -1;}
        return 1;
    }
    public ArrayList<ChiTietPhieuNhap> getDSSP(String MaPN){
        ArrayList<ChiTietPhieuNhap> sanp= new ArrayList<>();
        String s= "SELECT * FROM CTPhieuNhap where MaPN ='"+MaPN+"'";
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            ChiTietPhieuNhap hd= new ChiTietPhieuNhap();
            hd.setMaPN(c.getString(0));
            hd.setMaMH(c.getString(1));
            hd.setSoLuongNhap(c.getInt(2));
            hd.setDgNhap(c.getFloat(3));

            sanp.add(hd);
            c.moveToNext();
        }
        c.close();
        return sanp;
    }
}
