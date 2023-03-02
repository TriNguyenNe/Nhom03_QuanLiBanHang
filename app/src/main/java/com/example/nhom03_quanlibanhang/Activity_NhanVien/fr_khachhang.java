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
import android.widget.Button;
import android.widget.ListView;

import com.example.nhom03_quanlibanhang.Adapter.Adapter_KhachHang;
import com.example.nhom03_quanlibanhang.R;
import com.example.nhom03_quanlibanhang.SQLite.DBHelper;
import com.example.nhom03_quanlibanhang.SQLite.KhachHangDAO;
import com.example.nhom03_quanlibanhang.model.KhachHang;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fr_khachhang#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fr_khachhang extends Fragment {
    Button back;
    public static ListView dsnv;
    ArrayList<KhachHang> ls = new ArrayList<>();
    KhachHang khachang = new KhachHang();
    Adapter_KhachHang adapter_khachHang;
    KhachHangDAO khachHangDAO;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fr_khachhang() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fr_khachhang.
     */
    // TODO: Rename and change types and number of parameters
    public static fr_khachhang newInstance(String param1, String param2) {
        fr_khachhang fragment = new fr_khachhang();
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
        return inflater.inflate(R.layout.fragment_fr_khachhang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DBHelper dbHelper = new DBHelper(getContext());
        dbHelper.openDatabase();
        khachHangDAO = new KhachHangDAO(getContext());
        ls = khachHangDAO.getDSSP();
        adapter_khachHang = new Adapter_KhachHang(getContext(),ls);
        dsnv= view.findViewById(R.id.lsv);
        dsnv.setAdapter(adapter_khachHang);
        dsnv.setClickable(true);
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