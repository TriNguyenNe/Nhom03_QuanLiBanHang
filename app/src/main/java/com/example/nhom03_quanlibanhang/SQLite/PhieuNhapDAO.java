package com.example.nhom03_quanlibanhang.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nhom03_quanlibanhang.model.PhieuNhap;

import java.util.ArrayList;
import java.util.List;

public class PhieuNhapDAO {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private Context context;
    public PhieuNhapDAO(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public int themsp(PhieuNhap hd){
        ContentValues values = new ContentValues();
        values.put("MaPN",hd.getMaPN());
        values.put("MaNV",hd.getMaNV());
        values.put("MaNCC",hd.getMaNCC());
        values.put("NgayLap",hd.getNgayLap());

        long kq = db.insert("PhieuNhap",null,values);
        if(kq<=0)
        {return -1;}
        return 1;
    }
    public int xoasp(String MaPN){
        int kq = db.delete("PhieuNhap","MaPN =?",new String[]{MaPN});
        if(kq<=0)
        {return -1;}
        return 1;
    }
    public int update( String MaPN,PhieuNhap hd){

        ContentValues values = new ContentValues();
        //values.put("MaPN",hd.getMaPN());
        values.put("MaNV",hd.getMaNV());
        values.put("MaNCC",hd.getMaNCC());
        values.put("NgayLap",hd.getNgayLap());
        int kq = db.update("PhieuNhap",values,"MaPN =?",new String[]{MaPN});
        if(kq<=0)
        {return -1;}
        return 1;
    }
    public ArrayList<PhieuNhap> getDSSP(){
        ArrayList<PhieuNhap> sanp= new ArrayList<>();
        String s= "SELECT * FROM PhieuNhap";
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            PhieuNhap hd= new PhieuNhap();
            hd.setMaPN(c.getString(0));
            hd.setMaNV(c.getString(1));
            hd.setMaNCC(c.getString(2));
            hd.setNgayLap(c.getString(3));

            sanp.add(hd);
            c.moveToNext();
        }
        c.close();
        return sanp;
    }
}
