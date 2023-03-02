package com.example.nhom03_quanlibanhang.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhom03_quanlibanhang.R;
import com.example.nhom03_quanlibanhang.model.ChiTietHoaDon;

import java.text.DecimalFormat;
import java.util.List;

public class Adapter_ChiTietHoaDon extends BaseAdapter {
    private android.content.Context Context;
    private List<ChiTietHoaDon> list_nv;
    LayoutInflater inflter;
    public Adapter_ChiTietHoaDon (android.content.Context context, List<ChiTietHoaDon> list_nv) {
        Context = context;
        this.list_nv = list_nv;
        inflter = (LayoutInflater.from(Context.getApplicationContext()));
    }

    @Override
    public int getCount() {
        return list_nv.size();
    }

    @Override
    public Object getItem(int position) {
        if(position<0)
            return null;
        return  list_nv.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflter.inflate(R.layout.dong_ctdh,null);
        ImageView anh = convertView.findViewById(R.id.image1);
        TextView tensp= convertView.findViewById(R.id.txtmasps);
        TextView masp = convertView.findViewById(R.id.txtMdh);
        TextView luong = convertView.findViewById(R.id.txtsoluongm);
        TextView gia = convertView.findViewById(R.id.txtdongia);
        ChiTietHoaDon sp = list_nv.get(position);
        anh.setImageResource(R.drawable.ic_baseline_edit_note_24);
        tensp.setText("MaSP: "+sp.getMaMH());
        masp.setText("MaHD: "+sp.getMaHD());
        luong.setText("Số lượng "+sp.getSoLuong());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        gia.setText(decimalFormat.format(sp.getDonGia())+" VND");
        return convertView;
    }
}
