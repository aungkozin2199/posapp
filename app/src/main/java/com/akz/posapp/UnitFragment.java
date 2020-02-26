package com.akz.posapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.akz.posapp.adapter.ColorAdapter;
import com.akz.posapp.adapter.RcAdapter;
import com.akz.posapp.dao.UnitDAO;
import com.akz.posapp.dao.ColorDAO;
import com.akz.posapp.model.UnitModel;


public class UnitFragment extends Fragment {
    public static int selectedcolorId=1;
    public static TextView selectedcolor;
    RecyclerView rcView;
    Button btnsave,btncancel;
    EditText edtname;
    private View v;
    private RecyclerView colordata;
    UnitDAO unitDAO;

    public UnitFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         v= inflater.inflate(R.layout.fragment_unit, container, false);
        initData();
        loadData();
        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UnitModel model =new UnitModel();
                model.Name=edtname.getText().toString().trim();
                model.ColorId=selectedcolorId;
                unitDAO.saveModel(model);
                clearData();
            }
        });
        loadColor();
        loadData();

        return v;
    }

    public void loadColor(){
        colordata=(RecyclerView)v.findViewById(R.id.colordata);
        ColorDAO colorDAO=new ColorDAO(getContext());
        ColorAdapter adapter=new ColorAdapter(colorDAO.getModel(),getContext(),getResources().getString(R.string.unit_frag));
        colordata.setAdapter(adapter);
        GridLayoutManager gm=new GridLayoutManager(getContext(),3);
        colordata.setLayoutManager(gm);
    }
    public void initData(){
        edtname=v.findViewById(R.id.name);
        btnsave=v.findViewById(R.id.btnsave);
        rcView=v.findViewById(R.id.rcview);
        unitDAO=new UnitDAO(getContext());
        selectedcolor=v.findViewById(R.id.selectedcolor);
    }
    public void clearData(){
        edtname.setText("");
        selectedcolorId=1;
    }
    public void loadData(){
        RcAdapter adapter=new RcAdapter( unitDAO.getModels().toArray(),getContext(),getResources().getString(R.string.unit_frag));
        rcView.setAdapter(adapter);
        rcView.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));


    }


}
