package com.example.nhom03_quanlibanhang;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhom03_quanlibanhang.Activity_KhachHang.TrangChuKhachHang;
import com.example.nhom03_quanlibanhang.Activity_NhanVien.TrangChuNhanVien;
import com.example.nhom03_quanlibanhang.Adapter.Adapter_NhanVien;
import com.example.nhom03_quanlibanhang.SQLite.DBHelper;
import com.example.nhom03_quanlibanhang.SQLite.NhanVienDAO;
import com.example.nhom03_quanlibanhang.model.KhachHang;
import com.example.nhom03_quanlibanhang.model.MatHang;
import com.example.nhom03_quanlibanhang.model.NhanVien;
import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    Button dangnhap,huy;
    ListView lv;
    TextView t,dangki ;
    TextInputLayout user, pass;
    RadioButton khachahng, nhanvien;
    public static String username ="";
    public static String username_nhanvien="";
    public static String chuvu="";
    private ListView lvProduct;
    private List<MatHang> mProductList;
    private DBHelper mDBHelper;
    private Adapter_NhanVien adapter_nv;
    NhanVienDAO nhanVienDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDBHelper = new DBHelper(this);
        File database = getApplicationContext().getDatabasePath(DBHelper.DBNAME);
        if(false == database.exists()) {
            mDBHelper.getReadableDatabase();
            //Copy db
            if(copyDatabase(this)) {
                Toast.makeText(this, "Copy database succes", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Copy data error", Toast.LENGTH_SHORT).show();
                return;
            }
        }
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
       mDBHelper.update_a_mathang(db);
        try{
           mDBHelper.onCreate(db);
        }catch (Exception e){}
        thamchieu();
        huy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user.getEditText().setText("");
                pass.getEditText().setText("");
            }
        });
        dangnhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user.getEditText().getText().toString().equals(""))
                    Toast.makeText(getApplicationContext(),"Bạn cần điền tên đăng nhập",Toast.LENGTH_SHORT).show();
                else {
                    if(nhanvien.isChecked()==false && khachahng.isChecked()==false)
                        Toast.makeText(getApplicationContext(),"Bạn là nhân viên hay khách hàng",Toast.LENGTH_SHORT).show();
                    else{
                            if (khachahng.isChecked())
                                dangnhap();
                            if (nhanvien.isChecked())
                                dangnhap_nv();
                    }
                }
            }
        });
        dangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,DangKi.class);
                startActivity(i);

            }
        });
    }
    public boolean ktds(String ten,String matkhau){
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        List<KhachHang> sanp= new ArrayList<KhachHang>();
        String s= "SELECT * FROM KhachHang where SdtKH = '"+ten+"' and pass='"+matkhau+"'";
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
        if(sanp.size()==0){
            return false;
        }else
            return true;
    }
    public boolean ktds_nv(String ten,String matkhau){
        SQLiteDatabase db = mDBHelper.getReadableDatabase();
        List<NhanVien> sanp= new ArrayList<NhanVien>();
        String s= "SELECT * FROM NhanVien where MaNV = '"+ten+"' and MatKhau='"+matkhau+"'";
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
            chuvu = c.getString(8);
            sanp.add(sp);
            c.moveToNext();
        }
        c.close();
        if(sanp.size()==0){
            return false;
        }else
            return true;
    }
    public void dangnhap(){
       String tendn = user.getEditText().getText().toString();
       String mk= pass.getEditText().getText().toString();
       if(ktds(tendn,mk)==true){
           Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
           Intent i = new Intent(this, TrangChuKhachHang.class);
           username =tendn;
           startActivity(i);
       }
       else
           Toast.makeText(this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();

    }
    public void dangnhap_nv(){
        String tendn = user.getEditText().getText().toString();
        String mk= pass.getEditText().getText().toString();
        if(ktds_nv(tendn,mk)==true){
            Toast.makeText(this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(this, TrangChuNhanVien.class);
            username_nhanvien =tendn;
            nhanVienDAO= new NhanVienDAO(this);
            NhanVien a = new NhanVien();
            a = nhanVienDAO.info_kh( username_nhanvien);
            chuvu = a.getChucVu();
            startActivity(i);
        }
        else
            Toast.makeText(this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();

    }
    public  void thamchieu(){
        dangki = findViewById(R.id.txtDk);
        user = findViewById(R.id.textintputten);
        pass = findViewById(R.id.textintputpass);
        nhanvien = findViewById(R.id.rdbNV);
        khachahng = findViewById(R.id.rdbKH);
        dangnhap= findViewById(R.id.btndangnhap);
        huy  = findViewById(R.id.btnHuy);
    }
    private boolean copyDatabase(Context context) {
        try {
            InputStream inputStream = context.getAssets().open(DBHelper.DBNAME);
            String outFileName = DBHelper.DBLOCATION + DBHelper.DBNAME;
            OutputStream outputStream = new FileOutputStream(outFileName);
            byte[]buff = new byte[1024];
            int length = 0;
            while ((length = inputStream.read(buff)) > 0) {
                outputStream.write(buff, 0, length);
            }
            outputStream.flush();
            outputStream.close();
            Log.w("MainActivity","DB copied");
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}