package com.akz.posapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.akz.posapp.adapter.RcAdapter;
import com.akz.posapp.dao.BrandDAO;
import com.akz.posapp.dao.CategoryDAO;
import com.akz.posapp.dao.ItemDAO;
import com.akz.posapp.dao.UnitDAO;
import com.akz.posapp.model.BrandModel;
import com.akz.posapp.model.CategoryModel;
import com.akz.posapp.model.ItemModel;
import com.akz.posapp.model.UnitModel;

import java.util.ArrayList;


public class ItemFragment extends Fragment {


    public static int selectedcolorId=1;

    public ItemFragment() {
        // Required empty public constructor
    }

RadioButton rbtusecolor,rbtuseimage;
    Button btnsave,btncancel;
    EditText name,oprice,saleprice;
    Spinner spcategory,spbrand,spunit;
    CategoryDAO categoryDAO;
    UnitDAO unitDAO;
    BrandDAO brandDAO;
    ItemDAO itemDAO;
    RecyclerView rcItemlist;
    View view;
     ArrayList<UnitModel> unitModels;
     ArrayList <BrandModel> brandModels;
     ArrayList<CategoryModel>categoryModels;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =inflater.inflate(R.layout.fragment_item, container, false);
        initUI();
        loadSpinnerData();
        loadData();

        btnsave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ItemModel model=new ItemModel();
                model.Name=name.getText().toString().trim();
                model.OriginalPrice=Integer.parseInt(oprice.getText().toString());
                model.SalePrice=Integer.parseInt((saleprice.getText().toString()));
                model.UnitId=unitModels.get(spunit.getSelectedItemPosition()).Id;
                model.CategoryId=categoryModels.get(spcategory.getSelectedItemPosition()).Id;
                model.BrandId=brandModels.get(spbrand.getSelectedItemPosition()).Id;
                model.ColorId=selectedcolorId;
                if (ImageFragment.imgur!=null){
                    model.Picture=ImageFragment.imgur.toString();
                }
                itemDAO.saveModel(model);
                Toast.makeText(getContext(),"Save OK",Toast.LENGTH_LONG).show();;
                loadData();
                name.setText("");
                oprice.setText("");
                saleprice.setText("");
            }
        });
        rbtusecolor.setChecked(true);
        setFragment(new ColorFragment());
        rbtusecolor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new ColorFragment());
            }
        });
        rbtuseimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(new ImageFragment());
            }
        });
        return view;
    }
    public void setFragment(Fragment f){
        FragmentManager fm=getFragmentManager();
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.display,f);
        ft.commit();
    }
    public void initUI(){
        rbtusecolor=view.findViewById(R.id.rbtcolor);
        rbtuseimage=view.findViewById(R.id.rbtimage);
        btnsave=view.findViewById(R.id.btnsave);
        btncancel=view.findViewById(R.id.btncancel);
        name=view.findViewById(R.id.name);
        oprice=view.findViewById(R.id.oprice);
        saleprice=view.findViewById(R.id.saleprice);
        spcategory=view.findViewById(R.id.spcategory);
        spbrand=view.findViewById(R.id.spbrand);
        spunit=view.findViewById(R.id.spunit);
        rcItemlist=view.findViewById(R.id.itemlist);
        categoryDAO=new CategoryDAO(getContext());
        unitDAO=new UnitDAO(getContext());
        brandDAO=new BrandDAO(getContext());
        itemDAO=new ItemDAO(getContext());
    }
    public void loadSpinnerData(){
        unitModels=unitDAO.getModels();
        brandModels=brandDAO.getModels();
        categoryModels=categoryDAO.getModels();
        ArrayList<String>unitnames=new ArrayList<>();
        ArrayList<String>brandnames=new ArrayList<>();
        ArrayList<String>categorynames=new ArrayList<>();
        for (UnitModel model:unitModels){
            unitnames.add(model.Name);
        }
        for (BrandModel model:brandModels){
            brandnames.add(model.Name);
        }
        for (CategoryModel model:categoryModels){
            categorynames.add(model.Name);
        }
        ArrayAdapter<String>categoryadapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_dropdown_item_1line,categorynames);
        ArrayAdapter<String>unitadapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_dropdown_item_1line,unitnames);
        ArrayAdapter<String>brandadapter=new ArrayAdapter<String>(getContext(),android.R.layout.simple_dropdown_item_1line,brandnames);
        spunit.setAdapter(unitadapter);
        spbrand.setAdapter(brandadapter);
        spcategory.setAdapter(categoryadapter);
    }
    public void loadData(){
        ArrayList<ItemModel>itemModels=itemDAO.getModels();
        RcAdapter adapter=new RcAdapter(itemModels.toArray(),getContext(),getResources().getString(R.string.item_frag));
        rcItemlist.setAdapter(adapter);
        rcItemlist.setLayoutManager(new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false));

    }

}
