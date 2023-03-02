package com.example.nhom03_quanlibanhang.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nhom03_quanlibanhang.model.MatHang;

import java.util.ArrayList;
import java.util.List;

public class MatHangDAO {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private Context context;

    public MatHangDAO(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public int themsp(MatHang nv){
        ContentValues values = new ContentValues();
        values.put("MaMH",nv.getMaMH());
        values.put("TenMH",nv.getTenMH());
        values.put("DonViTinh",nv.getDonViTinh());
        values.put("DonGia",nv.getDonGia());
        values.put("SoLuongTon",nv.getSoLuongTon());
        values.put("MaLH",nv.getMaLH());
        values.put("MaNCC",nv.getMaNCC());
        values.put("ID_a",nv.getID_a());

        long kq = db.insert("MatHang",null,values);
        if(kq<=0)
        {return -1;}
        return 1;
    }
    public int xoasp(String MaMH){
        int kq = db.delete("MatHang","MaMH =?",new String[]{MaMH});
        if(kq<=0)
        {return -1;}
        return 1;
    }
    public int update( String MaMH,MatHang nv){

        ContentValues values = new ContentValues();

        values.put("MaMH",nv.getMaMH());
        values.put("TenMH",nv.getTenMH());
        values.put("DonViTinh",nv.getDonViTinh());
        values.put("DonGia",nv.getDonGia());
        values.put("SoLuongTon",nv.getSoLuongTon());
        values.put("MaLH",nv.getMaLH());
        values.put("MaNCC",nv.getMaNCC());
        values.put("ID_a",nv.getID_a());
        int kq = db.update("MatHang",values,"MaMH =?",new String[]{MaMH});
        if(kq<=0)
        {return -1;}
        return 1;
    }
    public List<MatHang> getDSSP(){
        List<MatHang> sanp= new ArrayList<>();
        String s= "SELECT * FROM MatHang";
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            MatHang sp= new MatHang();
            sp.setMaMH(c.getString(0));
            sp.setTenMH(c.getString(1));
            sp.setDonViTinh(c.getString(2));
            sp.setDonGia(c.getFloat(3));
            sp.setSoLuongTon(Integer.parseInt(c.getString(4)));
            sp.setMaLH(c.getString(5));
            sp.setMaNCC(c.getString(6));
            sp.setID_a(c.getInt(7));
            sanp.add(sp);
            c.moveToNext();
        }
        c.close();
        return sanp;
    }
    public MatHang getDSSP_mamh(String MaMH){
        String s= "SELECT * FROM MatHang where MaMH ='"+MaMH+"'";
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
            MatHang sp= new MatHang();
            sp.setMaMH(c.getString(0));
            sp.setTenMH(c.getString(1));
            sp.setDonViTinh(c.getString(2));
            sp.setDonGia(c.getFloat(3));
            sp.setSoLuongTon(Integer.parseInt(c.getString(4)));
            sp.setMaLH(c.getString(5));
            sp.setMaNCC(c.getString(6));
            sp.setID_a(c.getInt(7));
        c.close();
        return sp;
    }
    public ArrayList<MatHang> getDSSP1(){
        ArrayList<MatHang> sanp= new ArrayList<>();
        String s= "SELECT * FROM MatHang";
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            MatHang sp= new MatHang();
            sp.setMaMH(c.getString(0));
            sp.setTenMH(c.getString(1));
            sp.setDonViTinh(c.getString(2));
            sp.setDonGia(c.getFloat(3));
            sp.setSoLuongTon(Integer.parseInt(c.getString(4)));
            sp.setMaLH(c.getString(5));
            sp.setMaNCC(c.getString(6));
            sp.setID_a(c.getInt(7));
            sanp.add(sp);
            c.moveToNext();
        }
        c.close();
        return sanp;
    }
}
