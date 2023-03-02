package com.example.nhom03_quanlibanhang.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nhom03_quanlibanhang.GioHang.Apdapter_giohang;
import com.example.nhom03_quanlibanhang.GioHang.GioHang;
import com.example.nhom03_quanlibanhang.GioHang.GioHangDAO;
import com.example.nhom03_quanlibanhang.MainActivity;
import com.example.nhom03_quanlibanhang.R;
import com.example.nhom03_quanlibanhang.SQLite.DBHelper;
import com.example.nhom03_quanlibanhang.model.MatHang;
import com.example.nhom03_quanlibanhang.model.NhanVien;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Adapter_MatHang  extends RecyclerView.Adapter<Adapter_MatHang.ViewHolder>{
    Context context;
    ArrayList<MatHang> list;
    TextView tvmasp, tvtensp,tvdonvitinh,tvdongia,tvsoluong,tvtenloai, tenncc;
    ImageView imgHinhSP;
    Button btntangsl,btngiamsl,themgiohang,back;
    EditText sld;
    BottomNavigationView bottomNavigationView;
    GioHangDAO gioHangDAO;
    GioHang giohang = new GioHang();
    Apdapter_giohang ad ;
    SQLiteDatabase db;
    DBHelper dbHelper;
    public Adapter_MatHang(Context context, ArrayList<MatHang> list){
        this.context=context;
        this.list = list;
    }
    @NonNull
    @Override
    public Adapter_MatHang.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View  view = inflater.inflate(R.layout.dongsan_pham,parent,false);
        return new Adapter_MatHang.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull Adapter_MatHang.ViewHolder holder, int position) {
        int i = position;
        MatHang a = list.get(position);
        holder.img.setImageResource(list.get(position).getID_a());
        holder.tensp.setText(list.get(position).getTenMH());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.giasp.setText("Giá: "+decimalFormat.format(list.get(position).getDonGia())+" VND");
        MatHang sp = list.get(position);
        holder.btnthem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chitiethoadon(a);
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tensp;
        TextView giasp;
        Button btnthem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_sp);
            tensp= itemView.findViewById(R.id.txtTenSP);
            giasp = itemView.findViewById(R.id.txtDonGia);
            btnthem = itemView.findViewById(R.id.btnthem);
        }
    }
    public void chitiethoadon(MatHang a){
        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.chitiet_sanpham);
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

        final ArrayList<NhanVien>[] ls = new ArrayList[]{new ArrayList<>()};
        NhanVien nhanvien = new NhanVien();
        final Adapter_NhanVien[] nhanVienadpter = new Adapter_NhanVien[1];
        tvmasp =  dialog.findViewById(R.id.txtMaSP);
        bottomNavigationView =  dialog.findViewById(R.id.bottom_naviga);
        tvtensp =  dialog.findViewById(R.id.txtTenSP);
        tvdonvitinh =  dialog.findViewById(R.id.txtDonvitinh);
        tvdongia =  dialog.findViewById(R.id.txtGIaSP);
        tvsoluong =  dialog.findViewById(R.id.soluongton);
        tvtenloai =  dialog.findViewById(R.id.txtTenLH);
        tenncc =  dialog.findViewById(R.id.txtTENNCC);
        btntangsl = dialog.findViewById(R.id.tangsl);
        btngiamsl =  dialog.findViewById(R.id.giamsl);
        themgiohang =  dialog.findViewById(R.id.themvaogiohang);
        sld =  dialog.findViewById(R.id.soluongdat);

        imgHinhSP= dialog.findViewById(R.id.id_act);
        initData(a);
        gioHangDAO = new GioHangDAO(context);
        int index=0;
        btntangsl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sld.setText(""+(Integer.parseInt(sld.getText().toString()) +1));
            }
        });
        btngiamsl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sl = Integer.parseInt(sld.getText().toString()) -1;
                if(sl<0)
                    sld.setText("0");
                else
                    sld.setText(""+sl);
            }
        });
        themgiohang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    GioHang e = new GioHang();
                    e.setMaKH(makh());
                    e.setMaMH(a.getMaMH());
                    e.setTenMH(a.getTenMH());
                    e.setSoluong(Integer.parseInt(sld.getText().toString()));
                    e.setDonGia(a.getDonGia());
                    e.setId_a(a.getID_a());
                    int kq = gioHangDAO.themsp(e);
                    if (kq == -1) {
                        int kq1 = gioHangDAO.update(a.getMaMH(),makh(),e);
                        if (kq1 == -1) {
                            Toast.makeText(context, "Số mặt hàng trong giỏ ko được update", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(context, "Số mặt hàng trong giỏ được update", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(context, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception ea) {

                    GioHang ex = new GioHang();
                    ex.setMaKH(makh());
                    ex.setMaMH(a.getMaMH());
                    ex.setTenMH(a.getTenMH());
                    ex.setSoluong(Integer.parseInt(sld.getText().toString()));
                    ex.setDonGia(a.getDonGia());
                    ex.setId_a(a.getID_a());
                    int kq = gioHangDAO.update(a.getMaMH(),makh(),ex);
                    if (kq == -1) {
                        Toast.makeText(context, "Số mặt hàng trong giỏ ko được update", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Số mặt hàng trong giỏ được update", Toast.LENGTH_SHORT).show();
                    }
                }
                dialog.dismiss();
            }

        });
        dialog.show();
    }
    public String makh(){
        String makh="";
        String s= "SELECT MaKH FROM KhachHang where SdtKH ='"+MainActivity.username+"'";
        dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
        makh = c.getString(0);
        c.close();
        return makh;
    }
    private void initData(MatHang a) {
        tvmasp.setText("Mã sản phẩm: "+a.getMaMH());
        tvtensp.setText(a.getTenMH());
        tvdonvitinh.setText("Đơn vị tính: "+a.getDonViTinh());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        tvdongia.setText("Đơn giá bán: "+decimalFormat.format(a.getDonGia())+ " VND");
        tvsoluong.setText("Số lượng: "+a.getSoLuongTon());
        imgHinhSP.setImageResource(a.getID_a());
    }
}
