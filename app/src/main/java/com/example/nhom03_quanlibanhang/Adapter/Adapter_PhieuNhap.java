package com.example.nhom03_quanlibanhang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom03_quanlibanhang.R;
import com.example.nhom03_quanlibanhang.model.ChiTietPhieuNhap;
import com.example.nhom03_quanlibanhang.model.HoaDon;
import com.example.nhom03_quanlibanhang.model.PhieuNhap;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Adapter_PhieuNhap extends BaseAdapter {
    private android.content.Context Context;
    private List<PhieuNhap> list_pn;
    LayoutInflater inflter;

    public Adapter_PhieuNhap(android.content.Context context, List<PhieuNhap> list_pn) {
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
        TextView ma_hd= convertView.findViewById(R.id.txtMa_ctrl);
        TextView  madh = convertView.findViewById(R.id.txt_list_mh);
        TextView ngaylap = convertView.findViewById(R.id.txt_list_sl);
        TextView gia = convertView.findViewById(R.id.txt_list_dg);
        PhieuNhap sp = list_pn.get(position);
        ma_hd.setText("MaPN: "+sp.getMaPN());
        madh.setText("MaNV: "+sp.getMaNV());
        ngaylap.setText("MaNCC: "+sp.getMaNCC());
        gia.setText("NgayLap: "+sp.getNgayLap());
        return convertView;
    }
}
