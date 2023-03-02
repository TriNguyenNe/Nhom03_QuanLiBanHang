package com.example.nhom03_quanlibanhang.Activity_KhachHang;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nhom03_quanlibanhang.MainActivity;
import com.example.nhom03_quanlibanhang.R;
import com.example.nhom03_quanlibanhang.SQLite.DBHelper;
import com.example.nhom03_quanlibanhang.SQLite.KhachHangDAO;
import com.example.nhom03_quanlibanhang.model.KhachHang;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fr_thongtinkh#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Fr_thongtinkh extends Fragment {
    TextView MaKH,tenKH,ngaysinh,diachi,sdt;
    KhachHangDAO khachHangDAO ;
    Button btnDoiPass,btnDangXuat,back;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Fr_thongtinkh() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Fr_thongtinkh.
     */
    // TODO: Rename and change types and number of parameters
    public static Fr_thongtinkh newInstance(String param1, String param2) {
        Fr_thongtinkh fragment = new Fr_thongtinkh();
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
        return inflater.inflate(R.layout.fragment_fr_thongtinkh, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MaKH = view.findViewById(R.id.txtMaKH);
        btnDoiPass = view.findViewById(R.id.btndoipass);
        btnDangXuat = view.findViewById(R.id.btndangxuat);
        tenKH=view.findViewById(R.id.txtTenKH);
        ngaysinh= view.findViewById(R.id.txtNgaysinh);

        diachi = view.findViewById(R.id.txtDiaChi);
        sdt = view.findViewById(R.id.txtsdt);
        khachHangDAO= new KhachHangDAO(getContext());
        KhachHang a= new KhachHang();
        a = khachHangDAO.info_kh(MainActivity.username);
        MaKH.setText(a.getMaKH());
        tenKH.setText(a.getHoTenKH());
        ngaysinh.setText(a.getNgaySinh());
        diachi.setText(a.getDiaChi());
        sdt.setText(""+MainActivity.username);
        btnDoiPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeedDoiPass();
            }
        });
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(),MainActivity.class);
                startActivity(i);
                getActivity().finish();
            }
        });
    }
    public void openFeedDoiPass(){
        final Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.doimatkhau);
        Window window = dialog.getWindow();
        if(window == null){
            return;
        }
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT,WindowManager.LayoutParams.WRAP_CONTENT);
        window.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        WindowManager.LayoutParams windowAttributes = window.getAttributes();
        windowAttributes.gravity= windowAttributes.gravity;
        window.setGravity(Gravity.BOTTOM);
        window.setAttributes(windowAttributes);
        if(Gravity.BOTTOM == windowAttributes.gravity){
            dialog.setCancelable(true);
        }
        else
            dialog.setCancelable(false);
        EditText matkhaumoi = dialog.findViewById(R.id.edtMaKhauMoi);
        EditText MatKhauxn = dialog.findViewById(R.id.edtXacNhanPass);
        Button btnluu = dialog.findViewById(R.id.btnLuu);
        Button btnhuy = dialog.findViewById(R.id.btnHuy);
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbHelper = new DBHelper(getContext());
                dbHelper.openDatabase();
                SQLiteDatabase db = dbHelper.getReadableDatabase();
                String update = "Update KhachHang set pass='"+matkhaumoi.getText().toString()+"'where SdtKH= '"+MainActivity.username+"'";
                try{
                    db.execSQL(update);
                    Toast.makeText(getContext(),"Đổi mật khẩu thành công",Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(getContext(),"Đổi mật khẩu không thành công",Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnhuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}