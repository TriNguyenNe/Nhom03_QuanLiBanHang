package com.example.nhom03_quanlibanhang.Activity_KhachHang;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import com.example.nhom03_quanlibanhang.Adapter.Adapter_ChiTietDonHang;
import com.example.nhom03_quanlibanhang.Adapter.Adapter_ChiTietHoaDon;
import com.example.nhom03_quanlibanhang.MainActivity;
import com.example.nhom03_quanlibanhang.R;
import com.example.nhom03_quanlibanhang.SQLite.CTDonHangDAO;
import com.example.nhom03_quanlibanhang.SQLite.ChiTietHoaDonDAO;
import com.example.nhom03_quanlibanhang.SQLite.DBHelper;
import com.example.nhom03_quanlibanhang.model.CTDonHang;
import com.example.nhom03_quanlibanhang.model.ChiTietHoaDon;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fr_tienich#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fr_tienich extends Fragment {
    Button btDonhang,btnHoaDon,back;
    ListView lsdh;
    DBHelper dbHelper;
    CTDonHangDAO ctDonHangDAO;
    ChiTietHoaDonDAO chiTietHoaDonDAO;
    ChiTietHoaDon chiTietHoaDon;
    List<ChiTietHoaDon> lsc = new ArrayList<>();
    List<CTDonHang> ls = new ArrayList<>();
    Adapter_ChiTietDonHang adapter_matHang;
    Adapter_ChiTietHoaDon adapter_chiTietHoaDon;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fr_tienich() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fr_tienich.
     */
    // TODO: Rename and change types and number of parameters
    public static Fr_tienich newInstance(String param1, String param2) {
        Fr_tienich fragment = new Fr_tienich();
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
        return inflater.inflate(R.layout.fragment_fr_tienich, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnHoaDon =  view.findViewById(R.id.button2);

        btDonhang =  view.findViewById(R.id.button);
        lsdh =  view.findViewById(R.id.lsvsa);
        btDonhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper = new DBHelper(getContext());
                ctDonHangDAO = new CTDonHangDAO(getContext());
                dbHelper.openDatabase();
                ls = ctDonHangDAO.getDSSP(makh());
                adapter_matHang = new Adapter_ChiTietDonHang(getContext(),ls);
                lsdh.setAdapter(adapter_matHang);
            }
        });
        btnHoaDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper = new DBHelper(getContext());
                chiTietHoaDonDAO = new ChiTietHoaDonDAO(getContext());
                dbHelper.openDatabase();
                lsc = chiTietHoaDonDAO.getDSSP(makh());
                adapter_chiTietHoaDon = new Adapter_ChiTietHoaDon(getContext(),lsc);
                lsdh.setAdapter(adapter_chiTietHoaDon);
            }
        });

    }
    public String makh(){
        String makh="";
        String s= "SELECT MaKH FROM KhachHang where SdtKH ='"+ MainActivity.username+"'";
        dbHelper = new DBHelper(getContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
        makh = c.getString(0);
        c.close();
        return makh;
    }
}