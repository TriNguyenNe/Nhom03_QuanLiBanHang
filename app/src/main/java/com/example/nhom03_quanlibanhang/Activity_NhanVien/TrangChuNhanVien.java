package com.example.nhom03_quanlibanhang.Activity_NhanVien;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.nhom03_quanlibanhang.MainActivity;
import com.example.nhom03_quanlibanhang.R;
import com.example.nhom03_quanlibanhang.SQLite.DBHelper;
import com.example.nhom03_quanlibanhang.SQLite.NhanVienDAO;
import com.example.nhom03_quanlibanhang.model.NhanVien;

public class TrangChuNhanVien extends AppCompatActivity {
    private DrawerLayout drLayout;
    NhanVienDAO nhanVienDAO;
    CardView donhang,hoadon,phieunhap,nhanvien,khachhang,sanpham,doipass,thongke,dangxuat;
    TextView ten,email,title;
    ImageView img;
    fr_khachhang fr_khachhang = new fr_khachhang();
    fr_nhanvien fr_nhanvien = new fr_nhanvien();
    fr_sanpham fr_sanpham = new fr_sanpham();
    fr_dondathang fr_dondathang= new fr_dondathang();
    fr_hoadon fr_hoadon= new fr_hoadon();
    fr_phieunhap fr_phieunhap= new fr_phieunhap();
    fr_thongke fr_thongke = new fr_thongke();
    fr_thongtinnhanvien fr_thongtinnhanvien = new fr_thongtinnhanvien();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu_nhan_vien);
        thamchieu();
        Toolbar toolbar1 = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar1);
        toolbar1.setNavigationIcon(R.drawable.itemnav);
        drLayout= findViewById(R.id.drawerlayout);
        ten= findViewById(R.id.txtht);
        title = findViewById(R.id.txttitle);
        title.setText("ĐƠN ĐẶT HÀNG");
        getSupportFragmentManager().beginTransaction().replace(R.id.container,fr_dondathang).commit();
        email=findViewById(R.id.txtemail);
        show_info();
        toolbar1.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drLayout.openDrawer(GravityCompat.START);
            }
        });
       // tennv.setText(MainActivity.chuvu);
        donhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container,fr_dondathang).commit();
                drLayout.closeDrawers();
                title.setText("ĐƠN ĐẶT HÀNG");
            }
        });
        hoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container,fr_hoadon).commit();
                drLayout.closeDrawers();
                title.setText("HÓA ĐƠN BÁN HÀNG");
            }
        });
        phieunhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container,fr_phieunhap).commit();
                drLayout.closeDrawers();
                title.setText("PHIẾU NHẬP HÀNG");
            }
        });
        nhanvien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.chuvu.equals("QuanLi"))
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container,fr_nhanvien).commit();
                    drLayout.closeDrawers();
                    title.setText("QL NHÂN VIÊN");
                }
                else
                    Toast.makeText(getApplicationContext(),"Bạn không có quyền thực hiện chức năng này",Toast.LENGTH_SHORT).show();
            }
        });
        khachhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container,fr_khachhang).commit();
                drLayout.closeDrawers();
                title.setText("QL KHÁCH HÀNG");
            }
        });
        sanpham.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container,fr_sanpham).commit();
                drLayout.closeDrawers();title.setText("QL SẢN PHẨM");
            }
        });
        doipass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeedDoiPass();
            }
        });
        thongke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container,fr_thongke).commit();
                drLayout.closeDrawers();
                title.setText("THỐNG KÊ DOANH THU");
            }
        });
        dangxuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert_show();
            }
        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction().replace(R.id.container,fr_thongtinnhanvien).commit();
                drLayout.closeDrawers();
                title.setText("THÔNG TIN NHÂN VIÊN");
            }
        });
    }
    public void show_info(){
        nhanVienDAO= new NhanVienDAO(this);
        NhanVien a= new NhanVien();
        a = nhanVienDAO.info_kh(MainActivity.username_nhanvien);
        ten.setText(a.getHoTenNV());
        email.setText(a.getEmailNV());
    }
    private void setSupportActionBar(Toolbar toolbar1) {
    }
    public void alert_show(){
        AlertDialog.Builder mydialog = new AlertDialog.Builder(this);
        mydialog.setTitle("Xác nhận");
        mydialog.setMessage("Bạn có chắc chắn muốn thoát");
        mydialog.setCancelable(false);
        mydialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TrangChuNhanVien.this.finish();
            }
        });
        mydialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                mydialog.setCancelable(false);
            }
        });
        AlertDialog alert = mydialog.create();
        alert.show();

    }
    public void thamchieu(){
       // tennv= findViewById(R.id.);
        donhang = findViewById(R.id.card_ddh);
        hoadon = findViewById(R.id.card_hoadon);
        phieunhap= findViewById(R.id.card_pn);
        nhanvien = findViewById(R.id.card_nv);
        khachhang = findViewById(R.id.card_kh);
        sanpham = findViewById(R.id.card_sp);
        doipass = findViewById(R.id.carddoipass);
        thongke = findViewById(R.id.cart_thognke);
        dangxuat = findViewById(R.id.cart_dx);
        img = findViewById(R.id.image1);
    }
    public void openFeedDoiPass(){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.doimatkhau);
        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity= windowAttributes.gravity;
        window.setGravity(Gravity.BOTTOM);
        window.setAttributes(windowAttributes);
        if(Gravity.BOTTOM == windowAttributes.gravity){
            dialog.setCancelable(true);
        }
        else
            dialog.setCancelable(false);
        EditText matkhaumoi = dialog.findViewById(R.id.edtMaKhauMoi);
        EditText MatKhauxn = dialog.findViewById(R.id.edtXacNhanPass);
        Button btnluu = dialog.findViewById(R.id.btnLuu);
        Button btnhuy = dialog.findViewById(R.id.btnHuy);
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(TrangChuNhanVien.this);
                dbHelper.openDatabase();
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                String update = "Update NhanVien set MatKhau='"+matkhaumoi.getText().toString()+"'where MaNV= '"+MainActivity.username_nhanvien+"'";
                try{
                    db.execSQL(update);
                    Toast.makeText(getApplicationContext(),"Đổi mật khẩu thành công",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(getApplicationContext(),"Đổi mật khẩu không thành công",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}