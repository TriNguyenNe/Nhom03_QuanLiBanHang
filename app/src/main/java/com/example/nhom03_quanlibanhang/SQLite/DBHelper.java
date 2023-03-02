package com.example.nhom03_quanlibanhang.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.nhom03_quanlibanhang.MainActivity;
import com.example.nhom03_quanlibanhang.R;
import com.example.nhom03_quanlibanhang.model.MatHang;
import com.example.nhom03_quanlibanhang.model.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "DataQLBHVM.db";
    public static final String DBLOCATION = "/data/data/com.example.nhom03_quanlibanhang/databases/";
    private Context mContext;
    private SQLiteDatabase mDatabase;
    public DBHelper(@Nullable Context context) {
        super(context, DBNAME, null, 1);
        this.mContext = context;
    }
    private String sql_giohang="Create table GioHang ( MaKH text not null, MaMH text not null,TenMH text,SoLuong int,DonGia Double,id_a int,primary key(MaKH,MaMH))";
    //private String sql_giohang= "Drop table GioHang";
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(sql_giohang);
        }
        catch (Exception e){}
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public void openDatabase() {
        String dbPath = mContext.getDatabasePath(DBNAME).getPath();
        if (mDatabase != null && mDatabase.isOpen()) {
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READWRITE);
    }

    public ArrayList<MatHang> getListProduct() {
        MatHang product = null;
        ArrayList<MatHang> productList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM MatHang", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            product = new MatHang(cursor.getString(0), cursor.getString(1), cursor.getString(2), cursor.getFloat(3),
                    cursor.getInt(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7));
            productList.add(product);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return productList;
    }
    public void closeDatabase() {
        if (mDatabase != null) {
            mDatabase.close();
        }
    }
    public void update_a_mathang(SQLiteDatabase mDatabase){
        String s1 = "update MatHang set Id_a =" + R.drawable.cg3fk + " where MaMH ='CG3FK'" ;
        String s2 = "update MatHang set Id_a =" + R.drawable.pukd3f+ " where MaMH ='PUKD3F'" ;
        String s27 = "update MatHang set Id_a =" + R.drawable.cbst+ " where MaMH ='CBST'" ;
        String s3 = "update MatHang set Id_a =" + R.drawable.tg3s+ " where MaMH ='TG3S'" ;
        String s4 = "update MatHang set Id_a =" + R.drawable.sttdmcd+ " where MaMH ='STTDMCD'" ;
        String s5 = "update MatHang set Id_a =" + R.drawable.sttvcd+ " where MaMH ='STTVCD'" ;
        String s6 = "update MatHang set Id_a =" + R.drawable.sttcghl+" where MaMH ='STTCGHL'" ;
        String s7 = "update MatHang set Id_a =" + R.drawable.bhdx+ " where MaMH ='BHDX'" ;
        String s8 = "update MatHang set Id_a =" + R.drawable.cswe+ " where MaMH ='CXWE'" ;
        String s9 = "update MatHang set Id_a =" + R.drawable.mh + " where MaMH ='MH'" ;
        String s10 = "update MatHang set Id_a =" + R.drawable.ht + " where MaMH ='HT'" ;
        String s11= "update MatHang set Id_a =" + R.drawable.dcwe + " where MaMH ='DCWE'" ;
        String s12= "update MatHang set Id_a =" + R.drawable.dhbm + " where MaMH ='DHHMN'" ;
        String s13= "update MatHang set Id_a =" + R.drawable.ctddvk + " where MaMH ='CTDDVK'" ;
        String s14= "update MatHang set Id_a =" + R.drawable.hctpct +" where MaMH ='HCTPCT'" ;
        String s15= "update MatHang set Id_a =" + R.drawable.ktglm +" where MaMH ='KTGLM'" ;
        String s16= "update MatHang set Id_a =" + R.drawable.xxdlg + " where MaMH ='XXDLG'" ;
        String s17= "update MatHang set Id_a =" + R.drawable.kcctclok + " where MaMH ='KCCTCLOK'" ;
        String s28 = "update MatHang set Id_a =" + R.drawable.sccd +" where MaMH ='SCCD'" ;
        String s18 = "update MatHang set Id_a =" + R.drawable.kx + " where MaMH ='KX'" ;
        String s19= "update MatHang set Id_a =" + R.drawable.koq +" where MaMH ='KOQ'" ;
       String s20= "update MatHang set Id_a =" + R.drawable.stko + " where MaMH ='STKO'" ;
        String s21 = "update MatHang set Id_a =" + R.drawable.bkshpm +" where MaMH ='BKSHPM'";
        String s22 = "update MatHang set Id_a =" + R.drawable.kgukm +  " where MaMH ='KGUKM'" ;
        String s23 = "update MatHang set Id_a =" + R.drawable.dgpmm +  " where MaMH ='DGPMM'" ;
        String s24 = "update MatHang set Id_a =" + R.drawable.ngkhcf + " where MaMH ='NGKHCF'" ;
        String s25 = "update MatHang set Id_a =" + R.drawable.ntlshd + " where MaMH ='NTLSHD'" ;
        String s26 = "update MatHang set Id_a =" + R.drawable.ngksl +  " where MaMH ='NGKSL'" ;
        String s[]={s1,s2,s3,s4,s5,s6,s7,s8,s9,s10,s11,s12,s13,s14,s15
        ,s16,s17,s18,s19,s20,s21,s22,s23,s24,s25,s26,s27,s28};
        for(int i=0;i<28;i++){
            mDatabase.execSQL(s[i]);
        }
    }
}
