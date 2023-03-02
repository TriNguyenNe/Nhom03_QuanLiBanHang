package com.example.nhom03_quanlibanhang.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.nhom03_quanlibanhang.model.KhachHang;
import com.example.nhom03_quanlibanhang.model.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private Context context;

    public NhanVienDAO(Context context) {
        this.context = context;
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }
    public int themsp(NhanVien nv){
        ContentValues values = new ContentValues();
        values.put("MaNV",nv.getMaNV());
        values.put("HoTenNV",nv.getHoTenNV());
        values.put("NgaySinh",nv.getNgaySinh());
        values.put("DiaChi",nv.getDiaChi());
        values.put("GioiTinh",nv.getGioiTinh());
        values.put("SdtNV",nv.getSdtNV());
        values.put("EmailNV",nv.getEmailNV());
        values.put("MatKhau",nv.getMatKhau());
        values.put("ChucVu",nv.getChucVu());
        long kq = db.insert("NhanVien",null,values);
        if(kq<=0)
        {return -1;}
        return 1;
    }
    public int xoasp(String MaNV){
        int kq = db.delete("NhanVien","MaNV =?",new String[]{MaNV});
        if(kq<=0)
        {return -1;}
        return 1;
    }
    public int update( String MaNV,NhanVien nv){

        ContentValues values = new ContentValues();
        //values.put("MaNV",nv.getMaNV());
        values.put("HoTenNV",nv.getHoTenNV());
        values.put("NgaySinh",nv.getNgaySinh());
        values.put("DiaChi",nv.getDiaChi());
        values.put("GioiTinh",nv.getGioiTinh());
        values.put("SdtNV",nv.getSdtNV());
        values.put("EmailNV",nv.getEmailNV());
        values.put("MatKhau",nv.getMatKhau());
        values.put("ChucVu",nv.getChucVu());
        int kq = db.update("NhanVien",values,"MaNV =?",new String[]{MaNV});
        if(kq<=0)
        {return -1;}
        return 1;
    }
    public ArrayList<NhanVien> getDSSP(){
        ArrayList<NhanVien> sanp= new ArrayList<NhanVien>();
        String s= "SELECT * FROM NhanVien";
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
        while (c.isAfterLast()==false){
            NhanVien sp= new NhanVien();
            sp.setMaNV(c.getString(0));
            sp.setHoTenNV(c.getString(1));
            sp.setNgaySinh(c.getString(2));
            sp.setDiaChi(c.getString(3));
            sp.setGioiTinh(c.getString(4));
            sp.setSdtNV(c.getString(5));
            sp.setEmailNV(c.getString(6));
            sp.setMatKhau(c.getString(7));
            sp.setChucVu(c.getString(8));
            sanp.add(sp);
            c.moveToNext();
        }
        c.close();
        return sanp;
    }
    public NhanVien info_kh(String MaNV){
        NhanVien kh = new NhanVien();
        String s= "SELECT * FROM NhanVien where MaNV ='"+MaNV+"'";
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
        kh.setMaNV(c.getString(0));
        kh.setHoTenNV(c.getString(1));
        kh.setNgaySinh(c.getString(2));
        kh.setDiaChi(c.getString(3));
        kh.setGioiTinh(c.getString(4));
        kh.setSdtNV(c.getString(5));
        kh.setEmailNV(c.getString(6));
        kh.setMatKhau(c.getString(7));
        kh.setChucVu(c.getString(8));
        c.close();
        return kh;
    }

}
