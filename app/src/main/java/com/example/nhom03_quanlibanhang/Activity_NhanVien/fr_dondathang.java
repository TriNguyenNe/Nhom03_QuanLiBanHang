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

import com.example.nhom03_quanlibanhang.Adapter.Adapter_ChiTietDonHang;
import com.example.nhom03_quanlibanhang.Adapter.Adapter_DonHang;
import com.example.nhom03_quanlibanhang.MainActivity;
import com.example.nhom03_quanlibanhang.R;
import com.example.nhom03_quanlibanhang.SQLite.CTDonHangDAO;
import com.example.nhom03_quanlibanhang.SQLite.DBHelper;
import com.example.nhom03_quanlibanhang.SQLite.DonHangDAO;
import com.example.nhom03_quanlibanhang.getMa_DoiTuong.getMa_DoiTuong;
import com.example.nhom03_quanlibanhang.model.CTDonHang;
import com.example.nhom03_quanlibanhang.model.DonHang;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fr_dondathang#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fr_dondathang extends Fragment {
    EditText MaDH, NgayDat, NgaygiaoDK;
    Spinner spnKhachhang;
    Button Them, xoa, sua, lammoi, thoat,xemct,back;
    ListView dsnv;
    List<String> lsmakh;
    DonHangDAO donHangDAO;
    Adapter_DonHang adapter_donHang;
    CTDonHangDAO ctDonHangDAO;
    ArrayList<DonHang> ls = new ArrayList<>();
    DonHang donHang = new DonHang();
    ArrayList<CTDonHang> ls1 = new ArrayList<>();
    getMa_DoiTuong getMa_doiTuong;
    Adapter_ChiTietDonHang adapter_chiTietDonHang;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fr_dondathang() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fr_dondathang.
     */
    // TODO: Rename and change types and number of parameters
    public static fr_dondathang newInstance(String param1, String param2) {
        fr_dondathang fragment = new fr_dondathang();
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
        return inflater.inflate(R.layout.fragment_fr_dondathang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        thamchieuDDH(view);
        DBHelper dbHelper = new DBHelper(getContext());
        dbHelper.openDatabase();
        donHangDAO= new DonHangDAO(getContext());
        getMa_doiTuong = new getMa_DoiTuong(getContext());
        lsmakh = getMa_doiTuong.getMakh();
        ArrayAdapter<String> a_nn = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,lsmakh);
        a_nn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnKhachhang.setAdapter(a_nn);
        ls = donHangDAO.getDSSP1();
        dsnv= view.findViewById(R.id.lsv);
        dsnv.setClickable(true);
        adapter_donHang= new Adapter_DonHang(getContext(),ls);
        dsnv.setAdapter(adapter_donHang);
        dsnv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DonHang nv_cl = (DonHang)  parent.getItemAtPosition(position);
                MaDH.setText(""+nv_cl.getMaDH());
                NgayDat.setText(nv_cl.getNgayDat());
                NgaygiaoDK.setText(nv_cl.getNgayGiaoDuKien());
                spnKhachhang.setSelection(vt_spn(nv_cl.getMaKH()));
            }
        });
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert_show();
            }
        });
        lammoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaDH.setText("");
                NgayDat.setText("");
                NgaygiaoDK.setText("");
            }
        });
        NgayDat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chon_ngay();
            }
        });
        NgaygiaoDK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chon_ngay_giao();
            }
        });
        Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    DonHang e = new DonHang();
                    e.setMaKH(spnKhachhang.getSelectedItem().toString());
                    e.setNgayDat(NgayDat.getText().toString());
                    e.setNgayGiaoDuKien(NgaygiaoDK.getText().toString());
                    int kq = donHangDAO.themsp(e);
                    if (kq == -1) {
                        Toast.makeText(getContext(), "Thêm không thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                        hienthi();
                    }
                }catch (Exception e){}
            }
        });
        xemct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chitiethoadon();
            }
        });
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

    public void chitiethoadon(){
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.chucnang_ctdonhang);
        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT);
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
        ctDonHangDAO = new CTDonHangDAO(getContext());
        ls1 = ctDonHangDAO.getDSSP1(MaDH.getText().toString());
        adapter_chiTietDonHang = new Adapter_ChiTietDonHang(getContext(),ls1);
        ls_ct.setAdapter(adapter_chiTietDonHang);
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
                giaban.setText(""+ls1.get(position).getDgBan());
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
                    CTDonHang e = new CTDonHang();
                    e.setMaGH(MaDH.getText().toString());
                    e.setMaMH(mamh.getSelectedItem().toString());
                    e.setSoLuong(Integer.parseInt(soluong.getText().toString()));
                    e.setDgBan(Float.parseFloat(giaban.getText().toString()));
                    int kq = ctDonHangDAO.themsp(e);
                    if (kq == -1) {
                        Toast.makeText(getContext(), "Thêm không thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }
                    ctDonHangDAO = new CTDonHangDAO(getContext());
                    ls1 = ctDonHangDAO.getDSSP1(MaDH.getText().toString());
                    adapter_chiTietDonHang = new Adapter_ChiTietDonHang(getContext(),ls1);
                    ls_ct.setAdapter(adapter_chiTietDonHang);
                }catch (Exception e){}
            }
        });
        btncapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(MainActivity.chuvu.equals("QuanLi")) {
                    try {
                        update_ct(mamh.getSelectedItem().toString(), Integer.parseInt(soluong.getText().toString())
                                , Float.parseFloat(giaban.getText().toString()));
                        ctDonHangDAO = new CTDonHangDAO(getContext());
                        ls1 = ctDonHangDAO.getDSSP1(MaDH.getText().toString());
                        adapter_chiTietDonHang = new Adapter_ChiTietDonHang(getContext(),ls1);
                        ls_ct.setAdapter(adapter_chiTietDonHang);
                    }catch (Exception e){}
                }
                else
                    Toast.makeText(getContext(), "Bạn không có quyền thực hiện chức năng này", Toast.LENGTH_SHORT).show();
            }
        });
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.chuvu.equals("QuanLi")) {
                    try{
                        xoa_ct(mamh.getSelectedItem().toString());
                        ctDonHangDAO = new CTDonHangDAO(getContext());
                        ls1 = ctDonHangDAO.getDSSP1(MaDH.getText().toString());
                        adapter_chiTietDonHang = new Adapter_ChiTietDonHang(getContext(),ls1);
                        ls_ct.setAdapter(adapter_chiTietDonHang); }
                    catch (Exception e){}}
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
    public void xoa_ct(String s){
        AlertDialog.Builder mydialog = new AlertDialog.Builder(getContext());
        mydialog.setTitle("Xác nhận");
        mydialog.setMessage("Bạn có chắc chắn muốn xóa");
        mydialog.setCancelable(false);
        mydialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    xoa_ctdh(s);
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
    public void update_ct(String s,int sl,float dg){
        AlertDialog.Builder mydialog = new AlertDialog.Builder(getContext());
        mydialog.setTitle("Xác nhận");
        mydialog.setMessage("Bạn có chắc chắn muốn cập nhật");
        mydialog.setCancelable(false);
        mydialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    update_Ct(s, sl, dg);
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
    private void hienthi() {
        donHangDAO= new DonHangDAO(getContext());
        ls.clear();
        ls = donHangDAO.getDSSP1();
        Adapter_DonHang  adapter_donHang = new Adapter_DonHang(getContext(),ls);
        dsnv.setAdapter(adapter_donHang);
    }

    public int vt_spn(String s){
        int index=0;
        for (int i =0;i<lsmakh.size();i++){
            if(lsmakh.get(i).equals(s))
                index =i;
        }
        return  index;
    }
    private void thamchieuDDH(View v) {
        MaDH=(EditText) v.findViewById(R.id.txtMaGH);
        NgayDat=(EditText) v.findViewById(R.id.txtngaydat);
        NgaygiaoDK=(EditText) v.findViewById(R.id.txtngaygiao);
        spnKhachhang=(Spinner) v.findViewById(R.id.spnkhachhang);
        Them=(Button) v.findViewById(R.id.btnThem);
        xoa=(Button) v.findViewById(R.id.btnXoa);
        sua=(Button) v.findViewById(R.id.btnsua);
        lammoi=(Button) v.findViewById(R.id.btnlammoi);
        thoat=(Button) v.findViewById(R.id.btnThoat);
        xemct = v.findViewById(R.id.btnXemCT);
    }
    public void alert_show_upd(){
        AlertDialog.Builder mydialog = new AlertDialog.Builder(getContext());
        mydialog.setTitle("Xác nhận");
        mydialog.setMessage("Bạn có chắc chắn muốn cập nhật");
        mydialog.setCancelable(false);
        mydialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                update();
                hienthi();
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
    public void alert_show_udele(){
        AlertDialog.Builder mydialog = new AlertDialog.Builder(getContext());
        mydialog.setTitle("Xác nhận");
        mydialog.setMessage("Bạn có chắc chắn muốn xóa");
        mydialog.setCancelable(false);
        mydialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                xoa();hienthi();
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

    private void xoa() {
        DonHang e = new DonHang();
        int kq =donHangDAO.xoasp(MaDH.getText().toString());
        if(kq ==-1){
            Toast.makeText(getContext(),"Xóa không thành công",Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(getContext(),"Xóa thành công",Toast.LENGTH_SHORT).show();
    }
    private void xoa_ctdh(String s) {
        CTDonHang e = new CTDonHang();
        int kq =ctDonHangDAO.xoasp(MaDH.getText().toString(),s);
        if(kq ==-1){
            Toast.makeText(getContext(),"Xóa không thành công",Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(getContext(),"Xóa thành công",Toast.LENGTH_SHORT).show();

    }
    private void update() {
        DonHang e = new DonHang();
        e.setMaKH(spnKhachhang.getSelectedItem().toString());
        e.setNgayDat(NgayDat.getText().toString());
        e.setNgayGiaoDuKien(NgaygiaoDK.getText().toString());
        int kq =donHangDAO.update(MaDH.getText().toString(),e);
        if(kq ==-1){
            Toast.makeText(getContext(),"Cập nhật không thành công",Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(getContext(),"Cập nhật thành công",Toast.LENGTH_SHORT).show();
        hienthi();
    }
    private void update_Ct( String MaMH, int soluong,float dongia) {
        CTDonHang e = new CTDonHang();
        e.setSoLuong(soluong);
        e.setDgBan( dongia);
        int kq =ctDonHangDAO.update(MaDH.getText().toString(),MaMH,e);
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
                NgayDat.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
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
                NgaygiaoDK.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth);
        datePickerDialog.show();
    }
}