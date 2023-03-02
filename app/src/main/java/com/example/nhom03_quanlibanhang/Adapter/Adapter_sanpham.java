package com.example.nhom03_quanlibanhang.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nhom03_quanlibanhang.R;
import com.example.nhom03_quanlibanhang.model.MatHang;

import java.util.List;

public class Adapter_sanpham extends BaseAdapter {
    private android.content.Context c;
    private List<MatHang> list_sp;

    LayoutInflater inflter;
    public Adapter_sanpham(Context context,List<MatHang> list_sp ) {
        c = context;
        this.list_sp = list_sp;
        inflter = (LayoutInflater.from(c.getApplicationContext()));
    }
    @Override
    public int getCount() {
        return list_sp.size();
    }

    @Override
    public Object getItem(int position) {
        if(position<0)
            return null;
        return  list_sp.get(position);
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
        MatHang sp = list_sp.get(position);
        ma_list.setText(sp.getMaMH());
        mamh.setText(sp.getTenMH());
        sl.setText("Sl: "+sp.getSoLuongTon());
        giasp.setText("gia: "+sp.getDonGia());
        return convertView;
    }
}
