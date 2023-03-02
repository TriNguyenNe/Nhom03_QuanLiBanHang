package com.example.nhom03_quanlibanhang.Activity_KhachHang;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.ViewFlipper;

import com.example.nhom03_quanlibanhang.Adapter.Adapter_MatHang;
import com.example.nhom03_quanlibanhang.GioHang.GioHang;
import com.example.nhom03_quanlibanhang.R;
import com.example.nhom03_quanlibanhang.SQLite.DBHelper;
import com.example.nhom03_quanlibanhang.model.MatHang;
import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class TrangChuKhachHang extends AppCompatActivity {
    ViewFlipper vfshow;
    public static String mamh;
    BottomNavigationView bottomNavigationView;
    RecyclerView gvDanhSach;
    ArrayList<MatHang> ls = new ArrayList<>();
    Adapter_MatHang adapter_matHang;
    public static List<GioHang> addgio = new ArrayList<GioHang>();
    Home_khachhang home_khachhang = new Home_khachhang();
    GioHangKhachHang gioHangKhachHang = new GioHangKhachHang();
    Fr_tienich tienich = new Fr_tienich();
    Fr_thongtinkh thongtinkh = new Fr_thongtinkh();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu_khach_hang);
        bottomNavigationView = findViewById(R.id.bottom_naviga);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,home_khachhang).commit();
        bottomNavigationView.setSelectedItemId(R.id.trangchu);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.DiCho:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,gioHangKhachHang).commit();
                        return true;
                    case R.id.taikhoan:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,thongtinkh).commit();
                        return true;
                    case R.id.tienich:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,tienich).commit();
                        return true;
                    case R.id.trangchu:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,home_khachhang).commit();
                        return true;
                }
                return false;
            }
        });
    }
    public void slider(){
        ArrayList<Integer> id_a = new ArrayList<>();
        id_a.add(R.drawable.slide1);
        id_a.add(R.drawable.slide2);
        id_a.add(R.drawable.slide3);
        for(int i: id_a){
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(i);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            vfshow.addView(imageView);
        }
        vfshow.setFlipInterval(5000);
        vfshow.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(this,R.anim.slide_left);
        Animation animation_slide_out = AnimationUtils.loadAnimation(this,R.anim.slide_right);
        vfshow.setInAnimation( animation_slide_in);
        vfshow.setOutAnimation(animation_slide_out);
    }

}