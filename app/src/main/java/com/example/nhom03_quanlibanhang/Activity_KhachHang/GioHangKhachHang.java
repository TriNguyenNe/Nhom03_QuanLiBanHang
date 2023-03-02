package com.example.nhom03_quanlibanhang.Activity_KhachHang;

import android.app.DatePickerDialog;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhom03_quanlibanhang.GioHang.Apdapter_giohang;
import com.example.nhom03_quanlibanhang.GioHang.GioHang;
import com.example.nhom03_quanlibanhang.GioHang.GioHangDAO;
import com.example.nhom03_quanlibanhang.MainActivity;
import com.example.nhom03_quanlibanhang.R;
import com.example.nhom03_quanlibanhang.SQLite.CTDonHangDAO;
import com.example.nhom03_quanlibanhang.SQLite.DBHelper;
import com.example.nhom03_quanlibanhang.SQLite.DonHangDAO;
import com.example.nhom03_quanlibanhang.model.CTDonHang;
import com.example.nhom03_quanlibanhang.model.DonHang;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GioHangKhachHang#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GioHangKhachHang extends Fragment {
    RecyclerView ls ;
    ArrayList<GioHang> list= new ArrayList<>();
    GioHang s = new GioHang();
    GioHangDAO gioHangDAO;
    BottomNavigationView bottomNavigationView;
    DBHelper dbHelper;
    public static TextView tongtien;
    DonHangDAO donHangDAO;
    CTDonHangDAO ctDonHangDAO;
    EditText ngaydat ,ngaygiao;
    Button back, dathang;
    Apdapter_giohang adpsp;
    SQLiteDatabase db;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GioHangKhachHang() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GioHangKhachHang.
     */
    // TODO: Rename and change types and number of parameters
    public static GioHangKhachHang newInstance(String param1, String param2) {
        GioHangKhachHang fragment = new GioHangKhachHang();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gio_hang_khach_hang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ls = view.findViewById(R.id.lsdsgiohang);
        dbHelper = new DBHelper(getContext());
        gioHangDAO= new GioHangDAO(getContext());
        ngaydat = view.findViewById(R.id.editTextDate);
        ngaygiao = view.findViewById(R.id.editTextDate2);
        dbHelper.openDatabase();
        dathang = view.findViewById(R.id.btndathang);
        tongtien = view.findViewById(R.id.lbltongtien);
        list = gioHangDAO.getDSSP(makh());

        adpsp = new Apdapter_giohang(getContext(),list);
        ls.setAdapter(adpsp);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tongtien.setText("total: "+decimalFormat.format(tongtiengio())+" VND");
        ls.setLayoutManager(new LinearLayoutManager(getContext()));
        adpsp.setOnItemClickListener(new Apdapter_giohang.OnItemClistener() {
            @Override
            public void onItemClick(int position) {
                s= list.get(position);
                gioHangDAO.xoasp(s.getMaMH());
                list = gioHangDAO.getDSSP(makh());

                adpsp = new Apdapter_giohang(getContext(),list);
                ls.setAdapter(adpsp);
                tongtien.setText("total: "+decimalFormat.format(tongtiengio())+" VND");
            }
        });
        dathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    insert_donhang();
                }catch (Exception e){

                }
                list = gioHangDAO.getDSSP(makh());

                adpsp = new Apdapter_giohang(getContext(),list);
                ls.setAdapter(adpsp);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                tongtien.setText("total: "+decimalFormat.format(tongtiengio())+" VND");
                ls.setLayoutManager(new LinearLayoutManager(getContext()));
            }
        });
        ngaygiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedYear = 2022;
                int selectedMonth = 5;
                int selectedDayOfMonth = 10;
                DatePickerDialog.OnDateSetListener dateSetListener= new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        ngaygiao.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                        dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth);
                datePickerDialog.show();
            }
        });
        ngaydat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedYear = 2022;
                int selectedMonth = 5;
                int selectedDayOfMonth = 10;
                DatePickerDialog.OnDateSetListener dateSetListener= new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        ngaydat.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                };
                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                        dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth);
                datePickerDialog.show();
            }
        });
    }

    private void insert_donhang() {
        DonHang e = new DonHang();
        donHangDAO= new DonHangDAO(getContext());
        e.setMaKH(makh());
        e.setNgayDat(ngaydat.getText().toString());
        e.setNgayGiaoDuKien(ngaygiao.getText().toString());
        int kq = donHangDAO.themsp(e);
        if(kq==1){
            for(int i=0; i< list.size();i++){
                CTDonHang ctDonHang = new CTDonHang();
                ctDonHang.setMaMH(list.get(i).getMaMH());
                ctDonHang.setMaGH(madonhang());
                ctDonHang.setSoLuong(list.get(i).getSoluong());
                ctDonHang.setDgBan((float)list.get(i).getDonGia());
                ctDonHangDAO = new CTDonHangDAO(getContext());
                int kq_ct= ctDonHangDAO.themsp(ctDonHang);
                if(kq_ct == 1){
                    String sql = "Delete from GioHang where MaKH ='"+makh()+"'" +
                            "and MaMH = '"+list.get(i).getMaMH()+"'";
                    SQLiteDatabase db = dbHelper.getReadableDatabase();
                    db.execSQL(sql);
                    Toast.makeText(getContext(),"Đặt hàng thành công",Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getContext(),"Đặt hàng không thành công",Toast.LENGTH_SHORT).show();
            }
        }
    }

    private String madonhang() {
        String ma ="";
        String s= "SELECT MaDH FROM DonHang where MaKH ='"+makh()+"' " +
                " and NgayDat ='"+ngaydat.getText().toString()+"'" +
                "and NgayGiaoDuKien = '"+ngaygiao.getText().toString()+"'";
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
        ma = c.getString(0);
        c.close();
        return ma;
    }

    private double tongtiengio() {
        double tongtiengioahang=0;
        String s= "SELECT sum(SoLuong*DonGia)as tong FROM GioHang where MaKH ='"+ makh()+"'";
        dbHelper = new DBHelper(getContext());
        db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
        tongtiengioahang = c.getDouble(0);
        c.close();
        return tongtiengioahang;
    }

    private String makh() {
        String makh="";
        String s= "SELECT MaKH FROM KhachHang where SdtKH ='"+ MainActivity.username+"'";
        dbHelper = new DBHelper(getContext());
        db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
        makh = c.getString(0);
        c.close();
        return makh;
    }
}