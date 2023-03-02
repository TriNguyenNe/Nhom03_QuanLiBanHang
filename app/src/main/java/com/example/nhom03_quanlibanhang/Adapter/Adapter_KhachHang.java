package com.example.nhom03_quanlibanhang.Adapter;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.nhom03_quanlibanhang.Activity_NhanVien.fr_khachhang;
import com.example.nhom03_quanlibanhang.MainActivity;
import com.example.nhom03_quanlibanhang.R;
import com.example.nhom03_quanlibanhang.SQLite.KhachHangDAO;
import com.example.nhom03_quanlibanhang.model.KhachHang;
import com.example.nhom03_quanlibanhang.model.NhanVien;

import java.util.ArrayList;
import java.util.List;


public class Adapter_KhachHang extends BaseAdapter {
    private android.content.Context Context;
    private List<KhachHang> list_pn;
    LayoutInflater inflter;

    public Adapter_KhachHang(android.content.Context context, List<KhachHang> list_pn) {
        Context = context;
        this.list_pn = list_pn;
        inflter = (LayoutInflater.from(Context.getApplicationContext()));
    }

    @Override
    public int getCount() {
        return list_pn.size();
    }

    @Override
    public Object getItem(int position) {
        if(position<0)
            return null;
        return  list_pn.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflter.inflate(R.layout.dong_nhanvien,null);

        TextView tenkh=convertView.findViewById(R.id.txtTenNV);
        TextView makh =convertView.findViewById(R.id.txtMaNV);
        TextView ngaysinh =convertView.findViewById(R.id.txtgioitinh);
        Button xem =convertView.findViewById(R.id.xem);
        KhachHang sp = list_pn.get(position);
        tenkh.setText("Mã khách hàng: "+sp.getMaKH());
        makh.setText(sp.getHoTenKH());
        ngaysinh.setText("Ngày sinh "+sp.getNgaySinh());
        xem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chitiethoadon(sp.getMaKH());
            }
        });
        return convertView;
    }
    public void chitiethoadon(String makh){
        final Dialog dialog = new Dialog(Context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.chucnang_quanlykhachhang);
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
        EditText MaKH, Hoten, Ngaysinh, diachi,sdtnv, pass;
        Button Them, xoa, sua, lammoi, thoat,back;
        ListView dsnv;
        KhachHang khachang = new KhachHang();
        final Adapter_KhachHang[] adapter_khachHang = new Adapter_KhachHang[1];
        final KhachHangDAO[] khachHangDAO = new KhachHangDAO[1];
        final ArrayList<KhachHang>[] ls = new ArrayList[]{new ArrayList<>()};
        NhanVien nhanvien = new NhanVien();
        MaKH=(EditText) dialog.findViewById(R.id.txtMaKH);
        Hoten=(EditText) dialog.findViewById(R.id.txtHoTen);
        Ngaysinh=(EditText) dialog.findViewById(R.id.ngaysinh);
        diachi=(EditText) dialog.findViewById(R.id.txtDiachi);
        sdtnv=(EditText) dialog.findViewById(R.id.txtsdtnv);
        pass=(EditText) dialog.findViewById(R.id.txtpass);
        Them=(Button) dialog.findViewById(R.id.btnThem);
        xoa=(Button) dialog.findViewById(R.id.btnXoa);
        sua=(Button) dialog.findViewById(R.id.btnsua);
        lammoi=(Button) dialog.findViewById(R.id.btnlammoi);
        thoat=(Button) dialog.findViewById(R.id.btnThoat);
        dsnv= dialog.findViewById(R.id.lsv);
        khachHangDAO[0] = new KhachHangDAO(Context);
        KhachHang a= new KhachHang();
        a = khachHangDAO[0].info_kh1(makh);
        MaKH.setText(a.getMaKH());
        Hoten.setText(a.getHoTenKH());
        Ngaysinh.setText(a.getNgaySinh());
        sdtnv.setText(""+a.getSdtKH());
        diachi.setText(a.getDiaChi());
        pass.setText(a.getPass());
        Ngaysinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedYear = 2022;
                int selectedMonth = 5;
                int selectedDayOfMonth = 10;
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Ngaysinh.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(Context,
                        android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                        dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth);
                datePickerDialog.show();
            }
        });
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    KhachHang e = new KhachHang();
                    e.setMaKH(MaKH.getText().toString());
                    e.setHoTenKH(Hoten.getText().toString());
                    e.setNgaySinh(Ngaysinh.getText().toString());
                    e.setDiaChi(diachi.getText().toString());
                    e.setSdtKH(sdtnv.getText().toString());
                    e.setPass(pass.getText().toString());
                    int kq = khachHangDAO[0].themkh(e);
                    if (kq == -1) {
                        Toast.makeText(Context, "Thêm không thành công", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(Context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                    ls[0] = khachHangDAO[0].getDSSP();
                    adapter_khachHang[0] = new Adapter_KhachHang(Context,ls[0]);
                    fr_khachhang.dsnv.setAdapter(adapter_khachHang[0]);
                }
                catch (Exception e){}
            }
        });
        lammoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaKH.setText("");
                Hoten.setText("");
                Ngaysinh.setText("");
                diachi.setText("");
                sdtnv.setText("");
            }
        });
        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.chuvu.equals("QuanLi")) {
                    try {
                        AlertDialog.Builder mydialog = new AlertDialog.Builder(Context);
                        mydialog.setTitle("Xác nhận");
                        mydialog.setMessage("Bạn có chắc chắn muốn cập nhật");
                        mydialog.setCancelable(false);
                        mydialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                KhachHang e= new KhachHang();
                                e.setHoTenKH(Hoten.getText().toString());
                                e.setNgaySinh(Ngaysinh.getText().toString());
                                e.setDiaChi(diachi.getText().toString());
                                e.setSdtKH(sdtnv.getText().toString());
                                e.setPass(pass.getText().toString());
                                int kq = khachHangDAO[0].update(MaKH.getText().toString(),e);
                                if(kq ==-1){
                                    Toast.makeText(Context,"Sửa không thành công",Toast.LENGTH_SHORT).show();
                                }
                                else Toast.makeText(Context,"Sửa thành công",Toast.LENGTH_SHORT).show();
                                ls[0] = khachHangDAO[0].getDSSP();
                                adapter_khachHang[0] = new Adapter_KhachHang(Context,ls[0]);
                                fr_khachhang.dsnv.setAdapter(adapter_khachHang[0]);
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
                    }catch (Exception e){}  }
                else
                    Toast.makeText(Context,"Bạn không có quyền thực hiện chức năng này",Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }
}
