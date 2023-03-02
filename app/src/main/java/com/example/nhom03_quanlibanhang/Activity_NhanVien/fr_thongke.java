package com.example.nhom03_quanlibanhang.Activity_NhanVien;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.nhom03_quanlibanhang.Adapter.Adapter_ChiTietHoaDon;
import com.example.nhom03_quanlibanhang.R;
import com.example.nhom03_quanlibanhang.SQLite.ChiTietHoaDonDAO;
import com.example.nhom03_quanlibanhang.SQLite.DBHelper;
import com.example.nhom03_quanlibanhang.getMa_DoiTuong.getMa_DoiTuong;
import com.example.nhom03_quanlibanhang.model.ChiTietHoaDon;
import com.example.nhom03_quanlibanhang.model.HoaDon;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fr_thongke#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fr_thongke extends Fragment {
    Spinner MaMH;
    PieChart barChart;
    ArrayList<PieEntry> data ;
    TextView ketqua;
    ListView dsban;
    Button xemkq ,back;
    getMa_DoiTuong getMa_doiTuong;
    private SQLiteDatabase db;
    private DBHelper dbHelper;
    private Context context;
    ChiTietHoaDonDAO chiTietHoaDonDAO;
    ArrayList<HoaDon> ls= new ArrayList<HoaDon>();
    HoaDon hoadon = new HoaDon();
    ArrayList<ChiTietHoaDon> ls1 = new ArrayList<>();
    Adapter_ChiTietHoaDon adapter_chiTiethoadon;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public fr_thongke() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fr_thongke.
     */
    // TODO: Rename and change types and number of parameters
    public static fr_thongke newInstance(String param1, String param2) {
        fr_thongke fragment = new fr_thongke();
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
        return inflater.inflate(R.layout.fragment_fr_thongke, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        barChart = view.findViewById(R.id.barChart);
        MaMH = view.findViewById(R.id.spinner);
        dsban = view.findViewById(R.id.lsv);
        ketqua = view.findViewById(R.id.txtketqua);
        getMa_doiTuong = new getMa_DoiTuong(getContext());
        List<String> Mamh_list = getMa_doiTuong.getMaMH();
        ArrayAdapter<String> a_nn = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,Mamh_list);
        a_nn.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        MaMH.setAdapter(a_nn);
        xemkq = view.findViewById(R.id.btnxemkq);
        xemkq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    chiTietHoaDonDAO = new ChiTietHoaDonDAO(getContext());
                    ls1 = getDSSP_ban(MaMH.getSelectedItem().toString());
                    adapter_chiTiethoadon = new Adapter_ChiTietHoaDon(getContext(),ls1);
                    dsban.setAdapter(adapter_chiTiethoadon);
                    ketqua (MaMH.getSelectedItem().toString());
                }catch (Exception e){}
            }
        });
    }
    public ArrayList<ChiTietHoaDon> getDSSP_ban(String MaMH){
        dbHelper = new DBHelper(getContext());
        db = dbHelper.getWritableDatabase();
        ArrayList<ChiTietHoaDon> sanp= new ArrayList<>();
        String s= "SELECT MaHD,MaMH,SoLuong,DonGia FROM ChiTietHD where MaMH ='"+MaMH+"' ";
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
        data = new ArrayList<>();
        while (c.isAfterLast()==false){
            ChiTietHoaDon hd= new ChiTietHoaDon();
            hd.setMaHD(c.getString(0));
            hd.setMaMH(c.getString(1));
            hd.setSoLuong(c.getInt(2));
            hd.setDonGia(c.getFloat(3));
            data.add(new PieEntry(Float.parseFloat(String.valueOf(c.getFloat(3)*c.getFloat(2))),("MaHD: " + c.getString(0))));
            sanp.add(hd);
            c.moveToNext();
        }
        PieDataSet barDataSet = new PieDataSet(data,"data");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);
        barDataSet.setDrawIcons(false);
        barDataSet.setSliceSpace(3f);
        barDataSet.setIconsOffset(new MPPointF(0, 40));
        barDataSet.setSelectionShift(5f);
        PieData barData = new PieData (barDataSet);
        barChart.setData(barData);
        barChart.getDescription().setText("test piechart");
        barChart.animateY(2000);
        c.close();
        return sanp;
    }
    public void ketqua (String MaMH){
        dbHelper = new DBHelper(getContext());
        db = dbHelper.getWritableDatabase();
        ArrayList<ChiTietHoaDon> sanp= new ArrayList<>();
        String s= "SELECT Sum(SoLuong)as tongsoluong, Sum(DonGia*SoLuong) as tongdoanhthu FROM  ChiTietHD  where MaMH ='"+MaMH+"'";
        Cursor c = db.rawQuery(s,null);
        c.moveToFirst();
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        ketqua.setText(" Tổng số lượng bán: "+ c.getInt(0)+" - Doanh thu: "+decimalFormat.format(c.getFloat(1))+" VND");
        c.close();
    }
}