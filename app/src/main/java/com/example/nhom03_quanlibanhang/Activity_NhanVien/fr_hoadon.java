package com.example.nhom03_quanlibanhang.Activity_NhanVien;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nhom03_quanlibanhang.Adapter.Adapter_ChiTietHoaDon;
import com.example.nhom03_quanlibanhang.Adapter.Adapter_HoaDon;
import com.example.nhom03_quanlibanhang.MainActivity;
import com.example.nhom03_quanlibanhang.R;
import com.example.nhom03_quanlibanhang.SQLite.ChiTietHoaDonDAO;
import com.example.nhom03_quanlibanhang.SQLite.DBHelper;
import com.example.nhom03_quanlibanhang.SQLite.HoaDonDAO;
import com.example.nhom03_quanlibanhang.getMa_DoiTuong.getMa_DoiTuong;
import com.example.nhom03_quanlibanhang.model.ChiTietHoaDon;
import com.example.nhom03_quanlibanhang.model.HoaDon;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fr_hoadon#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fr_hoadon extends Fragment {
    EditText ngaylap,ngaygiao,tongtien;
    Button Them, xoa, sua, lammoi, thoat,chitiethoadon,back;
    Spinner spnNhanvien, spnGiohang;
    ListView dsnv;
    List<String> lsmadh;
    String MaHD="";
    List<String> lsmanv;
    HoaDonDAO hoaDonDAO;
    Adapter_HoaDon adapter_hoadon;
    ChiTietHoaDonDAO chiTietHoaDonDAO;
    ArrayList<HoaDon> ls= new ArrayList<HoaDon>();
    HoaDon hoadon = new HoaDon();
    ArrayList<ChiTietHoaDon> ls1 = new ArrayList<>();
    getMa_DoiTuong getMa_doiTuong;
    Adapter_ChiTietHoaDon adapter_chiTiethoadon;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fr_hoadon() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fr_hoadon.
     */
    // TODO: Rename and change types and number of parameters
    public static fr_hoadon newInstance(String param1, String param2) {
        fr_hoadon fragment = new fr_hoadon();
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
        return inflater.inflate(R.layout.fragment_fr_hoadon, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        thamchieuHD(view);
        DBHelper dbHelper = new DBHelper(getContext());
        dbHelper.openDatabase();
        hoaDonDAO= new HoaDonDAO(getContext());
        getMa_doiTuong = new getMa_DoiTuong(getContext());
        lsmadh = getMa_doiTuong.getMaDH();
        ArrayAdapter<String> a_nn = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,lsmadh);
        a_nn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnGiohang.setAdapter(a_nn);
        lsmanv = getMa_doiTuong.getManv();
        ArrayAdapter<String> a_nn1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,lsmanv);
        a_nn1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnNhanvien.setAdapter(a_nn1);
        ngaylap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chon_ngay();
            }
        });
        ngaygiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chon_ngay_giao();
            }
        });
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert_show();
            }
        });
        ls =hoaDonDAO.getDSSP();
        dsnv.setClickable(true);
        adapter_hoadon= new Adapter_HoaDon(getContext(),ls);
        dsnv.setAdapter(adapter_hoadon);
        dsnv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MaHD= ""+ls.get(position).getMaHD();
                spnGiohang.setSelection(vt_spn(ls.get(position).getMaDH()));
                spnNhanvien.setSelection(vt_snv(ls.get(position).getMaNV()));
                ngaylap.setText(ls.get(position).getNgayLapHD());
                ngaygiao.setText(ls.get(position).getNgayGiaoThucte());
                tongtien.setText(""+ls.get(position).getTongTien());
            }
        });
        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.chuvu.equals("QuanLi")) {
                    alert_show_udele();  }
                else
                    Toast.makeText(getContext(),"Bạn không có quyền thực hiện chức năng này",Toast.LENGTH_SHORT).show();

            }
        });
        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.chuvu.equals("QuanLi")) {
                    try {
                        alert_show_upd();
                    }
                    catch (Exception e){}
                }
                else
                    Toast.makeText(getContext(),"Bạn không có quyền thực hiện chức năng này",Toast.LENGTH_SHORT).show();

            }
        });
        Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    HoaDon e = new HoaDon();
                    e.setMaGH(spnGiohang.getSelectedItem().toString());
                    e.setMaNV(spnNhanvien.getSelectedItem().toString());
                    e.setNgayLapHD(ngaylap.getText().toString());
                    e.setNgayGiaoThucte(ngaygiao.getText().toString());
                    e.setTongTien(Float.parseFloat(tongtien.getText().toString()));
                    int kq = hoaDonDAO.themsp(e);
                    if (kq == -1) {
                        Toast.makeText(getContext(), "Thêm không thành công", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    hienthi();
                }
                catch (Exception e){}
            }
        });
        lammoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ngaylap.setText("");
                ngaygiao.setText("");
                tongtien.setText("");
            }
        });
        chitiethoadon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chitiethoadon();
            }
        });
    }
    public void chitiethoadon(){
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.chucnang_hoadon);
        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity= windowAttributes.gravity;
        window.setAttributes(windowAttributes);
        window.setGravity(Gravity.BOTTOM);
        if(Gravity.BOTTOM == windowAttributes.gravity){
            dialog.setCancelable(true);
        }
        else
            dialog.setCancelable(false);
        Spinner mamh = dialog.findViewById(R.id.spinner_mathang);
        EditText soluong = dialog.findViewById(R.id.edtSoLuong);
        EditText giaban = dialog.findViewById(R.id.edtGiaBan);
        Button btnThem = dialog.findViewById(R.id.btnThemCT);
        Button btnxoa = dialog.findViewById(R.id.btnXoaCT);
        Button btnlanmoi = dialog.findViewById(R.id.btnlammoiCT);
        Button btncapnhat = dialog.findViewById(R.id.btnXemCT);
        Button btnHuy = dialog.findViewById(R.id.btnHuyCT);
        ListView ls_ct = dialog.findViewById(R.id.lsv_ctdh);
        List<String> Mamh_list = getMa_doiTuong.getMaMH();
        ArrayAdapter<String> a_nn = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,Mamh_list);
        a_nn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mamh.setAdapter(a_nn);
        chiTietHoaDonDAO = new ChiTietHoaDonDAO(getContext());
        ls1 = chiTietHoaDonDAO.getDSSP1(MaHD);
        adapter_chiTiethoadon = new Adapter_ChiTietHoaDon(getContext(),ls1);
        ls_ct.setAdapter(adapter_chiTiethoadon);
        ls_ct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int index=0;
                for (int i =0;i<Mamh_list.size();i++){
                    if(Mamh_list.get(i).equals(ls1.get(position).getMaMH()))
                        index =i;
                };
                mamh.setSelection(index);
                soluong.setText(""+ls1.get(position).getSoLuong());
                giaban.setText(""+ls1.get(position).getDonGia());
            }
        });

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    ChiTietHoaDon e = new ChiTietHoaDon();
                    e.setMaHD(MaHD);
                    e.setMaMH(mamh.getSelectedItem().toString());
                    e.setSoLuong(Integer.parseInt(soluong.getText().toString()));
                    e.setDonGia(Float.parseFloat(giaban.getText().toString()));
                    int kq = chiTietHoaDonDAO.themsp(e);
                    if (kq == -1) {
                        Toast.makeText(getContext(), "Thêm không thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }
                    chiTietHoaDonDAO = new ChiTietHoaDonDAO(getContext());
                    ls1 = chiTietHoaDonDAO.getDSSP1(MaHD);
                    adapter_chiTiethoadon = new Adapter_ChiTietHoaDon(getContext(),ls1);
                    ls_ct.setAdapter(adapter_chiTiethoadon);
                }
                catch (Exception e){}
            }
        });
        btncapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.chuvu.equals("QuanLi")) {
                    try{
                        update_Ct(MaHD,mamh.getSelectedItem().toString(),Integer.parseInt(soluong.getText().toString())
                                ,Float.parseFloat(giaban.getText().toString()));
                        chiTietHoaDonDAO = new ChiTietHoaDonDAO(getContext());
                        ls1 = chiTietHoaDonDAO.getDSSP1(MaHD);
                        adapter_chiTiethoadon = new Adapter_ChiTietHoaDon(getContext(),ls1);
                        ls_ct.setAdapter(adapter_chiTiethoadon); }
                    catch (Exception e){}}
                else
                    Toast.makeText(getContext(),"Bạn không có quyền thực hiện chức năng này",Toast.LENGTH_SHORT).show();

            }
        });
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.chuvu.equals("QuanLi")) {
                    try{
                        xoa_ctdh_theosp(MaHD,mamh.getSelectedItem().toString());
                        chiTietHoaDonDAO = new ChiTietHoaDonDAO(getContext());
                        ls1 = chiTietHoaDonDAO.getDSSP1(MaHD);
                        adapter_chiTiethoadon = new Adapter_ChiTietHoaDon(getContext(),ls1);
                        ls_ct.setAdapter(adapter_chiTiethoadon);  }
                    catch (Exception e ){}
                }
                else
                    Toast.makeText(getContext(),"Bạn không có quyền thực hiện chức năng này",Toast.LENGTH_SHORT).show();

            }
        });
        btnlanmoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soluong.setText("");
                giaban.setText("");
            }
        });
        dialog.show();
    }

    public int vt_spn(String s){
        int index=0;
        for (int i =0;i<lsmadh.size();i++){
            if(lsmadh.get(i).equals(s))
                index =i;
        }
        return  index;
    }
    public int vt_snv(String s){
        int index=0;
        for (int i =0;i<lsmanv.size();i++){
            if(lsmanv.get(i).equals(s)) {
                index =i;
            }
        }
        return  index;
    }
    public void thamchieuHD(View v)
    {
        spnNhanvien=(Spinner) v.findViewById(R.id.spnnhanvien);
        spnGiohang=(Spinner) v.findViewById(R.id.spnmadh);
        Them=(Button) v.findViewById(R.id.btnThem);
        ngaylap= v.findViewById(R.id.txtngaylap);
        ngaygiao = v.findViewById(R.id.txtngaygiao);
        tongtien = v.findViewById(R.id.txtTongTien);
        xoa=(Button) v.findViewById(R.id.btnXoa);
        sua=(Button) v.findViewById(R.id.btnsua);
        lammoi=(Button) v.findViewById(R.id.btnlammoi);
        thoat=(Button) v.findViewById(R.id.btnThoat);
        chitiethoadon= v.findViewById(R.id.btnChiTietHD);
        dsnv=v.findViewById(R.id.lsv);
    }
    public void alert_show_upd(){
        AlertDialog.Builder mydialog = new AlertDialog.Builder(getContext());
        mydialog.setTitle("Xác nhận");
        mydialog.setMessage("Bạn có chắc chắn muốn cập nhật");
        mydialog.setCancelable(false);
        mydialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    update(MaHD);
                    hienthi();
                }catch (Exception e){}
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
    private void hienthi() {
        hoaDonDAO= new HoaDonDAO(getContext());
        ls.clear();
        ls = hoaDonDAO.getDSSP();
        Adapter_HoaDon adapter_donHang = new Adapter_HoaDon(getContext(),ls);
        dsnv.setAdapter(adapter_donHang);
    }

    public void alert_show_udele(){
        AlertDialog.Builder mydialog = new AlertDialog.Builder(getContext());
        mydialog.setTitle("Xác nhận");
        mydialog.setMessage("Bạn có chắc chắn muốn xóa");
        mydialog.setCancelable(false);
        mydialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    xoa_ctdh(MaHD);
                    xoa(MaHD);hienthi();
                }
                catch (Exception e){}
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

    private void xoa(String mahd) {
        HoaDon e = new HoaDon();
        int kq =hoaDonDAO.xoasp(mahd);
        if(kq ==-1){
            Toast.makeText(getContext(),"Xóa không thành công",Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(getContext(),"Xóa thành công",Toast.LENGTH_SHORT).show();
    }
    private void xoa_ctdh(String mahd) {
        ChiTietHoaDon e = new ChiTietHoaDon();
        int kq =chiTietHoaDonDAO.xoasp(mahd);
        if(kq ==-1){
            Toast.makeText(getContext(),"Xóa không thành công",Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(getContext(),"Xóa thành công",Toast.LENGTH_SHORT).show();

    }
    private void xoa_ctdh_theosp(String mahd, String s) {
        ChiTietHoaDon e = new ChiTietHoaDon();
        int kq =chiTietHoaDonDAO.xoasp_hd(mahd,s);
        if(kq ==-1){
            Toast.makeText(getContext(),"Xóa không thành công",Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(getContext(),"Xóa thành công",Toast.LENGTH_SHORT).show();

    }
    private void update(String MaHD) {
        HoaDon e = new HoaDon();
        e.setMaGH(spnGiohang.getSelectedItem().toString());
        e.setMaNV(spnNhanvien.getSelectedItem().toString());
        e.setNgayLapHD(ngaylap.getText().toString());
        e.setNgayGiaoThucte(ngaygiao.getText().toString());
        e.setTongTien(Float.parseFloat(tongtien.getText().toString()));
        int kq =hoaDonDAO.update(MaHD,e);
        if(kq ==-1){
            Toast.makeText(getContext(),"Cập nhật không thành công",Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(getContext(),"Cập nhật thành công",Toast.LENGTH_SHORT).show();
        hienthi();
    }
    private void update_Ct( String maHD, String MaMH, int soluong,float dongia) {
        ChiTietHoaDon e = new ChiTietHoaDon();
        e.setSoLuong(soluong);
        e.setDonGia( dongia);
        int kq =chiTietHoaDonDAO.update(maHD,MaMH,e);
        if(kq ==-1){
            Toast.makeText(getContext(),"Cập nhật không thành công",Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(getContext(),"Cập nhật thành công",Toast.LENGTH_SHORT).show();
        hienthi();
    }
    public void chon_ngay(){
        int selectedYear = 2022;
        int selectedMonth = 5;
        int selectedDayOfMonth = 10;
        DatePickerDialog.OnDateSetListener dateSetListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                ngaylap.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth);
        datePickerDialog.show();
    }
    public void chon_ngay_giao() {
        int selectedYear = 2022;
        int selectedMonth = 5;
        int selectedDayOfMonth = 10;
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
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
    public void alert_show(){
        AlertDialog.Builder mydialog = new AlertDialog.Builder(getContext());
        mydialog.setTitle("Xác nhận");
        mydialog.setMessage("Bạn có chắc chắn muốn thoát");
        mydialog.setCancelable(false);
        mydialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                getActivity().finish();
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
}