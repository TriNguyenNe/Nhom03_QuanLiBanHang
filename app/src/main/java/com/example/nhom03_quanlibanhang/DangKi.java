package com.example.nhom03_quanlibanhang;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.nhom03_quanlibanhang.SQLite.KhachHangDAO;
import com.example.nhom03_quanlibanhang.model.KhachHang;
import com.google.android.material.textfield.TextInputLayout;

public class DangKi extends AppCompatActivity {
    TextInputLayout makh,tenkh,ngaysinh,diachi,sdt,matkhau;
    Button dangki,quayve;
    KhachHangDAO khachHangDAO;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ki);
        makh = findViewById(R.id.textintputma);
        tenkh = findViewById(R.id.textintputhoten);
        ngaysinh = findViewById(R.id.textintputngaysinh);
        diachi= findViewById(R.id.textintputdiachi);
        sdt= findViewById(R.id.textintputsdt);
        matkhau= findViewById(R.id.textintputpass);
        dangki = findViewById(R.id.btndangki);
        quayve = findViewById(R.id.btnquayve);
        khachHangDAO= new KhachHangDAO(this);
        dangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    KhachHang e = new KhachHang();
                    e.setMaKH(makh.getEditText().getText().toString());
                    e.setHoTenKH(tenkh.getEditText().getText().toString());
                    e.setNgaySinh(ngaysinh.getEditText().getText().toString());
                    e.setDiaChi(diachi.getEditText().getText().toString());
                    e.setSdtKH(sdt.getEditText().getText().toString());
                    e.setPass(matkhau.getEditText().getText().toString());
                    int kq = khachHangDAO.themkh(e);
                    if (kq == -1) {
                        Toast.makeText(getApplicationContext(), "Đăng kí không thành công", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getApplicationContext(), "Đăbg kí thành công", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                }
            }
        });
        quayve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DangKi.this.finish();
            }
        });

    }
}