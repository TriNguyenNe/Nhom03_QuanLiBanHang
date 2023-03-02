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
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.example.nhom03_quanlibanhang.Activity_NhanVien.fr_nhanvien;
import com.example.nhom03_quanlibanhang.MainActivity;
import com.example.nhom03_quanlibanhang.R;
import com.example.nhom03_quanlibanhang.SQLite.NhanVienDAO;
import com.example.nhom03_quanlibanhang.model.NhanVien;

import java.util.ArrayList;
import java.util.List;

public class Adapter_NhanVien extends BaseAdapter {
    private android.content.Context Context;
    private List<NhanVien> list_pn;
    String s ="";
    LayoutInflater inflter;

    public Adapter_NhanVien(android.content.Context context, List<NhanVien> list_pn) {
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
        NhanVien sp = list_pn.get(position);
        tenkh.setText("Mã nhân viên: "+sp.getMaNV());
        makh.setText(sp.getHoTenNV());
        ngaysinh.setText("Giới tính: "+sp.getGioiTinh());
        xem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chitiethoadon(sp.getMaNV());
            }
        });
        return convertView;
    }
    public void chitiethoadon(String manv){
        final Dialog dialog = new Dialog(Context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.chucnang_quanlynhanvien);
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
        TextView edtNhanVien,edtHoTen,edtDiaChi,edtSDt,edtEmail,edtMatKhau,edtNgaySinh;
        RadioButton rdbNam,rdbNu;
        Spinner spnChuvu;
        NhanVienDAO nhanVienDAO;
        Button btnThem,btnXoa,btnSUa,btnLamMoi,btnThoat,back;
        ListView lstnv;
        final ArrayList<NhanVien>[] ls = new ArrayList[]{new ArrayList<>()};
        NhanVien nhanvien = new NhanVien();
        final Adapter_NhanVien[] nhanVienadpter = new Adapter_NhanVien[1];
        edtNgaySinh = dialog.findViewById(R.id.ngaysinh);
        edtNhanVien =dialog.findViewById(R.id.txtMaNV);
        edtHoTen=dialog.findViewById(R.id.txtHoTen);
        rdbNam = dialog.findViewById(R.id.rdbnam);
        rdbNu = dialog.findViewById(R.id.rdbnu);
        spnChuvu = dialog.findViewById(R.id.spnchucvu);
        edtDiaChi = dialog.findViewById(R.id.txtDiachi);
        edtSDt = dialog.findViewById(R.id.txtsdtnv);
        edtMatKhau = dialog.findViewById(R.id.txtpass);
        btnThem = dialog.findViewById(R.id.btnThem);
        edtEmail = dialog.findViewById(R.id.txtemailnv);
        btnSUa = dialog.findViewById(R.id.btnsua);
        btnThoat =dialog.findViewById(R.id.btnThoat);
        nhanVienDAO= new NhanVienDAO(Context);
        NhanVien a= new NhanVien();
        a = nhanVienDAO.info_kh(manv);
        edtNhanVien.setText(a.getMaNV());
        edtHoTen.setText(a.getHoTenNV());
        if(a.getGioiTinh().equals("Nam"))
            rdbNam.setChecked(true);
        else rdbNu.setChecked(false);
        String[] tenphong = new String[]{"QuanLi","NhanVien"};
        ArrayAdapter<String> a_nn = new ArrayAdapter<String>(Context, android.R.layout.simple_spinner_item,tenphong);
        a_nn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnChuvu.setAdapter(a_nn);
        int index=0;
            for (int i =0;i<tenphong.length;i++){
                if(tenphong[i].equals(a.getChucVu()))
                    index =i;
            }
        spnChuvu.setSelection(index);
            edtNgaySinh.setText(a.getNgaySinh());
            edtDiaChi.setText(a.getDiaChi());
            edtEmail.setText(a.getEmailNV());
            edtSDt.setText(""+a.getSdtNV());
            edtMatKhau.setText(a.getMatKhau());
        edtNgaySinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedYear = 2022;
                int selectedMonth = 5;
                int selectedDayOfMonth = 10;
                DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        edtNgaySinh.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(Context,
                        android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                        dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth);
                datePickerDialog.show();
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.chuvu.equals("QuanLi")) {
                    try {
                        NhanVien e = new NhanVien();
                        String s = "";
                        if (rdbNam.isChecked()) s = rdbNam.getText().toString();
                        else s = rdbNu.getText().toString();
                        e.setMaNV(edtNhanVien.getText().toString());
                        e.setHoTenNV(edtHoTen.getText().toString());
                        e.setNgaySinh(edtNgaySinh.getText().toString());
                        e.setDiaChi(edtDiaChi.getText().toString());
                        e.setGioiTinh(s);
                        e.setChucVu(spnChuvu.getSelectedItem().toString());
                        e.setSdtNV((edtSDt.getText().toString()));
                        e.setEmailNV(edtEmail.getText().toString());
                        e.setMatKhau(edtMatKhau.getText().toString());
                        int kq = nhanVienDAO.themsp(e);
                        if (kq == -1) {
                            Toast.makeText(Context, "Thêm không thành công", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(Context, "Thêm thành công", Toast.LENGTH_SHORT).show();
                        ls[0] = nhanVienDAO.getDSSP();
                        nhanVienadpter[0] = new Adapter_NhanVien(Context,ls[0]);
                        fr_nhanvien.lstnv.setAdapter(nhanVienadpter[0]);
                    }
                catch (Exception e){}}
                else
                    Toast.makeText(Context,"Bạn không có quyền thực hiện chức năng này",Toast.LENGTH_SHORT).show();
            }
        });
        btnSUa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(MainActivity.chuvu.equals("QuanLi")) {
                        AlertDialog.Builder mydialog = new AlertDialog.Builder(Context);
                        mydialog.setTitle("Xác nhận");
                        mydialog.setMessage("Bạn có chắc chắn muốn cập nhật");
                        mydialog.setCancelable(false);
                        mydialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                NhanVien e = new NhanVien();
                                String s ="";
                                if(rdbNam.isChecked()) s =rdbNam.getText().toString();
                                else s= rdbNu.getText().toString();
                                e.setChucVu(spnChuvu.getSelectedItem().toString());
                                e.setHoTenNV(edtHoTen.getText().toString());
                                e.setNgaySinh(edtNgaySinh.getText().toString());
                                e.setDiaChi(edtDiaChi.getText().toString());
                                e.setGioiTinh( s);
                                e.setSdtNV((edtSDt.getText().toString()));
                                e.setEmailNV(edtEmail.getText().toString());
                                e.setMatKhau(edtMatKhau.getText().toString());
                                int kq =nhanVienDAO.update(edtNhanVien.getText().toString(),e);
                                if(kq ==-1){
                                    Toast.makeText(Context,"Sửa không thành công",Toast.LENGTH_SHORT).show();
                                }
                                else Toast.makeText(Context,"Sửa thành công",Toast.LENGTH_SHORT).show();
                                ls[0] = nhanVienDAO.getDSSP();
                                nhanVienadpter[0] = new Adapter_NhanVien(Context,ls[0]);
                                fr_nhanvien.lstnv.setAdapter(nhanVienadpter[0]);
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
                    else
                        Toast.makeText(Context,"Bạn không có quyền thực hiện chức năng này",Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){}
            }
        });
        dialog.show();
    }


}
