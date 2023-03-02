package com.example.nhom03_quanlibanhang.GioHang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom03_quanlibanhang.Activity_KhachHang.GioHangKhachHang;
import com.example.nhom03_quanlibanhang.R;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Apdapter_giohang extends RecyclerView.Adapter<Apdapter_giohang.ViewHolder> {
    Context context;
    ArrayList<GioHang> list;
    private OnItemClistener listener;
    public interface OnItemClistener{
        void onItemClick(int position);
    }
    ///phuong thuc
    public void setOnItemClickListener(OnItemClistener clickListener){
        listener = clickListener;
    }
    public Apdapter_giohang(Context context, ArrayList<GioHang> list){
        this.context=context;
        this.list = list;
    }
    @NonNull
    @Override
    public Apdapter_giohang.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View  view = inflater.inflate(R.layout.dong_giohang,parent,false);
        return new  Apdapter_giohang.ViewHolder(view,listener);
    }
    @Override
    public void onBindViewHolder(@NonNull Apdapter_giohang.ViewHolder holder, int position) {
        int i =position;
        holder.img.setImageResource(list.get(position).getId_a());
        holder.masp.setText(list.get(position).getMaMH());
        holder.tensp.setText(list.get(position).getTenMH());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.giasp.setText("Giá: "+decimalFormat.format(list.get(position).getDonGia())+" VND");
        holder.soluong.setText(""+list.get(position).getSoluong());
        holder.btntang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double tong = 0;
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
               holder.soluong.setText(""+(Integer.parseInt(holder.soluong.getText().toString()) +1));
               tong =Integer.parseInt( holder.soluong.getText().toString())* list.get(i).getDonGia();
               GioHangKhachHang.tongtien.setText("total: "+decimalFormat.format(tong)+" VND");
            }
        });
        holder.btngiam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double tong = 0;
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                int sl = Integer.parseInt(holder.soluong.getText().toString()) -1;
                if(sl<0)
                    holder.soluong.setText("0");
                else
                    holder.soluong.setText(""+sl);
                tong =Integer.parseInt( holder.soluong.getText().toString())*list.get(i).getDonGia();
                GioHangKhachHang.tongtien.setText("total: "+decimalFormat.format(tong)+" VND");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView masp;
         TextView tensp;TextView giasp;
        EditText soluong;
        Button btntang,btngiam,delete;
        public ViewHolder(@NonNull View itemView, OnItemClistener listener) {
            super(itemView);
            img = itemView.findViewById(R.id.imggh);
             masp= itemView.findViewById(R.id.lblmasp);
             tensp = itemView.findViewById(R.id.lbltensp);
             giasp = itemView.findViewById(R.id.giamasp);
            soluong = itemView.findViewById(R.id.soluongdat);
             btntang = itemView.findViewById(R.id.tangsl);
             btngiam = itemView.findViewById(R.id.giamsl);
            delete = itemView.findViewById(R.id.btnxoa);
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
