package com.example.nhom03_quanlibanhang.Activity_KhachHang;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import com.example.nhom03_quanlibanhang.Adapter.Adapter_MatHang;
import com.example.nhom03_quanlibanhang.R;
import com.example.nhom03_quanlibanhang.SQLite.DBHelper;
import com.example.nhom03_quanlibanhang.model.MatHang;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Home_khachhang#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Home_khachhang extends Fragment {
    ViewFlipper vfshow;
    BottomNavigationView bottomNavigationView;
    RecyclerView gvDanhSach;
    ArrayList<MatHang> ls = new ArrayList<>();
    Adapter_MatHang adapter_matHang;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Home_khachhang() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Home_khachhang.
     */
    // TODO: Rename and change types and number of parameters
    public static Home_khachhang newInstance(String param1, String param2) {
        Home_khachhang fragment = new Home_khachhang();
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
        return inflater.inflate(R.layout.fragment_home_khachhang, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vfshow = view.findViewById(R.id.vfSlider);
        slider();
        gvDanhSach = view.findViewById(R.id.rclv);
        DBHelper dbHelper = new DBHelper(getContext());
        dbHelper.openDatabase();
        ls = dbHelper.getListProduct();
        adapter_matHang= new Adapter_MatHang(getContext(),ls);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(),2);
        gvDanhSach.setAdapter(adapter_matHang);
        gvDanhSach.setLayoutManager(gridLayoutManager);

    }

    private void slider() {
        ArrayList<Integer> id_a = new ArrayList<>();
        id_a.add(R.drawable.slide1);
        id_a.add(R.drawable.slide2);
        id_a.add(R.drawable.slide3);
        for(int i: id_a){
            ImageView imageView = new ImageView(getContext());
            imageView.setBackgroundResource(i);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            vfshow.addView(imageView);
        }
        vfshow.setFlipInterval(5000);
        vfshow.setAutoStart(true);
        Animation animation_slide_in = AnimationUtils.loadAnimation(getContext(),R.anim.slide_left);
        Animation animation_slide_out = AnimationUtils.loadAnimation(getContext(),R.anim.slide_right);
        vfshow.setInAnimation( animation_slide_in);
        vfshow.setOutAnimation(animation_slide_out);
    }
}