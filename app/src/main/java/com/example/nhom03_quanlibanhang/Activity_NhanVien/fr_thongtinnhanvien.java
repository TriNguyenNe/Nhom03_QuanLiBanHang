package com.example.nhom03_quanlibanhang.Activity_NhanVien;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nhom03_quanlibanhang.MainActivity;
import com.example.nhom03_quanlibanhang.R;
import com.example.nhom03_quanlibanhang.SQLite.NhanVienDAO;
import com.example.nhom03_quanlibanhang.model.NhanVien;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fr_thongtinnhanvien#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fr_thongtinnhanvien extends Fragment {
    TextView MaNV,tenNV,ngaysinh,diachi,sdt,email,gioitinh,chucvu;
    NhanVienDAO nhanVienDAO;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fr_thongtinnhanvien() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fr_thongtinnhanvien.
     */
    // TODO: Rename and change types and number of parameters
    public static fr_thongtinnhanvien newInstance(String param1, String param2) {
        fr_thongtinnhanvien fragment = new fr_thongtinnhanvien();
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
        return inflater.inflate(R.layout.fragment_fr_thongtinnhanvien, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MaNV = view.findViewById(R.id.txtMaKH);
        tenNV=view.findViewById(R.id.txtTenKH);
        ngaysinh= view.findViewById(R.id.txtNgaysinh);

        diachi = view.findViewById(R.id.txtDiaChi);
        sdt = view.findViewById(R.id.txtsdt);
        nhanVienDAO= new NhanVienDAO(getContext());
        NhanVien a= new NhanVien();
        a = nhanVienDAO.info_kh(MainActivity.username_nhanvien);
        MaNV.setText(a.getMaNV());
        tenNV.setText(a.getHoTenNV());
        ngaysinh.setText(a.getNgaySinh());
        diachi.setText(a.getDiaChi());
        sdt.setText(""+a.getSdtNV());
        gioitinh = view.findViewById(R.id.txtGioITinh);
        gioitinh.setText(a.getGioiTinh());
        email = view.findViewById(R.id.txtemail);
        email.setText(a.getEmailNV());
        chucvu = view.findViewById(R.id.txtChucvu);
        chucvu.setText(MainActivity.chuvu);
    }
}