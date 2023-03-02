package com.example.nhom03_quanlibanhang.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nhom03_quanlibanhang.model.KhachHang;

import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private Context context;

    public KhachHangDAO(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public int themkh(KhachHang kh){
        ContentValues values = new ContentValues();
        values.put("MaKH", kh.getMaKH());
        values.put("HoTenKH",kh.getHoTenKH());
        values.put("NgaySinh",kh.getNgaySinh());
        values.put("DiaChi",kh.getDiaChi());
        values.put("SdtKH",kh.getSdtKH());
        values.put("pass",kh.getPass());
        long kq = db.insert("KhachHang",null,values);
        if(kq<=0)
        {return -1;}
        return 1;
    }
    public int xoakh(String MaKH){
        int kq = db.delete("KhachHang","MaKH =?",new String[]{MaKH});
        if(kq<=0)
        {return -1;}
        return 1;
    }
    public int update( String MaKH,KhachHang kh){
        ContentValues values = new ContentValues();
        //values.put("MaKH", kh.getMaKH());
        values.put("HoTenKH",kh.getHoTenKH());
        values.put("NgaySinh",kh.getNgaySinh());
        values.put("DiaChi",kh.getDiaChi());
        values.put("SdtKH",kh.getSdtKH());
        values.put("pass",kh.getPass());
        int kq = db.update("KhachHang",values,"MaKH =?",new String[]{MaKH});
        if(kq<=0)
        {return -1;}
        return 1;
    }
    public KhachHang info_kh(String sdt){
        KhachHang kh = new KhachHang();
        String s= "SELECT * FROM KhachHang where SdtKH ='"+sdt+"'";
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
        kh.setMaKH(c.getString(0));
        kh.setHoTenKH(c.getString(1));
        kh.setNgaySinh(c.getString(2));
        kh.setDiaChi(c.getString(3));
        kh.setSdtKH(c.getString(4));
        kh.setPass(c.getString(5));
        c.close();
        return kh;
    }
    public KhachHang info_kh1(String MaKH){
        KhachHang kh = new KhachHang();
        String s= "SELECT * FROM KhachHang where MaKH ='"+MaKH+"'";
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
        kh.setMaKH(c.getString(0));
        kh.setHoTenKH(c.getString(1));
        kh.setNgaySinh(c.getString(2));
        kh.setDiaChi(c.getString(3));
        kh.setSdtKH(c.getString(4));
        kh.setPass(c.getString(5));
        c.close();
        return kh;
    }
    public ArrayList<KhachHang> getDSSP(){
        ArrayList<KhachHang> sanp= new ArrayList<KhachHang>();
        String s= "SELECT * FROM KhachHang";
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            KhachHang sp= new KhachHang();
            sp.setMaKH(c.getString(0));
            sp.setHoTenKH(c.getString(1));
            sp.setNgaySinh(c.getString(2));
            sp.setDiaChi(c.getString(3));
            sp.setSdtKH(c.getString(4));
            sp.setPass(c.getString(5));
            sanp.add(sp);
            c.moveToNext();
        }
        c.close();
        return sanp;
    }
    public List<String> getManv(){
        List<String> manv= new ArrayList<String>();
        String s= "SELECT * FROM NHANVIEN";
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            String sp ="";
            sp = String.valueOf(c.getInt(0));
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
            sp = String.valueOf(c.getInt(0));
            manv.add(sp);
            c.moveToNext();
        }
        c.close();
        return manv;
    }
}
