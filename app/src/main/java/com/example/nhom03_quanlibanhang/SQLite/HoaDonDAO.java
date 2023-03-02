package com.example.nhom03_quanlibanhang.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nhom03_quanlibanhang.model.HoaDon;

import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private Context context;
    public HoaDonDAO(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public int themsp(HoaDon hd){
        ContentValues values = new ContentValues();
        //values.put("MaHD",hd.getMaHD());
        values.put("MaNV",hd.getMaNV());
        values.put("MaDH",hd.getMaGH());
        values.put("NgayLapHD",hd.getNgayLapHD());
        values.put("NgayGiaoThucte",hd.getNgayGiaoThucte());
        values.put("TongTien",hd.getTongTien());
        long kq = db.insert("HoaDon",null,values);
        if(kq<=0)
        {return -1;}
        return 1;
    }
    public int xoasp(String MaHD){
        int kq = db.delete("HoaDon","MaHD =?",new String[]{MaHD});
        if(kq<=0)
        {return -1;}
        return 1;
    }
    public int update( String MaHD,HoaDon hd){

        ContentValues values = new ContentValues();
        //values.put("MaHD",hd.getMaHD());
        values.put("MaNV",hd.getMaNV());
        values.put("MaDH",hd.getMaHD());
        values.put("NgayLapHD",hd.getNgayLapHD());
        values.put("NgayGiaoThucTe",hd.getNgayGiaoThucte());
        values.put("TongTien",hd.getTongTien());
        int kq = db.update("HoaDon",values,"MaHD =?",new String[]{MaHD});
        if(kq<=0)
        {return -1;}
        return 1;
    }
    public ArrayList<HoaDon> getDSSP(){
        ArrayList<HoaDon> sanp= new ArrayList<>();
        String s= "SELECT * FROM HoaDon";
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            HoaDon hd= new HoaDon();
            hd.setMaHD(c.getString(0));
            hd.setMaNV(c.getString(1));
            hd.setMaGH(c.getString(2));
            hd.setNgayLapHD(c.getString(3));
            hd.setNgayGiaoThucte(c.getString(4));
            hd.setTongTien(c.getFloat(5));
            sanp.add(hd);
            c.moveToNext();
        }
        c.close();
        return sanp;
    }
}
