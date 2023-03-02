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
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhom03_quanlibanhang.Adapter.Adapter_ChiTietPhieuNhap;
import com.example.nhom03_quanlibanhang.Adapter.Adapter_PhieuNhap;
import com.example.nhom03_quanlibanhang.MainActivity;
import com.example.nhom03_quanlibanhang.R;
import com.example.nhom03_quanlibanhang.SQLite.ChiTietPhieuNhapDAO;
import com.example.nhom03_quanlibanhang.SQLite.DBHelper;
import com.example.nhom03_quanlibanhang.SQLite.PhieuNhapDAO;
import com.example.nhom03_quanlibanhang.getMa_DoiTuong.getMa_DoiTuong;
import com.example.nhom03_quanlibanhang.model.ChiTietPhieuNhap;
import com.example.nhom03_quanlibanhang.model.PhieuNhap;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fr_phieunhap#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fr_phieunhap extends Fragment {
    EditText MaPN, Ngaylap;
    Spinner spnNhanvien,spnMNCC;
    Button Them, xoa, sua, lammoi, thoat, xemctpn,back;
    ListView dsnv;
    List<String> lsmakh;
    List<String> lsmancc;
    PhieuNhapDAO phieuNhapDAO;
    ArrayList<PhieuNhap> ls = new ArrayList<>();
    ArrayList<ChiTietPhieuNhap> ls1 = new ArrayList<>();
    PhieuNhap phieuNhap = new PhieuNhap();
    Adapter_PhieuNhap adapter_phieuNhap;
    Adapter_ChiTietPhieuNhap adapter_chiTietPhieuNhap;
    ChiTietPhieuNhapDAO chiTietPhieuNhapDAO;
    ChiTietPhieuNhap chiTietPhieuNhap;
    getMa_DoiTuong getMa_doiTuong;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fr_phieunhap() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fr_phieunhap.
     */
    // TODO: Rename and change types and number of parameters
    public static fr_phieunhap newInstance(String param1, String param2) {
        fr_phieunhap fragment = new fr_phieunhap();
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
        return inflater.inflate(R.layout.fragment_fr_phieunhap, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        thamchieuphieunhap(view);
        DBHelper dbHelper = new DBHelper(getContext());
        dbHelper.openDatabase();
        phieuNhapDAO= new PhieuNhapDAO(getContext());
        getMa_doiTuong = new getMa_DoiTuong(getContext());
        lsmakh = getMa_doiTuong.getManv();
        ArrayAdapter<String> a_nn = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,lsmakh);
        a_nn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnNhanvien.setAdapter(a_nn);
        lsmancc = getMa_doiTuong.getMaNCC();
        ArrayAdapter<String> a_nn1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,lsmancc);
        a_nn1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMNCC.setAdapter(a_nn1);
        ls = phieuNhapDAO.getDSSP();
        adapter_phieuNhap = new Adapter_PhieuNhap(getContext(),ls);
        Ngaylap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chon_ngay();
            }
        });
        dsnv.setClickable(true);
        dsnv.setAdapter(adapter_phieuNhap);
        dsnv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MaPN.setText(ls.get(position).getMaPN());
                spnNhanvien.setSelection(vt_spn(ls.get(position).getMaNV()));
                spnMNCC.setSelection(vt_spnncc(ls.get(position).getMaNCC()));
                Ngaylap.setText(ls.get(position).getNgayLap());
            }
        });
        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert_show();
            }
        });
        Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    PhieuNhap e = new PhieuNhap();
                    e.setMaNV(spnNhanvien.getSelectedItem().toString());
                    e.setMaNCC(spnMNCC.getSelectedItem().toString());
                    e.setNgayLap(Ngaylap.getText().toString());
                    int kq = phieuNhapDAO.themsp(e);
                    if (kq == -1) {
                        Toast.makeText(getContext(), "Thêm không thành công", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    hienthi();
                }
                catch (Exception e){}
            }
        });
        xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.chuvu.equals("QuanLi")) {
                    try {
                        alert_show_udele();
                    }
                    catch (Exception e){}}
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
                    catch (Exception e){}}
                else
                    Toast.makeText(getContext(),"Bạn không có quyền thực hiện chức năng này",Toast.LENGTH_SHORT).show();

            }
        });
        xemctpn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xemchitietphieunhap();
            }
        });
        lammoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaPN.setText("");
                Ngaylap.setText("");
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
    public void xemchitietphieunhap(){
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
        TextView txttitle = dialog.findViewById(R.id.textView15);
        Button btnxoa = dialog.findViewById(R.id.btnXoaCT);
        Button btnlanmoi = dialog.findViewById(R.id.btnlammoiCT);
        Button btncapnhat = dialog.findViewById(R.id.btnXemCT);
        Button btnHuy = dialog.findViewById(R.id.btnHuyCT);
        txttitle.setText("Chi Tiết Phiếu Nhập");
        ListView ls_ct = dialog.findViewById(R.id.lsv_ctdh);
        List<String> Mamh_list = getMa_doiTuong.getMaMH();
        ArrayAdapter<String> a_nn = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,Mamh_list);
        a_nn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mamh.setAdapter(a_nn);
        chiTietPhieuNhapDAO = new ChiTietPhieuNhapDAO(getContext());
        ls1 = chiTietPhieuNhapDAO.getDSSP(MaPN.getText().toString());
        adapter_chiTietPhieuNhap = new Adapter_ChiTietPhieuNhap(getContext(),ls1);
        ls_ct.setAdapter( adapter_chiTietPhieuNhap);
        ls_ct.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int index=0;
                for (int i =0;i<Mamh_list.size();i++){
                    if(Mamh_list.get(i).equals(ls1.get(position).getMaMH()))
                        index =i;
                };
                mamh.setSelection(index);
                soluong.setText(""+ls1.get(position).getSoLuongNhap());
                giaban.setText(""+ls1.get(position).getDgNhap());
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
                    ChiTietPhieuNhap e = new ChiTietPhieuNhap();
                    e.setMaPN(MaPN.getText().toString());
                    e.setMaMH(mamh.getSelectedItem().toString());
                    e.setSoLuongNhap(Integer.parseInt(soluong.getText().toString()));
                    e.setDgNhap(Float.parseFloat(giaban.getText().toString()));
                    int kq = chiTietPhieuNhapDAO.themsp(e);
                    if (kq == -1) {
                        Toast.makeText(getContext(), "Thêm không thành công", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    }
                    chiTietPhieuNhapDAO = new ChiTietPhieuNhapDAO(getContext());
                    ls1 = chiTietPhieuNhapDAO.getDSSP(MaPN.getText().toString());
                    adapter_chiTietPhieuNhap = new Adapter_ChiTietPhieuNhap(getContext(),ls1);
                    ls_ct.setAdapter(adapter_chiTietPhieuNhap);
                }
                catch (Exception e){}
            }
        });
        btncapnhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.chuvu.equals("QuanLi")) {
                    try{
                        update_Ct(MaPN.getText().toString(),mamh.getSelectedItem().toString(),Integer.parseInt(soluong.getText().toString())
                                ,Float.parseFloat(giaban.getText().toString()));
                        chiTietPhieuNhapDAO = new ChiTietPhieuNhapDAO(getContext());
                        ls1 = chiTietPhieuNhapDAO.getDSSP(MaPN.getText().toString());
                        adapter_chiTietPhieuNhap = new Adapter_ChiTietPhieuNhap(getContext(),ls1);
                        ls_ct.setAdapter(adapter_chiTietPhieuNhap);
                    }catch (Exception e){}
                }
                else
                    Toast.makeText(getContext(),"Bạn không có quyền thực hiện chức năng này",Toast.LENGTH_SHORT).show();

            }
        });
        btnxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.chuvu.equals("QuanLi")) {
                    try{
                        xoa_ctdh_theosp(MaPN.getText().toString(),mamh.getSelectedItem().toString());
                        chiTietPhieuNhapDAO = new ChiTietPhieuNhapDAO(getContext());
                        ls1 = chiTietPhieuNhapDAO.getDSSP(MaPN.getText().toString());
                        adapter_chiTietPhieuNhap = new Adapter_ChiTietPhieuNhap(getContext(),ls1);
                        ls_ct.setAdapter( adapter_chiTietPhieuNhap);
                    }
                    catch (Exception e){}
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
        for (int i =0;i<lsmakh.size();i++){
            if(lsmakh.get(i).equals(s))
                index =i;
        }
        return  index;
    }
    public int vt_spnncc(String s){
        int index=0;
        for (int i =0;i<lsmancc.size();i++){
            if(lsmancc.get(i).equals(s))
                index =i;
        }
        return  index;
    }
    private void update_Ct( String maPN, String MaMH, int soluong,float dongia) {
        ChiTietPhieuNhap e = new ChiTietPhieuNhap();
        e.setSoLuongNhap(soluong);
        e.setDgNhap( dongia);
        int kq =chiTietPhieuNhapDAO.update(maPN,MaMH,e);
        if(kq ==-1){
            Toast.makeText(getContext(),"Cập nhật không thành công",Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(getContext(),"Cập nhật thành công",Toast.LENGTH_SHORT).show();
    }
    public void thamchieuphieunhap(View v)
    {

        MaPN=(EditText) v.findViewById(R.id.txtPN);
        spnNhanvien=(Spinner) v.findViewById(R.id.spnnhanvien);
        spnMNCC=(Spinner) v.findViewById(R.id.spnmancc);
        Ngaylap=(EditText) v.findViewById(R.id.txtngaylap);
        Them=(Button) v.findViewById(R.id.btnThem);
        xoa=(Button) v.findViewById(R.id.btnXoa);
        sua=(Button) v.findViewById(R.id.btnsua);
        lammoi=(Button) v.findViewById(R.id.btnlammoi);
        thoat=(Button) v.findViewById(R.id.btnThoat);
        xemctpn = v.findViewById(R.id.btnChiTietPhieuNhap);
        dsnv=(ListView) v.findViewById(R.id.lsv);
    }
    private void hienthi() {
        ls = phieuNhapDAO.getDSSP();
        adapter_phieuNhap = new Adapter_PhieuNhap(getContext(),ls);
        dsnv.setClickable(true);
        dsnv.setAdapter(adapter_phieuNhap);
    }
    private void xoa_ctdh(String mapn) {
        ChiTietPhieuNhap e = new ChiTietPhieuNhap();
        int kq =chiTietPhieuNhapDAO.xoasp(mapn);
        if(kq ==-1){
            Toast.makeText(getContext(),"Xóa không thành công",Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(getContext(),"Xóa thành công",Toast.LENGTH_SHORT).show();

    }
    private void xoa_ctdh_theosp(String mahd, String s) {
        ChiTietPhieuNhap e = new ChiTietPhieuNhap();
        int kq =chiTietPhieuNhapDAO.xoasp_hd(mahd,s);
        if(kq ==-1){
            Toast.makeText(getContext(),"Xóa không thành công",Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(getContext(),"Xóa thành công",Toast.LENGTH_SHORT).show();

    }
    private void xoa() {
        PhieuNhap e = new PhieuNhap();
        int kq =phieuNhapDAO.xoasp(MaPN.getText().toString());
        if(kq ==-1){
            Toast.makeText(getContext(),"Xóa không thành công",Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(getContext(),"Xóa thành công",Toast.LENGTH_SHORT).show();
        hienthi();
    }

    private void update() {
        PhieuNhap e = new PhieuNhap();
        e.setMaNV(spnNhanvien.getSelectedItem().toString());
        e.setMaNCC(spnMNCC.getSelectedItem().toString());
        e.setNgayLap(Ngaylap.getText().toString());
        int kq =phieuNhapDAO.update(MaPN.getText().toString(),e);
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
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                Ngaylap.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                dateSetListener, selectedYear, selectedMonth, selectedDayOfMonth);
        datePickerDialog.show();}
    public void alert_show_upd(){
        AlertDialog.Builder mydialog = new AlertDialog.Builder(getContext());
        mydialog.setTitle("Xác nhận");
        mydialog.setMessage("Bạn có chắc chắn muốn cập nhật");
        mydialog.setCancelable(false);
        mydialog.setPositiveButton("yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                update();
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
                xoa();
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