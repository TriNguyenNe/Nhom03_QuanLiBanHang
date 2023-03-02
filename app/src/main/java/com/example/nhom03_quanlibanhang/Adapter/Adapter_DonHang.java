package com.example.nhom03_quanlibanhang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom03_quanlibanhang.R;
import com.example.nhom03_quanlibanhang.model.ChiTietPhieuNhap;
import com.example.nhom03_quanlibanhang.model.DonHang;
import com.example.nhom03_quanlibanhang.model.NhanVien;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Adapter_DonHang extends BaseAdapter {
    private android.content.Context Context;
    private List<DonHang> list_pn;
    LayoutInflater inflter;

    public Adapter_DonHang(android.content.Context context, List<DonHang> list_pn) {
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
        convertView = inflter.inflate(R.layout.dong_list,null);
        ImageView img = convertView.findViewById(R.id.image1);
        TextView ma_list= convertView.findViewById(R.id.txtMa_ctrl);
        TextView  mamh = convertView.findViewById(R.id.txt_list_mh);
        TextView sl = convertView.findViewById(R.id.txt_list_sl);
        TextView giasp = convertView.findViewById(R.id.txt_list_dg);
        DonHang sp = list_pn.get(position);
        ma_list.setText("MaDH: "+sp.getMaDH());
        mamh.setText("MaKH: "+sp.getMaKH());
        sl.setText("NgayDat: "+sp.getNgayDat());
        giasp.setText("NgayGiao: "+sp.getNgayGiaoDuKien());
        return convertView;
    }
}
