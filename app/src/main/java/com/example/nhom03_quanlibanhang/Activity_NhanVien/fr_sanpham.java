package com.example.nhom03_quanlibanhang.Activity_NhanVien;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nhom03_quanlibanhang.Adapter.Adapter_sanpham;
import com.example.nhom03_quanlibanhang.R;
import com.example.nhom03_quanlibanhang.SQLite.DBHelper;
import com.example.nhom03_quanlibanhang.SQLite.MatHangDAO;
import com.example.nhom03_quanlibanhang.getMa_DoiTuong.getMa_DoiTuong;
import com.example.nhom03_quanlibanhang.model.MatHang;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fr_sanpham#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fr_sanpham extends Fragment {
    EditText MaMH,TenMH,Dongia,SLTon,id_a;
    Spinner spnDVT,spnMLH,spnMNCC;
    Button Them, xoa, sua, lammoi, thoat,back;
    ListView dsnv;
    List<String> lsmakh;
    String[] dvt;
    List<String> lsmancc;
    List<String> lsdvt;
    MatHangDAO donHangDAO;
    ArrayList<MatHang> ls = new ArrayList<>();
    MatHang mathang = new MatHang();
    Adapter_sanpham adapter_sanpham;
    getMa_DoiTuong getMa_doiTuong;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fr_sanpham() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fr_sanpham.
     */
    // TODO: Rename and change types and number of parameters
    public static fr_sanpham newInstance(String param1, String param2) {
        fr_sanpham fragment = new fr_sanpham();
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
        return inflater.inflate(R.layout.fragment_fr_sanpham, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        thamchieumathang(view);
        DBHelper dbHelper = new DBHelper(getContext());
        dbHelper.openDatabase();
        donHangDAO = new MatHangDAO(getContext());
        getMa_doiTuong = new getMa_DoiTuong(getContext());
        lsmakh = getMa_doiTuong.getMaNCC();
        lsmancc = getMa_doiTuong.getMaLH();
        dvt = new String[]{"Cái","Lon","Hộp",
                "Thùng","Kg","Gói","Chai"};
        ArrayAdapter<String> a_nn2 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,dvt);
        a_nn2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnDVT.setAdapter(a_nn2);
        ArrayAdapter<String> a_nn = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,lsmakh);
        a_nn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMNCC.setAdapter(a_nn);
        ArrayAdapter<String> a_nn1 = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,lsmancc);
        a_nn1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnMLH.setAdapter(a_nn1);
        ls = donHangDAO.getDSSP1();

        thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert_show();
            }
        });
        adapter_sanpham = new Adapter_sanpham(getContext(),ls);
        dsnv.setAdapter(adapter_sanpham);
        dsnv.setClickable(true);

        dsnv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MaMH.setText(ls.get(position).getMaMH());
                TenMH.setText(ls.get(position).getTenMH());
                Dongia.setText(""+ls.get(position).getDonGia());
                SLTon.setText(""+ls.get(position).getSoLuongTon());
                id_a.setText(""+ls.get(position).getID_a());
                spnMNCC.setSelection(vt_spnncc(ls.get(position).getMaNCC()));
                spnMLH.setSelection(vt_spn(ls.get(position).getMaLH()));
                spnDVT.setSelection(vt_dvt(ls.get(position).getDonViTinh()));
            }
        });
        lammoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MaMH.setText("");
                TenMH.setText("");
                Dongia.setText("");
                SLTon.setText("");
                id_a.setText("");
            }
        });
        Them.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MatHang e = new MatHang();
                    e.setMaMH(MaMH.getText().toString());
                    e.setTenMH(TenMH.getText().toString());
                    e.setDonGia(Float.parseFloat(Dongia.getText().toString()));
                    e.setDonViTinh(spnDVT.getSelectedItem().toString());
                    e.setMaLH(spnMLH.getSelectedItem().toString());
                    e.setSoLuongTon(Integer.parseInt(SLTon.getText().toString()));
                    e.setID_a(Integer.parseInt(id_a.getText().toString()));
                    e.setMaNCC(spnMNCC.getSelectedItem().toString());
                    int kq = donHangDAO.themsp(e);
                    if (kq == -1) {
                        Toast.makeText(getContext(), "Thêm không thành công", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                    hienthi();
                }
                catch (Exception e){}
            }
        });
        sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    alert_show_upd();
                }
                catch (Exception e){}
            }
        });
    }
    public int vt_spnncc(String s){
        int index=0;
        for (int i =0;i<lsmancc.size();i++){
            if(lsmancc.get(i).equals(s))
                index =i;
        }
        return  index;
    }
    public int vt_spn(String s){
        int index=0;
        for (int i =0;i<lsmakh.size();i++){
            if(lsmakh.get(i).equals(s))
                index =i;
        }
        return  index;
    }
    public int vt_dvt(String s){
        int index=0;
        for (int i =0;i<dvt.length;i++){
            if(dvt[i].equals(s))
                index =i;
        }
        return  index;
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
    public void thamchieumathang(View v)
    {
        /*EditText MaMH, MaMH,TenMH,Dongia,SLTon
        Spinner spnDVT,spnMLH,spnMNCC;
        Button Them, xoa, sua, lammoi, thoat;
         ListView dsnv;*/
        id_a = v.findViewById(R.id.txtid_a);
        MaMH=(EditText) v.findViewById(R.id.txtMaMH);
        TenMH=(EditText) v.findViewById(R.id.txttenMH);
        Dongia=(EditText) v.findViewById(R.id.txtdongia);
        SLTon=(EditText) v.findViewById(R.id.txtsoluongton);
        spnDVT=(Spinner) v.findViewById(R.id.spnDVT);
        spnMLH=(Spinner) v.findViewById(R.id.spnmlh);
        spnMNCC=(Spinner) v.findViewById(R.id.spnmncc);
        Them=(Button) v.findViewById(R.id.btnThem);
        xoa=(Button) v.findViewById(R.id.btnXoa);
        sua=(Button) v.findViewById(R.id.btnsua);
        lammoi=(Button) v.findViewById(R.id.btnlammoi);
        thoat=(Button) v.findViewById(R.id.btnThoat);
        dsnv=v.findViewById(R.id.lsv);
    }
    private void hienthi() {
        ls = donHangDAO.getDSSP1();
        adapter_sanpham = new Adapter_sanpham(getContext(),ls);
    }

    private void update() {
        MatHang e = new MatHang();
        e.setTenMH(TenMH.getText().toString());
        e.setDonGia(Float.parseFloat(Dongia.getText().toString()));
        e.setDonViTinh(spnDVT.getSelectedItem().toString());
        e.setMaLH(spnMLH.getSelectedItem().toString());
        e.setSoLuongTon(Integer.parseInt(SLTon.getText().toString()));
        e.setID_a(Integer.parseInt(id_a.getText().toString()));
        e.setMaNCC(spnMNCC.getSelectedItem().toString());
        int kq =donHangDAO.update(MaMH.getText().toString(),e);
        if(kq ==-1){
            Toast.makeText(getContext(),"Cập nhật không thành công",Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(getContext(),"Cập nhật thành công",Toast.LENGTH_SHORT).show();
        hienthi();
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